package com.coderlook.tgs.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sst_account", catalog = "tgs")
public class SstAccount implements java.io.Serializable {

	private String loginId;
	private UserType userType;
	private String geoLocType;
	private String geoLocCode;
	private String password;
	private String email;
	private String salt;
	private String lastPass1;
	private String lastPass2;
	private String lastPass3;
	private String locked;
	private String reset;
	private Date updateDate;
	private Integer updateBy;
	private String status;
//	private Set sstUserAccounts = new HashSet(0);

	public SstAccount() {
	}

	public SstAccount(String loginId, Date updateDate, String status) {
		this.loginId = loginId;
		this.updateDate = updateDate;
		this.status = status;
	}

	public SstAccount(String loginId, UserType userType, String geoLocType, String geoLocCode, String password,
			String salt, String lastPass1, String lastPass2, String lastPass3, String locked, String reset,
			Date updateDate, Integer updateBy, String status, Set sstUserAccounts) {
		this.loginId = loginId;
		this.userType = userType;
		this.geoLocType = geoLocType;
		this.geoLocCode = geoLocCode;
		this.password = password;
		this.salt = salt;
		this.lastPass1 = lastPass1;
		this.lastPass2 = lastPass2;
		this.lastPass3 = lastPass3;
		this.locked = locked;
		this.reset = reset;
		this.updateDate = updateDate;
		this.updateBy = updateBy;
		this.status = status;
//		this.sstUserAccounts = sstUserAccounts;
	}

	@Id

	@Column(name = "LOGIN_ID", unique = true, nullable = false, length = 50)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@ManyToOne
	@JoinColumn(name = "USER_TYPE")
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Column(name = "GEO_LOC_TYPE", length = 20)
	public String getGeoLocType() {
		return this.geoLocType;
	}

	public void setGeoLocType(String geoLocType) {
		this.geoLocType = geoLocType;
	}

	@Column(name = "GEO_LOC_CODE", length = 50)
	public String getGeoLocCode() {
		return this.geoLocCode;
	}

	public void setGeoLocCode(String geoLocCode) {
		this.geoLocCode = geoLocCode;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SALT", length = 50)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "LAST_PASS_1", length = 50)
	public String getLastPass1() {
		return this.lastPass1;
	}

	public void setLastPass1(String lastPass1) {
		this.lastPass1 = lastPass1;
	}

	@Column(name = "LAST_PASS_2", length = 50)
	public String getLastPass2() {
		return this.lastPass2;
	}

	public void setLastPass2(String lastPass2) {
		this.lastPass2 = lastPass2;
	}

	@Column(name = "LAST_PASS_3", length = 50)
	public String getLastPass3() {
		return this.lastPass3;
	}

	public void setLastPass3(String lastPass3) {
		this.lastPass3 = lastPass3;
	}

	@Column(name = "LOCKED", length = 2)
	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Column(name = "RESET", length = 2)
	public String getReset() {
		return this.reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE", nullable = false, length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_BY")
	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "STATUS", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SstAccount [loginId=" + loginId + ", userType=" + userType + ", geoLocType=" + geoLocType
				+ ", geoLocCode=" + geoLocCode + ", status=" + status + "]";
	}

}
