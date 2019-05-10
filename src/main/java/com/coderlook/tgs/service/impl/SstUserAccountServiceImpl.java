package com.coderlook.tgs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.coderlook.tgs.common.ServiceRequestHandler;
import com.coderlook.tgs.constants.TgsConstants;
import com.coderlook.tgs.dto.SstUserAccount;
import com.coderlook.tgs.dto.TargetConfirmationHistory;
import com.coderlook.tgs.repository.SstUserAccountRepository;
import com.coderlook.tgs.repository.TargetConfirmationHistoryRepository;
import com.coderlook.tgs.service.SstUserAccountService;
/**
 * 
 * @author Gouranga
 *
 */

@Service
public class SstUserAccountServiceImpl implements SstUserAccountService{

	@Autowired
	private SstUserAccountRepository sstUserAccountRepository;
	
	@Autowired
	private TargetConfirmationHistoryRepository targetConfirmationHistoryRepository;

	private ServiceRequestHandler srh;

	/**
	 * Saving App Settings
	 * 
	 * @param sstUserAccount
	 * @return
	 */
	@Override
	public ServiceRequestHandler saveSstUserAccount(SstUserAccount sstUserAccount) {
		try {
			sstUserAccount = sstUserAccountRepository.save(sstUserAccount);
			srh = new ServiceRequestHandler(sstUserAccount, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Updating and soft delete
	 * 
	 * @param sstUserAccount
	 * @return
	 */
	@Override
	public ServiceRequestHandler updateSstUserAccount(SstUserAccount sstUserAccount) {
		try {
			if (sstUserAccount.getId() > 0) {
				SstUserAccount sstUserAccountExist = sstUserAccountRepository.findById(sstUserAccount.getId()).get();

				sstUserAccountExist.setCreateDate(sstUserAccount.getCreateDate());
				sstUserAccountExist.setUpdateBy(sstUserAccount.getUpdateBy());
				sstUserAccountExist.setUpdateDate(sstUserAccount.getUpdateDate());
				sstUserAccountExist.setCreateBy(sstUserAccount.getCreateBy());
				sstUserAccountExist.setStatus(sstUserAccount.getStatus());
				sstUserAccount = sstUserAccountRepository.save(sstUserAccountExist);
				srh = new ServiceRequestHandler(sstUserAccount, HttpStatus.OK.toString(),
						TgsConstants.SUCCESS_STATUS);
			}
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Soft Delete
	 * 
	 * @param id
	 * @param firstName
	 * @return
	 */
	@Override
	public ServiceRequestHandler softDeleteSstUserAccount(int id) {
		try {
			SstUserAccount sstUserAccount = sstUserAccountRepository.findById(id).get();
			sstUserAccount.setStatus(TgsConstants.INACTIVE);
			srh = new ServiceRequestHandler(null, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * updateStatus
	 * 
	 * @param id
	 * @param firstName
	 * @return
	 */
	@Override
	public ServiceRequestHandler updateStatus(int id, String status) {
		try {
			SstUserAccount sstUserAccount = sstUserAccountRepository.findById(id).get();
			sstUserAccount.setStatus(status);
			srh = new ServiceRequestHandler(null, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Fetching data by [primary key]
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstUserAccountByProperty(int id) {
		try {
			if (id != 0) {
				SstUserAccount sstUserAccountExist = sstUserAccountRepository.findById(id).get();
				srh = new ServiceRequestHandler(sstUserAccountExist, HttpStatus.OK.toString(),
						TgsConstants.SUCCESS_STATUS);
			}
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Fetching data by [primary key]
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstUserAccountByUserId(int userId) {
		try {
			List<SstUserAccount> sstUserAccountList = sstUserAccountRepository.findBySstUserIdAndStatus(userId,
					TgsConstants.ACTIVE);
			srh = new ServiceRequestHandler(sstUserAccountList, HttpStatus.OK.toString(),
					TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Fetching data by status
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstUserAccountByStatus(String status) {
		try {
			List<SstUserAccount> sstUserAccountList = sstUserAccountRepository.findByStatus(status);
			srh = new ServiceRequestHandler(sstUserAccountList, HttpStatus.OK.toString(),
					TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}


	/**
	 * Fetching data by loginId
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchActiveSstUserAccountByLoginIdAndUserId(String loginId, int userId) {
		try {
			SstUserAccount sstUserAccount = sstUserAccountRepository
					.findBySstAccountLoginIdAndSstUserIdAndStatus(loginId, userId, TgsConstants.ACTIVE);
			srh = new ServiceRequestHandler(sstUserAccount, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Fetching data by loginId
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstUserAccountByLoginId(String loginId) {
		try {
			SstUserAccount sstUserAccount = sstUserAccountRepository.findBySstAccountLoginId(loginId);
			srh = new ServiceRequestHandler(sstUserAccount, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}
	
	
	@Override
	public SstUserAccount fetchActiveSstUserAccountByLoginId(String loginId) {
		SstUserAccount sstUserAccount = null;
		try {
			List<SstUserAccount> sstUserAccounts = sstUserAccountRepository.findBySstAccountLoginIdAndStatus(loginId,
					TgsConstants.ACTIVE);
			if (sstUserAccounts != null && sstUserAccounts.size() == 1) {
				sstUserAccount = sstUserAccounts.get(0);
			} else if (sstUserAccounts != null && sstUserAccounts.size() > 1) {
				// through error multiple active account with same login id
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sstUserAccount;
	}
	
	@Override
	public List<SstUserAccount> fetchActiveSstUserAccountListByLoginId(String loginId) {
		return sstUserAccountRepository.findBySstAccountLoginIdAndStatus(loginId, TgsConstants.ACTIVE);
	}
	@Override
	@Transactional
	public void softDeleteSstUserAccountByLoginId(String loginId) {
		try {
			List<SstUserAccount> sstUserAccounts = sstUserAccountRepository.findBySstAccountLoginIdAndStatus(loginId, TgsConstants.ACTIVE);
			for(SstUserAccount account:sstUserAccounts) {
				account.setStatus(TgsConstants.INACTIVE);
				sstUserAccountRepository.save(account);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<TargetConfirmationHistory> findTargetConfirmationHistoryByInputMonth(String inputMonth) {
		return targetConfirmationHistoryRepository.findByInputMonth(inputMonth);
	}

}
