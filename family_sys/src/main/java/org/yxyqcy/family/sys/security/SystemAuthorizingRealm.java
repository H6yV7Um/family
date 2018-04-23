package org.yxyqcy.family.sys.security;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.component.SpringContextHolder;
import org.yxyqcy.family.common.security.HmacSHA256Utils;
import org.yxyqcy.family.common.servlet.ValidateCodeServlet;
import org.yxyqcy.family.common.util.Encodes;
import org.yxyqcy.family.common.util.SystemUtil;
import org.yxyqcy.family.common.util.WebUtil;
import org.yxyqcy.family.sys.controller.LoginController;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.menu.entity.Menu;
import org.yxyqcy.family.sys.security.stateless.StatelessToken;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.service.SystemService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/1
 * Time: 下午2:01
 * description: 认证 realm
 */
@Component
public class  SystemAuthorizingRealm extends AuthorizingRealm {

    private SystemService systemService;
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Principal principal = (Principal) getAvailablePrincipal(principals);
        //排除 非 system realm 授权
        if(principal == null || !"systemToken".equals(principal.getToken()))
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
        } else {
            return null;
        }


    }
    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

        SystemUsernamePasswordToken token = (SystemUsernamePasswordToken) authToken;
        if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)) {
            // 判断验证码
            Session session = SecurityUtils.getSubject().getSession();
            String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
            if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)) {
                throw new CaptchaException("验证码错误.");
            }
        }

        User user = getSystemService().getUserByLoginName(token.getUsername());
        if (user != null) {
            List<DeptRole> deptRole = getSystemService().getDeptRoleByLoginName(token.getUsername());
            user.setDeptRoles(deptRole);
            byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(new Principal(user,"systemToken"),// systemToken 授权时判断
                    user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }

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

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemUtil.HASH_ALGORITHM);
        matcher.setHashIterations(SystemUtil.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
    /*
    * 支持无状态 token
    * */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && getAuthenticationTokenClass().isAssignableFrom(token.getClass());
        //return token != null &&
         //       ( token instanceof SystemUsernamePasswordToken || token instanceof StatelessToken );
    }

}
