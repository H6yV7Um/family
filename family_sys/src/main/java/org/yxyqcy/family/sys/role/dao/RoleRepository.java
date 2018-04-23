package org.yxyqcy.family.sys.role.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.role.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午10:03
 * description:
 */
@Repository
public interface RoleRepository extends Mapper<Role>{
    /**
     * 分页查询 roles
     * @param role
     * @return
     */
    @Select("<script>select sys_role.*,sys_user.NAME as username from sys_role  LEFT JOIN sys_user ON sys_role.CREATE_BY=sys_user.BUSINESS_ID where "
    		+ "sys_role.DEL_FLAG='0' <if test=\"name !=null \"> and sys_role.NAME like concat('%',#{name},'%') </if> "
    		+ " <if test=\"flag !=null \">and sys_role.FLAG like concat('%',#{flag},'%') </if> "
    		//+ "<if test=\"phone !=null \">phone = #{phone} </if> <if test=\"usermail !=null \">usermail = #{usermail } </if>"
    		+ "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Role> queryRoles(Role role);
    
    
    @Select("<script>select * from sys_role  where "
    		+ "DEL_FLAG='0' "
    		+ "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Role> queryRolesSelect(Role role);

    /**
     * 根据角色id  删除对应权限
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteRoleMenus(@Param("roleId") String roleId);
    
    
    /**
     * 根据角色id  删除对应部门
     * @param roleId
     * @return
     */
    @Delete("delete from sys_dept_role where role_id = #{roleId}")
    int deleteRoleDepts(@Param("roleId") String roleId);

    /**
     * 批量插入         角色_权限
     * @param menuRole
     * @return
     */
    int insertBatchRoleMenu(List<Map> menuRole);
    
    
    /**
     * 批量插入         角色_部门
     * @param       deptRole
     * @return
     */
    int insertBatchRoleDept(List<Map> deptRole);
    /**
     * 分页查询 roles
     * @param role
     * @return
     */
    @Select(" select "
    		+ " dr.BUSINESS_ID drid,r.BUSINESS_ID rid,r.name rname,r.code rcode,d.BUSINESS_ID did,d.name dname,d.dept_code deptcode, "
    		+ " IF(( "
    		+ " SELECT count(*) from sys_dept_role_user dru where dru.USER_ID=#{userId} "
    		+ " and dru.DEPT_ROLE_ID=dr.BUSINESS_ID"
    		+ " )>0,'1','0') as checked "
    		+ " FROM  sys_dept_role dr "
    		+ " inner JOIN sys_dept d ON d.BUSINESS_ID = dr.DEPT_ID "
    		+ " inner JOIN sys_role r on dr.ROLE_ID = r.BUSINESS_ID")
    @ResultMap(value = "drResultMap" )
    List<DeptRole> queryUserRolesByPagination(@Param("userId") String userId, Role role) ;
    
    
    /**
     * 分页查询 roles
     * @param role
     * @return
     */
    @Select("SELECT " +
            " role.BUSINESS_ID, " +
            " role.NAME, " +
            " role.CODE, " +
            " role.FLAG, " +
            " role.DEL_FLAG, " +
            " role.CREATE_BY, " +
            " role.create_date, " +
            "IF ( " +
            " ( " +
            "  SELECT " +
            "   count(*) " +
            "  FROM " +
            "   SYS_DEPT_ROLE dr " +
            "  WHERE " +
            "   dr.ROLE_ID = role.BUSINESS_ID " +
            "  AND ur.USER_ID = #{deptId}  " +
            " ) > 0, " +
            "  '1', " +
            "  '0' " +
            " ) AS checked " +
            "FROM " +
            " sys_role role " +
            "WHERE " +
            " DEL_FLAG = '0' and FLAG = '1'")
    @ResultMap(value = "BaseResultMap" )
    List<Role> queryDeptRolesByPagination(@Param("deptId") String deptId, Role role);
     
    @Delete("DELETE from sys_dept_role_user where DEPT_ROLE_ID="
    		+ "("
    		+ "SELECT BUSINESS_ID from sys_dept_role  where ROLE_ID=#{roleId}"
    		+ ")") 
    int deleteDeptRoleUserByroleId(@Param("roleId") String roleId);

    /**
     * 删除岗位
      * @param roleId
     * @return
     */
    @Delete("DELETE from sys_dept_role where ROLE_ID=#{roleId}")
    int deleteDeptRoleByRoleId(@Param("roleId") String roleId);

    /**
     * 删除权限
     * @param roleId
     * @return
     */
    @Delete("DELETE from sys_role_menu where ROLE_ID=#{roleId}")
    int deleteRoleMenuByRoleId(@Param("roleId") String roleId);
}
