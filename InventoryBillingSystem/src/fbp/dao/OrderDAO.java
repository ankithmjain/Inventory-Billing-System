package fbp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;






import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

import fbp.model.Order;

public class OrderDAO {
	

	//Connection object
		private Connection connection;
		//Database connection ers
	    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	    private String username = "fpuser";
	    private String password = "510";

	    //Method to create a order model to database
	    
		public Order create(Order order) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to insert a order record to the order table
			
			// Put the query to Insert into DAO database
			//Query to add user record in the customer table
			Statement stmt = null;
			try{
				//int oid=order.getOid();
				String pid=order.getPid();
				String username=order.getUsername();
				String orderdetail =order.getOrderdetail();
				//Query to add a user in the customer table
				System.out.println("adding sutomer into the db table...");
				stmt = connection.createStatement();
				String query = "INSERT INTO ajain_order "
						+ " (pid,username,orderdetail) VALUES ('"+pid+"','"+username+"','"+orderdetail+"');";
				System.out.println(query);
		//	int i =	 stmt.executeUpdate(query);
			int i=stmt.executeUpdate(query);
	        if(i>0)
	        {
	              System.out.println("success Order Created");
	              JOptionPane.showMessageDialog(null,"Order Created","Success",JOptionPane.PLAIN_MESSAGE);
	        }
	              else
	        {
	             System.out.println("stuck somewhere NO order");

	        }
				
			
			
			
			}
				 catch(SQLException e){
			        	order = null;
			            System.out.println("Error Creating order : " + e);
			        }
			
			
			
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			//Return the bank object that was inserted with the id field set.
			return order;
		}
		// Method to get the view all orders list similar to viewProdDAO

		public ObservableList<Order> showorder(Order vieworder) {
			// TODO Auto-generated method stub
			//Get a connection
			try {
	            System.out.println("Connecting to DB");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("DB Connection Successful");
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to View Product records from the product table
			Statement stmt = null;
			ObservableList<Order> vieworders = FXCollections.observableArrayList();
			//FXCollections.observableArrayList();
			try{
			System.out.println("Query to get order data to screen ");
			
            stmt = connection.createStatement();
//            String sql = "SELECT OID,PID,username,orderdetail FROM ajain_order;";
//            if(!LoginDAO.loggedinUser.equals("admin")){
//             sql = "SELECT OID,PID,username,orderdetail FROM ajain_order WHERE username = '"+LoginDAO.loggedinUser+"';";
//            }
            
            
        String sql =  "Select ajain_order.OID,ajain_order.PID,ajain_order.username,ajain_order.orderdetail,ajain_product.ProdName from ajain_order,ajain_product where   ajain_order.pid = ajain_product.pid;";
         if(!LoginDAO.loggedinUser.equals("admin")){
          //sql = "SELECT OID,PID,username,orderdetail FROM ajain_order WHERE username = '"+LoginDAO.loggedinUser+"';";
          
         sql =  "Select ajain_order.OID,ajain_order.PID,ajain_order.username,ajain_order.orderdetail,ajain_product.ProdName from ajain_order,ajain_product where  "
          + "username = '"+LoginDAO.loggedinUser+"' and ajain_order.pid = ajain_product.pid" 
          		+ " ; ";
          }            
            System.out.println(sql); // to see the query
            ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
            while(rs.next()){ 
            	            
                        	 Order vp = new Order();
                        	 vp.setOid(rs.getInt("OID"));
                        	 vp.setPid(rs.getString("PID"));
                        	 vp.setUsername(rs.getString("username"));
                        	 vp.setOrderdetail(rs.getString("orderdetail"));
                        	 vp.setProdname(rs.getString("ProdName"));
                        	 System.out.println("Inserting Data to list one by one  "+vp.getOid()+vp.getProdname()+vp.getPid());
                             vieworders.add(vp);
            }
            rs.close();
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
			System.out.println("Goodbye!");
			return vieworders;
				
		}	

// method to show only particular customer order
// Method to get the view all orders list similar to viewProdDAO

		public ObservableList<Order> showeachorder(Order vieworder) {
			// TODO Auto-generated method stub
			//Get a connection
			try {
	            System.out.println("Connecting to DB");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("DB Connection Successful");
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to View single Order record from the order table
			Statement stmt = null;
			ObservableList<Order> vieworders = FXCollections.observableArrayList();
			//FXCollections.observableArrayList();
			try{
			System.out.println("Query to get order data to screen ");
            stmt = connection.createStatement();
			String username=vieworder.getUsername();
            String sql1 = "SELECT OID,PID,username,orderdetail FROM ajain_order WHERE username = '"+username+"';";
            System.out.println(sql1); // to see the query
            ResultSet rs = stmt.executeQuery(sql1);
         // Extract data from result set
            while(rs.next()){
                        	 Order vp = new Order();
                        	 vp.setOid(rs.getInt("OID"));
                        	 vp.setPid(rs.getString("PID"));
                        	 vp.setUsername(rs.getString("username"));
                        	 vp.setOrderdetail(rs.getString("orderdetail"));
			
                        	 System.out.println("Inserting Data to list one by one  "+vp.getOid()+"  "+vp.getPid());
                             vieworders.add(vp);
            }
            rs.close();
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
			System.out.println("Goodbye!");
			return vieworders;
				
		}	
		


}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			