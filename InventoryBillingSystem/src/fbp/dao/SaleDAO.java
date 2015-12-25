package fbp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fbp.model.Order;
import fbp.model.Sale;

public class SaleDAO {
	// Connection object
	private Connection connection;
	// Database connection ers
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	public Sale createsale(Sale sale) {
		System.out.println("i will create sale");
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to DB");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a order record to the sale table
		// Query to add user record in the customer table
		Statement stmt = null;
		try {
			// int oid=order.getOid();
			int oid = sale.getOid();
			String salesamount = sale.getSalesamount();
			// query to add sales record for the order
			System.out.println("Creating sales into the db table...");
			stmt = connection.createStatement();
			String query = "INSERT INTO ajain_sale " + " (oid,salesamount) VALUES ('" + oid + "','" + salesamount
					+ "');";
			System.out.println(query);

			int i = stmt.executeUpdate(query);
			if (i > 0) {
				System.out.println("success sales Created");
				JOptionPane.showMessageDialog(null,"Sale Created","Success",JOptionPane.PLAIN_MESSAGE);

			} else {
				System.out.println("stuck somewhere NO sales record generated");

			}

		} catch (SQLException e) {
			sale = null;

			JOptionPane.showMessageDialog(null, "Product Sold already / Error input again", "Error", JOptionPane.ERROR_MESSAGE);

			System.out.println("Error Creating order : " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the bank object that was inserted with the id field set.
		return sale;
		// TODO Auto-generated method stub

	}

	public ObservableList<Sale> showsales(Sale viewsale) {
		// TODO Auto-generated method stub
		System.out.println("ObservableList to popup data");
		// Get a connection
		try {
			System.out.println("Connecting to DB");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connection Successful");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to View Sales records from the Sales table
		Statement stmt = null;
		ObservableList<Sale> viewsales = FXCollections.observableArrayList();
		// FXCollections.observableArrayList();
		System.out.println("ObservableList to popup data");
		try {
			System.out.println("Query to get sales data to screen ");
			stmt = connection.createStatement();
			String sql = "SELECT SID,OID,SalesAmount FROM ajain_sale;";

			System.out.println(sql); // to see the query
			ResultSet rs = stmt.executeQuery(sql);
			// Extract data from result set
			while (rs.next()) {
				Sale vp = new Sale();
				vp.setSid(rs.getInt("SID"));
				vp.setOid(rs.getInt("OID"));
				vp.setSalesamount(rs.getString("SalesAmount"));

				System.out.println("Inserting Data to list one by one  " + vp.getSid() + vp.getOid());
				viewsales.add(vp);
			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return viewsales;
	}

	// to pop up all the sales done use the list

}
