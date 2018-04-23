package org.activiti.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lcy on 17/6/17.
 */
public class WorkflowTask implements Serializable {
    private static final long serialVersionUID = -1953182580941344885L;

    public WorkflowTask() {
    }

    private String taskId, taskName, processExecutionId, assignee, processDefinitionId, processInstanceId;

    private Date createTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }



    public String getProcessExecutionId() {
        return processExecutionId;
    }

    public void setProcessExecutionId(String processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}