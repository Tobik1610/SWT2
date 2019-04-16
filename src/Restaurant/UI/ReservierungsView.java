package Restaurant.UI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReservierungsView {
	
	private Scene scene;
	
	public ReservierungsView() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("ReservierungsView.fxml"));
			scene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void show(Stage stage) {
		stage.setScene(scene);
		stage.show();
	}
}
