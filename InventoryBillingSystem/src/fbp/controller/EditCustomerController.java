package fbp.controller;

import javax.swing.JOptionPane;

import fbp.dao.LoginDAO;
import fbp.main.CustomerWelcomeMain;
import fbp.main.EditCustomerMain;
import fbp.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditCustomerController {
	// This is the parent stage
	private Stage dialogStage;

	// This is the Text box element in the view for username of Add Customer page
	@FXML
	private TextField username;
	// This is the Text box element in the view for password of Add Customer page	
	@FXML
	private TextField password;
	// This is the Text box element in the view for username of Add Customer page
	@FXML
	private TextField address;
	// This is the Text box element in the view for password of Add Customer page	
	@FXML
	private TextField email;
// Method to set the parent stage of the current view
public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	
	
	
	
	
	
	
	
	
	
	
	
public void update(){
	System.out.println("I will update customer details to DB @EditCustomerController");
	// Extract the data from the view elements
String username = this.username.getText();
	String password = this.password.getText();
	String address = this.address.getText();
	String email = this.email.getText();

	
	// Validate the data
	if (username == null || username.trim().equals("")) {
		JOptionPane.showMessageDialog(null, "Enter"
				+ "Username Try again","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	if (password == null || password.trim().equals("")) {
		JOptionPane.showMessageDialog(null, "Enter"
				+ "Password Try again","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	if (address == null || address.trim().equals("")) {
		JOptionPane.showMessageDialog(null, "Enter"
				+ "address Try again","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	if (email == null || email.trim().equals("")) {
		JOptionPane.showMessageDialog(null, "Enter"
				+ "email Try again","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	// Create the model object
	Customer customer = new Customer(username, password, address, email);

	// Create a DAO instance of the model
	LoginDAO b = new LoginDAO();
	// Use the DAO to persist the model to database
	b.updateuser(customer);
	System.out.println("logged in user username "+customer.getUserName());

	
}
	
	
	
	public void updatescreen(){
		System.out.println("I will show customer edition screen");
	EditCustomerMain ecm = new EditCustomerMain();
		Stage ps=new Stage();
		ecm.start(ps);
		
	}	
	public void back(){
		
		System.out.println("I will take to customer order screen");
		// to close the screen
	dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
	// object created to call Customer Welcome Main screen
			CustomerWelcomeMain lm = new CustomerWelcomeMain();
			Stage ps=new Stage();
			lm.start(ps);
				
	}
	private void close() {
		dialogStage.fireEvent(new WindowEvent(dialogStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	
	
	
}
