package org.activiti.service;

import org.activiti.entity.WorkflowForm;
import org.activiti.model.QueryWorkflowModel;
import org.yxyqcy.family.common.component.PageUtil;

import java.util.List;

/**
 * Created by lcy on 17/6/14.
 */
public interface WorkflowFormService {

    /**
     *  分页查询  form
     * @param paginationUtility
     * @param workflow
     * @return
     */
    public List<WorkflowForm> queryWorkflowFormByPagination(PageUtil<WorkflowForm> paginationUtility, QueryWorkflowModel workflow);
}
