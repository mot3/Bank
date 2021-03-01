package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	private String phoneNumber;
	private String address;
	private List<Account> accounts;
	
	public Customer(User user, String phoneNumber, String address) {
		super(user);
		this.phoneNumber = phoneNumber;
		this.address = address;
		accounts = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "Phone Number = " + phoneNumber
				+ "\nAddress = " + address;
	}
	
	public String ToStringAccountNumbers() {
		int index = 1;
		String str = "";
		for(Account account: accounts) {
			str += index++ + " - " + account.toString() + "\n";
		}
		return str;		
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void replaceAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Customer(User user) {
		super(user);
		accounts = new ArrayList<>();
	}
	
	public Customer() {
		accounts = new ArrayList<>();
	}
	
	public void addAccountNumber(Account account) {
		accounts.add(account);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
