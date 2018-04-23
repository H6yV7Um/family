package org.yxyqcy.family.sys.log.entity;


import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 系统日志管理
 * @author yangxuenan
 *
 */
@Entity
@Table(name="sys_log")
public class Log extends IdEntity<Log> {

	private static final long serialVersionUID = 5426319278573203656L;
	
	//信息
	private String message;
	//级别
	private String level;
	//细节
	private String detail;

	//获取的用户名
	@Transient
	private String username;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
