package Restaurant.UI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReservierungsView extends Stage {
	
	public ReservierungsView() throws IOException{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservierungsview.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        setX(250);
        setY(75);
        
        setScene(scene);
        showAndWait();
    }

}
