package org.yxyqcy.family.sys.util;

import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.yxyqcy.family.common.component.SpringContextHolder;
import org.yxyqcy.family.common.constant.DelStatus;
import org.yxyqcy.family.common.constant.FlagStatus;
import org.yxyqcy.family.common.entity.DataEntity;
import org.yxyqcy.family.sys.menu.dao.MenuRepository;
import org.yxyqcy.family.sys.menu.entity.Menu;
import org.yxyqcy.family.sys.role.dao.RoleRepository;
import org.yxyqcy.family.sys.security.Principal;
import org.yxyqcy.family.sys.user.dao.UserRepository;
import org.yxyqcy.family.sys.user.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:19
 * description:  用户util
 */
public class UserUtils {

    private static UserRepository userDao = SpringContextHolder.getBean(UserRepository.class);
    private static RoleRepository roleDao = SpringContextHolder.getBean(RoleRepository.class);
    private static MenuRepository menuDao = SpringContextHolder.getBean(MenuRepository.class);


    public static final String CACHE_USER = "user";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";

    public static User getUser(){
        User user = (User)getCache(CACHE_USER);
        if (user == null){
            try{
                Subject subject = SecurityUtils.getSubject();
                if(subject.isRemembered()){
                    return new User();
                }
                Principal principal = (Principal)subject.getPrincipal();
                if (principal!=null){
                    user = userDao.get(principal.getId());
//					Hibernate.initialize(user.getRoleList());
                    putCache(CACHE_USER, user);
                }
            }catch (UnavailableSecurityManagerException e) {

            }catch (InvalidSessionException e){

            }
        }
        if (user == null){
            user = new User();
            try{
                SecurityUtils.getSubject().logout();
            }catch (UnavailableSecurityManagerException e) {

            }catch (InvalidSessionException e){

            }
        }
        return user;
    }

    /**
     * 权限不足 -> 判断是否登陆   记住我 -> 退出
     * @return
     */
    public static User getUserByState(){
        User user = new User();
        try{
            Subject subject = SecurityUtils.getSubject();
            if(subject.isRemembered()){
                SecurityUtils.getSubject().logout();
                return user;
            }
            Principal principal = (Principal)subject.getPrincipal();
            if (principal!=null){
                user = userDao.get(principal.getId());
//					Hibernate.initialize(user.getRoleList());
                putCache(CACHE_USER, user);
            }
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }


        return user;
    }
    public static User getUser(boolean isRefresh){
        if (isRefresh){
            removeCache(CACHE_USER);
        }
        return getUser();
    }
    // ============== User Cache ==============

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getCacheMap().get(key);
        return obj==null?defaultValue:obj;
    }

    public static void putCache(String key, Object value) {
        getCacheMap().put(key, value);
    }

    public static void removeCache(String key) {
        getCacheMap().remove(key);
    }

    public static Map<String, Object> getCacheMap(){
        Map<String, Object> map = Maps.newHashMap();
        try{
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal)subject.getPrincipal();
            return principal!=null?principal.getCacheMap():map;
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return map;
    }


    public static List<Menu> getMenuList(){
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
        if (menuList == null){
            User user = getUser();
            menuList = menuDao.findByUserId(user.getBusinessId());
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 持久化 当前操作人
     */
    public static void setCurrentPersistOperation(DataEntity entity){
        User user =  getUser();
        entity.setCreateBy(user.getBusinessId());
        entity.setCreateDate(new Date());
        entity.setUpdateBy(user.getBusinessId());
        entity.setUpdateDate(new Date());
        entity.setFlag(FlagStatus.NORMAL.getStatus());
        entity.setDelFlag(DelStatus.NORMAL.getStatus());
    }

    /**
     * 持久化 当前修改人
     */
    public static void setCurrentMergeOperation(DataEntity entity){
        User user =  getUser();
        entity.setUpdateBy(user.getBusinessId());
        entity.setUpdateDate(new Date());
    }

    /**
     * 持久化 逻辑删除
     */
    public static void setCurrentRemoveOperation(DataEntity entity){
        entity.setDeleted();
        setCurrentMergeOperation(entity);
    }

}
