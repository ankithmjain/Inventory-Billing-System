package fbp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.stage.Stage;
//import fbp.controller.WelcomeController;
import fbp.main.AddProductMain;
import fbp.model.Product;

public class AddProdDAO {
	// to make new screen popup create a stage object
			private Stage ps = new Stage();
	//Connection object
		private Connection connection;
		//Database connection ers
	    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	    private String username = "fpuser";
	    private String password = "510";
	    //Method to save a AddProduct model to database
		public Product create(Product addproduct) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }	
			// Query to insert a product detail to database
			Statement stmt = null;		
			try{
				String pid=addproduct.getPid();
				String prodname=addproduct.getProdName();
				String prodcategory = addproduct.getProdCategory();
				//Query to validate a user in the Login table
				System.out.println("Inserting Prod data into the product table...");
			
				stmt = connection.createStatement();
				String query = "INSERT INTO ajain_product "
						+ " (PID, ProdName,ProdCategory) VALUES ('"+pid+"', "
						+ "'"+prodname+"',"
								+ "'"+prodcategory+"');";
				System.out.println(query);
				 stmt.executeUpdate(query);
			System.out.println("Data Inserted to Db");
			JOptionPane.showMessageDialog(null, "Product"
					+ "  Inserted","Success",JOptionPane.PLAIN_MESSAGE);
			AddProductMain ap = new AddProductMain();
			ap.start(ps);
				 
			}catch(SQLException e){
	        	addproduct = null;
	        	System.out.println("Error Logging to Billing: " + e);
	        	JOptionPane.showMessageDialog(null, "Invalid "
						+ "Input/Duplicate Entry"
						+ " Try again","Error",JOptionPane.ERROR_MESSAGE);
	        	AddProductMain ap = new AddProductMain();
				ap.start(ps);
	        }
			
			
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			
			
			//Return the bank object that was inserted with the id field set.
			return addproduct;
		
		}
		
		
		 //Method to update existing product a AddProduct model to database
		public Product update(Product addproduct){
			System.out.println("I will connect to db and update the product details");
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }	
			// Query to insert a product detail to database
			Statement stmt = null;		
			try{
				String pid=addproduct.getPid();
				String prodname=addproduct.getProdName();
				String prodcategory = addproduct.getProdCategory();
				//Query to validate a user in the Login table
				System.out.println("Updating Prod data into the product table...");
			
				stmt = connection.createStatement();
				String query = "UPDATE ajain_product SET ProdName = '"+prodname+"',ProdCategory = '"+prodcategory+"' WHERE PID = '"+pid+"';";
				System.out.println(query);
				
				
				int i = stmt.executeUpdate(query);
				if(i>0)
		        {
		              System.out.println("success");
		              System.out.println("Data Updated to Db");
		  			JOptionPane.showMessageDialog(null, "Product"
		  					+ "  Updated","Success",JOptionPane.PLAIN_MESSAGE);
		  			AddProductMain ap = new AddProductMain();
		  			ap.start(ps);
		              
		              
		        }
		              else
		        {
		             System.out.println("stuck somewhere");
		             JOptionPane.showMessageDialog(null, "Invalid "
								+ "Input/Data not in database"
								+ " Try again","Error",JOptionPane.ERROR_MESSAGE);
		             AddProductMain ap = new AddProductMain();
		 			ap.start(ps);
		        }
				 
			}catch(SQLException e){
	        	addproduct = null;
	        	System.out.println("Error Logging to Billing: " + e);
	        	JOptionPane.showMessageDialog(null, "Invalid "
						+ "Input/Duplicate Entry"
						+ " Try again","Error",JOptionPane.ERROR_MESSAGE);
	        	AddProductMain ap = new AddProductMain();
				ap.start(ps);
	        }
			
			
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			return addproduct;
			
		}
		//delete
		public Product delete(Product addproduct){
			System.out.println("I will write a delete query and delete the data in database");
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }	
			// Query to insert a product detail to database
			Statement stmt = null;		
			try{
				String pid=addproduct.getPid();
				//String prodname=addproduct.getProdName();
				//String prodcategory = addproduct.getProdCategory();
				//Query to validate a user in the Login table
				System.out.println("Deleting Prod data into the product table...");
			
				stmt = connection.createStatement();
				String query = "DELETE FROM ajain_product WHERE PID = '"+pid+"';";
				System.out.println(query);
				 int i = stmt.executeUpdate(query);
				 if(i>0)
			        {
			              System.out.println("success");
			              System.out.println("Data deleted in Db");
			  			JOptionPane.showMessageDialog(null, "Product"
			  					+ "  Deleted","Success",JOptionPane.PLAIN_MESSAGE);
			  			AddProductMain ap = new AddProductMain();
			  			ap.start(ps);
			              
			              
			        }
			              else
			        {
			             System.out.println("stuck somewhere");
			             JOptionPane.showMessageDialog(null, "Invalid "
									+ "Input/Data not in database"
									+ " Try again","Error",JOptionPane.ERROR_MESSAGE);
			             AddProductMain ap = new AddProductMain();
			 			ap.start(ps);
			        }
				 
				 // keep a check exception here
			
				 
			}catch(SQLException e){
	        	addproduct = null;
	        	System.out.println("Error Logging to Billing: " + e);
	        	JOptionPane.showMessageDialog(null, "Invalid "
						+ "Input/Duplicate Entry"
						+ " Try again","Error",JOptionPane.ERROR_MESSAGE);
	        	AddProductMain ap = new AddProductMain();
				ap.start(ps);
	        }
			
			
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			
		return addproduct;
		
		}
		
}
