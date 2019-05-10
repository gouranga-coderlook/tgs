package com.coderlook.tgs.service;

import java.util.List;

import com.coderlook.tgs.common.ServiceRequestHandler;
import com.coderlook.tgs.dto.SstAccount;
import com.coderlook.tgs.model.ActionModel;

/**
 * @author Gouranga
 *
 */
public interface SstAccountService {

	ServiceRequestHandler saveSstAccount(SstAccount sstAccount);

	ServiceRequestHandler updateSstAccount(SstAccount sstAccount);

	ServiceRequestHandler softDeleteSstAccount(String loginId);

	SstAccount fetchSstAccountByProperty(String loginId);

	ServiceRequestHandler fetchSstAccountByLocTypeAndLocCode(String loctype, String locCode);

	ServiceRequestHandler fetchSstAccountByLoginIdCount(String loginId);

	ServiceRequestHandler fetchSstAccountByEmail(String email);

	ServiceRequestHandler fetchSstAccountByUserType(short id);

	List<String> fetchAllLoginId();

	ServiceRequestHandler fecthLoginIdByGeoCodeAndType(String code, String type);

	boolean lockUnlockAccount(ActionModel actionModel);

	boolean resetPassword(ActionModel actionModel);

	ServiceRequestHandler fetchAccountsByStatus(String status);

	ServiceRequestHandler fetchAccountsByStatusAndUserType(String status, Short adminId);

	ServiceRequestHandler softDeleteAll();

	SstAccount fetchSstAccountByLoginId(String loginId);

	boolean changePassword(String loginId, String oldPassword, String newPassword);

}
