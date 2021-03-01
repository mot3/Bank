package show;

import context.UnitOfWork;
import model.User;
import services.ManagerRepository;
import services.UserRepository;

public class ManagerShow extends EmployeeShow {
	public void show(User user) {
		try(UnitOfWork db = new UnitOfWork()) {
			ManagerRepository managerRepository = db.ManagerRepository();
	        int selected = -1;
	        while (selected != 0) {
	            System.out.println("------------Manager Menu------------");
	            System.out.println("Hi " + user.getFullname() + " (Manager)");
	            System.out.println(show);
	            System.out.println("1.Manager\n"
	            		+ "2.Employee\n"
	            		+ "3.Customer\n"
						+ "0.exit");
	
	            String check = scanner.nextLine();
	            selected = check_int(check);
	            
	            
	            if(selected < 100) {
	            	managerProcessOption(selected, user, managerRepository);
	            }
	            else {
	            	processOption(selected, user, managerRepository);
	            }
            }
		}
	}
	
	private void managerProcessOption(int selected, User user, ManagerRepository manager) {
		switch (selected) {
			case 1:
				Manager(manager);
				break;
			case 2:
				Employee(manager);
				break;
			case 3:
				Customer(manager);
				break;
		}
	}
	
	private void Employee(ManagerRepository manager) {
		int selected = -1;
        while (selected != 0) {
            System.out.println("------------Employee------------");
            System.out.println("1.Add Employee\n"
            		+ "2.Remove Employee\n"
					+ "0.exit");

            String check = scanner.nextLine();
            selected = check_int(check);
            
            switch (selected) {
				case 1:
					addEmployee(manager);
					break;
				case 2:
					removeEmployee(manager);
					break;
            }
		}
	}
	
	private void addEmployee(ManagerRepository manager) {
		User user = UserRepository.getUser();
		int check = manager.InsertEmployee(user);

		executeCheck(check, 1);
	}
	
	private void Manager(ManagerRepository manager) {
		int selected = -1;
        while (selected != 0) {
            System.out.println("------------Manager------------");
            System.out.println("1.Add Manager\n"
            		+ "2.Remove Manager\n"
					+ "0.exit");

            String check = scanner.nextLine();
            selected = check_int(check);
            
            switch (selected) {
				case 1:
					addManager(manager);
					break;
				case 2:
					removeManager(manager);
					break;
            }
		}
	}
	
	private void addManager(ManagerRepository manager) {
		User user = UserRepository.getUser();
		int check = manager.InsertManager(user);

		executeCheck(check,1);
	}
	
	private void removeEmployee(ManagerRepository manager) {
		String username = getUsername();
		
		int check = manager.DeleteEmployee(username);
		
		executeCheck(check,1);
	}
	
	private void removeManager(ManagerRepository manager) {
		String username = getUsername();
		
		int check = manager.DeleteManager(username);
		
		executeCheck(check,1);
	}
}














