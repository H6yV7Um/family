package org.yxyqcy.family.sys.dept.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.dept.entity.Dept;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Repository
public interface DeptRepository extends Mapper<Dept>{

    /**
     * 查询根节点    dept tree
     * @return      list
     */
    @Select("SELECT "
            + "business_id,PARENT_ID,DEPT_CODE,SEQ_DATE,NAME "
            + "FROM sys_dept "
            + "WHERE "
            + "PARENT_ID IS NULL "
            + "AND DEL_FLAG = '0' ")
    @ResultMap(value = "BaseResultMap")
    List<Dept> queryRootDeptTree();


    /**
     * 根据pid  查询子节点
     * @param pid  父节点id
     * @return
     */
    @Select("SELECT "
            + "business_id,PARENT_ID,DEPT_CODE,SEQ_DATE,NAME "
            + "FROM sys_dept "
            + "WHERE "
            + "DEL_FLAG = '0' AND PARENT_ID = #{pid}")
    @ResultMap(value = "BaseResultMap")
    List<Dept> queryDeptTreeByParentId(@Param("pid") String pid);
    
    
    /**
     * 根据pid  查询子节点
     * @param pid  父节点id roleId 用于和部门绑定的角色
     * @return
     */
    @Select("SELECT d.*, "
    		+ " IF ( "
    		+ " (  "
    		+ " SELECT  count(*)  "
    		+ "FROM sys_dept_role rd "
    		+ "where rd.DEPT_ID = d.business_id  and rd.ROLE_ID = #{roleId}"
    		+ " ) > 0,  '1',  '0'  )"
    		+ " AS checked  FROM sys_dept d WHERE  d.DEL_FLAG = '0' AND d.PARENT_ID =#{pid}")
    @ResultMap(value = "BaseResultMap")
    List<Dept> queryDeptTreeByParentIdRoleId(@Param("roleId") String roleId, @Param("pid") String pid);


    /**
     * 根据部门code查询部门
     * @param deptCode
     * @return
     */
    @Select("SELECT "
            + "* "
            + "FROM sys_dept "
            + "WHERE "
            + "DEL_FLAG = '0' AND DEPT_CODE = #{deptCode}")
    @ResultMap(value = "BaseResultMap")
    List<Dept> queryDept(@Param("deptCode") String deptCode);


    /**
     * 根据 部门id 删除 用户岗位
     * @param deptId
     * @return
     */
    @Delete("DELETE from sys_dept_role_user where DEPT_ROLE_ID="
    		+ "("
    		+ "SELECT business_id from sys_dept_role  where DEPT_ID=#{deptId}"
    		+ ")") 
    int deleteDeptRoleUserByDeptId(@Param("deptId") String deptId);


    /**
     * 根据部门id 删除岗位
     * @param deptId
     * @return
     */
    @Delete("DELETE from sys_dept_role where DEPT_ID=#{deptId}")
    int deleteDeptRoleByDeptId(@Param("deptId") String deptId);
}
