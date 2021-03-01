package context;

public class Tables {
	public static final String TABLE_USER="Users";

	public static final String COL_USER_USERNAME="username";
	public static final String COL_USER_PASSWORD="password";
	public static final String COL_USER_FULLNAME="fullname";
	public static final String COL_USER_TYPEID="typeId";
	
	
	public static final String TABLE_TYPE="Types";
	
	public static final String COL_TYPE_TYPEID="typeId";
	public static final String COL_TYPE_TYPE="type";
	
	
	public static final String TABLE_ACCOUNTS="Accounts";
	
	public static final String COL_ACCOUNTS_ACCOUNTNUMBER="accountNumber";
	public static final String COL_ACCOUNTS_USERNAME="username";
	public static final String COL_ACCOUNTS_INVENTORY="inventory";
	public static final String COL_ACCOUNTS_ACCOUNTTYPE="accountType";
	
	
	public static final String TABLE_CUSTOMERINFO="CustomerInfo";
	
	public static final String COL_CUSTOMERINFO_USERNAME="username";
	public static final String COL_CUSTOMERINFO_PHONENUMBER="phoneNumber";
	public static final String COL_CUSTOMERINFO_ADDRESS="address";
	
	
	public static final String TABLE_TRANSACTIONS="Transactions";
	
	public static final String COL_TRANSACTIONS_ACCOUNTNUMBER="accountNumber";
	public static final String COL_TRANSACTIONS_TRANSACTION="taction";
	public static final String COL_TRANSACTIONS_DATE="Date";
}









