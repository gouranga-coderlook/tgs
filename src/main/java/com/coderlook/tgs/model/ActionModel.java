/**
 * 
 */
package com.coderlook.tgs.model;

/**
 * 
 * @author Gouranga
 *
 */

public class ActionModel {

	private String userId;
	private String account;
	private String actionType;
	private String action;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "ActionModel [userId=" + userId + ", account=" + account + ", actionType="
				+ actionType + ", action="	+ action + "]";
	}
	
}
