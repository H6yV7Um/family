package org.yxyqcy.family.sys.security;

import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.user.entity.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/3/4.
 * /**
 * 授权用户信息
 */


public class Principal  implements Serializable {


    private static final long serialVersionUID = 7311967861149181811L;
    private String id;
    private String loginName;
    private String name;
    private Map<String, Object> cacheMap;
    private List<DeptRole> deptRoles ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    /**
     * 获取职位信息
     * 职位进行拼接
     */
    public String getJobName() {
        if(deptRoles != null && deptRoles.size()>0){
            StringBuffer jobName= new StringBuffer();
            for (DeptRole deptRole : deptRoles) {
                jobName.append(deptRole.getDname()+deptRole.getRname() + " ");
            }
            return jobName.toString();
        }else{
            return "";
        }
    }

    public Principal(User user,String token) {
        this.id = user.getBusinessId();
        this.loginName = user.getLoginName();
        this.name = user.getName();
        this.deptRoles = user.getDeptRoles();
        this.token = token;
    }

    public List<DeptRole> getDeptRoles(){
        return deptRoles;
    }

    public String getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getCacheMap() {
        if (cacheMap==null){
            cacheMap = new HashMap<String, Object>();
        }
        return cacheMap;
    }

}

