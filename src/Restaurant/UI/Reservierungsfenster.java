package Restaurant.UI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reservierungsfenster extends Stage {

	Stage stage;
	public Reservierungsfenster() throws IOException{
        stage = this;
         Parent root = FXMLLoader.load(getClass().getResource("reservierungsview.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

}
