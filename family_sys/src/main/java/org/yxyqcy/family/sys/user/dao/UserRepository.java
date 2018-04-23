package org.yxyqcy.family.sys.user.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.user.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:56
 * description:  user repository
 */
@Repository
public interface UserRepository extends Mapper<User> {
    /**
     * 根据用户名查找
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);
    
    /**
     * 根据用户名查询部门和职位
     * @MethodName:findDeptRoleByLoginName
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月14日 下午4:20:13
     * @version V1.0
     * @param loginName
     * @return
     */
    List<DeptRole> findDeptRoleByLoginName(String loginName);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from sys_user where BUSINESS_ID = #{id} and flag = '1' and del_flag = '0' ")
    @ResultMap(value = "BaseResultMap")
    User get(@Param("id") String id);

    /**
     * 分页查询用户
     * @param user  param
     * @return
     */
    @Select("<script>select * from sys_user  where "
    		+ "DEL_FLAG='0' <if test=\"loginName !=null \">and LOGIN_NAME like concat('%',#{loginName},'%') </if> "
//    		+ " <if test=\"flag !=null \">and FLAG =#{flag} </if>"
//    		+ " <if test=\"no !=null \">and NO = #{no} </if> "
//    		+ " <if test=\"email !=null \">and EMAIL =#{email}</if> "
    		//+ "<if test=\"phone !=null \">phone = #{phone} </if> <if test=\"usermail !=null \">usermail = #{usermail } </if>"
    		+ "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<User> queryUsers(User user);

    /**
     * 根据用户id  删除用户角色
     * @param userId
     * @return
     */
    @Delete("delete from sys_dept_role_user where user_id = #{userId}")
    int deleteUserRoles(@Param("userId") String userId);
    /**
     * 批量插入         用户_角色
     * @param userRole
     * @return
     */
    int insertBatchUserRole(List<Map> userRole);
    
    @Select("select * from sys_user where DEL_FLAG = '0'and OFFICE_ID=#{id}") 
    @ResultMap(value = "BaseResultMap" )
    List<User> queryUsersByDeptid(String id);
    
    
    /**
     * 根据用户id  删除用户角色
     * @param userId
     * @return
     */
    @Delete("delete from sys_dept_role_user where user_id = #{userId}")
    int deleteDeptRoleUserByUserId(@Param("userId") String userId);
    
    /**
     * 根据当前用户id查询所属部门职位code为roleCode的用户id
     * @MethodName:queryOwnDeptUserId
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月22日 下午4:55:52
     * @version V1.0
     * @param startPersonId
     * @param roleCode
     * @return 
     */
    @Select("SELECT s.business_id"
            +" FROM sys_user s"
            +" LEFT JOIN sys_dept_role_user dru ON s.business_id = dru.USER_ID"
            +" LEFT JOIN sys_dept_role dr ON dr.business_id = dru.DEPT_ROLE_ID"
            +" LEFT JOIN sys_role r ON r.ID = dr.ROLE_ID"
            +" WHERE r. CODE = '${roleCode}'"
            +" AND dr.DEPT_ID IN (CONCAT(\"\","
	        +" (SELECT group_concat(dd.ID)"
		    +" FROM sys_dept dd"
		    +" LEFT JOIN sys_dept_role ddr ON dd.ID = ddr.DEPT_ID"
		    +" LEFT JOIN sys_dept_role_user ddru ON ddru.DEPT_ROLE_ID = ddr.business_id"
		    +" WHERE ddru.USER_ID = '${startPersonId}'"
	        +" ),\"\""
		    +" ))") 
    List<String> queryOwnDeptUserId(@Param("startPersonId") String startPersonId, @Param("roleCode") String roleCode);
    
    @Select("SELECT s.*,"
            +" FROM sys_user s"
            +" WHERE s.business_ID = '${id}'")
    @ResultMap(value="BaseResultMap")
    User selectUserByPrimaryKey(@Param("id") String id);

    /**
     * 修改密码
     * @param param
     * @return
     */
    @Update("<script>update sys_user " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
                " <if test=\"password !='' and password !=null \" > password = #{password} ,</if>" +


                " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
                " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
                " <if test=\"createDate !=null \" > create_date = #{createDate} </if>" +
                " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
                " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
                " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateUserPass(User param);
}
