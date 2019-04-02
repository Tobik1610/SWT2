package Restaurant;

import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import Restaurant.UI.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Tischverwaltung tischverwaltung = new Tischverwaltung(new TischDao(), new ReservierungDao());
		
		Controller controller = new Controller(tischverwaltung);
		controller.start();
		
	}

}
