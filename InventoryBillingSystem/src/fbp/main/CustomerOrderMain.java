package fbp.main;


import fbp.controller.CustomerOrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerOrderMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fbp/view/CustomerViewOrder.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
            primaryStage.setTitle("Customer Order View");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
            CustomerOrderController controller = loader.getController();
            //Set the parent stage in the controller
            controller.setDialogStage(primaryStage);
            //Show the view
            primaryStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating order customer view: " + e);
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
