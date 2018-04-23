package org.yxyqcy.family.sys.role.controller;



import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.validate.ValidatedResult;
import org.yxyqcy.family.sys.role.entity.Role;
import org.yxyqcy.family.sys.role.service.RoleService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends PaginationController<Role> {

    @Autowired
    private RoleService roleServiceImpl;

     /**
     * ajax jquery validator  增加部门code
     * @return  true 合法   false 不合法  不能有其它的输出
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping("queryRoleValidate")
    @ResponseBody
    public String queryRoleValidate(Role role){
    	List<Role> roleList= roleServiceImpl.queryRolesByPagination(getPaginationUtility(),role);
    	if(roleList.size()==0)
    		return "true";
    	else 
    		return "false";
    }

    /**
     * 分页查询角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:role:view")
    @RequestMapping("queryRoles")
    public GridModel queryRoles(Role role){
        roleServiceImpl.queryRolesByPagination(getPaginationUtility(),role);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }
    /**
     * 分页查询角色   根据userId 进行选中checked
     * @param role
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:role:view")
    @RequestMapping("queryRolesByUser/{userId}")
    public GridModel queryRolesByUser(@PathVariable("userId") String userId, Role role){
        roleServiceImpl.queryUserRolesByPagination(getPaginationUtility(),userId,role);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    
    /**
     * 增加
     * @param
     * @return
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping("adminLog/roleAdd")
    @ResponseBody
    public AjaxResponse roleAdd(@Valid Role role, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = roleServiceImpl.persistRole(role);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * to 修改用户页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping("toBg/toRoleUpdatePage/{id}")
    public ModelAndView toUserUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("roleUpdate",roleServiceImpl.queryRoleById(id));
        modelAndView.setViewName("background/roleAdd");
        return modelAndView;
    }
    
    /**
     * 修改
     * @param 
     * @return
     */
    @RequiresPermissions("sys:role:update")
    @RequestMapping("adminLog/roleUpdate")
    @ResponseBody
    public AjaxResponse roleUpdate(@Valid Role role, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = roleServiceImpl.mergeRole(role);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * delete 部门
     * @param id
     * @return
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping("adminLog/roleDelete/{id}")
    @ResponseBody
    public AjaxResponse roleDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){

        PersistModel persistModel = roleServiceImpl.removeRole(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 权限分配
     * @param roleId        角色id
     * @param role_menus    角色绑定权限
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("sys:role:permission")
    @RequestMapping("adminLog/rolePermission")
    @ResponseBody
    public AjaxResponse rolePermission(@RequestParam("roleId") String roleId, @RequestParam("role_menus")
            String role_menus , AjaxResponse ajaxResponse){

        PersistModel persistModel = roleServiceImpl.doAuthorization(roleId,role_menus);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
    
    /**
     * 岗位绑定
     * @param roleId        角色id
     * @param role_depts    角色部门绑定
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("sys:role:permission")
    @RequestMapping("adminLog/deptPermission")
    @ResponseBody
    public AjaxResponse deptPermission(@RequestParam("roleId") String roleId, @RequestParam("role_depts")
            String role_depts , AjaxResponse ajaxResponse){

        PersistModel persistModel = roleServiceImpl.doAuthorizationDept(roleId,role_depts);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
}
