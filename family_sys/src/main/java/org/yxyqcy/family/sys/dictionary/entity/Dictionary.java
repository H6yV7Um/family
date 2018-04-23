package org.yxyqcy.family.sys.dictionary.entity;


import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 
 * @author yangxuenan
 * 数据字典
 *
 */
@Entity
@Table(name="sys_dictionary")
public class Dictionary extends IdEntity<Dictionary> {
	
	public Dictionary() {
    }

	private static final long serialVersionUID = 6077936404957951402L;
	
	private String pid;
	private String type;
	private String name;
	private String value;
	private String remarks;
	/**
	 * 获取的用户名
	 */
	@Transient
	private String username;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	

}
