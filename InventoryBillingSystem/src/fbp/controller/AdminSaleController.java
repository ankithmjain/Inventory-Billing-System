package fbp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import fbp.dao.LoginDAO;
import fbp.dao.OrderDAO;
import fbp.dao.SaleDAO;
import fbp.main.AdminSaleMain;
import fbp.main.ViewProductMain;
import fbp.main.WelcomeMain;
import fbp.model.Order;
import fbp.model.Product;
import fbp.model.Sale;
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


public class AdminSaleController  implements Initializable{
	// to make new screen popup create a stage object
		private Stage ps = new Stage();
	//This is the parent stage
	private Stage dialogStage;
	@FXML
	private TextField salesamount;

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

	// this will set data to list
	/*public void set(ObservableList<Order> viewOrders){
	this.viewOrders.setItems(viewOrders);
	}*/
	//This is the Text box element in the view for name of bank


	//Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
	this.dialogStage = dialogStage;
	}
	public void view() {
		// TODO Auto-generated method stub
		
		System.out.println("Poping up window to show all orders @ AdminSaleController");
		AdminSaleMain sm = new AdminSaleMain();
	    sm.start(ps);
		
	}	
	public void viewsales(){
		System.out.println(" i will show all sales done");
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		ViewSalesController vs = new ViewSalesController();
		vs.view();
	
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
		System.out.println("i will take to back to main admin menu @adminSalesController");
		// to close the screen
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		// object created to call Main screen of login
		WelcomeMain lm = new WelcomeMain();
		Stage ps=new Stage();
		lm.start(ps);	
		
	}
			
public void createsale(){
System.out.println("I am creating a sale and putting details to DB");	
Order selectvieworders = vieworders.getSelectionModel().getSelectedItem();
if (salesamount == null || salesamount.getText().equals("")) {
	JOptionPane.showMessageDialog(null, "Enter"
			+ "Sales Try again","Error",JOptionPane.ERROR_MESSAGE);
	return;
}

Sale sale = new Sale(1,selectvieworders.getOid(),salesamount.getText());

SaleDAO dao = new SaleDAO();
dao.createsale(sale);
System.out.println("Sale created");

}






}