package com.coderlook.tgs.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class SstUserId implements java.io.Serializable {

	private int id;
	private String firstName;

	public SstUserId() {
	}

	public SstUserId(int id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SstUserId))
			return false;
		SstUserId castOther = (SstUserId) other;

		return (this.getId() == castOther.getId())
				&& ((this.getFirstName() == castOther.getFirstName()) || (this.getFirstName() != null
						&& castOther.getFirstName() != null && this.getFirstName().equals(castOther.getFirstName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		return result;
	}

}
