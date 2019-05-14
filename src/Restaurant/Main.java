package Restaurant;
	
import Restaurant.UI.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new MainView(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
