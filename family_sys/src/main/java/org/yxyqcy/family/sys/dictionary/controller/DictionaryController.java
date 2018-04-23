package org.yxyqcy.family.sys.dictionary.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.sys.dictionary.entity.Dictionary;
import org.yxyqcy.family.sys.dictionary.service.DictionaryService;

import javax.servlet.http.HttpSession;

/**
 * @author yangxuenan
 * 数据字典
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends PaginationController<Dictionary> {


	 @Autowired
	 public DictionaryService dictionaryServiceImpl;

	 	/**
	     * 分页查询
	     * @param dictionary
	     * @return
	     */
	    @ResponseBody
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("queryDictionary")
	    public GridModel queryDictionary(Dictionary dictionary){
			dictionaryServiceImpl.queryDictionaryByPagination(getPaginationUtility(), dictionary);
	        GridModel gridModel = getGridModelResponse();
	        return  gridModel;
	    }

	    /**
	     * 添加
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("adminLog/dictionaryAdd")
	    @ResponseBody
	    public AjaxResponse dictionaryAdd(HttpSession session, Dictionary dictionary, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
	    	PersistModel persistModel = dictionaryServiceImpl.persistDictionary(dictionary);
	        if(persistModel.isSuccessBySinglePersist()) {
	        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,dictionary.getBusinessId());
	        }
	        else {
	        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
	        }
	        return ar;
	    }

	    /**
	     * 删除 delFlag=1
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("adminLog/dictionaryDel")
	    @ResponseBody
	    public AjaxResponse dictionaryDel(HttpSession session, Dictionary param, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
			PersistModel persistModel = new PersistModel(dictionaryServiceImpl.updateDictionary(param));
	        if(persistModel.isSuccessBySinglePersist())
	        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param.getName());
	        else
	        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
	        return ar;
	    }

	    /**
	     * to 修改页面
	     * @param id
	     * @param modelAndView
	     * @return
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("toBg/toDictionaryUpdatePage/{id}")
	    public ModelAndView toDictionaryUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
	        modelAndView.getModelMap().addAttribute("dictionaryUpdate",dictionaryServiceImpl.queryDictionaryById(id));
	        modelAndView.setViewName("background/dictionaryAdd");
	        return modelAndView;
	    }

	    /**
	     * to 修改
	     * @param param
	     * @return
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("adminLog/dictionaryUpdate")
	    @ResponseBody
	    public AjaxResponse dictionaryUpdate(HttpSession session, Dictionary param, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
	    	PersistModel persistModel = new PersistModel(dictionaryServiceImpl.updateDictionary(param));
	        if(persistModel.isSuccessBySinglePersist())
	        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param.getBusinessId());
	        else
	        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
	        return ar;
	    }

	    /**
	     * 查出type下拉框
	     * @param ar
	     * @return
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("adminLog/selectAllDictionaryType")
	    @ResponseBody
	    public AjaxResponse selectAllDictionaryType(AjaxResponse ar){
	        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,dictionaryServiceImpl.selectAllType());
	        return ar;
	    }


	    /**
	     * 根据type查出数据
	     * @param ar
	     * @return
	     */
	    @RequiresPermissions("sys:user:view")
	    @RequestMapping("adminLog/selectDictionaryByType")
	    @ResponseBody
	    public AjaxResponse selectDictionaryByType(AjaxResponse ar,String type){
	        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,dictionaryServiceImpl.selectByType(type));
	        return ar;
	    }

}
