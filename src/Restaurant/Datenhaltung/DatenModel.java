package Restaurant.Datenhaltung;

import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.stage.Stage;

public class DatenModel {

	private Stage primaryStage;
	private Tischverwaltung tischverwaltung;
	
	public DatenModel(Stage primaryStage) {
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
