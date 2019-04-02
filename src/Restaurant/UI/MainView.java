package Restaurant.UI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainView {
	
	private Scene scene;
	private GridPane grid;

	public MainView() {
		grid = new GridPane();
		
		scene = new Scene(grid, 800, 600);
	}
	
	public void show(Stage stage) {
		stage.setTitle("Restaurant");
		stage.setScene(scene);
		stage.show();
	}
}
