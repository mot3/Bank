package services;

import java.util.ArrayList;
import java.util.List;
import context.Database;
import context.Tables;
import model.Account;
import model.Customer;
import model.User;

public class EmployeeRepository extends CustomerRepository {

	public EmployeeRepository(Database database) {
		super(database);
	}
	
	protected int InsertUser(User user, int typeId) {
		String query = "INSERT INTO " + Tables.TABLE_USER
				+ " VALUES (?,?,?,?)";
		
		List<Object> param = new ArrayList<>(); 
		param.add(user.getUsername());
		param.add(user.getPassword()); 
		param.add(user.getFullname());
		param.add(typeId);
		
		return executeUpdate(query, param);
	}
	
	public int InsertCustomer(Customer customer) {
		int userCheck = InsertUser(customer, 3);
		
		String query = "INSERT INTO " + Tables.TABLE_CUSTOMERINFO
				+ " VALUES (?,?,?)";
		
		List<Object> param = new ArrayList<>(); 
		param.add(customer.getUsername());
		param.add(customer.getPhoneNumber()); 
		param.add(customer.getAddress());
		
		userCheck += executeUpdate(query, param);
		
		return userCheck;
	}
	
	protected int DeleteUser(String username, int typeId) {
		String query = "DELETE FROM " + Tables.TABLE_USER
    			+ " WHERE " + Tables.COL_USER_USERNAME + "=?"
    			+ " AND " + Tables.COL_USER_TYPEID + "=?";
		
		List<Object> param = new ArrayList<>();
		param.add(username);
		param.add(typeId);
		
		return executeUpdate(query, param);
	}
	
	public int DeleteCustomer(String username) {
		return DeleteUser(username, 3);
	}
	
	public int addAccountNumber(String username, Account account) {		
		String query = "INSERT INTO " + Tables.TABLE_ACCOUNTS
				+ " VALUES (?,?,?,?)";
		
		List<Object> param = new ArrayList<>(); 
		param.add(account.getAccountNumber());
		param.add(username);
		param.add(account.getInventory());
		param.add(account.getAccountTypeId());
		
		return executeUpdate(query, param);
	}
	
	public int removeAccountNumber(String accountNumber) {		
		String query = "DELETE FROM " + Tables.TABLE_ACCOUNTS
				+ " WHERE " + Tables.COL_ACCOUNTS_ACCOUNTNUMBER + "=?";
		
		List<Object> param = new ArrayList<>(); 
		param.add(accountNumber);
		
		return executeUpdate(query, param);
	}
}










