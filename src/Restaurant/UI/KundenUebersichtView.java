package Restaurant.UI;

import java.io.IOException;
import Restaurant.Datenhaltung.DatenModell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class KundenUebersichtView {

	public KundenUebersichtView(DatenModell datenModell) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("kundenUebersichtview.fxml"));
        loader.setController(new KundenUebersichtController(datenModell));
        
        Parent root = loader.load();
        
	    datenModell.getPrimaryStage().setTitle("Reservierungssoftware"); 
	    datenModell.getPrimaryStage().setScene(new Scene(root, 800, 450)); 
	    datenModell.getPrimaryStage().show(); 
	}
}
