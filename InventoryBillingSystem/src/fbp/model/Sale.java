package fbp.model;

public class Sale {
	private int sid;
	private int oid;
	private String salesamount;
	// Empty Default constructor
		public Sale() {
		}

		// Fully parameterized constructor
		
		
		public Sale(int sid, int oid,String salesamount ) {
			// TODO Auto-generated constructor stub
			this.sid = sid;
			this.oid = oid;
			this.salesamount = salesamount;
			
		}

		public int getSid() {
			return sid;
		}

		public void setSid(int sid) {
			this.sid = sid;
		}

		public int getOid() {
			return oid;
		}

		public void setOid(int oid) {
			this.oid = oid;
		}

		public String getSalesamount() {
			return salesamount;
		}

		public void setSalesamount(String salesamount) {
			this.salesamount = salesamount;
		}

}
