package fbp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fbp.dao.LoginDAO;
import fbp.dao.OrderDAO;
import fbp.dao.ViewProdDAO;
import fbp.main.CustomerWelcomeMain;
import fbp.main.LoginMain;
import fbp.model.Order;
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

public class CustomerWelcomeController  implements Initializable {
	// to make new screen popup create a stage object
	private Stage ps = new Stage();
//This is the parent stage
private Stage dialogStage;
@FXML
private TextField orderdetail;

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
//close();
public void view()
{
CustomerWelcomeMain cwm = new CustomerWelcomeMain();
Stage ps=new Stage();
cwm.start(ps);
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
public void createorder(){
	System.out.println("I will create order with details for u");
	Product selectviewproduct = viewproducts.getSelectionModel().getSelectedItem();
    
	Order order = new Order(1,selectviewproduct.getPid(),LoginDAO.loggedinUser,orderdetail.getText());
	
	OrderDAO dao = new OrderDAO();
	dao.create(order);
	System.out.println("Order created");
	
}
	
public void signout(){
	System.out.println("I will take to login screen");
	System.out.println("I am signout");
	// to close the screen
	dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
	// object created to call Main screen of login
	LoginMain lm = new LoginMain();
	Stage ps=new Stage();
	lm.start(ps);	
}
public void viewmyorder(){
	System.out.println("I will show order you created @ CustomerWelcomeController");
	System.out.println("I am view my order button @ customer welcome Controller");
	System.out.println("I am  taking to CustomerOrderController u need to hide buttons");
	
	dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
	System.out.println("taking to view Customer order controller for pop up and data");
	CustomerOrderController oview = new CustomerOrderController();
	oview.view();
	
	
}

public void editcustomer(){
	System.out.println("I will edit customer details @Customer Welcome Controller");
dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
EditCustomerController ecust = new EditCustomerController();
ecust.updatescreen();

}




}
