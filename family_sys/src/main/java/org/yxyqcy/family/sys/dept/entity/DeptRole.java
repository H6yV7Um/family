package org.yxyqcy.family.sys.dept.entity;

import java.io.Serializable;

/**
 * 显示dept_role
 * @author wangzhao
 *
 */
public class DeptRole implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public DeptRole() {
    }
    private String id;
    private String dname;
    private String dcode;
    private String did;
    private String rid;
    private String rname;
    private String rcode;
    private String checked;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
   
    
}
