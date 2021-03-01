package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Database;
import context.Tables;
import entity.*;
import model.Account;
import model.Customer;
import model.Transaction;
import model.User;

public class GenericRepository<T> {
	private final Database db;
	
	public GenericRepository(Database database) {
		db = database;
	}
	
	public int executeUpdate(SqlQuery query) {
		
		try	(Connection conn = db.Open();
			PreparedStatement pstmt = conn.prepareStatement(query.getQuery())) {
			
			int index = 0;
            for(Object param : query.getParams()){
                pstmt.setObject(++index, param);
            }
			
			return pstmt.executeUpdate();
	    		
		} catch(SQLException e) {
			db.printWarning(e, "GenericRepository.executeUpdate");
			return 0;
		} finally {
			db.close();
		}
	}
	
	public List<?> listSelect(SqlFinderQuery<Object> sqlFinderQuery) {
		List<Object> result = new ArrayList<>();;
		
		try	(Connection conn = db.Open();
				PreparedStatement pstmt = conn.prepareStatement(sqlFinderQuery.getQuery())) {
				
				int index = 0;
	            for(Object param : sqlFinderQuery.getParams()){
	                pstmt.setObject(++index, param);
	            }

	            try (ResultSet resultSet = pstmt.executeQuery()) {
	            	
	                while (resultSet.next()){
	                    result.add(sqlFinderQuery.getRowMapper().mapTo(resultSet));
	                }
	            }

			} catch(SQLException e) {
				db.printWarning(e, "GenericRepository.listSelect");
			} finally {
				db.close();
			}	
		return result;
	}
	
	public Object Select(SqlFinderQuery<Object> sqlFinderQuery) {
		List<?> select = listSelect(sqlFinderQuery);
		return (select.size() == 0) ? null : select.get(0);
	}
	
	public SqlRowMapper<User> UserSqlRowMapper() {
		return new SqlRowMapper<User>() {
			public User mapTo(ResultSet resultSet) {
				User user = new User();
				try {
					user.setUsername(resultSet.getString(Tables.COL_USER_USERNAME));
					user.setPassword(resultSet.getString(Tables.COL_USER_PASSWORD));
					user.setFullname(resultSet.getString(Tables.COL_USER_FULLNAME));
					user.setTypeId(resultSet.getInt(Tables.COL_TYPE_TYPEID));
					return user;
				} catch (SQLException e) {
					db.printWarning(e, "GenericRepository.UserSqlRowMapper");
					return null;
				}
				
			}
		};
	}
	
	public SqlRowMapper<Customer> CustomerSqlRowMapper() {
		return new SqlRowMapper<Customer>() {
			public Customer mapTo(ResultSet resultSet) {
				Customer customer = new Customer();
				try {
					customer.setUsername(resultSet.getString(Tables.COL_USER_USERNAME));
					customer.setPassword(resultSet.getString(Tables.COL_USER_PASSWORD));
					customer.setFullname(resultSet.getString(Tables.COL_USER_FULLNAME));
					customer.setTypeId(resultSet.getInt(Tables.COL_USER_TYPEID));
					customer.setPhoneNumber(resultSet.getString(Tables.COL_CUSTOMERINFO_PHONENUMBER));
					customer.setAddress(resultSet.getString(Tables.COL_CUSTOMERINFO_ADDRESS));
					return customer;
				} catch (SQLException e) {
					db.printWarning(e, "GenericRepository.UserSqlRowMapper");
					return null;
				}
				
			}
		};
	}
	
	public SqlRowMapper<Account> AccountSqlRowMapper() {
		return new SqlRowMapper<Account>() {
			public Account mapTo(ResultSet resultSet) {
				Account account = new Account();
				try {
					account.setAccountNumber(resultSet.getString(Tables.COL_ACCOUNTS_ACCOUNTNUMBER));
					account.setInventory(resultSet.getInt(Tables.COL_ACCOUNTS_INVENTORY));
					account.setAccountTypeId(resultSet.getInt(Tables.COL_ACCOUNTS_ACCOUNTTYPE));
					return account;
				} catch (SQLException e) {
					db.printWarning(e, "GenericRepository.UserSqlRowMapper");
					return null;
				}
				
			}
		};
	}
	public SqlRowMapper<Transaction> TransActionSqlRowMapper() {
		return new SqlRowMapper<Transaction>() {
			public Transaction mapTo(ResultSet resultSet) {
				Transaction transaction = new Transaction();
				try {
					transaction.setTransaction(resultSet.getInt(Tables.COL_TRANSACTIONS_TRANSACTION));
					transaction.setDate(resultSet.getTimestamp(Tables.COL_TRANSACTIONS_DATE));
					return transaction;
				} catch (SQLException e) {
					db.printWarning(e, "GenericRepository.UserSqlRowMapper");
					return null;
				}
				
			}
		};
	}

}