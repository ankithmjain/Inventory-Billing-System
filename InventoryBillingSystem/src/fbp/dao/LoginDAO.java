package fbp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.JOptionPane;

import fbp.main.LoginMain;
import fbp.main.WelcomeMain;
import fbp.model.Customer;
import fbp.model.Login;

public class LoginDAO {
	// to make new screen popup create a stage object
		private Stage ps = new Stage();
		public static String loggedinUser = "";

		// Connection object
	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to verify Login credentials from Login model in database
	public Login checkuser(Login login) {
		// Get a connection
		try {
			System.out.println("trying db connection");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successfull to Database");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// validate the user details present in database
		//Query to check user record in the user table
		Statement stmt = null;
		try{
			String username=login.getUserName();
			String password=login.getPassword();
			//Query to validate a user in the Login table
			System.out.println("Validating User into the table...");
			
			stmt = connection.createStatement();
		String query = "SELECT * FROM "
				+ "ajain_login where username='"+username+"'"
				+ "and password='"+password+"';";
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);
		System.out.println("RS Fetch");
		if(!rs.next()){
			JOptionPane.showMessageDialog(null, "Invalid "
					+ "Input Try again","Error",JOptionPane.ERROR_MESSAGE);
		

		return null;
		}
		//while(rs.next()){
			String un = rs.getString("username");
	         String ps1 = rs.getString("password");
	         System.out.println(", First: " + un);
	         System.out.println(", Last: " + ps1);
	         JOptionPane.showMessageDialog(null, "Valid "
						+ "User","Success",JOptionPane.PLAIN_MESSAGE);
				loggedinUser = new String(username);
				System.out.println("Username of login is   "+loggedinUser);
	        System.out.println(" i will populate admin panel"); 
	    	// Popping up welcome screen so created the object
	       	        //		     if(username = "admin" && password = 'admin' ){
		    // go to admin screen or go to customer screen
//		    	 }        
	      /* WelcomeMain wm1 = new WelcomeMain();
				
		     wm1.start(ps);*/
	     
		//}
		/*
		}*/
//		else if(rs.next()){
//			JOptionPane.showMessageDialog(null, "Valid "
//					+ "User","Success",JOptionPane.PLAIN_MESSAGE);
//			loggedinUser= new String(username);
//			
//			// Popping up welcome screen so created the object
//	      WelcomeMain wm = new WelcomeMain();
////			//calling method by stage paramerter in WelcomeMain Object
//			wm.start(ps);
//		
//		}
		} catch(SQLException e){
        	login = null;
            System.out.println("Error Logging to Billing: " + e);
        }			
		
		
		//Close the connection to the database - Very important!!!
		try {
			System.out.println("Connection closing DB");
            connection.close();
            
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }finally {
            if (stmt != null) {
                try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }
		// Return the login object that was inserted with the id field set.
        }		return login;

        }

	public Customer adduser(Customer customer){
		// Get a connection
				try {
					System.out.println("trying db connection");
					connection = DriverManager.getConnection(url, username, password);
					System.out.println("Connection successfull to Database");
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Add the user details to database
				//Query to add user record in the customer table
				Statement stmt = null;
				try{
					String username=customer.getUserName();
					String password=customer.getPassword();
					String address=customer.getAddress();
					String email =customer.getEmail();
					//Query to add a user in the customer table
					System.out.println("adding new cutomer into the db table...");
					stmt = connection.createStatement();
					String query = "INSERT INTO ajain_login "
							+ " (username,password) VALUES ('"+username+"','"+password+"');";
					String query1 = "INSERT INTO ajain_customer "
							+ " (username,address,email) VALUES ('"+username+"','"+address+"', "
							+ "'"+email+"');";

					int i=stmt.executeUpdate(query);
			        if(i>0)
			        {
			              System.out.println("success new customer added");
			              
			        }
			              else
			        {
			            		
			            	  System.out.println("stuck somewhere");

			        }
				
					
					System.out.println(query);
					System.out.println(query1);
					// stmt.executeUpdate(query);
					 //stmt.executeUpdate(query1);
					 int j=stmt.executeUpdate(query1);
				        if(j>0)
				        {
				              System.out.println("success");
				              JOptionPane.showMessageDialog(null,"Congrats,Now Login Again","Success",JOptionPane.PLAIN_MESSAGE);
				        }
				              else
				        {
				            		
			
				            	  System.out.println("stuck somewhere");

				        }
					 
					 
					 
					
						} catch(SQLException e){
				        	customer = null;
				        	JOptionPane.showMessageDialog(null, "Invalid "
									+ "Input Try again","Error",JOptionPane.ERROR_MESSAGE);
				            // if user name already present put exception
			             System.out.println("Error Logging to Billing: " + e); 
				            
				            
						}			
						
						
						//Close the connection to the database - Very important!!!
						try {
				            connection.close();
				            connection = null;
				        } catch(SQLException e) {
				            System.out.println("Error closing connection: " + e);
				        }finally {
				            if (stmt != null) {
				                try {
									stmt.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				              }
			
	}
				return customer;
	
	
	
	}

	public Customer updateuser(Customer customer){
		System.out.println("I will update new edited details in DB ");

		// Get a connection
		try {
			System.out.println("trying db connection");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successfull to Database");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Add the edited user details to database
		//Query to add edited user record in the customer table
		Statement stmt = null;
		try{
			String username=customer.getUserName();
			String password=customer.getPassword();
			String address=customer.getAddress();
			String email =customer.getEmail();
			//Query to add a user in the customer table
			System.out.println("adding sutomer into the db table...");
			stmt = connection.createStatement();
//			String query = "UPDATE ajain_login "
//					+ " (username,password) VALUES ('"+username+"','"+password+"');";
			String query =	"UPDATE ajain_login SET password = '"+password+"' WHERE username = '"+username+"';";			
			String query1 = "UPDATE ajain_customer SET address = '"+address+"',email = '"+email+"' WHERE username = '"+username+"';";
			
			/*String query1 = "UPDATE ajain_customer "
					+ " (username,address,email) VALUES ('"+username+"','"+address+"', "
					+ "'"+email+"');";
			*/
			
			System.out.println(query);
			System.out.println(query1);
			 stmt.executeUpdate(query);
			// stmt.executeUpdate(query1);
			 int i=stmt.executeUpdate(query1);
		        if(i>0)
		        {
		              System.out.println("updated the new customer data query success ");
		              JOptionPane.showMessageDialog(null,"Updation","Success",JOptionPane.PLAIN_MESSAGE);
		                     
		        }
		              else
		        {
		             System.out.println("stuck somewhere");

		         	JOptionPane.showMessageDialog(null, "Invalid "
							+ "Input Try again","Error",JOptionPane.ERROR_MESSAGE);
		             
		             
		        }
			 
			 
		} catch(SQLException e){
        	customer = null;
        	
            // if user name already present put exception
         System.out.println("Error Logging to Billing: " + e); 
            
		}			
		
		
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }finally {
            if (stmt != null) {
                try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }

}
				
		return customer;
	}
	
		}
	

