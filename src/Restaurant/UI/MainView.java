package Restaurant.UI;

import Restaurant.Datenhaltung.DatenModell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainView {

	public MainView(DatenModell datenModell) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml")); 
			loader.setController(new MainController(datenModell));
			Parent root = (Parent) loader.load();
			
		    datenModell.getPrimaryStage().setTitle("Reservierungssoftware"); 
		    datenModell.getPrimaryStage().setScene(new Scene(root, 800, 450)); 
		    datenModell.getPrimaryStage().show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
