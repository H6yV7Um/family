package org.activiti.dao;

import org.activiti.entity.WorkflowDeployment;
import org.activiti.entity.WorkflowInstance;
import org.activiti.entity.WorkflowTask;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/6/16.
 */
@Repository
public interface WorkflowRepository {
    /**
     * 查询 workflow instance
     *
     * "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +
     *  匹配
     * @param param
     * @return
     */
    @Select("<script>select * from act_ru_execution f where 1=1 " +
            "</script>")
    @Results({
        @Result(id = true,property = "processExecutionId",column = "ID_"),
            @Result(property = "processInstanceId",column = "PROC_INST_ID_"),
            @Result(property = "processDefinitionKey",column = "BUSINESS_KEY_"),
            @Result(property = "name",column = "NAME_"),
            @Result(property = "processDefinitionId",column = "PROC_DEF_ID_"),
            @Result(property = "actId",column = "ACT_ID_")
    })
    List<WorkflowInstance> queryWorkflowInstances(Map param);

    /**
     * 查询历史流程
     * @param param
     * @return
     */
    @Select("<script>select * from act_hi_procinst f where 1=1 and END_TIME_ is not null " +
            "</script>")
    @Results({
            @Result(id = true,property = "processExecutionId",column = "ID_"),
            @Result(property = "processInstanceId",column = "PROC_INST_ID_"),
            @Result(property = "processDefinitionKey",column = "BUSINESS_KEY_"),
            @Result(property = "name",column = "NAME_"),
            @Result(property = "processDefinitionId",column = "PROC_DEF_ID_"),
            @Result(property = "actId",column = "ACT_ID_")
    })
    List<WorkflowInstance> queryWorkflowHisInstances(Map param);


    /**
     * 查询流程部署
     * @param param
     * @return
     */
    @Select("<script>SELECT " +
            " ard.ID_ AS DEPLOYMENT_ID_, " +
            " arp.ID_ AS DEFINITION_ID_, " +
            " arp.KEY_ AS DEFINITION_KEY_, " +
            " ard.DEPLOY_TIME_ AS DEPLOY_TIME_, " +
            " ard.NAME_ AS NAME_, " +
            " arp.VERSION_ AS VERSION_ " +
            "FROM " +
            " act_re_deployment ard " +
            " LEFT JOIN act_re_procdef arp ON ard.ID_ = arp.DEPLOYMENT_ID_" +
            " where 1=1 " +
            "</script>")
    @Results({
            @Result(property = "processDeploymentId",column = "DEPLOYMENT_ID_"),
            @Result(property = "processDefinitionKey",column = "DEFINITION_KEY_"),
            @Result(property = "name",column = "NAME_"),
            @Result(property = "processDefinitionId",column = "DEFINITION_ID_"),
            @Result(property = "deployTime",column = "DEPLOY_TIME_",jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "version",column = "VERSION_")
    })
    List<WorkflowDeployment> queryWorkflowDeployments(Map param);


    /**
     * 查询待办任务
     * @param param
     * @return
     */
    @Select("<script>SELECT" +
            " v.TASK_ID AS taskId, " +
            " v.ACT_ID AS actId, " +
            " v.PROC_INST_ID AS processInstanceId, " +
            " v.ACT_NAME AS actName, " +
            " v.ASSIGNEE AS assignee, " +
            " v.business_key AS businessKey, " +
            " v.CREATE_TIME AS createTime, " +
            " v.CANDIDATE AS candidate " +
            " FROM " +
                    " v_act_waittasklist v" +
            " where 1=1 " +
            "</script>")
    @Results({
            @Result(property = "processDeploymentId",column = "DEPLOYMENT_ID_"),
            @Result(property = "processDefinitionKey",column = "DEFINITION_KEY_"),
            @Result(property = "name",column = "NAME_"),
            @Result(property = "processDefinitionId",column = "DEFINITION_ID_"),
            @Result(property = "deployTime",column = "DEPLOY_TIME_",jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "version",column = "VERSION_")
    })
    List<WorkflowTask> queryWorkflowWaitTasks(Map param);

    /**
     * 查询已办任务
     * @param param
     * @return
     */
    @Select("<script>SELECT " +
            " ard.ID_ AS DEPLOYMENT_ID_, " +
            " arp.ID_ AS DEFINITION_ID_, " +
            " arp.KEY_ AS DEFINITION_KEY_, " +
            " ard.DEPLOY_TIME_ AS DEPLOY_TIME_, " +
            " ard.NAME_ AS NAME_, " +
            " arp.VERSION_ AS VERSION_ " +
            "FROM " +
            " act_re_deployment ard " +
            " LEFT JOIN act_re_procdef arp ON ard.ID_ = arp.DEPLOYMENT_ID_" +
            " where 1=1 " +
            "</script>")
    @Results({
            @Result(property = "processDeploymentId",column = "DEPLOYMENT_ID_"),
            @Result(property = "processDefinitionKey",column = "DEFINITION_KEY_"),
            @Result(property = "name",column = "NAME_"),
            @Result(property = "processDefinitionId",column = "DEFINITION_ID_"),
            @Result(property = "deployTime",column = "DEPLOY_TIME_",jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "version",column = "VERSION_")
    })
    List<WorkflowTask> queryWorkflowAlreadyTasks(Map param);
}
