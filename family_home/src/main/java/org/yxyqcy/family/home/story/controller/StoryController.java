package org.yxyqcy.family.home.story.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.FileController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.util.DateUtil;
import org.yxyqcy.family.home.story.entity.Story;
import org.yxyqcy.family.home.story.entity.StoryFragment;
import org.yxyqcy.family.home.story.service.StoryService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by lcy on 17/3/16.
 */
@Controller
@RequestMapping(value={"/story","/api/story"})
public class StoryController extends FileController<Story>{


    @Resource
    private StoryService storyServiceImpl;

    /**
     * 分页查询 story
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:story:view")
    @RequestMapping("queryStories")
    public GridModel queryStories(Story param){
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        storyServiceImpl.queryStoriesByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * 分页查询 story fre
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:story:view")
    @RequestMapping("queryStoryFragments")
    public GridModel queryStoryFragments(StoryFragment param){
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        storyServiceImpl.queryStoryFragmentsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * get RomanticPage data
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("getRomanticData/{id}")
    public List<StoryFragment> getRomanticData(@PathVariable("id") String id){
        return storyServiceImpl.queryStoryFragments(id);
    }

    /**
     * story image 上传
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("home:story:view")
    @RequestMapping("uploadStoryImage")
    public String uploadImage(@RequestParam(value = "Filedata", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String tempPath =
                Global.getConfig("temp_upload_location") + File.separator + DateUtil.getSimepleDate("yyyy-MM-dd",new Date());
        super.transformCommonFile(file,tempPath,"UUID");
        return null;
    }


    //storyFragment model
    /**
     * 添加 故事片段
     * @param param
     * @return
     */
    @RequiresPermissions("home:story:add")
    @RequestMapping("storyFragmentAdd")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse storyFragmentAdd(@Valid StoryFragment param, BindingResult br
            , AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = storyServiceImpl.persistStoryFragment(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }


    /**
     * to 修改fragment页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("home:story:update")
    @RequestMapping("toHg/toStoryFragmentUpdatePage/{id}")
    public ModelAndView toStoryFragmentUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("fragmentUpdate",storyServiceImpl.queryFragmentById(id));
        modelAndView.setViewName("homeBack/storyFragmentAdd");
        return modelAndView;
    }


    /**
     * 修改 片段
     * @param account
     * @param
     * @return
     */
    @RequiresPermissions("home:story:update")
    @RequestMapping("storyFragmentUpdate")
    @ResponseBody
    public AjaxResponse storyFragmentUpdate(@Valid StoryFragment account, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = storyServiceImpl.mergeFragment(account);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,account);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }
    /**
     * delete story fragment
     * @param id
     * @return
     */
    @RequiresPermissions("home:story:delete")
    @RequestMapping("storyFragmentDelete/{id}")
    @ResponseBody
    public AjaxResponse storyFragmentDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = storyServiceImpl.removeFragment(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }


    /**
     * story model
     */
    /**
     * 添加 故事
     * @param param
     * @param model
     * @return
     */
    @RequiresPermissions("home:story:add")
    @RequestMapping("storyAdd")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse storyAdd(@Valid Story param, BindingResult br, RedirectAttributesModelMap model
            , AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = storyServiceImpl.persistStory(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }


    /**
     * to 修改story页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("home:story:update")
    @RequestMapping("toHg/toStoryUpdatePage/{id}")
    public ModelAndView toStoryUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("storyUpdate",storyServiceImpl.queryStoryById(id));
        modelAndView.setViewName("homeBack/storyAdd");
        return modelAndView;
    }
    /**
     * 查看single story
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:story:view")
    @RequestMapping("querySingleStory/{id}")
    public AjaxResponse querySingleStory(@PathVariable("id") String id,AjaxResponse ajaxResponse){
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,storyServiceImpl.queryStoryById(id));
        return ajaxResponse;
    }

    /**
     * 修改 故事
     * @param story
     * @param
     * @return
     */
    @RequiresPermissions("home:story:update")
    @RequestMapping("storyUpdate")
    @ResponseBody
    public AjaxResponse storyUpdate(@Valid Story story, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = storyServiceImpl.mergeStory(story);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,story);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }
    /**
     * delete story
     * @param id
     * @return
     */
    @RequiresPermissions("home:story:delete")
    @RequestMapping("storyDelete/{id}")
    @ResponseBody
    public AjaxResponse storyDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = storyServiceImpl.removeStory(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
}
