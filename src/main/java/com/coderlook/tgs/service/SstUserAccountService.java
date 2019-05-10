/**
 * 
 */
package com.coderlook.tgs.service;

import java.util.List;

import com.coderlook.tgs.common.ServiceRequestHandler;
import com.coderlook.tgs.dto.SstUserAccount;
import com.coderlook.tgs.dto.TargetConfirmationHistory;

/**
 * @author Gouranga
 *
 */
public interface SstUserAccountService {

	List<TargetConfirmationHistory> findTargetConfirmationHistoryByInputMonth(String inputMonth);

	void softDeleteSstUserAccountByLoginId(String loginId);

	ServiceRequestHandler fetchSstUserAccountByUserId(int userId);

	ServiceRequestHandler updateStatus(int id, String status);

	ServiceRequestHandler softDeleteSstUserAccount(int id);

	List<SstUserAccount> fetchActiveSstUserAccountListByLoginId(String loginId);

	SstUserAccount fetchActiveSstUserAccountByLoginId(String loginId);

	ServiceRequestHandler fetchSstUserAccountByLoginId(String loginId);

	ServiceRequestHandler fetchActiveSstUserAccountByLoginIdAndUserId(String loginId, int userId);

	ServiceRequestHandler fetchSstUserAccountByStatus(String status);

	ServiceRequestHandler fetchSstUserAccountByProperty(int id);

	ServiceRequestHandler updateSstUserAccount(SstUserAccount sstUserAccount);

	ServiceRequestHandler saveSstUserAccount(SstUserAccount sstUserAccount);

}
