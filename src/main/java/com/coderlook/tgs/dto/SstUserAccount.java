package com.coderlook.tgs.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sst_user_account", catalog = "tgs")
public class SstUserAccount implements java.io.Serializable {

	private Integer id;
	private SstAccount sstAccount;
	private SstUser sstUser;
	private Date createDate;
	private Integer createBy;
	private Date updateDate;
	private Integer updateBy;
	private String status;

	public SstUserAccount() {
	}

	public SstUserAccount(SstAccount sstAccount, SstUser sstUser, Date createDate, Date updateDate, String status) {
		this.sstAccount = sstAccount;
		this.sstUser = sstUser;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
	}

	public SstUserAccount(SstAccount sstAccount, SstUser sstUser, Date createDate, Integer createBy, Date updateDate, Integer updateBy, String status) {
		this.sstAccount = sstAccount;
		this.sstUser = sstUser;
		this.createDate = createDate;
		this.createBy = createBy;
		this.updateDate = updateDate;
		this.updateBy = updateBy;
		this.status = status;
	}

	public SstUserAccount(SstUser sstUser, SstAccount ssta, Date createDate, Date updateDate, int parseInt, String status) {
		this.sstUser = sstUser;
		this.sstAccount = ssta;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT", nullable = false)
	public SstAccount getSstAccount() {
		return this.sstAccount;
	}

	public void setSstAccount(SstAccount sstAccount) {
		this.sstAccount = sstAccount;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	public SstUser getSstUser() {
		return this.sstUser;
	}

	public void setSstUser(SstUser sstUser) {
		this.sstUser = sstUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_BY")
	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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
		return "SstUserAccount [id=" + id + ", sstAccount=" + sstAccount + ", sstUser=" + sstUser + ", createDate=" + createDate + ", createBy=" + createBy + ", updateDate=" + updateDate + ", updateBy=" + updateBy + ", status=" + status
				+ "]";
	}

}
