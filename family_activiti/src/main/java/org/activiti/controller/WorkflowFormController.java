package org.activiti.controller;

import org.activiti.entity.WorkflowForm;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.service.WorkflowFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.model.GridModel;

/**
 * Created by lcy on 17/6/14.
 */
@Controller
@RequestMapping(value = "/workflow/form")
public class WorkflowFormController extends PaginationController<WorkflowForm> {

    @Autowired
    private WorkflowFormService workflowFormServiceImpl;

    /**
     * query 流程部署模型
     * @param workflow
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWorkflowForm")
    public GridModel queryWorkflowForm(QueryWorkflowModel workflow){
        workflowFormServiceImpl.queryWorkflowFormByPagination(getPaginationUtility(),workflow);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

}
