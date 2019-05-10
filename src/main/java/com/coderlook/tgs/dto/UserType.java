package com.coderlook.tgs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type", catalog = "tgs")
public class UserType implements java.io.Serializable {

	@Override
	public String toString() {
		return "UserType [id=" + id + ", typeName=" + typeName + ", status=" + status + "]";
	}

	private Short id;
	private String typeName;
	private String status;

	public UserType() {
	}

	public UserType(Short id) {
		this.id = id;
	}

	public UserType(String typeName, String status) {
		this.typeName = typeName;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "TYPE_NAME", nullable = false, length = 45)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "STATUS", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
