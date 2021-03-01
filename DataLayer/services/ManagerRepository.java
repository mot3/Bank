package services;

import java.util.ArrayList;
import java.util.List;

import context.Database;
import context.Tables;
import model.User;

public class ManagerRepository extends EmployeeRepository {

	public ManagerRepository(Database database) {
		super(database);
	}
	
	public int InsertEmployee(User user) {
		return InsertUser(user, 2);
	}
	
	public int InsertManager(User user) {
		return InsertUser(user, 1);
	}
	
	public int DeleteEmployee(String username) {
		return DeleteUser(username, 2);
	}
	
	public int DeleteManager(String username) {
		return DeleteUser(username, 1);
	}

	public Object GetAllByUsername(String username) {
		String query = "SELECT * FROM " + Tables.TABLE_USER
				+ " WHERE " + Tables.COL_USER_USERNAME + "=?";
		
		List<Object> param = new ArrayList<>();
		param.add(username);
		
		return selectUser(query, param);
	}
}
