package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import context.Database;
import context.Tables;
import menu.Menu;
import model.Account;
import model.Customer;
import model.Transaction;
import model.User;

public class CustomerRepository extends UserRepository {

	public CustomerRepository(Database database) {
		super(database);
	}
	
	public static Customer getCustomer() {
		String phoneNumber, address;

		Scanner scanner = Menu.scanner;
		
		User user = UserRepository.getUser();
		
		do {
			System.out.print("Phone Number (Optional) : ");
			phoneNumber = scanner.nextLine();
		}while(!phoneNumber.equals("") && check_int(phoneNumber));
		
		System.out.print("Address (Optional) : ");
		address = scanner.nextLine();

		return new Customer(user, phoneNumber, address);
	}
	
	private static boolean check_int(String check) {
		try {
			 Integer.parseInt(check);
			 return false;
		} catch (Exception e) {
			System.out.println("\n*** Please Enter Phone Number ***\n");
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<Account> GetAccountsByUsername(String username) {
		String query = "SELECT " + Tables.COL_ACCOUNTS_ACCOUNTNUMBER + ","
				+ Tables.COL_ACCOUNTS_INVENTORY + ","
				+ Tables.COL_ACCOUNTS_ACCOUNTTYPE
				+ " FROM " + Tables.TABLE_ACCOUNTS
				+ " WHERE " + Tables.COL_ACCOUNTS_USERNAME + " = ?";
		
		List<Object> param = new ArrayList<>();
		param.add(username);
		
		
		return (List<Account>) selectAccountNumbers(query, param);
	}
	
	public Account GetAccountByAccountNumber(String accountNumber) {
		String query = "SELECT " + Tables.COL_ACCOUNTS_ACCOUNTNUMBER + ","
				+ Tables.COL_ACCOUNTS_INVENTORY + ","
				+ Tables.COL_ACCOUNTS_ACCOUNTTYPE
				+ " FROM " + Tables.TABLE_ACCOUNTS
				+ " WHERE " + Tables.COL_ACCOUNTS_ACCOUNTNUMBER + " = ?";
		
		List<Object> param = new ArrayList<>();
		param.add(accountNumber);
		
		
		return (Account) selectAccountNumber(query, param);
	}
	
	public Customer GetCustomerByUsername(String username) {
		Customer customer = null;
		{
			String query = "SELECT Users.username, password, fullname, typeId," + 
					"phoneNumber, address " + 
					"FROM Users " + 
					"INNER JOIN CustomerInfo " + 
					"ON Users.username = CustomerInfo.username " +
					"WHERE Users.username = ? " + 
					"AND typeId = ?";
			
			List<Object> param = new ArrayList<>();
			param.add(username);
			param.add(3);
			
			customer = (Customer) selectCustomer(query, param);
		}
		
		if(customer != null) {
			List<Account> accounts = GetAccountsByUsername(username);
			
			for(Account account: accounts) {
				customer.addAccountNumber(account);
			}
		}
		
		return customer;
	}
	
	public List<Transaction> getTransaction(String accountNumber) {
		String query = "SELECT " + Tables.COL_TRANSACTIONS_TRANSACTION + ","
				+ Tables.COL_TRANSACTIONS_DATE
				+ " FROM " + Tables.TABLE_TRANSACTIONS
				+ " WHERE " + Tables.COL_TRANSACTIONS_ACCOUNTNUMBER + " = ?"
				+ " ORDER BY Date DESC LIMIT 5";
		
		List<Object> param = new ArrayList<>();
		param.add(accountNumber);
		
		
		return (List<Transaction>) selectTransaction(query, param);
	}
	
	private int transaction(int money, String accountNumber) {
		String query = "INSERT INTO " + Tables.TABLE_TRANSACTIONS + "("
				+ Tables.COL_TRANSACTIONS_ACCOUNTNUMBER + ","
				+ Tables.COL_TRANSACTIONS_TRANSACTION + ","
				+ Tables.COL_TRANSACTIONS_DATE
				+ ") VALUES (?, ?, ?)";
		
		List<Object> param = new ArrayList<>();
		param.add(accountNumber); 
		param.add(money);
		param.add(new Timestamp(now()));
		
		return executeUpdate(query, param);
	}
	
	private long now() {
		return System.currentTimeMillis(); 
	}
	
	public int UpdateMoney(Account account, int money) {
		String query = "UPDATE " + Tables.TABLE_ACCOUNTS
				+ " SET " + Tables.COL_ACCOUNTS_INVENTORY + "=? "
				+ "WHERE " + Tables.COL_ACCOUNTS_ACCOUNTNUMBER + "=?";
		
		List<Object> param = new ArrayList<>();
		param.add(account.getInventory());
		param.add(account.getAccountNumber()); 
		
		int check = executeUpdate(query, param);
		
		check = transaction(money, account.getAccountNumber());
		
		return check;
	}
	
}
