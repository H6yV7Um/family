package org.activiti.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lcy on 17/6/14.
 *
 *   `plugin` varchar(255) DEFAULT NULL COMMENT '插件',
     `NAME` varchar(155) DEFAULT NULL COMMENT '字段名称',
     `title` varchar(155) DEFAULT NULL COMMENT '字段标题',
     `type` varchar(155) DEFAULT NULL COMMENT '字段类型',
     `flow` varchar(155) DEFAULT NULL COMMENT '',
     `form_ID` varchar(64) NOT NULL COMMENT '表单ID',
 */
@Entity
@Table(name = "acf_field")
public class WorkflowFormField  extends IdEntity<WorkflowFormField> {

    private static final long serialVersionUID = 5147996897263628429L;

    public WorkflowFormField() {
    }

    private String plugin,name,title,type,flow,formId;

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
