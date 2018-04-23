package org.yxyqcy.family.home.rtopic.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.rtopic.entity.RTopic;
import org.yxyqcy.family.home.rtopic.service.RTopicService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.validation.Valid;
import java.util.List;

/**
* Created by licy on 2017-9-29 19:56:42.
*/
@Controller
@RequestMapping("/rtopic")
public class RTopicController extends PaginationController<RTopic> {
    @Autowired
    private RTopicService rTopicServiceImpl;

    /**
    * 插入RTopic
    * @param rTopic
    * @return
    */
    @RequestMapping("/rtopicAdd")
    @ResponseBody
    @RequiresPermissions("home:rtopic:add")
    public AjaxResponse rtopicAdd(@Valid RTopic rTopic, BindingResult br, AjaxResponse ajaxResponse) {
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse, MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = rTopicServiceImpl.persistRtopic(rTopic);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,rTopic);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }

    /**
     * 修改 rtopic
     * @param rTopic
     * @param ar
     * @return
     */
    @RequestMapping("/rtopicUpdate")
    @ResponseBody
    @RequiresPermissions("home:rtopic:update")
    public AjaxResponse rtopicUpdate(RTopic rTopic, AjaxResponse ar) {
        PersistModel result = rTopicServiceImpl.mergeRtopic(rTopic);
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ar;
    }

    /**
    * 逻辑删除RTopic
    * @param id
    * @param ar
    * @return
    */
    @RequestMapping("/rtopicDelete/{id}")
    @ResponseBody
    @RequiresPermissions("home:rtopic:delete")
    public AjaxResponse rtopicDelete(@PathVariable("id") String id, AjaxResponse ar) {
        PersistModel result = rTopicServiceImpl.removeRTopic(id);
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ar;
    }

    /**
    * 根据主键唯一查找
    * @param businessId
    * @param ar
    * @return
    */
    @RequestMapping("/queryOneById/{id}")
    @ResponseBody
    @RequiresPermissions("home:rtopic:view")
    public AjaxResponse queryOneById(@PathVariable("id") String businessId,AjaxResponse ar) {
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,rTopicServiceImpl.selectById(businessId));
        return ar;
    }


    /**
    * 根据条件分页查询
    * @param param
    * @return
    */
    @RequestMapping("/queryRTopicsByPagination")
    @ResponseBody
    @RequiresPermissions("home:rtopic:view")
    public GridModel queryRTopics(RTopic param) {
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        //没有   管理权限,直接看自己的
        if(!sub.isPermitted("home:rtopic:manage"))
            param.setUser(UserUtils.getUser().getBusinessId());
        rTopicServiceImpl.queryRTopicsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * anno 根据条件分页查询
     * @param param
     * @return
     */
    @RequestMapping("/queryRTopicsAnno")
    @ResponseBody
    public GridModel queryRTopicsAnno(RTopic param) {
        param.setIsBlogCount("1");
        rTopicServiceImpl.queryRTopicsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * 根据 rtopic 查询 users
     * @param param
     * @return
     */
    @RequestMapping("/queryUsersByRTopics")
    @ResponseBody
    @RequiresPermissions("home:rtopic:view")
    public GridModel queryUsersByRTopics(RTopic param) {
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        rTopicServiceImpl.queryUsersByRTopicsByPagination(getPaginationUtility(), param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }



    /**
     * 根据条件分页查询
     * @param param
     * @return
     */
    @RequestMapping("/queryMyRTopicsByPagination")
    @ResponseBody
    @RequiresPermissions("home:rtopic:view")
    public GridModel queryMyRTopicsByPagination(RTopic param) {
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        param.setUser(UserUtils.getUser().getBusinessId());
        List<RTopic> list = rTopicServiceImpl.queryRTopicsByPagination(getPaginationUtility(), param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


}
