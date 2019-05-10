package com.coderlook.tgs.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "target_confirmation_history", catalog = "tgs")
public class TargetConfirmationHistory implements java.io.Serializable {

	private Long id;
	private SstAccount sstAccount;
	private SstUser sstUser;
	private String inputMonth;
	private Date dateTime;

	public TargetConfirmationHistory() {
	}

	public TargetConfirmationHistory(SstAccount sstAccount, SstUser sstUser, String inputMonth, Date dateTime) {
		this.sstAccount = sstAccount;
		this.sstUser = sstUser;
		this.inputMonth = inputMonth;
		this.dateTime = dateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ACCOUNT", nullable = false)
	public SstAccount getSstAccount() {
		return this.sstAccount;
	}

	public void setSstAccount(SstAccount sstAccount) {
		this.sstAccount = sstAccount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOGGEDIN_USER", nullable = false)
	public SstUser getSstUser() {
		return this.sstUser;
	}

	public void setSstUser(SstUser sstUser) {
		this.sstUser = sstUser;
	}

	@Column(name = "INPUT_MONTH", nullable = false, length = 20)
	public String getInputMonth() {
		return this.inputMonth;
	}

	public void setInputMonth(String inputMonth) {
		this.inputMonth = inputMonth;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_TIME", nullable = false, length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
