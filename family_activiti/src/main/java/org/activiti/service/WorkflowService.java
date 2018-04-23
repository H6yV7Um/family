package org.activiti.service;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.entity.WorkflowDeployment;
import org.activiti.entity.WorkflowInstance;
import org.activiti.entity.WorkflowModel;
import org.activiti.entity.WorkflowTask;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.model.WorkflowBpmnModel;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/6/8.
 *
 * workflow service
 */

public interface WorkflowService {
    /**
     * 分页查询工作流模型
     * @param paginationUtility
     * @param workflow
     * @return
     */
    GridModel queryWorkflowModelByActPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflow);

    /**
     * 删除模型
     * @param id
     * @return
     */
    PersistModel  modelRemove(String id);

    /**
     *  工作流
     * @param workflowModel
     * @return
     */
    PersistModel  persistModel(WorkflowModel workflowModel);

    /**
     * 部署model
     * @param modelId
     * @return
     */
    Deployment deployModel(String modelId);

    /**
     * 字节码模型
     * @param modelId   modelId
     * @param type      bpmn or json
     * @return
     */
    WorkflowBpmnModel getByteModel(String modelId, String type);

    /**
     * 查询流程部署模型
     * @param paginationUtility
     * @param workflow
     * @return
     */
    List<WorkflowDeployment> queryWorkflowDeploymentByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflow);

    /**
     * 启动流程
     * @param key   key
     * @param businessKey businessId
     * @param variable  流程
     * @param autoPassSubmit
     * @return
     */
    ProcessInstance startProcessByKey(String key,String businessKey, Map<String,Object> variable, boolean autoPassSubmit);

    /**
     * 查询流程实例
     * @param paginationUtility
     * @param workflow
     * @return
     */
    List<WorkflowInstance> queryWorkflowInstanceByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflow);
    /**
     * 查询流程实例
     * @param paginationUtility
     * @param workflowModel
     * @return
     */
    List<WorkflowInstance> queryWorkflowHisInstanceByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel);

    /**
     * 查询已办任务
     * @param paginationUtility
     * @param workflowModel
     * @return
     */
    List<WorkflowTask> queryWorkflowAlreadyTasksByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel);

    /**
     * 查询待办任务
     * @param paginationUtility
     * @param workflowModel
     * @return
     */
    List<WorkflowTask> queryWorkflowWaitTasksByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel);
}
