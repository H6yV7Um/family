package org.yxyqcy.family.sys.security.stateless;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.component.SpringContextHolder;
import org.yxyqcy.family.common.security.HmacSHA256Utils;
import org.yxyqcy.family.sys.menu.entity.Menu;
import org.yxyqcy.family.sys.security.Principal;
import org.yxyqcy.family.sys.service.SystemService;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.List;

/**
 * Created by lcy on 17/3/4.
 * stateless realm
 */
@Component
public class StatelessRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

    private SystemService systemService;


    public StatelessRealm() {
        super.setAuthenticationCachingEnabled(false);
    }


    /**
     * 获取系统业务对象
     */
    public SystemService getSystemService() {
        if (systemService == null){
            systemService = SpringContextHolder.getBean(SystemService.class);
        }
        return systemService;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // long useID = (long)principals.getPrimaryPrincipal();
        Principal principal = (Principal) getAvailablePrincipal(principals);
        //排除 非 system realm 授权
        if(principal == null || !"statelessToken".equals(principal.getToken()))
            return null;
        User user = getSystemService().getUserByLoginName(principal.getLoginName());
        if (user != null) {
            UserUtils.putCache("user", user);
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Menu> list = UserUtils.getMenuList();
            for (Menu menu : list){
                if (StringUtils.isNotBlank(menu.getPermission())){
                    // 添加基于Permission的权限信息
                    for (String permission : StringUtils.split(menu.getPermission(),",")){
                        info.addStringPermission(permission);
                    }
                }
            }
            // 更新登录IP和时间
            //getSystemService().updateUserLoginInfo(user.getId());
            return info;
        }
        return null;
    }
    /**
     * 认证回调函数, 登录时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        User user = getSystemService().getUserByLoginName(statelessToken.getPrincipal());

        //验证用户以及账套
        if(user == null ) {
           return null;
        }
        //验证摘要信息
        String serverDigetst = HmacSHA256Utils.digest(user.getDigestKey(),statelessToken.getOriginDigest());

        return new SimpleAuthenticationInfo(
                new Principal(user,"statelessToken"), //用户名
                serverDigetst,
                getName()  //realm name
        );
    }
}
