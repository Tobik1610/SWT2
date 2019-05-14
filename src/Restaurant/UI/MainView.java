package Restaurant.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {

	public MainView(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml")); 
			Parent root = (Parent) loader.load();
			
		    primaryStage.setTitle("Reservierungssoftware"); 
		    primaryStage.setScene(new Scene(root, 800, 450)); 
		    primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
