package Restaurant.Datenhaltung;

import Restaurant.Fachlogik.Kundenverwaltung.Kundenverwaltung;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.stage.Stage;

public class DatenModell {
	
	private Kundenverwaltung kundenverwaltung;
	private Tischverwaltung tischverwaltung;
	private Stage primaryStage;
	
	public DatenModell(Stage primaryStage) {
		kundenverwaltung = new Kundenverwaltung();
		kundenverwaltung.ladeDaten();
		tischverwaltung = new Tischverwaltung();
		tischverwaltung.ladeDaten();
		this.primaryStage = primaryStage;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Kundenverwaltung getKundenverwaltung() {
		return kundenverwaltung;
	}

	public Tischverwaltung getTischverwaltung() {
		return tischverwaltung;
	}

}
