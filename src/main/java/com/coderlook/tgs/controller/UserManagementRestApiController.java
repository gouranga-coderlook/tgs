/**
 * 
 */
package com.coderlook.tgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderlook.tgs.constants.TgsConstants;
import com.coderlook.tgs.dto.SstUserAccount;
import com.coderlook.tgs.model.ActionModel;
import com.coderlook.tgs.service.SstAccountService;
import com.coderlook.tgs.service.SstUserAccountService;
import com.coderlook.tgs.service.SstUserService;

/**
 * 
 * @author Gouranga
 *
 */
@RestController
@RequestMapping(value = "/admin/user/rest/api")
public class UserManagementRestApiController {

	@Autowired
	SstUserService sstUserService;

	@Autowired
	SstAccountService sstAccountService;
	
	@Autowired
	SstUserAccountService sstUserAccountService;

	@RequestMapping(value = "/admin-accounts/all", method = RequestMethod.GET)
	public Object fecthAllBranches() {
		return sstAccountService.fetchAccountsByStatusAndUserType(TgsConstants.ACTIVE, TgsConstants.ADMIN_ID).getResponseObj();
	}

	@RequestMapping(value = "/employee-id/check-availability", method = RequestMethod.GET)
	public boolean checkEmployeeIdAvailability(@RequestParam String employeeId) {
		return sstUserService.checkEmployeeIdAvailability(employeeId);
	}
	
	@RequestMapping(value = "/account-details", method = RequestMethod.GET)
	public SstUserAccount fetchActiveUserAccountDetails(@RequestParam String accountId) {
		return sstUserAccountService.fetchActiveSstUserAccountByLoginId(accountId);
	}
	
	@RequestMapping(value = "/update-status", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean uploadStatus(@RequestBody ActionModel actionModel) {
		boolean status = false;
		if (actionModel != null && actionModel.getActionType() != null && actionModel.getAction() != null) {
			if (actionModel.getActionType().equals(TgsConstants.ACTION_LOCK_UNLOCK)) {
				status = sstAccountService.lockUnlockAccount(actionModel);
			} else if (actionModel.getActionType().equals(TgsConstants.ACTION_DISABLE_ENABLE)) {
				status = sstUserService.enableDisableUser(actionModel);
			}
		} else {
			return status;
		}

		return status;
	}

	@RequestMapping(value = "/reset-pass", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean resetPassword(@RequestBody ActionModel actionModel) {
		boolean status = false;
		System.out.println(actionModel);
		if (actionModel != null && actionModel.getActionType() != null && actionModel.getAction() != null) {
			if (actionModel.getActionType().equals(TgsConstants.ACTION_RESET_PASS)) {
				status = sstAccountService.resetPassword(actionModel);
			}
		} else {
			return status;
		}

		return status;
	}

}
