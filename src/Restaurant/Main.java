package Restaurant;

import Restaurant.Datenhaltung.DatenModel;
import Restaurant.UI.MainVC;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private DatenModel datenModel;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		datenModel = new DatenModel(primaryStage);
		
		MainVC mainVC = new MainVC(datenModel);
		mainVC.show();
	}
	
	@Override
	public void stop() {
		datenModel.getTischVerwaltung().speicherDaten();
	}

}
