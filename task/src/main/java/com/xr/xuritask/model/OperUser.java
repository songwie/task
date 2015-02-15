package com.xr.xuritask.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "TTASK_USER")
public class OperUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPER_NO")
    private Integer operNo;


    public static OperUser getOperUserByUserNameAndPassoword(String username,String password){
    	List<OperUser> operUsers = entityManager().createQuery("select o from OperUser o where o.operUserName=?1 and o.operPassword=?2", OperUser.class)
    			.setParameter(1, username)
    			.setParameter(2, password)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }

    public static OperUser getOperUserByUsernameAndEmail(String username, String email){
    	List<OperUser> operUsers = entityManager().createQuery("select o from OperUser o where o.operUserName=?1 and o.operEmail=?2", OperUser.class)
    			.setParameter(1, username)
    			.setParameter(2, email)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
    public static OperUser getOperUserByTelphone(String telphone){
    	List<OperUser> operUsers = entityManager().createQuery("select o from OperUser o where o.operPhone=:telphone ", OperUser.class)
    			.setParameter("telphone", telphone)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
    public static OperUser getOperUserByMerchant(String operType, int merchantId){
    	List<OperUser> operUsers = entityManager().createQuery("select o from OperUser o where o.operType=:operType and o.operBranchId=:merchantId ", OperUser.class)
    			.setParameter("operType", operType)
    			.setParameter("merchantId", merchantId)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public static OperUser getOperUserByUsername(String username){
    	List<OperUser> operUsers = entityManager().createQuery("select o from OperUser o where o.operUserName=?1", OperUser.class)
    			.setParameter(1, username)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
    /**
     * 根据用户名查询资源组
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List<String> getAuthoritiesByUsername(String username){
    	return entityManager().createNativeQuery(" select AUTHORITIES_NAME from AUTHORITIES where AUTHORITIES.id in (" +
    			"" +" select AUTHORITIES_ID from USER_AUTHORITIES where OPER_USER_NAME=?1"+
    			") ").setParameter(1, username)
    			.getResultList();
    }
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@javax.persistence.PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new OperUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countOperUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OperUser o", Long.class).getSingleResult();
    }

	public static List<OperUser> findAllOperUsers() {
        return entityManager().createQuery("SELECT o FROM OperUser o", OperUser.class).getResultList();
    }

	public static OperUser findOperUser(Integer operNo) {
        if (operNo == null) return null;
        return entityManager().find(OperUser.class, operNo);
    }

	public static List<OperUser> findOperUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OperUser o", OperUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OperUser attached = OperUser.findOperUser(this.operNo);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public OperUser merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OperUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }


}
