package fbp.controller;


import java.net.URL;
import java.rmi.server.LoaderHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import fbp.dao.LoginDAO;
import fbp.dao.ViewProdDAO;
import fbp.main.AddProductMain;
import fbp.main.ViewProductMain;
import fbp.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewProductController implements Initializable {
	// to make new screen popup create a stage object
				private Stage ps = new Stage();
	//This is the parent stage
		private Stage dialogStage;
		@FXML
		private TableView<Product> viewproducts;
		
		@FXML
		private TableColumn<Product, String> pid;
		
		@FXML
		private TableColumn<Product, String> prodname ;
		@FXML
		private TableColumn<Product, String> prodcategory;
		@FXML
		//
		private ObservableList<Product> vplist = FXCollections.observableArrayList();
		
		// this will set data to list
		/*public void set(ObservableList<Product> viewproducts){
			this.viewproducts.setItems(viewproducts);
		}*/
		//This is the Text box element in the view for name of bank
		
		
		//Method to set the parent stage of the current view
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}
		
		public void showitem(){
			System.out.println("I will show selected item");
			Product selectviewproduct = viewproducts.getSelectionModel().getSelectedItem();
			System.out.println(selectviewproduct.getPid());
		   
		}
		//Method to view the products from database
		public void view()
		{
			System.out.println("I will show the all products view from ViewProductController");
		
			//Create the model object
			// calling the method to show view screen by creating object
						ViewProductMain ap = new ViewProductMain();
						ap.start(ps);
						
						//	
						//Close the stage after saving
						//close();
		}
		@Override
		public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
			// TODO Auto-generated method stub
			if(LoginDAO.loggedinUser.equals("admin")){
				System.out.println("Who has logged in "+LoginDAO.loggedinUser);
				//viewproducts.setVisible(false);
				
				/*ViewProduct selectviewproduct = viewproducts.getSelectionModel().getSelectedItem();
				System.out.println(selectviewproduct.getPid());*/
			   
			}
			
			System.out.println("Who has logged in "+LoginDAO.loggedinUser);
			pid.setCellValueFactory(new PropertyValueFactory<Product, String>("Pid"));
            prodname.setCellValueFactory(new PropertyValueFactory<Product, String>("ProdName"));
            prodcategory.setCellValueFactory(new PropertyValueFactory<Product, String>("ProdCategory"));
			
			
			//Create the model object
			Product viewproduct = new Product();
			
			//Create a DAO instance of the model
		   ViewProdDAO b = new ViewProdDAO();
		  
		   viewproducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		   
			//Use the DAO to persist the model to database
		   try {
			   vplist = b.create(viewproduct);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		   viewproducts.setItems(vplist);
				//controller.set(viewproducts);
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void back(){
			System.out.println("I will take back to Add Product Main Screen "
					+ "Method at ViewProduct");
			dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
			AddProductMain ap = new AddProductMain();
			ap.start(ps);
			
			
		}
		
		//Method to cancel the action
		public void cancel() {
			close();
		}
		
		//This is required as stage.close() in the program will not trigger any events.
		//To have callback listeners on the close event, we trigger the external close event
		private void close() {
			dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}


		
		
		
	

}
