package menu;

import java.util.Scanner;
import context.UnitOfWork;
import model.User;
import services.ManagerRepository;
import sha.GFG;
import show.CustomerShow;
import show.EmployeeShow;
import show.ManagerShow;
import show.UserShow;

public class Menu {
	
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {		
		Menu menu = new Menu();
		menu.menuBar();
	}
	
	
	private void menuBar() {
        int selected = -1;
        while (selected != 0) {
            System.out.println("------------***------------");
            System.out.println("1.Login\n"
//            		+ "2.\n"
					+ "0.exit");
            
            String check = scanner.nextLine();
            selected = check_int(check);
            
        	switch (selected) {
	    		case 1:
	    			login();
	    			break;
    		}
		}
	}
	
	private void login() {
        System.out.println("------------Login------------");
		System.out.print("User name : ");
		String username = scanner.nextLine();
		
		User user = null;
		
		try(UnitOfWork db = new UnitOfWork()) {
			ManagerRepository manager = db.ManagerRepository();
			user = (User) manager.GetAllByUsername(username);
		}
		
		if(user != null) {
			System.out.print("password : ");
			String password = scanner.nextLine();
			
			password = GFG.convert(password);
			
			if(password.equals(user.getPassword())) {
				UserShow userShow;
				
				
				switch (user.getTypeId()) {
					case 1:
						userShow = new ManagerShow();

						userShow.show(user);
						break;
					
					case 2:
						userShow = new EmployeeShow();
						
						userShow.show(user);
						break;
						
					case 3:
						userShow = new CustomerShow();
						
						userShow.show(user);
						break;
				}
				
			}
			else {
				PrintWarning("Wrong Password");
			}
		}
		else {
			PrintWarning("Wrong User Name");
		}
	}
	
	protected int check_int(String check) {
		try {
			return Integer.parseInt(check);
		} catch (Exception e) {
			PrintWarning("Please Enter Numbers in the Menu");
			return -1;
		}
	}
	
	protected void PrintWarning(String warning) {
		System.out.println("\n*** " + warning + " ***\n");
	}
}


















