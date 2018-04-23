package org.yxyqcy.family.sys.role.service;



import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.role.entity.Role;

import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
public interface RoleService {

    /**
     * 保存
     * @param role
     * @return
     */
    PersistModel persistRole(Role role);

    /**
     * 修改
     * @param role
     * @return
     */
    PersistModel mergeRole(Role role);

    /**
     * 删除
     * @param id
     * @return
     */
    PersistModel removeRole(String id);
    
    /**
     * getByid
     */
    Role queryRoleById(String id);
    /**
     * 分页查询角色
     * @param paginationUtility
     * @param role
     * @return
     */
    public List<Role> queryRolesByPagination(PageUtil<Role> paginationUtility, Role role);
    
    //select 
    List<Role> queryRolesSelect(Role role);

    /**
     * 授权(赋予角色 权限)
     * @param roleId
     * @param role_menus
     * @return
     */
    PersistModel doAuthorization(String roleId, String role_menus);
    
    PersistModel doAuthorizationDept(String roleId, String role_menus);
    /**
     * 分页查询角色   根据 userId   进行checked
     * @param paginationUtility
     * @param userId
     * @param role
     * @return
     */
    public List<DeptRole> queryUserRolesByPagination(PageUtil<Role> paginationUtility, String userId, Role role);
    
    public List<Role> queryDeptRolesByPagination(PageUtil<Role> paginationUtility, String DeptId, Role role);
}
