package Restaurant.UI;

import java.io.IOException;
import java.util.ArrayList;
import Restaurant.Datenhaltung.DatenModell;
import Restaurant.Fachlogik.Observer;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class KundenUebersichtController implements Observer{
	
	@FXML
	private ListView<Kunde> lvKunden;
	@FXML 
	private Label lName, lStrasse, lStadt;
	
	private Kunde selektierterKunde;
	
	private DatenModell datenModell;
	private ArrayList<Kunde> kunden;
	
	public KundenUebersichtController(DatenModell datenModell) {
		this.datenModell = datenModell;
		this.datenModell.getKundenverwaltung().fuegeObserverHinzu(this);
		kunden = this.datenModell.getKundenverwaltung().getKunden();
	}
	
	@FXML
	public void initialize() {

		lvKunden.getSelectionModel().selectedItemProperty().addListener((prop, alterKunde, neuerKunde) -> {
			selektierterKunde = neuerKunde;
			detailsAktualisieren();
		});
		
		listeAktualisieren();
	}
	
	public void onStartseite() {
		new MainView(datenModell);
	}
	
	public void onNeuerKunde() {
		try {
			new KundenView(datenModell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void detailsAktualisieren() {
		if(selektierterKunde == null) {
			lName.setText("Kein Kunde ausgewählt");
			lStrasse.setText("");
			lStadt.setText("");
		}
		else {
			lName.setText(selektierterKunde.getVorname() + " " + selektierterKunde.getNachname());
			if(selektierterKunde.getAdresse() != null) {
				lStrasse.setText(selektierterKunde.getAdresse().getStrasse() + ", " + selektierterKunde.getAdresse().getHausNr());
				lStadt.setText(selektierterKunde.getAdresse().getPlz() + ", " + selektierterKunde.getAdresse().getOrt());
			}else {
				lStrasse.setText("Keine Addresse vorhanden");
				lStadt.setText("");
			}
		}
	}
	
	public void listeAktualisieren() {
		lvKunden.getItems().clear();
		lvKunden.getItems().addAll(kunden);
		detailsAktualisieren();
	}

	@Override
	public void aktualiseren() {
		kunden = datenModell.getKundenverwaltung().getKunden();
		listeAktualisieren();
	}

}
