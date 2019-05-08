package Restaurant.UI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reservierungsfenster extends Stage {
	
	public Reservierungsfenster() throws IOException{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservierungsview.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        setScene(scene);
        showAndWait();
    }

}
