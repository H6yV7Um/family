package org.activiti.entity;

import org.activiti.engine.repository.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lcy on 17/6/8.
 */
public class WorkflowModel  implements Serializable{

    public WorkflowModel() {
    }

    private static final long serialVersionUID = 1L;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    private String modelId;
    private	int rev;
    private String name;
    private String key;
    private String category;
    private Date createTime;
    private Date lastUpdateTime;
    private int version;
    private String metaInfo;
    private String deploymentId;
    private String editorSourceValueId;;
    private String editorSourceExtraValueId;
    private String tenantId;
    //add model
    private String modelName,modelKey,modelDes;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public String getModelDes() {
        return modelDes;
    }

    public void setModelDes(String modelDes) {
        this.modelDes = modelDes;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getEditorSourceValueId() {
        return editorSourceValueId;
    }

    public void setEditorSourceValueId(String editorSourceValueId) {
        this.editorSourceValueId = editorSourceValueId;
    }

    public String getEditorSourceExtraValueId() {
        return editorSourceExtraValueId;
    }

    public void setEditorSourceExtraValueId(String editorSourceExtraValueId) {
        this.editorSourceExtraValueId = editorSourceExtraValueId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void copyModel(Model model) {
        // TODO Auto-generated method stub
        this.setName(model.getName());
        this.setCategory(model.getCategory());
        this.setModelId(model.getId());
        this.setKey(model.getKey());
        this.setMetaInfo(model.getMetaInfo());
        this.setTenantId(model.getTenantId());
        this.setVersion(model.getVersion());
        this.setCreateTime(model.getCreateTime());
        this.setLastUpdateTime(model.getLastUpdateTime());
        this.setDeploymentId(model.getDeploymentId());
    }
}
