package org.yxyqcy.family.home.account.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.FileController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.account.entity.Account;
import org.yxyqcy.family.home.account.entity.AccountServerSetting;
import org.yxyqcy.family.home.account.service.AccountServerSettingService;
import org.yxyqcy.family.home.account.service.AccountService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/19
 * Time: 下午9:15
 * description: 帐号 controller
 */
@Controller
@RequestMapping(value={"/account","/api/account"})
public class AccountController extends FileController<Account> {

    @Resource
    private AccountService accountServiceImpl;
    @Resource
    private AccountServerSettingService accountServerSettingServiceImpl;

    /**
     * 分页查询帐号
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:account:view")
    @RequestMapping("queryAccounts")
    public GridModel queryAccounts(Account param){
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        accountServiceImpl.queryAccountsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * 添加帐号
     * @param param
     * @param model
     * @return
     */
    @RequiresPermissions("home:account:view")
    @RequestMapping("accountAdd")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse accountAdd(@Valid Account param, BindingResult br, RedirectAttributesModelMap model
                , AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = accountServiceImpl.persistAccount(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }


    /**
     * to 修改帐户页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("home:account:update")
    @RequestMapping("toHg/toAccountUpdatePage/{id}")
    public ModelAndView toAccountUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("accountUpdate",accountServiceImpl.queryAccountById(id));
        modelAndView.setViewName("homeBack/accountAdd");
        return modelAndView;
    }

    /**
     * 查看 account
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:account:view")
    @RequestMapping("querySingleAccount/{id}")
    public AjaxResponse querySingleAccount(@PathVariable("id") String id,AjaxResponse ajaxResponse){
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,accountServiceImpl.queryAccountById(id));
        return ajaxResponse;
    }

    /**
     * 修改帐号
     * @param account
     * @param
     * @return
     */
    @RequiresPermissions("home:account:update")
    @RequestMapping("accountUpdate")
    @ResponseBody
    public AjaxResponse accountUpdate(@Valid Account account, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = accountServiceImpl.mergeAccount(account);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,account);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }
    /**
     * delete 帐号
     * @param id
     * @return
     */
    @RequiresPermissions("home:account:delete")
    @RequestMapping("accountDelete/{id}")
    @ResponseBody
    public AjaxResponse accountDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = accountServiceImpl.removeAccount(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 统计
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:account:view")
    @RequestMapping("statisticsCount")
    @ResponseBody
    public AjaxResponse statisticsCount(@RequestParam("type")String type, AjaxResponse ajaxResponse){
        List<Map> result = accountServiceImpl.statisticsCount(type,UserUtils.getUser().getBusinessId());
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ajaxResponse;
    }


    /**
     * 统计
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:account:view")
    @RequestMapping("queryAccountServerSettings")
    @ResponseBody
    public AjaxResponse queryAccountServerSettings(AccountServerSetting accountServerSetting, AjaxResponse ajaxResponse){
        List<AccountServerSetting> result = accountServerSettingServiceImpl.queryServerSettingsByServerId(accountServerSetting);
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ajaxResponse;
    }


    /**
     * 添加setting
     * @param param
     * @param model
     * @return
     */
    @RequiresPermissions("home:account:view")
    @RequestMapping("accountServerSettingAdd")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse accountServerSettingAdd(@Valid AccountServerSetting param, BindingResult br, RedirectAttributesModelMap model
            , AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = accountServerSettingServiceImpl.persistAccountServerSetting(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }
    /**
     * 修改setting
     * @param param
     * @param model
     * @return
     */
    @RequiresPermissions("home:account:view")
    @RequestMapping("accountServerSettingUpdate")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse accountServerSettingUpdate(@Valid AccountServerSetting param, BindingResult br, RedirectAttributesModelMap model
            , AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = accountServerSettingServiceImpl.mergeAccountServerSetting(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }
    /**
     * delete 服务器setting
     * @param id
     * @return
     */
    @RequiresPermissions("home:account:delete")
    @RequestMapping("accountServerSettingDelete/{id}")
    @ResponseBody
    public AjaxResponse accountServerSettingDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = accountServerSettingServiceImpl.removeAccountServerSetting(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
}
