package Restaurant.Datenhaltung;

import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.stage.Stage;

public class DataModel {

	private Stage primaryStage;
	private Tischverwaltung tischverwaltung;
	
	public DataModel(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.tischverwaltung = new Tischverwaltung(new TischDao(), new ReservierungDao());
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Tischverwaltung getTischVerwaltung() {
		return tischverwaltung;
	}
}
