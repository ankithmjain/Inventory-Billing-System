package fbp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fbp.dao.LoginDAO;
import fbp.dao.OrderDAO;
import fbp.main.AdminSaleMain;
import fbp.main.CustomerOrderMain;
import fbp.main.CustomerWelcomeMain;
import fbp.model.Order;
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

public class CustomerOrderController implements Initializable{
	// to make new screen popup create a stage object
			private Stage ps = new Stage();
		//This is the parent stage
		private Stage dialogStage;

		@FXML
		private TableView<Order> vieworders;

		@FXML
		private TableColumn<Order, String> oid;

		@FXML
		private TableColumn<Order, String> pid ;
		@FXML
		private TableColumn<Order, String> username;
		@FXML
		private TableColumn<Order, String> orderdetail;
		
		@FXML
		private TableColumn<Order, String> prodname;
		//
		private ObservableList<Order> vplist = FXCollections.observableArrayList();


		//Method to set the parent stage of the current view
		public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		}

	
		public void view() {
			// TODO Auto-generated method stub
			
			System.out.println("Poping up window to show each order of customer @ AdminSaleController");
			CustomerOrderMain vo = new CustomerOrderMain();
		    vo.start(ps);
			
		}
		
		@Override
		public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
			if(LoginDAO.loggedinUser.equals("admin")){
				System.out.println("Who has logged in "+LoginDAO.loggedinUser);
				//viewproducts.setVisible(false);
			}
		
			System.out.println("Who has logged in "+LoginDAO.loggedinUser);
			oid.setCellValueFactory(new PropertyValueFactory<Order, String>("Oid"));
		    pid.setCellValueFactory(new PropertyValueFactory<Order, String>("Pid"));
		    username.setCellValueFactory(new PropertyValueFactory<Order, String>("Username"));
			orderdetail.setCellValueFactory(new PropertyValueFactory<Order, String>("Orderdetail"));
			prodname.setCellValueFactory(new PropertyValueFactory<Order, String>("Prodname"));
			//Create the model object
			Order vieworder = new Order();
			//Create a DAO instance of the model
			   OrderDAO b = new OrderDAO();

			   vieworders.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

				//Use the DAO to persist the model to database
			   try {
				   vplist = b.showorder(vieworder);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			   vieworders.setItems(vplist);
					//controller.set(viewproducts);
				
			}
			
			
			
		public void back(){
			System.out.println("i will take to back to main customer menu");
			System.out.println("I am at Customer Order Controller");
			// to close the screen
			dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
			// object created to call Main screen of login
			CustomerWelcomeMain lm = new CustomerWelcomeMain();
			Stage ps=new Stage();
			lm.start(ps);
		
			
			
		}

		
		
		
		
		
		
		
		
		
		
		
		
	
}
