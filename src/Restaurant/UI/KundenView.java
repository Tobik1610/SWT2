package Restaurant.UI;

import java.io.IOException;

import Restaurant.Datenhaltung.DatenModell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KundenView extends Stage {
	
	public KundenView(DatenModell datenModell) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("kundenview.fxml"));
        loader.setController(new KundenController(datenModell));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        setWidth(400);
        setX(250);
        setY(75);
        
        setScene(scene);
        showAndWait();
	}

}
