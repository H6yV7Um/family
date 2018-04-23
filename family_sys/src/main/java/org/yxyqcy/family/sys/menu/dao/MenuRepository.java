package org.yxyqcy.family.sys.menu.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.menu.entity.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午10:03
 * description: menu repository
 */
@Repository
public interface MenuRepository extends Mapper<Menu>{


    /**
     * 根据用户id查询 menu
     * @param id
     * @return
     */
    @Select("SELECT " +
            " DISTINCT(m.BUSINESS_ID),m.*,'' as checked " +
            " FROM " +
            " sys_menu m " +
            " LEFT JOIN SYS_ROLE_MENU rm ON m.BUSINESS_ID = rm.MENU_ID "+
            " LEFT JOIN sys_dept_role dr ON rm.ROLE_ID = dr.ROLE_ID "+ 
            " LEFT JOIN sys_dept_role_user dru ON dr.BUSINESS_ID = dru.DEPT_ROLE_ID  " +
            " where m.DEL_FLAG = '0' and m.FLAG = '1' and  dru.USER_ID = #{id} order by CASE_COUNT asc,SORT asc")
    @ResultMap(value = "BaseResultMap")
    List<Menu> findByUserId(@Param("id") String id);
    //sys_dept_role_user,,sys_dept_role,,sys_role_menu


    /**
     * 查询根节点    dept tree
     * @return      list
     */
    @Select("SELECT "
            + " BUSINESS_ID,PARENT_ID,PARENT_IDS,HREF,TARGET,ICON,SORT,IS_SHOW,"
            + " CODE,NAME,IS_ACTIVITI,PERMISSION,REMARKS,'' as checked"
            + " FROM sys_menu "
            + " WHERE "
            + " PARENT_ID IS NULL "
            + " AND DEL_FLAG = '0' order by sort asc")
    @ResultMap(value = "BaseResultMap")
    List<Menu> queryRootMenuTree();


    /**
     * 根据pid  查询子节点
     * @param pid  父节点id
     * @return
     */
    @Select("SELECT "
            + " BUSINESS_ID,PARENT_ID,PARENT_IDS,HREF,TARGET,ICON,SORT,IS_SHOW,"
            +"  CODE,NAME,IS_ACTIVITI,PERMISSION,REMARKS,'' as checked"
            + " FROM sys_menu "
            + " WHERE "
            + " DEL_FLAG = '0' AND PARENT_ID = #{pid} order by sort asc")
    @ResultMap(value = "BaseResultMap")
    List<Menu> queryMenuTreeByParentId(@Param("pid") String pid);

    /**
     * 逻辑删除菜单集合
     * @param list
     */
    int logicRemoveMenus(List<Menu> list);

    /**
     * 更新某些列   根据主键
     * 使用注解 update   值 不能加 ' '
     * @param menu
     * @return
     */
    @Update("update sys_menu set " +
            " HREF = #{menu.href}," +
            " TARGET = #{menu.target}," +
            " ICON = #{menu.icon}," +
            " IS_SHOW = #{menu.isShow}," +
            " REMARKS = #{menu.remarks}," +
            " SORT = #{menu.sort}," +
            " CODE = #{menu.code}," +
            " IS_ACTIVITI = #{menu.isActiviti}," +
            " PERMISSION = #{menu.permission}," +
            " NAME = #{menu.name} ," +
            " MODEL_ID = #{menu.modelId} ," +
            " CASE_COUNT = #{menu.caseCount} " +
            " where BUSINESS_ID = #{menu.businessId}")
    int updateAppointedColumnByPrimaryKey(@Param("menu") Menu menu);

    /**
     * 带checked的根节点
     * @param roleId
     * @return
     */
    List<Menu> queryRootMenuTreeForSelectedByRP(@Param("roleId") String roleId);

    /**
     * left join 查询菜单 带checked 根据 parentId  roleId
     * @param roleId
     * @param id
     * @return
     */
    @Select("SELECT "
            + " menu.BUSINESS_ID,menu.PARENT_ID,menu.PARENT_IDS,menu.HREF,menu.TARGET,menu.ICON,menu.SORT,menu.IS_SHOW,"
            +"  menu.CODE,menu.NAME,menu.IS_ACTIVITI,menu.PERMISSION,menu.REMARKS,"
            +"IF ( " +
            " ( " +
            "  SELECT " +
            "   count(*) " +
            "  FROM " +
            "   SYS_ROLE_MENU rm where rm.MENU_ID = menu.BUSINESS_ID " +
            "   and rm.ROLE_ID = #{roleId} " +
            " ) > 0, " +
            " '1', " +
            " '0' " +
            ") AS checked"
            + " FROM sys_menu menu"
            + " WHERE "
            + " menu.DEL_FLAG = '0' AND menu.PARENT_ID = #{pid} order by menu.sort asc")
    @ResultMap(value = "BaseResultMap")
    List<Menu> queryMenuTreeByParentIdRP(@Param("roleId") String roleId, @Param("pid") String id);

    /**
     * 删除   角色菜单
     * @param menuId
     * @return
     */
    @Delete("DELETE from sys_role_menu where MENU_ID=#{menuId}")
    int deleteRoleMenuByMenuId(@Param("menuId") String menuId);
}
