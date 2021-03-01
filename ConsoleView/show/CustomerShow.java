package show;

import java.util.ArrayList;
import java.util.List;

import context.Tables;
import context.UnitOfWork;
import model.Account;
import model.Customer;
import model.Transaction;
import model.User;
import services.CustomerRepository;

public class CustomerShow extends UserShow {
	public void show(User user) {
		try(UnitOfWork db = new UnitOfWork()) {
			CustomerRepository customerRepository = db.CustomerRepository();
			Customer customer = customerRepository.GetCustomerByUsername(user.getUsername());
			
			int selected = -1;
			while (selected != 0) {
				System.out.println("------------Customer Menu------------");
				System.out.println("Hi " + user.getFullname() + " (Customer)");
				System.out.println(show);
				System.out.println("1.Show AccountNumbers\n"
							+ "2.Account\n"
							+ "0.exit");
				
				String check = scanner.nextLine();
				selected = check_int(check);
				
				if(selected < 100) {
					CustomerProcessOption(selected, customer, customerRepository);
				}
				else {
					processOption(selected, customer, customerRepository);
				}
			}
		}
	}
	
	
	private void CustomerProcessOption(int selected, Customer customer, CustomerRepository customerRepository) {
		switch (selected) {
			case 1:
				ShowAccountNumber(customer.ToStringAccountNumbers());
				break;
			case 2:
				ShowAccountNumber(customer.ToStringAccountNumbers());
				Account(customerRepository, customer.getAccounts());
				break;
		}
	}
	
	protected void Account(CustomerRepository customer, List<Account> accounts) {
		List<Object> list = getAccount(accounts);
		if(list.get(0) != null) {
			int selected = -1;
			while (selected != 0) {
				System.out.println("------------Account Menu------------");
				System.out.println("1.Get Inventory\n"
							+ "2.Get Money\n"
							+ "3.Transfer Money\n"
							+ "4.Get Transaction\n"
							+ "0.exit");
				
				String check = scanner.nextLine();
				selected = check_int(check);
				
				switch (selected) {
					case 1:
						getInventory((Account) list.get(0));
						break;
					case 2:
						GetMoney(customer, (Account) list.get(0));
						break;
					case 3:
						Transfer(customer, (Account) list.get(0));
						break;
					case 4:
						getTransaction(customer, (Account) list.get(0));
						break;
				}
			}
		}
	}
	
	private void getTransaction(CustomerRepository customer, Account account) {
		List<Transaction> transaction = customer.getTransaction(account.getAccountNumber());
		account.replaceTransaction(transaction);
		
		System.out.println(account.ToStringtransactions());
	}
	
	@SuppressWarnings("unused")
	private void Transfer(CustomerRepository customer, Account account) {
		int money = getCast();
		
		if (money != -1) {
			int newMoney = account.getInventory() - money;
			
			if(newMoney >= 0) {
				Account otherAccount = getAccountNumber(customer);
				
				if(otherAccount != null) {
					otherAccount.setInventory(otherAccount.getInventory() + money);
					int check = customer.UpdateMoney(otherAccount, money);

					account.setInventory(newMoney);
					check = customer.UpdateMoney(account, -money);
					
					executeCheck(check, 1);
				}
				else {
					PrintWarning("AccountNumber wrong");
				}
			}
			else {
				PrintWarning("not enogh");
			}
		}
	}
	
	protected Account getAccountNumber(CustomerRepository customer) {
		String otherAccountNumber = getString("AccountNumber : ");
		
		return customer.GetAccountByAccountNumber(otherAccountNumber);
	}
	
	private void GetMoney(CustomerRepository customer, Account account) {
		int money = getCast();
		
		if(money != -1) {
			int newMoney = account.getInventory() - money;
			
			if(newMoney >= 0) {
				account.setInventory(newMoney);
				
				int check = customer.UpdateMoney(account, -money);
				
				executeCheck(check, 1);
			}
			else {
				PrintWarning("not enogh");
			}
		}
	}
	
	protected int getCast() {
		String moneyStr = getString("How mush : ");
		return check_int(moneyStr);
	}
	
	private void getInventory(Account account) {
		System.out.println(account);
	}
	
	protected List<Object> getAccount(List<Account> accounts) {
		String numStr = getString("Number of AccountNumber : ");
		int number = check_int(numStr);
		

		Account account = null;
		if(number != -1 && number <= accounts.size()) {
			account = accounts.get(number - 1);
		}
		
		List<Object> list = new ArrayList<>();
		list.add(account);
		list.add(number);
		return list;
	}
	
	protected void ShowAccountNumber(String AccountNumbers) {
		System.out.print(AccountNumbers);
	}
	
	

}
