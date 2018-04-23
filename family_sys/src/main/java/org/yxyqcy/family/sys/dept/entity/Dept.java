package org.yxyqcy.family.sys.dept.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by lcy on 16/7/30.
 */
@Entity
@Table(name = "sys_dept")
public class Dept extends IdEntity<Dept> {
	
    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Transient
    private String checked;     // 根据权限 是否被选中

    public Dept() {
    }

    private String parentId;  //父机构id

    private String name;  //部门名称

    private String deptCode; //部门编号

    private Date seqDate ; //排序日期

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Date getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(Date seqDate) {
        this.seqDate = seqDate;
    }
}
