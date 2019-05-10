package com.coderlook.tgs.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sst_user", catalog = "tgs")
public class SstUser implements java.io.Serializable {

	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String employeeId;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String status;
	// private Set bmInputsForInputBy = new HashSet(0);
	// private Set bmInputsForUpdateBy = new HashSet(0);

	public SstUser() {
	}

//	public SstUser(SstUserModel sstUserModel) {
//		this.firstName = sstUserModel.getFirstName();
//		this.middleName = sstUserModel.getMiddleName();
//		this.lastName = sstUserModel.getLastName();
//		this.employeeId = sstUserModel.getEmployeeId();
//		this.createBy = sstUserModel.getCreateBy();
//		this.createDate = sstUserModel.getCreateDate();
//		this.updateBy = sstUserModel.getUpdateBy();
//		this.updateDate = sstUserModel.getUpdateDate();
//		this.status = TgsConstants.ACTIVE;
//	}

	public SstUser(int id, String firstName, String middleName, String lastName, String employeeId, String createBy, Date createDate, String updateBy, Date updateDate, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
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

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME", length = 50)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "STATUS", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "EMPLOYEE_ID")
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, length = 19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE", nullable = false, length = 19)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "SstUser [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", employeeId=" + employeeId + ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", status=" + status + "]";
	}

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "sstUserByInputBy")
	// public Set getBmInputsForInputBy() {
	// return this.bmInputsForInputBy;
	// }
	//
	// public void setBmInputsForInputBy(Set bmInputsForInputBy) {
	// this.bmInputsForInputBy = bmInputsForInputBy;
	// }
	//
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "sstUserByUpdateBy")
	// public Set getBmInputsForUpdateBy() {
	// return this.bmInputsForUpdateBy;
	// }
	//
	// public void setBmInputsForUpdateBy(Set bmInputsForUpdateBy) {
	// this.bmInputsForUpdateBy = bmInputsForUpdateBy;
	// }

}
