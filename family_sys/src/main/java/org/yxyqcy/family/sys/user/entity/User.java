package org.yxyqcy.family.sys.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;
import org.yxyqcy.family.common.entity.IdEntity;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.role.entity.Role;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:07
 * description: user
 */
@Entity
@Table(name = "sys_user")
public class User extends IdEntity<User> {
    private static final long serialVersionUID = 1L;

    private String loginName;// 登录名
    private String password;// 密码
    private String no;		// 工号
    private String name;	// 姓名
    private String email;	// 邮箱
    private String phone;	// 电话
    private String mobile;	// 手机
    //private String userType;// 用户类型  0 mother 1 father  2 children  3 others
    private String loginIp;	// 最后登陆IP
    private String officeId;
    private Date loginDate;	// 最后登陆日期
    @Transient
	private List<DeptRole> deptRoles ;

	public List<DeptRole> getDeptRoles() {
		return deptRoles;
	}

	public void setDeptRoles(List<DeptRole> deptRoles) {
		this.deptRoles = deptRoles;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}


    @Transient//必须加到属性上面   getter()  不起作用
    private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //@Email @Length(min=0, max=200)
    public String getEmail() {
        return email;
    }

    @Transient
    @JsonIgnore
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

/*    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }*/

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    
    /**
     * 以下是新加的字段
     */
    private String personId;
    private Date entryDate;
    private Integer emplGroup;//员工组
    private Integer trialOrNot;
    private Integer trialLong;
    private Date trialDate;
    private String workPosition;
    private String address;
    private String urgencyPerson;
    private String urgencyLine;
    private String college;
    private String speciality;
    private Integer academic;//学历
    private Integer degree;//学位
    private Date graduateDate;
    private Date workDate;
    private Integer nation;//民族
    private String hometown;
    private Integer personProperty;//户口性质
    private Date birthday;
    private Integer sex;//性别
    private Integer marryOrNot;//婚姻状况
    private String bankName;
    private String bankNum;
    private String accountBy;
    private Integer contractType;//合同类型
    private Date contractStart;
    private Date contractEnd;
    private Date renewContractStart;
    private Date renewContractEnd;
    private Integer agreementType;//协议类型
    private Date agreementStart;
    private Date agreementEnd;

    private String userStates;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	public Integer getTrialOrNot() {
		return trialOrNot;
	}

	public void setTrialOrNot(Integer trialOrNot) {
		this.trialOrNot = trialOrNot;
	}

	public Integer getTrialLong() {
		return trialLong;
	}

	public void setTrialLong(Integer trialLong) {
		this.trialLong = trialLong;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getTrialDate() {
		return trialDate;
	}

	public void setTrialDate(Date trialDate) {
		this.trialDate = trialDate;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrgencyPerson() {
		return urgencyPerson;
	}

	public void setUrgencyPerson(String urgencyPerson) {
		this.urgencyPerson = urgencyPerson;
	}

	public String getUrgencyLine() {
		return urgencyLine;
	}

	public void setUrgencyLine(String urgencyLine) {
		this.urgencyLine = urgencyLine;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getMarryOrNot() {
		return marryOrNot;
	}

	public void setMarryOrNot(Integer marryOrNot) {
		this.marryOrNot = marryOrNot;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	


	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getAccountBy() {
		return accountBy;
	}

	public void setAccountBy(String accountBy) {
		this.accountBy = accountBy;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getRenewContractStart() {
		return renewContractStart;
	}

	public void setRenewContractStart(Date renewContractStart) {
		this.renewContractStart = renewContractStart;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getRenewContractEnd() {
		return renewContractEnd;
	}
	
	public void setRenewContractEnd(Date renewContractEnd) {
		this.renewContractEnd = renewContractEnd;
	}

	public Integer getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(Integer agreementType) {
		this.agreementType = agreementType;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getAgreementStart() {
		return agreementStart;
	}

	public void setAgreementStart(Date agreementStart) {
		this.agreementStart = agreementStart;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getAgreementEnd() {
		return agreementEnd;
	}

	public void setAgreementEnd(Date agreementEnd) {
		this.agreementEnd = agreementEnd;
	}

	public Integer getEmplGroup() {
		return emplGroup;
	}

	public void setEmplGroup(Integer emplGroup) {
		this.emplGroup = emplGroup;
	}

	public Integer getAcademic() {
		return academic;
	}

	public void setAcademic(Integer academic) {
		this.academic = academic;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public Integer getPersonProperty() {
		return personProperty;
	}

	public void setPersonProperty(Integer personProperty) {
		this.personProperty = personProperty;
	}

	public String getUserStates() {
		return userStates;
	}

	public void setUserStates(String userStates) {
		this.userStates = userStates;
	}


	private String digestKey;

	public String getDigestKey() {
		return digestKey;
	}

	public String getWeixinName() {
		return weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}

	public void setDigestKey(String digestKey) {
		this.digestKey = digestKey;
	}

	private String weixinName;// weixin登录名

	@Transient
	private String checked;

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
