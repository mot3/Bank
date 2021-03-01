package model;

public class Transaction {
	private int transaction;
	java.sql.Timestamp date;

	public java.sql.Timestamp getDate() {
		return date;
	}
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	public int getTransaction() {
		return transaction;
	}
	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}
	
	@Override
	public String toString() {
		return String.valueOf(transaction) + " : " + date;
	}
	

//	long millis=System.currentTimeMillis();
//	java.sql.Timestamp date=new java.sql.Timestamp(millis);  
}
