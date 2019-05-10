package com.coderlook.tgs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coderlook.tgs.common.ServiceRequestHandler;
import com.coderlook.tgs.constants.TgsConstants;
import com.coderlook.tgs.dto.SstAccount;
import com.coderlook.tgs.model.ActionModel;
import com.coderlook.tgs.repository.SstAccountRepository;
import com.coderlook.tgs.service.SstAccountService;

/**
 * 
 * @author Gouranga
 *
 */
@Service
public class SstAccountServiceImpl implements SstAccountService {

	@Autowired
	private SstAccountRepository sstAccountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Saving App Settings
	 * 
	 * @param sstAccount
	 * @return
	 */
	@Override
	public ServiceRequestHandler saveSstAccount(SstAccount sstAccount) {
		ServiceRequestHandler srh;
		try {
			sstAccount = sstAccountRepository.save(sstAccount);
			srh = new ServiceRequestHandler(sstAccount, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Updating and soft delete
	 * 
	 * @param sstAccount
	 * @return
	 */
	@Override
	public ServiceRequestHandler updateSstAccount(SstAccount sstAccount) {
		ServiceRequestHandler srh = null;
		try {
			if (!sstAccount.getLoginId().equals("")) {
				SstAccount sstAccountExist = sstAccountRepository.findById(sstAccount.getLoginId()).get();
				sstAccountExist.setGeoLocCode(sstAccount.getGeoLocCode());
				sstAccountExist.setGeoLocType(sstAccount.getGeoLocType());
				sstAccountExist.setLastPass1(sstAccount.getLastPass1());
				sstAccountExist.setLastPass2(sstAccount.getLastPass2());
				sstAccountExist.setLastPass3(sstAccount.getLastPass3());
				sstAccountExist.setLocked(sstAccount.getLocked());
				sstAccountExist.setPassword(sstAccount.getPassword());
				sstAccountExist.setReset(sstAccount.getReset());
				sstAccountExist.setSalt(sstAccount.getSalt());
				sstAccountExist.setUserType(sstAccount.getUserType());
				sstAccountExist.setStatus(sstAccount.getStatus());

				sstAccount = sstAccountRepository.save(sstAccountExist);
				srh = new ServiceRequestHandler(sstAccount, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			}
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
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
	public ServiceRequestHandler softDeleteSstAccount(String loginId) {
		ServiceRequestHandler srh;
		try {
			SstAccount sstAccount = sstAccountRepository.findById(loginId).get();
			sstAccount.setStatus(TgsConstants.INACTIVE);
			srh = new ServiceRequestHandler(null, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
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
	public SstAccount fetchSstAccountByProperty(String loginId) {
		SstAccount account = null;
		try {
			if (!loginId.equals("")) {
				account = sstAccountRepository.findById(loginId).get();
				/*
				 * srh = new ServiceRequestHandler(account, HttpStatus.OK.toString(),
				 * TgsConstants.SUCCESS_STATUS);
				 */
			}
		} catch (Exception e) {
			/*
			 * srh = new ServiceRequestHandler(null,
			 * HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			 */
			e.printStackTrace();
		}
		return account;
	}

	/**
	 * Fetching data by LocType And LocCode
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstAccountByLocTypeAndLocCode(String loctype, String locCode) {
		ServiceRequestHandler srh;
		try {
			System.out.println("Loc Type: " + loctype + " Loc Code: " + locCode);
			List<SstAccount> sstAccountList = sstAccountRepository.findByGeoLocTypeAndGeoLocCode(loctype, locCode);
			srh = new ServiceRequestHandler(sstAccountList, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			System.out.println("Result: " + sstAccountList.size());

		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	/**
	 * Fetching data by LocType And LocCode
	 * 
	 * @param property
	 * @return
	 */
	@Override
	public ServiceRequestHandler fetchSstAccountByLoginIdCount(String loginId) {
		ServiceRequestHandler srh;
		try {
			List<SstAccount> sstAccountList = sstAccountRepository.findByLoginIdStartingWith(loginId);

			srh = new ServiceRequestHandler(sstAccountList.size(), HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			System.out.println("Result: " + sstAccountList.size());

		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public ServiceRequestHandler fetchSstAccountByEmail(String email) {
		ServiceRequestHandler srh;
		try {
			SstAccount sstAccountList = sstAccountRepository.findByEmail(email);

			srh = new ServiceRequestHandler(sstAccountList, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			System.out.println("Result: " + sstAccountList);

		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public ServiceRequestHandler fetchSstAccountByUserType(short id) {
		ServiceRequestHandler srh;
		try {
			List<SstAccount> sstAccountList = sstAccountRepository.findByUserTypeId(id);

			srh = new ServiceRequestHandler(sstAccountList, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			System.out.println("Result: " + sstAccountList.size());

		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public List<String> fetchAllLoginId() {

		List<String> loginIdList = new ArrayList<String>();

		try {
			List<SstAccount> sstAccountList = sstAccountRepository.findByStatus(TgsConstants.ACTIVE);

			for (SstAccount sstAc : sstAccountList) {
				loginIdList.add(sstAc.getLoginId());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("size: " + loginIdList.size());
		return loginIdList;
	}

	@Override
	public ServiceRequestHandler fecthLoginIdByGeoCodeAndType(String code, String type) {
		ServiceRequestHandler srh;
		try {
			List<SstAccount> sstAccountList = sstAccountRepository.findByGeoLocCodeAndGeoLocTypeAndStatus(code, type, TgsConstants.ACTIVE);
			if (sstAccountList != null && sstAccountList.size() == 1) {
				srh = new ServiceRequestHandler(sstAccountList.get(0), HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			} else {
				srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			}

		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	@Transactional
	public boolean lockUnlockAccount(ActionModel actionModel) {
		boolean success = false;
		SstAccount sstAccount = sstAccountRepository.findById(actionModel.getAccount()).get();
		if (sstAccount != null) {
			sstAccount.setLocked(actionModel.getAction());
			sstAccount = sstAccountRepository.save(sstAccount);
			if (sstAccount.getLocked().equals(actionModel.getAction())) {
				success = true;
			}
		}
		return success;
	}

	@Transactional
	@Override
	public boolean resetPassword(ActionModel actionModel) {
		boolean success = false;
		SstAccount sstAccount = sstAccountRepository.findById(actionModel.getAccount()).get();
		if (sstAccount != null) {
			sstAccount.setPassword(passwordEncoder.encode(actionModel.getAction()));
			sstAccount.setReset("1");
			sstAccount = sstAccountRepository.save(sstAccount);
			success = true;
		}
		return success;
	}

	@Override
	public ServiceRequestHandler fetchAccountsByStatus(String status) {
		ServiceRequestHandler srh = null;
		try {
			if (status != null && !status.equals("")) {
				List<SstAccount> sstAccounts = sstAccountRepository.findByStatus(status);
				srh = new ServiceRequestHandler(sstAccounts, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			}
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public ServiceRequestHandler fetchAccountsByStatusAndUserType(String status, Short adminId) {
		ServiceRequestHandler srh = null;
		try {
			if (status != null && !status.equals("")) {
				List<SstAccount> sstAccounts = sstAccountRepository.findByStatusAndUserTypeId(status, adminId);
				srh = new ServiceRequestHandler(sstAccounts, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
			}
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public ServiceRequestHandler softDeleteAll() {
		ServiceRequestHandler srh;
		try {
			List<SstAccount> distList = (List<SstAccount>) sstAccountRepository.findAll();
			for (SstAccount dist : distList) {
				dist.setStatus(TgsConstants.INACTIVE);
				dist = sstAccountRepository.save(dist);
			}
			srh = new ServiceRequestHandler(null, HttpStatus.OK.toString(), TgsConstants.SUCCESS_STATUS);
		} catch (Exception e) {
			srh = new ServiceRequestHandler(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), TgsConstants.ERROR_STATUS);
			e.printStackTrace();
		}
		return srh;
	}

	@Override
	public SstAccount fetchSstAccountByLoginId(String loginId) {
		return sstAccountRepository.findByLoginId(loginId);
	}

	@Transactional
	@Override
	public boolean changePassword(String loginId, String oldPassword, String newPassword) {
		boolean success = false;
		SstAccount sstAccount = sstAccountRepository.findById(loginId).get();

		// SstAccount sstAccount =
		// sstAccountRepository.findByLoginIdAndPassword(loginId, oldPassword);

		System.out.println("new: " + sstAccount.toString());
		if (sstAccount != null) {
			// if(sstAccount.getPassword().equals(oldPassword)) {
			// sstAccount.setPassword(passwordEncoder.encode(actionModel.getAction()));
			sstAccount.setPassword(newPassword);
			sstAccount.setReset("1");
			sstAccount = sstAccountRepository.save(sstAccount);
			success = true;
			// }
		}
		return success;
	}

}
