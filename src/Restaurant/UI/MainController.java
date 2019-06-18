package Restaurant.UI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import Restaurant.Datenhaltung.DatenModell;
import Restaurant.Fachlogik.Observer;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
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

public class MainController implements Observer {

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

	private ArrayList<Reservierung> reservierungen;
	private ArrayList<Tisch> tische;
	private int aktiverTisch;
	private DatenModell datenModell;

	public MainController(DatenModell datenModell) {
		this.datenModell = datenModell;
		this.datenModell.getTischverwaltung().fuegeObserverHinzu(this);
		tische = new ArrayList<>();
		tische = datenModell.getTischverwaltung().getTische();
		reservierungen = new ArrayList<>();
	}

	@FXML
	public void initialize() {
		dpDatum.setValue(LocalDate.now());
		dpDatum.valueProperty().addListener((observable, oldDate, newDate) -> {
			reservierungenAktualisieren();
		});

		// Handler für Tische setzen
		TischHandler tischHandler = new TischHandler();
		for (Tisch tisch : tische) {
			tisch.setOnAction(tischHandler);
			paneTische.getChildren().add(tisch);
		}

		btnZurueck.setVisible(false);

		reservierungenAktualisieren();
	}

	public void reservierungenAktualisieren() {
		// Datum aus Datepicker holen
		LocalDate datum = dpDatum.getValue();

		tischListView.getItems().clear();

		if (aktiverTisch == 0) {
			// Reservierungen zum allen Tischen holen
			reservierungen = datenModell.getTischverwaltung().getReservierungen(datum);
			for (Reservierung reservierung : reservierungen)
				tischListView.getItems().add(reservierung.toString());
		} else {
			// Reservierungen zum ausgewählten Tisch holen
			reservierungen = datenModell.getTischverwaltung().getReservierungen(datum, aktiverTisch);
			for (Reservierung reservierung : reservierungen)
				tischListView.getItems().add(reservierung.toStringOhneTisch());
		}

	}

	public void onReservieren() {
		try {
			new ReservierungsView(datenModell);
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
			datenModell.getTischverwaltung().loescheReservierung(reservierungen.get(index));
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

	@Override
	public void aktualiseren() {
		reservierungenAktualisieren();
	}
	
	public void onKundenZeigen() {
		try {
			new KundenUebersichtView(datenModell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
