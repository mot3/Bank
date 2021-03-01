package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {
	private String accountNumber;
	private int inventory;
	private int accountTypeId;
	private String accountType;
	
	public String getAccountType() {
		return accountType;
	}

	private void setAccountType(int accountTypeId) {
		switch (accountTypeId) {
		case 1:
			this.accountType = "sarmaye_kotahModat";
			break;
		case 2:
			this.accountType = "sarmaye_bolandModat";
			break;
		case 3:
			this.accountType = "gharz_pasandaz";
			break;
		case 4:
			this.accountType = "gharz_jari";
			break;
		}
	}
	
	public static void AccountTypesPrint() {
		System.out.println("\n1 : Sarmaye kotahModat\n"
				+ "2 : Sarmaye bolandModat\n"
				+ "3 : Gharz pasandaz\n"
				+ "4 : Gharz jari");
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
		setAccountType(accountTypeId);
	}
	
	private List<Transaction> transactions;
	
	public Account(int inventory, int type) {
		int n = 100000000 + new Random().nextInt(999999999);
		this.accountNumber = String.valueOf(n);
		this.inventory = inventory;
		this.accountTypeId = type;
		transactions = new ArrayList<>();
	}

	public String ToStringtransactions() {
		int index = 1;
		String str = "";
		for(Transaction transaction: transactions) {
			str += index++ + " - " + transaction.toString() + "\n";
		}
		return str;		
	}
	
	public void replaceTransaction(List<Transaction> transaction) {
		transactions = transaction;
	}
	
	@Override
	public String toString() {
		return accountNumber + " : " + inventory + " : " + accountType;
	}
	
	public Account() {
		transactions = new ArrayList<>();
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
}
