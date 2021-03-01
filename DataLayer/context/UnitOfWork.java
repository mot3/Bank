package context;

import services.*;

public class UnitOfWork implements AutoCloseable {
	private Database db;
	
	public UnitOfWork() {
		db = new Database();
	}

	private UserRepository userRepository;
	
	public ManagerRepository ManagerRepository() {
		
		if(userRepository == null) {
			userRepository = new  ManagerRepository(db);
		}
		
		return (ManagerRepository) userRepository;
	}
	
	public EmployeeRepository EmployeeRepository() {
		
		if(userRepository == null) {
			userRepository = new  EmployeeRepository(db);
		}
		
		return (EmployeeRepository) userRepository;
	}
	
	public CustomerRepository CustomerRepository() {
		
		if(userRepository == null) {
			userRepository = new CustomerRepository(db);
		}
		
		return (CustomerRepository) userRepository;
	}

	public void close() {
		userRepository = null;
		db.close();
		db = null;
	}
}
