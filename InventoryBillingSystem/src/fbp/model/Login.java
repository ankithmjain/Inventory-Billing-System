package fbp.model;

public class Login {
	private String username; //UserName at the login page
	private String password; //password at the login page
	
	//Empty Default constructor
	public Login() {
	}
	
	//Fully parameterized constructor
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
//Getter  Setter methods for each attribute of the class
	
	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}


