package com.songwie.task.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TTASK_USER")
public class OperUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPER_NO")
    private Integer operNo;
    
    @Column(name = "OPER_TYPE", length = 10)
    private String operType;

	@Column(name = "OPER_USER_NAME", length = 20)
    private String operUserName;

	@Column(name = "OPER_PASSWORD", length = 20)
    private String operPassword;

	@Column(name = "OPER_BRANCH_ID")
    private Integer operBranchId;

	@Column(name = "OPER_REAL_NAME", length = 20)
    private String operRealName;

	@Column(name = "OPER_PHONE", length = 15)
    private String operPhone;

	@Column(name = "OPER_EMAIL", length = 50)
    private String operEmail;

	@Column(name = "OPER_ID_NO", length = 20)
    private String operIdNo;

	@Column(name = "OPER_ROLE", length = 10)
    private String operRole;

	@Column(name = "OPER_STATUS", length = 10)
    private String operStatus;

	@Column(name = "OPER_START_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date operStartDate;

	@Column(name = "OPER_END_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date operEndDate;
 
    /**
     * 替换合并两个OperUser
     * @param operUser  原始的user
     * @param replaceOperUser 要替换的user
     * @return
     */
    public static OperUser replaceUser(OperUser operUser,OperUser replaceOperUser){
    	if(replaceOperUser.getOperBranchId()!=null)operUser.setOperBranchId(replaceOperUser.getOperBranchId());
    	if(replaceOperUser.getOperEmail()!=null)operUser.setOperEmail(replaceOperUser.getOperEmail());
    	if(replaceOperUser.getOperEndDate()!=null)operUser.setOperEndDate(replaceOperUser.getOperEndDate());
    	if(replaceOperUser.getOperIdNo()!=null)operUser.setOperIdNo(replaceOperUser.getOperIdNo());
    	if(replaceOperUser.getOperNo()!=null)operUser.setOperNo(replaceOperUser.getOperNo());
    	if(replaceOperUser.getOperPassword()!=null)operUser.setOperPassword(replaceOperUser.getOperPassword());
    	if(replaceOperUser.getOperPhone()!=null)operUser.setOperPhone(replaceOperUser.getOperPhone());
    	if(replaceOperUser.getOperRealName()!=null)operUser.setOperRealName(replaceOperUser.getOperRealName());
    	if(replaceOperUser.getOperRole()!=null)operUser.setOperRole(replaceOperUser.getOperRole());
    	if(replaceOperUser.getOperStartDate()!=null)operUser.setOperStartDate(replaceOperUser.getOperStartDate());
    	if(replaceOperUser.getOperStatus()!=null)operUser.setOperStatus(replaceOperUser.getOperStatus());
    	if(replaceOperUser.getOperType()!=null)operUser.setOperType(replaceOperUser.getOperType());
    	if(replaceOperUser.getOperUserName()!=null)operUser.setOperUserName(replaceOperUser.getOperUserName());
    	return operUser;
    }


	

	public String getOperType() {
        return operType;
    }

	public void setOperType(String operType) {
        this.operType = operType;
    }

	public String getOperUserName() {
        return operUserName;
    }

	public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

	public String getOperPassword() {
        return operPassword;
    }

	public void setOperPassword(String operPassword) {
        this.operPassword = operPassword;
    }

	public Integer getOperBranchId() {
        return operBranchId;
    }

	public void setOperBranchId(Integer operBranchId) {
        this.operBranchId = operBranchId;
    }

	public String getOperRealName() {
        return operRealName;
    }

	public void setOperRealName(String operRealName) {
        this.operRealName = operRealName;
    }

	public String getOperPhone() {
        return operPhone;
    }

	public void setOperPhone(String operPhone) {
        this.operPhone = operPhone;
    }

	public String getOperEmail() {
        return operEmail;
    }

	public void setOperEmail(String operEmail) {
        this.operEmail = operEmail;
    }

	public String getOperIdNo() {
        return operIdNo;
    }

	public void setOperIdNo(String operIdNo) {
        this.operIdNo = operIdNo;
    }

	public String getOperRole() {
        return operRole;
    }

	public void setOperRole(String operRole) {
        this.operRole = operRole;
    }

	public String getOperStatus() {
        return operStatus;
    }

	public void setOperStatus(String operStatus) {
        this.operStatus = operStatus;
    }

	public Date getOperStartDate() {
        return operStartDate;
    }

	public void setOperStartDate(Date operStartDate) {
        this.operStartDate = operStartDate;
    }

	public Date getOperEndDate() {
        return operEndDate;
    }

	public void setOperEndDate(Date operEndDate) {
        this.operEndDate = operEndDate;
    }

	public Integer getOperNo() {
        return this.operNo;
    }

	public void setOperNo(Integer operNo) {
        this.operNo = operNo;
    }

}
