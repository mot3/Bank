package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import context.Database;
import context.Tables;
import entity.SqlFinderQuery;
import entity.SqlQuery;
import menu.Menu;
import model.User;
import sha.GFG;

public class UserRepository {

	protected final GenericRepository<User> genericRepository;
	
	protected UserRepository(Database database) {
		genericRepository = new GenericRepository<>(database);
	}
	
	protected Object selectUser(String query, List<Object> param) {
		return genericRepository.Select(new SqlFinderQuery<Object>(query, param, genericRepository.UserSqlRowMapper()));
	}
	
	protected Object selectCustomer(String query, List<Object> param) {
		return genericRepository.Select(new SqlFinderQuery<Object>(query, param, genericRepository.CustomerSqlRowMapper()));
	}
	
	protected Object selectAccountNumber(String query, List<Object> param) {
		return genericRepository.Select(new SqlFinderQuery<Object>(query, param, genericRepository.AccountSqlRowMapper()));
	}
	
	protected List<?> selectTransaction(String query, List<Object> param) {
		return genericRepository.listSelect(new SqlFinderQuery<Object>(query, param, genericRepository.TransActionSqlRowMapper()));
	}
	
	protected List<?> selectAccountNumbers(String query, List<Object> param) {
		return genericRepository.listSelect(new SqlFinderQuery<Object>(query, param, genericRepository.AccountSqlRowMapper()));
	}
	
	protected int executeUpdate(String query, List<Object> param) {
		return genericRepository.executeUpdate(new SqlQuery(query, param));
	}
	
	public static User getUser() {
		String username, password, fullname;

		Scanner scanner = Menu.scanner;
		
		do{
			System.out.print("User Name (required) : ");
			username = scanner.nextLine();
		}while(checkEmpty(username));
		// TODO check exist user
		
		do {
			System.out.print("Password (required) : ");
			password = scanner.nextLine();
		}while(checkEmpty(password));
		
		password = GFG.convert(password);
		
		System.out.print("FullName (Optional) : ");
		fullname = scanner.nextLine();
		
		return new User(username, password, fullname);
	}
	private static boolean checkEmpty(String check) {
		if(check.equals("")) {
			System.out.println("\n*** Please Enter required ***\n");
			return true;
		}
		
		return false;
	}
	
	public int ChangePassword(User user) {
		String query = "UPDATE " + Tables.TABLE_USER 
				+ " SET " + Tables.COL_USER_PASSWORD + "=?"
				+ " WHERE " + Tables.COL_USER_USERNAME + "=?";
		
		List<Object> param = new ArrayList<>();
		param.add(user.getPassword());
		param.add(user.getUsername());
		
		return executeUpdate(query, param);
	}
//		
//	public int updateUser(User user) {
//		String query = "UPDATE " + Tables.TABLE_USER 
//				+ " SET " + Tables.COL_USER_PASSWORD + "=?,"
//				+ Tables.COL_USER_FULLNAME + "=? "
//				+ "WHERE " + Tables.COL_USER_USERNAME + "=?";
//		
//		List<Object> param = new ArrayList<>();
//		param.add(user.getPassword());
//		param.add(user.getFullname()); 
//		param.add(user.getUsername());
//		
//		return genericRepository.executeUpdate(new SqlQuery(query, param));
//	}
//	
//	public int InsertCustomer(User user) {
//		return Insert(user, 3);
//	}
//	
//	protected int delete(String username, int typeId) {
//		String query = "DELETE FROM " + Tables.TABLE_USER
//    			+ " WHERE " + Tables.COL_USER_USERNAME + "=?"
//    			+ " AND " + Tables.COL_USER_TYPEID + "=?";
//		
//		List<Object> param = new ArrayList<>();
//		param.add(username);
//		param.add(typeId);
//		
//		return genericRepository.executeUpdate(new SqlQuery(query, param));
//	}
//	
//	protected Object GetByUsername(String username, int typeId) {
//		String query = "SELECT * FROM " + Tables.TABLE_USER
//				+ " WHERE " + Tables.COL_USER_USERNAME + "=?"
//				+ " AND " + Tables.COL_USER_TYPEID + "=?";
//		
//		List<Object> param = new ArrayList<>();
//		param.add(username);
//		param.add(typeId);
//		
//		return genericRepository.Select(new SqlFinderQuery<User>(query, param, genericRepository.UserSqlRowMapper()));
//	}
}
