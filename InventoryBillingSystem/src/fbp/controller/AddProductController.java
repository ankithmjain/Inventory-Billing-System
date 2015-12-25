package fbp.controller;

import javax.swing.JOptionPane;

import fbp.dao.AddProdDAO;

import fbp.main.WelcomeMain;
import fbp.model.Product;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddProductController {

	// to make new screen popup create a stage object
	private Stage ps = new Stage();
	// This is the parent stage
	private Stage dialogStage;

	// This is the Text box element in the view for product id of product
	@FXML
	private TextField pid;
	// This is the Text box element in the view for address of bank
	@FXML
	private TextField prodname;

	// This is the Text box element in the view for address of bank
	@FXML
	private TextField prodcategory;

	// Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// Method to save the product details to database
	public void save() {
		System.out.println("I am saving Product details to database method at AddProductController");
		// Extract the data from the view elements
		String pid = this.pid.getText();
		String prodname = this.prodname.getText();
		String prodcategory = this.prodcategory.getText();
		// Validate the data
		if (pid == null || pid.trim().equals("")) {
			System.out.println("enter data to insert");
			JOptionPane.showMessageDialog(null, "Enter data to insert / Enter PID" + " Try again", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (prodname == null || prodname.trim().equals("")) {
			return;
		}
		if (prodcategory == null || prodcategory.trim().equals("")) {
			return;
		}

		// Create the model object
		Product addproduct = new Product();
		// Set the values from the input form
		addproduct.setPid(pid);
		addproduct.setProdName(prodname);
		addproduct.setProdCategory(prodcategory);
		// Create a DAO instance of the model
		AddProdDAO b = new AddProdDAO();
		// Use the DAO to persist the model to database
		b.create(addproduct);
		// Close the stage after saving
		close();

	}

	public void updateproduct() {
		System.out.println("i will update a product @ AddProductController");
		// Extract the data from the view elements
		String pid = this.pid.getText();
		String prodname = this.prodname.getText();
		String prodcategory = this.prodcategory.getText();
		// Validate the data
		if (pid == null || pid.trim().equals("")) {
			System.out.println("enter PID");
			JOptionPane.showMessageDialog(null, "Enter PID /Input Valid PID" + " Try again", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (prodname == null || prodname.trim().equals("")) {
			return;
		}
		if (prodcategory == null || prodcategory.trim().equals("")) {
			return;
		}

		// Create the model object
		Product addproduct = new Product();
		// Set the values from the input form
		addproduct.setPid(pid);
		addproduct.setProdName(prodname);
		addproduct.setProdCategory(prodcategory);
		// Create a DAO instance of the model
		AddProdDAO b = new AddProdDAO();
		// Use the DAO to persist the model to database
		b.update(addproduct);
		// Close the stage after saving
		close();
	}

	public void deleteproduct() {
		System.out.println("i will delete a product @ AddProductController");
		// Extract the data from the view elements
		String pid = this.pid.getText();
		String prodname = this.prodname.getText();
		String prodcategory = this.prodcategory.getText();
		// Validate the data
		if (pid == null || pid.trim().equals("")) {
			System.out.println("enter PID");
			JOptionPane.showMessageDialog(null, "Enter PID to delete / Input Valid PID" + " Try again", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (prodname == null || prodname.trim().equals("")) {
			return;
		}
		if (prodcategory == null || prodcategory.trim().equals("")) {
			return;
		}

		// Create the model object
		Product addproduct = new Product();
		// Set the values from the input form
		addproduct.setPid(pid);
		addproduct.setProdName(prodname);
		addproduct.setProdCategory(prodcategory);
		// Create a DAO instance of the model
		AddProdDAO b = new AddProdDAO();
		b.delete(addproduct);

		// Close the stage after saving
		close();

	}

	// Method to view the product data from database
	public void viewscreen() {
		System.out.println("I am view product screen taker at AddProductController");

		dialogStage.fireEvent(new WindowEvent(dialogStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		System.out.println("taking to view product controller for pop up and data");
		ViewProductController pdview = new ViewProductController();
		pdview.view();

	}

	// Method to cancel the action
	public void cancel() {
		System.out.println("I am cancel going back to welcomescreen");
		// to close the screen
		dialogStage.fireEvent(new WindowEvent(dialogStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		// object created to call Welcome screen of login
		WelcomeMain lm = new WelcomeMain();
		Stage ps = new Stage();
		lm.start(ps);
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
