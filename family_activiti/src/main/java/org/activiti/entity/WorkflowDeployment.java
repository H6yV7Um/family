package org.activiti.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lcy on 17/6/11.
 */
public class WorkflowDeployment implements Serializable {

    private static final long serialVersionUID = -8384609815619317518L;

    public WorkflowDeployment() {
    }

    private String processDeploymentId,processDefinitionKey,processDefinitionId;
    private String name;
    private String version;
    private Date deployTime;


}
