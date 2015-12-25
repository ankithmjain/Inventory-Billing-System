package fbp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fbp.dao.LoginDAO;
import fbp.dao.SaleDAO;
import fbp.main.AdminSaleMain;
import fbp.main.ViewSaleMain;
import fbp.main.WelcomeMain;
import fbp.model.Order;
import fbp.model.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewSalesController implements Initializable {
	// to make new screen popup create a stage object
	private Stage ps = new Stage();
//This is the parent stage
private Stage dialogStage;
@FXML
private TableView<Sale> viewsales;

@FXML
private TableColumn<Sale, String> sid;

@FXML
private TableColumn<Sale, String> oid ;
@FXML
private TableColumn<Sale, String> salesamount;
@FXML

private ObservableList<Sale> vplist = FXCollections.observableArrayList();
	
//Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
	this.dialogStage = dialogStage;
	}
// to show all sales
	public void view() {
		// TODO Auto-generated method stub
		
		System.out.println("Poping up window to show all sales @ ViewSalesController");
		ViewSaleMain sm = new ViewSaleMain();
	    sm.start(ps);	
	}		
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	
		if(LoginDAO.loggedinUser.equals("admin")){
			System.out.println("Who has logged in "+LoginDAO.loggedinUser);
			//viewproducts.setVisible(false);
		}
	
		System.out.println("Who has logged in "+LoginDAO.loggedinUser);
		sid.setCellValueFactory(new PropertyValueFactory<Sale, String>("Sid"));
	    oid.setCellValueFactory(new PropertyValueFactory<Sale, String>("Oid"));
	    salesamount.setCellValueFactory(new PropertyValueFactory<Sale, String>("Salesamount"));
		
		
		//Create the model object
		Sale viewsale = new Sale();
		//Create a DAO instance of the model
		   SaleDAO b = new SaleDAO();

		   viewsales.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			//Use the DAO to persist the model to database
		   try {
			   vplist = b.showsales(viewsale);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		   viewsales.setItems(vplist);
				//controller.set(viewproducts);
			
		}
		
	
	
	public void back()
	{
		System.out.println("I will take back to create sale screen @ViewSalesController");
		// to close the screen
				dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
				// object created to call Main screen of login
				WelcomeMain lm = new WelcomeMain();
				Stage ps=new Stage();
				lm.start(ps);
		
	}
	
	
	
	
	
}
