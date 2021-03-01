package show;

import menu.Menu;
import model.User;
import services.UserRepository;
import sha.GFG;

public abstract class UserShow extends Menu {
	public abstract void show(User user);
	
	static final String show="100.Profile"
//					+ "2.Management"
					;
	
	protected void processOption(int selected, User user, UserRepository userRepository) {
		switch (selected) {
			case 100:
//				ProfileShow(user.getUsername(), userRepository);
				Profile(userRepository, user);
				break;
		}
	}
	
	private void Profile(UserRepository userRepository, User user) {
		int selected = -1;
        while (selected != 0) {
            System.out.println("------------Profile------------");
            System.out.println("1.Show Profile\n"
					+ "2.Change Password\n"
//            		+ "2.Edit Profile\n"
					+ "0.exit");

            String check = scanner.nextLine();
            selected = check_int(check);
            
            switch (selected) {
				case 1:
					ProfileShow(user);
					break;
				case 2:
					ChangePassword(userRepository, user);
					break;
            }
		}
	}
	
	private void ChangePassword(UserRepository userRepository, User user) {
		String password = getString("Password : ");
		password = GFG.convert(password);
		
		if(password.equals(user.getPassword())) {
			String newPassword = getString("New Password : ");
			if(newPassword.equals(getString("Again New Password : "))) {
				newPassword = GFG.convert(newPassword);
				user.setPassword(newPassword);
				int check = userRepository.ChangePassword(user);
				
				executeCheck(check, 1);
			}
			else {
				PrintWarning("Wrong");
			}
		}
		else {
			PrintWarning("Wrong Password");
		}
	}
	
	protected String getString(String get) {
		String str;
		do{
			System.out.print(get);
			str = scanner.nextLine();
		}while(str.equals(""));
		
		return str;
	}
	
	protected String getUsername() {
		return getString("User Name : ");
	}
	
	protected void ProfileShow(User user) {
		System.out.println(user);
	}
	
	protected void executeCheck(int check, int size) {
		if(check == size)
			System.out.println("\n*** Success ***\n");
		else
			System.out.println("\n*** Denied ***\n");
	}
}
