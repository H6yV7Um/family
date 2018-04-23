package org.yxyqcy.family.sys.dept.controller;



import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.ResponseController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.model.TreeViewModel;
import org.yxyqcy.family.common.validate.ValidatedResult;
import org.yxyqcy.family.sys.dept.service.DeptService;
import org.yxyqcy.family.sys.dept.entity.Dept;
import org.yxyqcy.family.sys.role.entity.Role;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.role.service.RoleService;
import org.yxyqcy.family.sys.user.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends ResponseController {

    @Autowired
    private DeptService deptServiceImpl;
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RoleService roleServiceImpl;


    /**
     * 构建  treeView
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:dept:view")
    @RequestMapping("queryDeptsForTree")
    public List<TreeViewModel> queryTreeView(){

        return deptServiceImpl.queryDeptTree();
    }
    
    /**
     * 查部门树同时根据角色反填部门角色信息(已经分配部门的为选中状态)
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:dept:view")
    @RequestMapping("queryDeptsTreeByRoleId/{id}")
    public List<TreeViewModel> queryDeptsTreeByRoleId(@PathVariable("id") String id){
        return deptServiceImpl.queryDeptsTreeByRoleId(id);
    }
    
    /**
     * 构建  select
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:role:view")
    @RequestMapping("queryRolesSelect")
    public List<Role> queryRolesSelect(Role role){
        return roleServiceImpl.queryRolesSelect(role);
    }
    
    


    /**
     * ajax jquery validator  增加部门code
     * @return  true 合法   false 不合法  不能有其它的输出
     */
    @RequiresPermissions("sys:dept:view")
    @RequestMapping("queryDeptCodeValidate")
    @ResponseBody
    public String queryDeptCodeValidate(@RequestParam("deptCode") String deptCode){

        return "true";
    }

    /**
     * 增加 部门
     * @param dept
     * @return
     */
    @RequiresPermissions("sys:dept:view")
    @RequestMapping("adminLog/deptAdd")
    @ResponseBody
    public AjaxResponse deptAdd(@Valid Dept dept, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = deptServiceImpl.persistDept(dept);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @RequiresPermissions("sys:dept:view")
    @RequestMapping("adminLog/deptUpdate")
    @ResponseBody
    public AjaxResponse deptUpdate(@Valid Dept dept, BindingResult br,AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = deptServiceImpl.mergeDept(dept);
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
    @RequiresPermissions("sys:user:view")
    @RequestMapping("adminLog/deptDelete/{id}")
    @ResponseBody
    public AjaxResponse deptDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = deptServiceImpl.removeDept(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequiresPermissions("sys:role:view")
    @RequestMapping("querySelect")
    public List<Dept> querySelect(){
        return deptServiceImpl.querySelect();
    }

}
