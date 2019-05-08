package Restaurant;
	
import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import Restaurant.UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/mainview.fxml")); 
			Parent root = (Parent) loader.load();
			
			MainController mainController = (MainController) loader.getController();
			Tischverwaltung tischverwaltung = new Tischverwaltung(new TischDao(), new ReservierungDao());
			mainController.setTischverwaltung(tischverwaltung);
			
		    primaryStage.setTitle("Reservierungssoftware"); 
		    primaryStage.setScene(new Scene(root, 700, 500)); 
		    primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
