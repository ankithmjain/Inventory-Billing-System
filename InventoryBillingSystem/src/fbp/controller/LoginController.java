package fbp.controller;

import fbp.dao.LoginDAO;
import fbp.main.AddProductMain;
import fbp.main.CreateCustomerMain;
import fbp.main.CustomerWelcomeMain;
import fbp.main.LoginMain;
import fbp.main.WelcomeMain;
import fbp.model.Login;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {
	// This is the parent stage
	private Stage dialogStage;

	// This is the Text box element in the view for username of Login page
	@FXML
	private TextField username;
	// This is the Text box element in the view for password of Login page
	@FXML
	private TextField password;

	// Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	// to make new screen popup create a stage object
				private Stage ps = new Stage();
	public void login() {
		System.out.println("I am login");
		// Extract the data from the view elements
		String username = this.username.getText();
		String password = this.password.getText();
		// Validate the data
		if (username == null || username.trim().equals("")) {
			return;
		}
		if (password == null || password.trim().equals("")) {
			return;
		}
		// Create the model object
		Login login = new Login();
		// Set the values from the input form to model
		login.setUserName(username);
		login.setPassword(password);
		// Create a DAO instance of the model
		LoginDAO b = new LoginDAO();
		// Use the DAO to persist the model to database
		Login retval = b.checkuser(login);
		// put a condition to close the wrong user screen
		// condition to popup the screens to relvant username
		if(retval == null)
		{
			LoginMain lm = new LoginMain();
			lm.start(ps);
		}
	if(login.getUserName().equals("admin")){
		System.out.println("admin is logging "+login.getUserName());
		 WelcomeMain wm1 = new WelcomeMain();
			
	     wm1.start(ps);
	     close();
	}	
	else if (retval !=null)
	{
		System.out.println("Customer username "+login.getUserName());
		CustomerWelcomeMain lm = new CustomerWelcomeMain();
		Stage ps=new Stage();
		lm.start(ps);
		// Close the stage after saving
				close();
	}
		
close();
	}

	public void newuser() {
		System.out.println("I am new user button taking to customer form");
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		CreateCustomerMain cm = new CreateCustomerMain();
		
	          cm.start(ps);

	}

	// Method to cancel the action
	public void cancel() {
		close();
	}

	// This is required as stage.close() in the program will not trigger any
	// events.
	// To have callback listeners on the close event, we trigger the external
	// close event
	private void close() {
		dialogStage.fireEvent(new WindowEvent(dialogStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}