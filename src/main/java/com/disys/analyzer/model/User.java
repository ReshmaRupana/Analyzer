package com.disys.analyzer.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sajid
 * @since Nov 15, 2019
 */

/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name = "Users")//,schema="Analyser.dbo")
@NamedQueries(value = { @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u") })

@NamedStoredProcedureQueries({ 
    @NamedStoredProcedureQuery(
        name = "spSetUserLoginAttempt", 
        procedureName = "wireless.spSetUserLoginAttempt", 
        parameters = { 
            @StoredProcedureParameter(
                mode = ParameterMode.IN, 
                name = "varLoggedUserID", 
                type = String.class),
            @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varIPAddress", 
                    type = String.class),
            @StoredProcedureParameter(
                mode = ParameterMode.IN, 
                name = "varLoginStatus", 
                type = String.class),
            @StoredProcedureParameter(
                    mode = ParameterMode.OUT, 
                    name = "varUserLogId", 
                    type = Integer.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "spLogoutUserActivity", 
            procedureName = "wireless.spLogoutUserActivity", 
            parameters = { 
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLoggedUserID", 
                    type = String.class),
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varUserLogId", 
                    type = Integer.class)
                }
        ),
    
    @NamedStoredProcedureQuery(
            name = "spAddADAnalyzerUserEntry", 
            procedureName = "wireless.spAddADAnalyzerUserEntry", 
            parameters = { 
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLoggedUserID", 
                    type = String.class),
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varADLoginId", 
                    type = String.class),
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varFirstName", 
                    type = String.class),
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLastName", 
                    type = String.class),
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varStatus", 
                    type = String.class)
                }
	    ),
	
	    @NamedStoredProcedureQuery(
	            name = "spProcessADSyncData", 
	            procedureName = "wireless.spProcessADSyncData", 
	            parameters = { 
	                @StoredProcedureParameter(
	                    mode = ParameterMode.IN, 
	                    name = "varLoggedUserID", 
	                    type = String.class)
	                }
     )
    
    /*    
    ,
    @NamedStoredProcedureQuery(
            name = "spGetAnalyzerPropertyValue", 
            procedureName = "wireless.spGetAnalyzerPropertyValue", 
            parameters = { 
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLoggedUserID", 
                    type = String.class),
                @StoredProcedureParameter(
                        mode = ParameterMode.IN, 
                        name = "varUserLogId", 
                        type = Integer.class)
                }
        ),
    @NamedStoredProcedureQuery(
            name = "spAddUserBatchJobAudit", 
            procedureName = "wireless.spAddUserBatchJobAudit", 
            parameters = { 
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLoggedUserID", 
                    type = String.class),
                @StoredProcedureParameter(
                        mode = ParameterMode.IN, 
                        name = "varUserLogId", 
                        type = Integer.class)
                }
        ),
	//For both activate and  terminate AD Users
    @NamedStoredProcedureQuery(
            name = "spAddActivateADUserToAnalyzer", 
            procedureName = "wireless.spAddActivateADUserToAnalyzer", 
            parameters = { 
                @StoredProcedureParameter(
                    mode = ParameterMode.IN, 
                    name = "varLoggedUserID", 
                    type = String.class),
                @StoredProcedureParameter(
                        mode = ParameterMode.IN, 
                        name = "varUserLogId", 
                        type = Integer.class)
                }
        )  
     */
    
})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "Acceptance")
	private Boolean acceptance;

	@Column(name = "Addr_1")
	private String addr1;

	@Column(name = "Addr_2")
	private String addr2;

	@Column(name = "ADPCode")
	private String ADPCode;

	@Column(name = "Alternate_Email")
	private String alternateEmail;

	@Column(name = "AutoExpenseLimit")
	private String autoExpenseLimit;

	@Column(name = "City")
	private String city;

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "internal_user_id")
	private Integer internalUserId;

	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Location")
	private Integer location;

	@Column(name = "Lunch_Hour")
	private String lunchHour;

	@Column(name = "manager_id")
	private String managerId;

	@Column(name = "OtherExpenseLimit")
	private String otherExpenseLimit;

	@Column(name = "Overtime")
	private Boolean overtime;

	@Column(name = "Password")
	private String password;

	@Column(name = "PayrollCode")
	private String payrollCode;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "ROL_ID")
	private Integer roleId;
	
	@Column(name = "ADAnalyzerGroupSyncId")
	private Integer ADAnalyzerGroupSyncId;
	
	@Column(name = "BatchId")
	private Integer batchId;

	@Column(name = "SpeedCap")
	private Boolean speedCap;

	@Column(name = "SpeedLimit")
	private Integer speedLimit;

	@Column(name = "State")
	private String state;

	@Column(name = "Status")
	private Integer status;

	@Column(name = "TimeZone")
	private Integer timeZone;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_Date")
	private Timestamp updatedDate;

	@Column(name = "Zip")
	private String zip;

	// bi-directional many-to-one association to AnalyserSubContractor
	/*@OneToMany(mappedBy = "user")
	private List<AnalyserSubContractor> analyserSubContractors;*/

	@Column(name = "ORG_ID")
	private Integer orgId;
	
	@Column(name = "CompanyCode")
	private String companyCode;

	/**
	 * @param internalUserId
	 */
	public User(Integer internalUserId) {
		super();
		this.internalUserId = internalUserId;
	}

	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1
	 *            the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2
	 *            the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * @return the alternateEmail
	 */
	public String getAlternateEmail() {
		return alternateEmail;
	}

	/**
	 * @param alternateEmail
	 *            the alternateEmail to set
	 */
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getAcceptance() {
		return this.acceptance;
	}

	public void setAcceptance(Boolean acceptance) {
		this.acceptance = acceptance;
	}

	public String getADPCode() {
		return this.ADPCode;
	}

	public void setADPCode(String ADPCode) {
		this.ADPCode = ADPCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getInternalUserId() {
		return this.internalUserId;
	}

	public void setInternalUserId(Integer internalUserId) {
		this.internalUserId = internalUserId;
	}

	public Integer getLocation() {
		return this.location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Boolean getOvertime() {
		return this.overtime;
	}

	public void setOvertime(Boolean overtime) {
		this.overtime = overtime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayrollCode() {
		return this.payrollCode;
	}

	public void setPayrollCode(String payrollCode) {
		this.payrollCode = payrollCode;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getSpeedCap() {
		return this.speedCap;
	}

	public void setSpeedCap(Boolean speedCap) {
		this.speedCap = speedCap;
	}

	public Integer getSpeedLimit() {
		return this.speedLimit;
	}

	public void setSpeedLimit(Integer speedLimit) {
		this.speedLimit = speedLimit;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	/*public List<AnalyserSubContractor> getAnalyserSubContractors() {
		return this.analyserSubContractors;
	}

	public void setAnalyserSubContractors(
			List<AnalyserSubContractor> analyserSubContractors) {
		this.analyserSubContractors = analyserSubContractors;
	}

	public AnalyserSubContractor addAnalyserSubContractor(
			AnalyserSubContractor analyserSubContractor) {
		getAnalyserSubContractors().add(analyserSubContractor);
		analyserSubContractor.setUser(this);

		return analyserSubContractor;
	}

	public AnalyserSubContractor removeAnalyserSubContractor(
			AnalyserSubContractor analyserSubContractor) {
		getAnalyserSubContractors().remove(analyserSubContractor);
		analyserSubContractor.setUser(null);

		return analyserSubContractor;
	}*/

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the autoExpenseLimit
	 */
	public String getAutoExpenseLimit() {
		return autoExpenseLimit;
	}

	/**
	 * @param autoExpenseLimit the autoExpenseLimit to set
	 */
	public void setAutoExpenseLimit(String autoExpenseLimit) {
		this.autoExpenseLimit = autoExpenseLimit;
	}

	/**
	 * @return the lunchHour
	 */
	public String getLunchHour() {
		return lunchHour;
	}

	/**
	 * @param lunchHour the lunchHour to set
	 */
	public void setLunchHour(String lunchHour) {
		this.lunchHour = lunchHour;
	}

	/**
	 * @return the otherExpenseLimit
	 */
	public String getOtherExpenseLimit() {
		return otherExpenseLimit;
	}

	/**
	 * @param otherExpenseLimit the otherExpenseLimit to set
	 */
	public void setOtherExpenseLimit(String otherExpenseLimit) {
		this.otherExpenseLimit = otherExpenseLimit;
	}
	/**
	 * @return the ADAnalyzerGroupSyncId
	 */
	public Integer getADAnalyzerGroupSyncId() {
		return ADAnalyzerGroupSyncId;
	}
	/**
	 * @param ADAnalyzerGroupSyncId the ADAnalyzerGroupSyncId to set
	 */
	public void setADAnalyzerGroupSyncId(Integer aDAnalyzerGroupSyncId) {
		ADAnalyzerGroupSyncId = aDAnalyzerGroupSyncId;
	}
	/**
	 * @return the batchId
	 */
	public Integer getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}