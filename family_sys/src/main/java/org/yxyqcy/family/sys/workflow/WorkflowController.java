package org.yxyqcy.family.sys.workflow;

import org.activiti.entity.WorkflowModel;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.sys.util.UserUtils;

/**
 * Created by lcy on 17/6/17.
 */
public class WorkflowController extends PaginationController<WorkflowModel> {

    @Autowired
    WorkflowService workflowServiceImpl;




    /**
     * 查询待办任务 列表
     * @param workflowModel
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWaitTasks")
    public GridModel queryWaitTasks(QueryWorkflowModel workflowModel){
        workflowModel.setCreateBy(UserUtils.getUser().getBusinessId());
        workflowServiceImpl.queryWorkflowWaitTasksByPagination(getPaginationUtility(),workflowModel);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * 查询已办任务 列表
     * @param workflowModel
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAlreadyTasks")
    public GridModel queryAlreadyTasks(QueryWorkflowModel workflowModel){
        workflowModel.setCreateBy(UserUtils.getUser().getBusinessId());
        workflowServiceImpl.queryWorkflowAlreadyTasksByPagination(getPaginationUtility(),workflowModel);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }
}
