package com.coderlook.tgs.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author Gouranga
 *
 */
public class UserLoginModel {
	@NotNull(message = "{user.name.notempty}")
	@Size(min=2, max=100, message = "{user.name.minimum}")
	private String userName;
	
	@NotNull(message = "{user.password.notempty}")
	@Size(min=2, max=100, message = "{user.password.minimum}")
	private String password;
	
	public UserLoginModel() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", password=" + password + "]";
	}

}
