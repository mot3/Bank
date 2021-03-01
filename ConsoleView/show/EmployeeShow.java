package show;

import java.util.List;

import com.sun.accessibility.internal.resources.accessibility;

import context.UnitOfWork;
import model.*;
import services.CustomerRepository;
import services.EmployeeRepository;

public class EmployeeShow extends CustomerShow {
	public void show(User user) {
		try(UnitOfWork db = new UnitOfWork()) {
			EmployeeRepository employeeRepository = db.EmployeeRepository();
	        int selected = -1;
	        while (selected != 0) {
	            System.out.println("------------Employee Menu------------");
	            System.out.println("Hi " + user.getFullname() + " (Employee)");
	            System.out.println(show);
	            System.out.println("1.Customer\n"
	//            		+ "2."
						+ "0.exit");
	
	            String check = scanner.nextLine();
	            selected = check_int(check);
	            
					
	            if(selected < 100) {
	            	EmployeeProcessOption(selected, user, employeeRepository);
	            }
	            else {
	            	processOption(selected, user, employeeRepository);
	            }
	        }
		}
	}
	
	private void EmployeeProcessOption(int selected, User user, EmployeeRepository employee) {
		switch (selected) {
			case 1:
				Customer(employee);
				break;
		}
	}
	
	protected void Customer(EmployeeRepository employee) {
		int selected = -1;
        while (selected != 0) {
            System.out.println("------------Customer------------");
            System.out.println("1.Customer Menu\n"
            		+ "2.Add Customer\n"
					+ "0.exit");

            String check = scanner.nextLine();
            selected = check_int(check);
            
            switch (selected) {
				case 1:
					CustomerMenu(employee);
					break;
				case 2:
					addCustomer(employee);
					break;
            }
		}
	}
	
	private void CustomerMenu(EmployeeRepository employee) {
		String username = getUsername();
		
		Customer customer = employee.GetCustomerByUsername(username);
		
		if(customer != null) {
			int selected = -1;
		loop:	while (selected != 0) {
	            System.out.println("------------Customer menu------------");
	            System.out.println(customer.getUsername() + " : " + customer.getFullname());
	            System.out.println("1.Add AccountNumbers\n"
	            		+ "2.Show AccountNumbers\n"
	            		+ "3.remove AccountNumbers\n"
	            		+ "4.Show Customer\n"
	            		+ "5.Accounts\n"
						+ "6.Income Money\n"
	            		+ "7.Remove Customer\n"
						+ "0.exit");
	
	            String check = scanner.nextLine();
	            selected = check_int(check);
	            
	            switch (selected) {
					case 1:
						customer.addAccountNumber(addAccountNumber(employee, customer.getUsername()));
						break;
					case 2:
						ShowAccountNumber(customer.ToStringAccountNumbers());
						break;
					case 3:
						ShowAccountNumber(customer.ToStringAccountNumbers());
						customer.replaceAccounts(removeAccountNumber(employee, customer.getAccounts()));
						break;
					case 4:
						ProfileShow(customer);
						break;
					case 5:
						ShowAccountNumber(customer.ToStringAccountNumbers());
						Account(employee, customer.getAccounts());
						break;
					case 6:
						ShowAccountNumber(customer.ToStringAccountNumbers());
						IncomeMoney(employee, customer.getAccounts());
						break;
					case 7:
						if(removeCustomer(employee, customer.getUsername()))
								break loop;
						break;
	            }
			}
		}
		else {
			PrintWarning("Username Wrong");
		}
	}
	
	private List<Account> removeAccountNumber(EmployeeRepository employee, List<Account> accounts) {
		List<Object> list = getAccount(accounts);
			
		if(list.get(0) != null) {
			int check = employee.removeAccountNumber(((model.Account) list.get(0)).getAccountNumber());
			
			executeCheck(check, 1);
			
			if(check != 0)
				accounts.remove((Integer) list.get(1) - 1);
		}
		
		return accounts;
	}
	
	private Account addAccountNumber(EmployeeRepository employee, String username) {
		int inventory = -1;
		int AccountType = -1;
		Account account = new Account();
		
		do {
			String inventorystr = getString("Inventory : ");
			inventory = check_int(inventorystr);
		}while(inventory == -1);		
		do {
			Account.AccountTypesPrint();
			String AccountTypestr = getString("Account Type : ");
			AccountType = check_int(AccountTypestr);
		}while(inventory == -1);
		
		if(AccountType <= 4 && AccountType >= 1) {
			account = new Account(inventory, AccountType);
			
			int check = employee.addAccountNumber(username, account);
			
			executeCheck(check, 1);
		}
		
		return account;
	}
	
	private void IncomeMoney(EmployeeRepository employe, List<Account> accounts) {
		List<Object> list = getAccount(accounts);
		Account account = (model.Account) list.get(0);
		
		if(account != null) {
			int money = getCast();
			
			if(money != -1) {
				int newMoney = account.getInventory() + money;
				
				account.setInventory(newMoney);
				
				int check = employe.UpdateMoney(account, money);
				
				executeCheck(check, 1);
			}
		}else {
			PrintWarning("AccountNumber Wrong");
		}
	}
	
	private void addCustomer(EmployeeRepository employee) {
		Customer customer = CustomerRepository.getCustomer();
		int check = employee.InsertCustomer(customer);

		executeCheck(check, 2);
	}
	
	private boolean removeCustomer(EmployeeRepository employee, String username) {
		if(getString("Are you sure (y,n) : ").equals("y")) {
			int check = employee.DeleteCustomer(username);
			
			executeCheck(check, 1);
			return true;
		}
		return false;
	}
}














