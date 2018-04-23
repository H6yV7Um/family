package org.activiti.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lcy on 17/6/14.
 *
 *    `NAME` varchar(255) DEFAULT NULL COMMENT '表单名称',
     `code` varchar(20) DEFAULT NULL COMMENT '表单编码',
     `original_html` TEXT DEFAULT NULL COMMENT '原始html',
     `parse_html` TEXT DEFAULT NULL COMMENT '解析后html',
     `filed_count` int  COMMENT '字段总数',
     `table_name` varchar(100) DEFAULT NULL COMMENT '数据表名称',
 */
@Entity
@Table(name = "acf_form")
public class WorkflowForm extends IdEntity<WorkflowForm> {


    private static final long serialVersionUID = 5573873289721430497L;

    public WorkflowForm() {
    }

    private String name;
    private String code;
    private String originalHtml;
    private String parseHtml;
    private String tableName;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private String des;

    private int fieldCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginalHtml() {
        return originalHtml;
    }

    public void setOriginalHtml(String originalHtml) {
        this.originalHtml = originalHtml;
    }

    public String getParseHtml() {
        return parseHtml;
    }

    public void setParseHtml(String parseHtml) {
        this.parseHtml = parseHtml;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }
}
