package fbp.model;

public class Customer extends Login {
	
	private String address;
	private String email;
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String password, String address, String email) {
		super(username, password);
		this.address = address;
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}