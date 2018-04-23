package org.yxyqcy.family.sys.menu.controller;


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
import org.yxyqcy.family.common.model.CommonMenuModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.model.TreeViewModel;
import org.yxyqcy.family.common.validate.ValidatedResult;
import org.yxyqcy.family.sys.menu.entity.Menu;
import org.yxyqcy.family.sys.menu.service.MenuService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends ResponseController {

    @Autowired
    private MenuService menuServiceImpl;


    /**
     * 构建  treeView
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("queryMenusForTree")
    public List<TreeViewModel> queryTreeView(){

        return menuServiceImpl.queryMenuTree();
    }

    /**
     * 根据用户权限获取菜单(已获取权限的为选中状态)
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("queryMenusOfRolePermission/{id}")
    public List<TreeViewModel> queryMenusOfRolePermission(@PathVariable("id") String id){
        return menuServiceImpl.queryMenuTreeForSelectedByRP(id);
    }

    /**
     * 根据id 查询 menu
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("queryMenuById/{id}")
    public AjaxResponse queryMenuById(@PathVariable("id") String id, AjaxResponse ajaxResponse){

        Menu menu = menuServiceImpl.queryMenuById(id);
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,menu);
        return ajaxResponse;
    }


    /**
     * ajax jquery validator  增加菜单code
     * @return  true 合法   false 不合法  不能有其它的输出
     */
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("queryMenuCodeValidate")
    @ResponseBody
    public String queryDeptCodeValidate(@RequestParam("menuCode") String menuCode){

        return "true";
    }

    /**
     * 增加 菜单
     * @param menu
     * @return
     */
    @RequiresPermissions("sys:menu:add")
    @RequestMapping("adminLog/menuAdd")
    @ResponseBody
    public AjaxResponse menuAdd(@Valid Menu menu, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = menuServiceImpl.persistMenu(menu);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @RequiresPermissions("sys:menu:update")
    @RequestMapping("adminLog/menuUpdate")
    @ResponseBody
    public AjaxResponse menuUpdate(@Valid Menu menu, BindingResult br,AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            ValidatedResult vr = new ValidatedResult();
            vr.dealBindingResult(br);
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_INFO_INVALID,vr);
            return ajaxResponse;
        }
        PersistModel persistModel = menuServiceImpl.mergeMenu(menu);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * delete 菜单
     * @param id
     * @return
     */
    @RequiresPermissions("sys:menu:delete")
    @RequestMapping("adminLog/menuDelete/{id}")
    @ResponseBody
    public AjaxResponse menuDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){

        PersistModel persistModel = menuServiceImpl.removeMenu(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 列出菜单
     * @param model  模块Id
     * @param selectId 选中ID
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequestMapping("menus/{model}")
    public AjaxResponse menusByModels(@PathVariable("model") String model,
        String selectId,AjaxResponse ajaxResponse){
        CommonMenuModel menuModel = menuServiceImpl.queryMenusListByModels(model,selectId);
        ajaxResponse.setSuccessMessage("查询成功",menuModel);
        return ajaxResponse;
    }
}
