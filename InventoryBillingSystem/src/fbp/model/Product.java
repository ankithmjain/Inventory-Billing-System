package fbp.model;

public class Product {

	private String pid; //Primary column of the table
	private String prodname; //Name of the AddProduct
	private String prodcategory; //productcategory of the AddProduct
	
	//Empty Default constructor
	public Product() {
	}
	
	//Fully parameterized constructor
	public Product(String pid, String prodname, String prodcategory) {
		this.pid = pid;
		this.prodname = prodname;
		this.prodcategory = prodcategory;
	}

	//Getter / Setter methods for each attribute of the class
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProdName() {
		return prodname;
	}

	public void setProdName(String prodname) {
		this.prodname = prodname;
	}

	public String getProdCategory() {
		return prodcategory;
	}

	public void setProdCategory(String prodcategory) {
		this.prodcategory = prodcategory;
	}
}