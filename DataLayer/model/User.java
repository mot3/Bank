package model;

public class User {
	private String fullname;
	private String username;
	private String password;
	private String type;
	private int typeId;

	public User(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
	public User(User user) {
		this(user.username, user.password, user.fullname);
	}
	
	public User() {
		this("", "", "");
	}
	
	@Override
	public String toString() {
		return "\nFull name = " + getFullname()
				+ "\nUsername = " + username 
				+ "\nType = " + type + "\n";
	}
	
	public String getFullname() {
		return (fullname == null ? "" : fullname);
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	private void setType(int typeId) {
		switch (typeId) {
			case 1:
				this.type = "Manager";
				break;
			case 2:
				this.type = "Employee";
				break;
			case 3:
				this.type = "Customer";
				break;
		}
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
		setType(typeId);
	}
}
