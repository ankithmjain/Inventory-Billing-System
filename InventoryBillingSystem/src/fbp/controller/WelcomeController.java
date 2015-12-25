package fbp.controller;

import fbp.main.AddProductMain;
import fbp.main.LoginMain;
//import fbp.main.WelcomeMain;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WelcomeController {
	// to make new screen popup create a stage object
			private Stage ps = new Stage();
	// This is the parent stage
	private Stage dialogStage;

	// Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	private String Loggedinuser;

	// Method to signout
	public void signout() {
		System.out.println("I am signout");
		// to close the screen
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		// object created to call Main screen of login
		LoginMain lm = new LoginMain();
		Stage ps=new Stage();
		lm.start(ps);
	
	}

	// Method to add product
	public void addproduct() {
		System.out.println("I am add product method @ Wlcm Controller");
		System.out.println("Showing Screen from Addproduct Main");
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		AddProductMain ap = new AddProductMain();
		ap.start(ps);
	}
	// Method to create customer
	public void createcustomer() {
		System.out.println("I am createcustomer @ Wlcm Controller");

	}

	// Method to create order
	public void createorder() {
		System.out.println("I am createorder @ Wlcm Controller");

	}

	// Method to add create sales
	public void createsales() {
		System.out.println("I am createsales button @ Wlcm Controller");
		System.out.println("I am  taker to AdminsaleController");
		
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		System.out.println("taking to view product controller for pop up and data");
 	AdminSaleController sview = new AdminSaleController();
		sview.view();
		

	}

}
