package org.activiti.controller;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.entity.WorkflowModel;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;

import java.util.HashMap;

/**
 * Created by lcy on 17/6/11.
 * 核心工作流 controller
 */
@Controller
@RequestMapping(value = "/workflow/central")
public class WorkflowCentralController extends PaginationController<WorkflowModel> {


    @Autowired
    WorkflowService workflowServiceImpl;



    /**
     * query 流程部署模型
     * @param workflow
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWorkflowDeployment")
    public GridModel queryWorkflowDeployment(QueryWorkflowModel workflow){
        workflowServiceImpl.queryWorkflowDeploymentByPagination(getPaginationUtility(),workflow);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * 查询工作流实例
     * @param workflow
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWorkflowInstance")
    public GridModel queryWorkflowInstance(QueryWorkflowModel workflow){
        workflowServiceImpl.queryWorkflowInstanceByPagination(getPaginationUtility(),workflow);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * 查询历史流程信息
     * @param workflowModel
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWorkflowHisInstance")
    public GridModel queryWorkflowHisInstance(QueryWorkflowModel workflowModel){
        workflowServiceImpl.queryWorkflowHisInstanceByPagination(getPaginationUtility(),workflowModel);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }




    /**
     * test 流程  process
     * @param workflowModel
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequestMapping("startProcess")
    public AjaxResponse testStartProcess(WorkflowModel workflowModel,AjaxResponse ajaxResponse){
        ProcessInstance ps = workflowServiceImpl.startProcessByKey("process", "323232", new HashMap<String, Object>(), true);
        if(null != ps)
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,ps.getProcessInstanceId());
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

}
