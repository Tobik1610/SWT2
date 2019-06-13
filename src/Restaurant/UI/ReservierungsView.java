package Restaurant.UI;

import java.io.IOException;

import Restaurant.Datenhaltung.DatenModell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReservierungsView extends Stage {
	
	public ReservierungsView(DatenModell datenModell) throws IOException{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservierungsview.fxml"));
        loader.setController(new ReservierungsController(datenModell));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        setX(250);
        setY(75);
        
        setScene(scene);
        showAndWait();
    }

}
