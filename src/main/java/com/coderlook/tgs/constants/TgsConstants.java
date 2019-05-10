package com.coderlook.tgs.constants;

public class TgsConstants {
	// Response Status and Messages
	public static final String SUCCESS_STATUS = "SUCCESS";
	public static final String DUPLICATE_STATUS = "DUPLICATE";
	public static final String ERROR_STATUS = "ERROR";
	public static final String ERROR_MESSAGE_ACCOUNT_NOT_FOUND = "Error ! SST Account Not Found";
	public static final String PARSE_ERROR_STATUS = "PARSE ERROR";
	public static final String CONFLICT_STATUS = "CONFLICT";

	public static final String DRAFT = "100";
	public static final String ACTIVE = "200";
	public static final String INACTIVE = "400";
	public static final String RESEST_NO = "0";
	public static final String RESEST_YES = "1";
	public static final String LOCKED_NO = "0";
	public static final String LOCKED_YES = "1";

	public static final String ACTION_LOCK_UNLOCK = "LOCK_UNLOCK";
	public static final String ACTION_DISABLE_ENABLE = "DISABLE_ENABLE";
	public static final String ACTION_RESET_PASS = "RESET_PASS";

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// public static final Integer ACCEPTED_INT = 300;
	// public static final Integer CANCELLED_INT = 500;
	// public static final Short DEFAULT_IMG_SHORT = 600;

	// LOC TYPES
	public static final String ADMIN = "ADMIN";
	public static final String BRANCH = "BRANCH";
	public static final String ASM_ZONE = "ASM ZONE";
	public static final String TERRITORY = "TERRITORY";
	public static final String LOB = "LOB";
	public static final String BEAT = "BEAT";
	public static final String RETAILER = "RETAILER";
	public static final String SALESMAN = "SALESMAN";
	public static final String DISTRIBUTER = "DISTRIBUTER";
	public static final String PRIMARY_SALES = "PRIMARY SALES";
	public static final String SECONDARY_SALES = "SECONDARY SALES";
	public static final String ACCOUNT = "ACCOUNT";

	public static final Short ADMIN_ID = 1;
	public static final Short BRANCH_ID = 2;
	public static final Short ASM_ZONE_ID = 3;
	public static final Short TERRITORY_ID = 4;

	// DATA VALIDATION REGEX
	public static final String LOB_HEADING = "COST_SECTION_CODE,DESCRIPTION";
	public static final String BRANCH_HEADING = "CODE,NAME,BR_CODE,BR_NAME,COUNTRY_CODE,COUNTRY,REGION_CODE,REGION,STATE_CODE,STATE";
	public static final String ASM_ZONE_HEADING = "CODE,NAME,BRANCH";
	public static final String BEAT_HEADING = "CODE,NAME";
	public static final String DISTRIBUTOR_HEADING = "CODE,NAME";
	public static final String DIST_SALES_BEAD_RETAIL_HEADING = "DISTRIBUTOR,SALESMAN,BEAT,RETAILER,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY";
	public static final String RETAILER_HEADING = "CODE,NAME";
	public static final String SALESMAN_HEADING = "CODE,NAME";
	public static final String TERRITORY_HEADING = "CODE,NAME,ASM_ZONE";
	public static final String TERRITORY_DISTRIBUTOR_HEADING = "TERRITORY,DISTRIBUTOR,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY";
	public static final String SECONDARY_SALES_HEADING = "DISTRIBUTOR,RETAILER,SALESMAN,BEAT,INVOICE_NUMBER,INVOICE_DATE,SKU_ID,SKU_CODE,UOM,QUANTITY,FREE_QUANTITY,PTR,TAXABLE_AMOUNT,TOTAL_TAX,NET_AMOUNT,PROHIERCODE_LEVEL2,PROHIERNAME_LEVEL2";

	public static final String FROM_MAIL = "";
	public static final String FROM_PASSWORD = "";
	public static final String EMAIL_SUBJECT = "Forget Password";
	public static final String EMAIL_BODY = "Your password is ";

	// USER TYPES
	public static final String BM = "BM";
	public static final String ASM = "ASM";
	public static final String SO = "SO";

	// UI CONSTANTS
	public static final String PASSWORD_EXPIRATION_DURATION = "PASSWORD_EXPIRATION_DURATION";
	public static final String TARGET_CHANGE_TOLERANCE = "TARGET_CHANGE_TOLERANCE";
	public static final String TARGET_MODIFICATION_DURATION = "TARGET_MODIFICATION_DURATION";
	public static final String BM_INPUT_TOLERANCE = "BM_INPUT_TOLERANCE";
	
	public static final String MONTH_WITH_31_DAYS = "MONTH_WITH_31_DAYS";
	public static final String MONTH_WITH_30_DAYS = "MONTH_WITH_30_DAYS";
	public static final String MONTH_WITH_29_DAYS = "MONTH_WITH_29_DAYS";
	public static final String MONTH_WITH_28_DAYS = "MONTH_WITH_28_DAYS";

	// Roles for Auth
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_SSTG_USER = "ROLE_SSTG_USER";
	public static final String ROLE_BM = "ROLE_BM";
	public static final String ROLE_ASM = "ROLE_ASM";
	public static final String ROLE_SO = "ROLE_SO";
	public static final String ROLE_BUSINESS_USER = "ROLE_BUSINESS_USER";

	public static final int ID_ADMIN = 1;
	public static final int ID_BM = 2;
	public static final int ID_ASM = 3;
	public static final int ID_SO = 4;
	// public static final int ID_BUSINESS_USER=5;

	public static final String DEFAULT_PASS = "Password123";
	public static final long MAX_EXCEL_ROW_PER_SHEET = 65000;

	public static final String[] SALESMAN_COLUMN_ARRAY = {"Branch Code","Branch Name","ASM Zone Code","ASM Zone Name","Territory Code","Distributer Code","Distributer Name","Salesman Code","LOB Code","LOB Description","SST Volume System","SST Volume Modified","Rate","SST Value System","SST Value Modified"};
	public static final String[] SALESMAN_SHEET_ARRAY = {"Salesman List1","Salesman List2"};
}
