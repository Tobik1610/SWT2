package Restaurant.UI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainController {

	@FXML
	private Button btnZurueck, btnLoeschen;
	@FXML
	private ListView<String> tischListView;
	@FXML
	private DatePicker dpDatum;
	@FXML
	private Label tischLabel;
	@FXML
	private ImageView imgLoeschen;
	@FXML
	private MenuButton btnEinstellungen;
	@FXML
	private Pane paneTische;

	private Tischverwaltung tischverwaltung;
	private ArrayList<Reservierung> reservierungen;
	private int aktiverTisch;

	public MainController() {
		tischverwaltung = new Tischverwaltung();
	}

	@FXML
	public void initialize() {
		dpDatum.setValue(LocalDate.now());
		dpDatum.valueProperty().addListener((observable, oldDate, newDate) -> {
			reservierungenAktualisieren();
		});

		// Handler für Tische setzen
		TischHandler tischHandler = new TischHandler();
		for(Tisch tisch : tischverwaltung.getTische()) {
			tisch.setOnAction(tischHandler);
			paneTische.getChildren().add(tisch);
		}

		btnZurueck.setVisible(false);

		reservierungenAktualisieren();
		
		btnEinstellungen.setVisible(false);
	}

	private void farbenAktualisieren() {
		for(Tisch tisch : tischverwaltung.getTische())
			tisch.anzReservierungenZuruecksetzen();
		
		for(Reservierung r : reservierungen)
			tischverwaltung.getTisch(r.getTischNr()).erhoeheAnzReservierungen();
	}
	
	public void reservierungenAktualisieren() {
		// Datum aus Datepicker holen
		LocalDate datum = dpDatum.getValue();

		if (tischverwaltung != null) {
			// Daten laden und Liste reseten
			tischverwaltung.ladeDaten();
			tischListView.getItems().clear();

			if (aktiverTisch == 0) {
				// Reservierungen zum allen Tischen holen
				reservierungen = tischverwaltung.getReservierungen(datum);
				for (Reservierung reservierung : reservierungen)
					tischListView.getItems().add(reservierung.toString());
			} else {
				// Reservierungen zum ausgewählten Tisch holen
				reservierungen = tischverwaltung.getReservierungen(datum, aktiverTisch);
				for (Reservierung reservierung : reservierungen)
					tischListView.getItems().add(reservierung.toStringOhneTisch());
			}
		}
		//farbenAktualisieren();
	}

	public void onReservieren() {
		try {
			new ReservierungsView();
			reservierungenAktualisieren();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onZurueck() {
		btnZurueck.setVisible(false);
		String s = "Tischinformationen:";
		tischLabel.setText(s);
		aktiverTisch = 0;
		reservierungenAktualisieren();
	}

	public void onReservierungLoeschen() {
		int index = tischListView.getSelectionModel().getSelectedIndex();
		if (index != -1) {
			tischverwaltung.loescheReservierung(reservierungen.get(index));
			reservierungenAktualisieren();
		}
	}

	public void onLayoutBearbeiten() {
		try {
			new BauView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class TischHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Tisch tisch = (Tisch) event.getSource();
			aktiverTisch = tisch.getTischNr();
			String ausgabe = "Tisch " + aktiverTisch + ":";
			tischLabel.setText(ausgabe);
			reservierungenAktualisieren();
			btnZurueck.setVisible(true);
		}

	}
}
