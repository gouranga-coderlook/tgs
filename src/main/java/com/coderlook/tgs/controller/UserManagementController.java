package com.coderlook.tgs.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderlook.tgs.common.ServiceRequestHandler;
import com.coderlook.tgs.dto.SstUser;
import com.coderlook.tgs.dto.SstUserAccount;
import com.coderlook.tgs.dto.TargetConfirmationHistory;
import com.coderlook.tgs.dto.UserType;
import com.coderlook.tgs.service.SstUserAccountService;
import com.coderlook.tgs.dto.AsmZone;
import com.coderlook.tgs.dto.SstDataBmInputStatus;
import com.coderlook.tgs.dto.Territory;
import com.coderlook.tgs.model.SearchForUnfreeze;
import com.coderlook.tgs.model.SearchUserModel;
import com.coderlook.tgs.model.SearchUserNotMappedModel;
import com.coderlook.tgs.model.SearchUserReturn;
import com.coderlook.tgs.model.SstUserAccountModel;
import com.coderlook.tgs.model.SstUserModel;
import com.coderlook.tgs.model.SstUserOneAccountModel;
import com.coderlook.tgs.service.AsmZoneService;
import com.coderlook.tgs.service.BmInputService;
import com.coderlook.tgs.service.BranchService;
import com.coderlook.tgs.service.SstUserService;
import com.coderlook.tgs.service.TerritoryService;
import com.coderlook.tgs.service.UserTypeService;
import com.coderlook.tgs.util.DateUtil;
import com.coderlook.tgs.util.TgsConstants;

/**
 * 
 * @author Gouranga
 *
 */

@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

	@Autowired
	UserTypeService userTypeService;
	@Autowired
	BranchService branchService;
	@Autowired
	AsmZoneService asmZoneService;
	@Autowired
	TerritoryService territoryService;
	@Autowired
	SstUserService sstUserService;
	@Autowired
	BmInputService bmInputService;
	@Autowired
	SstUserAccountService sstUserAccountService;
	
	// = new ServiceRequestHandler();
	SstUserAccountModel sstUserAccountModel = new SstUserAccountModel();
	private static final String ADD_USER_TEMPLATE = "secure-pages/admin/add-user";
	private static final String ADD_USER_REDIRECT_URL = "redirect:add";
	private static final String EDIT_USER_TEMPLATE = "secure-pages/admin/edit-user";
	private static final String EDIT_USER_REDIRECT_URL = "redirect:edit";
	private static final String SEARCH_USER_TEMPLATE = "secure-pages/admin/search-user";
	private static final String SEARCH_USER_REDIRECT_URL = "redirect:search";
	private static final String SEARCH_UNMAPPED_USER_TEMPLATE = "secure-pages/admin/unassigned-user";
	private static final String SEARCH_UNMAPPED_USER_REDIRECT_URL = "redirect:unassigned-user";
	private static final String SEARCH_UNFREEZE_SST_DATA_TEMPLATE = "secure-pages/admin/unfreeze-sst-data";
	private static final String SEARCH_UNFREEZE_SST_DATA_REDIRECT_URL = "redirect:unfreeze-sst-data";
	private static final String BM_INPUT_STATUS_TEMPLATE = "secure-pages/admin/bm-input-status";
	private static final String BM_INPUT_STATUS_REDIRECT_URL = "redirect:bm-input-status";
	private static final String TARGET_CONF_STATUS_TEMPLATE = "secure-pages/admin/target-confirmation-status";

	@GetMapping("/add")
	public String viewCreateUserForm(Model model, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "msg", required = false) String msg) {

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("user-role-management", true);
		breadcrumb.put("add-user", true);
		model.addAttribute("breadcrumb", breadcrumb);
		if (msg != null) {
			model.addAttribute("msg", msg);
		} else {
			model.addAttribute("msg", "");
		}
		if (status != null) {
			model.addAttribute("status", status);
		} else {
			model.addAttribute("status", "");
		}
		List<UserType> userTypeModels = new ArrayList<>();
		List<Branch> branchs = new ArrayList<>();
		List<AsmZone> asmZones = new ArrayList<>();
		List<Territory> territories = new ArrayList<>();

		SstUserModel sstUserModel = new SstUserModel();
		try {

			userTypeModels = (List<UserType>) userTypeService.fetchAllUserType().getResponseObj();
			branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
			asmZones = (List<AsmZone>) asmZoneService.fetchAsmZoneByStatus(TgsConstants.ACTIVE).getResponseObj();
			territories = (List<Territory>) territoryService.fetchTerritoryByStatus(TgsConstants.ACTIVE).getResponseObj();

		} catch (NullPointerException ne) {

		}
		model.addAttribute("userTypeModels", userTypeModels);
		model.addAttribute("branchs", branchs);
		model.addAttribute("asmZones", asmZones);
		model.addAttribute("territories", territories);
		model.addAttribute("sstUserModel", sstUserModel);
		model.addAttribute("pageTitle", "Create user");

		return ADD_USER_TEMPLATE;
	}

	@PostMapping("/add")
	public String addNewUser(@ModelAttribute SstUserModel sstUserModel, HttpSession httpSession) {
		SstUser loggedInUser = (SstUser) httpSession.getAttribute("loggedInUser");
		Date newDate = new Date();
		sstUserModel.setCreateDate(newDate);
		sstUserModel.setUpdateDate(newDate);
		sstUserModel.setCreateBy(String.valueOf(loggedInUser.getId()));
		sstUserModel.setUpdateBy(String.valueOf(loggedInUser.getId()));

		System.out.println(sstUserModel);
		ServiceRequestHandler serviceRequestHandler = null;
		try {
			serviceRequestHandler = sstUserService.saveSstUser(sstUserModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String returnString = "redirect:";
		System.out.println("Status :" + serviceRequestHandler.getStatus());
		if (serviceRequestHandler.getStatus().equalsIgnoreCase(HttpStatus.NOT_FOUND.toString())) {
			returnString = returnString + "add?status=add-Fail&msg=" + serviceRequestHandler.getMessage();
		} else if (serviceRequestHandler.getStatus().equalsIgnoreCase(HttpStatus.INTERNAL_SERVER_ERROR.toString())) {
			returnString = returnString + "add?status=add-Fail&msg=" + serviceRequestHandler.getMessage();
		} else if (serviceRequestHandler != null && serviceRequestHandler.getResponseObj() != null) {
			try {
				SstUserAccount sstUserAccount = (SstUserAccount) serviceRequestHandler.getResponseObj();
				if (sstUserAccount != null && sstUserAccount.getSstUser() != null) {
					returnString = returnString + Integer.toString(sstUserAccount.getSstUser().getId()).concat("/edit");
					if (sstUserAccount.getSstAccount() != null) {
						returnString = returnString + "?account=" + sstUserAccount.getSstAccount().getLoginId() + "&msg=add-success";
					}
				} else {
					returnString = returnString + "add";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			returnString = returnString + "add";
		}

		return returnString;
	}

	@GetMapping("/{id}/edit")
	public String viewEditUserForm(@PathVariable Integer id, @RequestParam(value = "account", required = false) String account, @RequestParam(value = "msg", required = false) String msg, Model model) {

		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("edit-user", true);
		model.addAttribute("breadcrumb", breadcrumb);
		if (msg != null) {
			model.addAttribute("msg", msg);
		} else {
			model.addAttribute("msg", "");
		}

		List<UserType> userTypeModels = new ArrayList<>();
		List<Branch> branchs = new ArrayList<>();
		List<AsmZone> asmZones = new ArrayList<>();
		List<Territory> territories = new ArrayList<>();

		SstUserOneAccountModel userAccount = new SstUserOneAccountModel();
		try {
			userAccount = sstUserService.fetchSstUserByIdAndLoginId(id, account);
			userTypeModels = (List<UserType>) userTypeService.fetchAllUserType().getResponseObj();
			branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
			switch (userAccount.getSstUserAccount().getSstAccount().getUserType().getId()) {
			case TgsConstants.ID_BM:
				userAccount.setBranch(userAccount.getSstUserAccount().getSstAccount().getGeoLocCode());
				userAccount.setAsmZone("");
				userAccount.setTerritory("");
				break;
			case TgsConstants.ID_ASM:
				userAccount.setAsmZone(userAccount.getSstUserAccount().getSstAccount().getGeoLocCode());
				userAccount.setBranch(((AsmZone) asmZoneService.fetchAsmZoneByCode(userAccount.getAsmZone()).getResponseObj()).getBranch().getCode());
				userAccount.setTerritory("");
				asmZones = (List<AsmZone>) asmZoneService.fetchAsmZoneByBranch(userAccount.getBranch());
				break;
			case TgsConstants.ID_SO:
				userAccount.setTerritory(userAccount.getSstUserAccount().getSstAccount().getGeoLocCode());
				userAccount.setAsmZone(((Territory) territoryService.fetchTerritoryByCode(userAccount.getTerritory()).getResponseObj()).getAsmZone().getCode());
				userAccount.setBranch(((AsmZone) asmZoneService.fetchAsmZoneByCode(userAccount.getAsmZone()).getResponseObj()).getBranch().getCode());
				asmZones = (List<AsmZone>) asmZoneService.fetchAsmZoneByBranch(userAccount.getBranch());
				territories = (List<Territory>) territoryService.fetchTerritoryByAsmZoneCode(userAccount.getAsmZone());
				break;
			default:
				break;
			}

		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		userAccount.setCurrentLoginId(account);
		model.addAttribute("pageTitle", "Edit user");
		model.addAttribute("userTypeModels", userTypeModels);
		model.addAttribute("branchs", branchs);
		model.addAttribute("asmZones", asmZones);
		model.addAttribute("territories", territories);
		model.addAttribute("userAccount", userAccount);

		return EDIT_USER_TEMPLATE;
	}

	/**
	 * Not in user
	 * 
	 * @param id
	 * @param account
	 * @param model
	 * @return
	 */
	@Deprecated
	@GetMapping("/{id}/edit/multiaccount")
	public String viewEditUserFormWithMultipleAccount(@PathVariable Integer id, @RequestParam("account") String account, Model model) {

		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("edit-user", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<UserType> userTypeModels = new ArrayList<>();
		List<Branch> branchs = new ArrayList<>();
		List<AsmZone> asmZones = new ArrayList<>();
		List<Territory> territories = new ArrayList<>();

		SstUserModel sstUserModel = new SstUserModel();
		try {

			userTypeModels = (List<UserType>) userTypeService.fetchAllUserType().getResponseObj();
			branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
			asmZones = (List<AsmZone>) asmZoneService.fetchAsmZoneByStatus(TgsConstants.ACTIVE).getResponseObj();
			territories = (List<Territory>) territoryService.fetchTerritoryByStatus(TgsConstants.ACTIVE).getResponseObj();

			sstUserAccountModel = (SstUserAccountModel) sstUserService.fetchSstUserByProperty(57).getResponseObj();

			sstUserModel.setFirstName(sstUserAccountModel.getSstUser().getFirstName());
			sstUserModel.setMiddleName(sstUserAccountModel.getSstUser().getMiddleName());
			sstUserModel.setLastName(sstUserAccountModel.getSstUser().getLastName());
			sstUserModel.setEmployeeId(sstUserAccountModel.getSstUser().getEmployeeId());
			sstUserModel.setUserName(sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getLoginId());

			if (sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocType().equals(TgsConstants.BRANCH)) {
				System.out.println("code: " + sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocCode());
				sstUserModel.setBranchCode(sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocCode().trim());
			} else if (sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocType().equals(TgsConstants.ASM_ZONE)) {
				sstUserModel.setAsmZoneCode(sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocCode());

			} else if (sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocType().equals(TgsConstants.TERRITORY)) {
				sstUserModel.setTerritoryCode(sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getGeoLocCode());

			}

			sstUserModel.setUserTypeId((short) sstUserAccountModel.getSstUserAccountList().get(0).getSstAccount().getUserType().getId());

		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}

		model.addAttribute("pageTitle", "Edit user");
		model.addAttribute("userTypeModels", userTypeModels);
		model.addAttribute("branchs", branchs);
		model.addAttribute("asmZones", asmZones);
		model.addAttribute("territories", territories);
		model.addAttribute("sstUserModel", sstUserModel);

		return EDIT_USER_TEMPLATE;
	}

	@PostMapping("/edit")
	public String editUserSublitForm(@ModelAttribute SstUserOneAccountModel userAccount, HttpServletRequest request, HttpSession httpSession) {
		SstUser loggedInUser = (SstUser) httpSession.getAttribute("loggedInUser");
		SstUserAccount sstUserAccount = new SstUserAccount();
		String EDIT_PAGE_TEMPLATE = "redirect:/admin/user/" + userAccount.getSstUser().getId() + "/edit?account=" + userAccount.getCurrentLoginId();
		System.out.println(request.getRequestURI());
		try {
			if (userAccount != null) {
				sstUserAccount = sstUserService.updateSstUserAccount(userAccount, loggedInUser.getId());
				EDIT_PAGE_TEMPLATE = "redirect:/admin/user/" + sstUserAccount.getSstUser().getId() + "/edit?account=" + sstUserAccount.getSstAccount().getLoginId();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EDIT_PAGE_TEMPLATE;
	}

	@GetMapping("/search")
	public String viewSearchUser(Model model) {

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("user-role-management", true);
		breadcrumb.put("search-user", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<UserType> userTypeModels = new ArrayList<>();
		List<Branch> branchs = new ArrayList<>();
		try {
			userTypeModels = (List<UserType>) userTypeService.fetchAllUserType().getResponseObj();
			branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}

		List<SearchUserReturn> userList = new ArrayList<>();

		model.addAttribute("searchUserModel", new SearchUserModel());
		model.addAttribute("userTypeModels", userTypeModels);
		model.addAttribute("branchs", branchs);
		model.addAttribute("userList", userList);
		model.addAttribute("pageTitle", "Search user");

		return SEARCH_USER_TEMPLATE;
	}

	@PostMapping("/search")
	public String searchUserSublitForm(@ModelAttribute SearchUserModel searchUserModel, Model model) {

		System.out.println("data: " + searchUserModel.toString());
		ServiceRequestHandler serviceRequestHandler = new ServiceRequestHandler();
		serviceRequestHandler = sstUserService.searchUser(searchUserModel);

		List<SearchUserReturn> userList = (List<SearchUserReturn>) serviceRequestHandler.getResponseObj();
		for (SearchUserReturn searchUserReturn : userList) {
			System.out.println("----------: " + searchUserReturn);
		}

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("user-role-management", true);
		breadcrumb.put("search-user", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<UserType> userTypeModels = new ArrayList<>();
		List<Branch> branchs = new ArrayList<>();
		try {
			userTypeModels = (List<UserType>) userTypeService.fetchAllUserType().getResponseObj();
			branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		model.addAttribute("userList", userList);
		model.addAttribute("searchUserModel", new SearchUserModel());
		model.addAttribute("userTypeModels", userTypeModels);
		model.addAttribute("branchs", branchs);
		model.addAttribute("pageTitle", "Search user");

		return SEARCH_USER_TEMPLATE;

	}

	@GetMapping("/unassigned-user")
	public String viewUnmappedUserPage(Model model) {

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("user-role-management", true);
		breadcrumb.put("unassigned-user", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<SstUser> userList = new ArrayList<>();

		model.addAttribute("notMappedModel", new SearchUserNotMappedModel());
		model.addAttribute("userList", userList);
		model.addAttribute("pageTitle", "Unmapped user");

		return SEARCH_UNMAPPED_USER_TEMPLATE;
	}

	@PostMapping("/unassigned-user")
	public String searchUnmappedUserSublitForm(@ModelAttribute SearchUserNotMappedModel notMappedModel, Model model) {

		List<SstUser> userList = new ArrayList<>();
		ServiceRequestHandler serviceRequestHandler = new ServiceRequestHandler();

		System.out.println("last name in controller: " + notMappedModel.getLastName());

		serviceRequestHandler = sstUserService.searchUserNotMapped(notMappedModel);

		userList = (List<SstUser>) serviceRequestHandler.getResponseObj();

		System.out.println("list size----------------------->" + userList.size());

		for (SstUser sstUser : userList) {
			System.out.println("f name: " + sstUser.getFirstName());

		}

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("user-role-management", true);
		breadcrumb.put("unassigned-user", true);
		model.addAttribute("breadcrumb", breadcrumb);

		model.addAttribute("userList", userList);
		model.addAttribute("notMappedModel", notMappedModel);
		model.addAttribute("pageTitle", "Unmapped user");

		return SEARCH_UNMAPPED_USER_TEMPLATE;

	}

	@GetMapping("/unfreeze-sst-data")
	public String viewUnfreezeSSTDataPage(Model model) {

		System.out.println("sst data");

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("unfreeze-sst-data", true);
		model.addAttribute("breadcrumb", breadcrumb);

		SearchForUnfreeze searchForUnfreeze = new SearchForUnfreeze();

		List<Branch> branchs = new ArrayList<>();
		branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
		model.addAttribute("searchForUnfreeze", searchForUnfreeze);
		model.addAttribute("branchs", branchs);
		model.addAttribute("pageTitle", "Unfreeze SST Data");

		return SEARCH_UNFREEZE_SST_DATA_TEMPLATE;
	}

	@PostMapping("/unfreeze-sst-data")
	public String searchUnfreezeSSTDataSublitForm(@ModelAttribute SearchForUnfreeze searchForUnfreeze, Model model) {

		System.out.println("searchForUnfreeze: " + searchForUnfreeze.toString());

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("unfreeze-sst-data", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<Branch> branchs = new ArrayList<>();
		branchs = (List<Branch>) branchService.fetchBranchByStatus(TgsConstants.ACTIVE).getResponseObj();
		model.addAttribute("searchForUnfreeze", searchForUnfreeze);
		model.addAttribute("branchs", branchs);
		model.addAttribute("pageTitle", "Unfreeze SST Data");

		return SEARCH_UNFREEZE_SST_DATA_TEMPLATE;

	}

	@GetMapping("/bm-input-status")
	public String allBmInputStatus(Model model) {

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("bm-input-status", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<SstDataBmInputStatus> sstDataBmInputStatus = new ArrayList<>();
		sstDataBmInputStatus = bmInputService.fetchAllBmInputStatus();
		model.addAttribute("sstDataBmInputStatus", sstDataBmInputStatus);

		model.addAttribute("pageTitle", "BM Input Status");

		// get next month
		String monthYear = "";
		try {
			monthYear = DateUtil.getNextMonthFromDateDispaly(new Date());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("nextMonth", monthYear);

		return BM_INPUT_STATUS_TEMPLATE;
	}
	
	
	@GetMapping("/target-conf-status")
	public String targetConfirmationStatus(Model model) throws ParseException {

		// use for breadcrumb
		Map<String, Boolean> breadcrumb = new HashMap<String, Boolean>();
		breadcrumb.put("home", true);
		breadcrumb.put("target-conf-status", true);
		model.addAttribute("breadcrumb", breadcrumb);

		List<TargetConfirmationHistory> targetConfirmationHistories = new ArrayList<>();
		targetConfirmationHistories = sstUserAccountService.findTargetConfirmationHistoryByInputMonth(DateUtil.getNextMonthFromDate(new Date()));
		model.addAttribute("targetConfirmation", targetConfirmationHistories);

		model.addAttribute("pageTitle", "Target Confirmation History");

		// get next month
		String monthYear = "";
		try {
			monthYear = DateUtil.getNextMonthFromDateDispaly(new Date());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("nextMonth", monthYear);

		return TARGET_CONF_STATUS_TEMPLATE;
	}
	
}
