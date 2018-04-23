package org.activiti.model;

import java.io.Serializable;

/**
 * Created by lcy on 17/6/8.
 */
public class QueryWorkflowModel implements Serializable {


    private static final long serialVersionUID = 3910278523563313545L;

    public QueryWorkflowModel(String modelName, String key) {
        this.modelName = modelName;
        this.key = key;
    }

    public QueryWorkflowModel() {
    }

    private String modelName,key;


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String flag,delFlag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    private String createBy;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
