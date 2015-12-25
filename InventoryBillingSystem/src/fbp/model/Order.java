package fbp.model;

public class Order {
	private int oid; // UserName at the login page
	private String pid; // password at the login page
	private String username; // UserName at the login page
	private String orderdetail; // password at the login page

	private String prodname;
	
	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	// Empty Default constructor
	public Order() {
	}

	// Fully parameterized constructor

	public Order(int oid, String pid, String username, String orderdetail,String prodname) {
		// TODO Auto-generated constructor stub
		this.oid = oid;
		this.pid = pid;
		this.username = username;
		this.orderdetail = orderdetail;
		this.prodname = prodname;
	}

	public Order(int oid, String pid, String username, String orderdetail) {
		// TODO Auto-generated constructor stub
		this.oid = oid;
		this.pid = pid;
		this.username = username;
		this.orderdetail = orderdetail;
	
	}

	
	
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(String orderdetail) {
		this.orderdetail = orderdetail;
	}

}
