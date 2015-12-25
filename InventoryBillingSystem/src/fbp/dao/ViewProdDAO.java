package fbp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fbp.model.Product;

public class ViewProdDAO {

	//Connection object
	private Connection connection;
	//Database connection ers
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";
	
    //Method to view products model from database
	public ObservableList<Product> create(Product viewproduct) {
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
		ObservableList<Product> viewproducts = FXCollections.observableArrayList(); // ???
		//FXCollections.observableArrayList();
		try{
             System.out.println("Query to get prod data to screen ");
             stmt = connection.createStatement();
             String sql = "SELECT PID, ProdName, ProdCategory FROM ajain_product;";
             System.out.println(sql); // to see the query
             ResultSet rs = stmt.executeQuery(sql);
             // why?
             
             // Extract data from result set
             while(rs.next()){
            	 Product vp = new Product();
            	 vp.setPid(rs.getString("PID"));
            	 vp.setProdName(rs.getString("ProdName"));
                 vp.setProdCategory(rs.getString("ProdCategory"));
              System.out.println("Inserting Data to list one by one  "+vp.getProdName()+"  "+vp.getProdCategory());
                 viewproducts.add(vp);
                 
            
               //Display values
//                 System.out.print("PID: " + pid);
//                 System.out.print(", PName: " + prodname);
//                 System.out.print(", P Cat: " + prodcategory);             
             }
             rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
//		   }finally{
//		      //finally block used to close resources
//		      try{
//		         if(stmt!=null)
//		            connection.close();
//		      }catch(SQLException se){
//		      }// do nothing
//		      try{
//		         if(connection!=null)
//		        	 connection.close();
//		      }catch(SQLException se){
//		         se.printStackTrace();
//		      }//end finally try
//		   }//end try
		   System.out.println("Goodbye!");
		return viewproducts;
	
	
	}
		}
