package Restaurant.UI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Restaurant.Datenhaltung.DatenModell;
import Restaurant.Fachlogik.Observer;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class ReservierungsController implements Observer {

	@FXML
	private TextField tfPersonen;
	@FXML
	private ChoiceBox<String> cbStunde, cbMinute, cbTisch;
	@FXML
	private DatePicker dpDatum;
	@FXML
	private Button btnReservieren, btnNeuerKunde;
	@FXML
	private ComboBox<Kunde> cbKunde;

	private ArrayList<Kunde> kunden;
	private ArrayList<Integer> tische;
	private Boolean bName = false, bPersonen = false, bDatum = true;
	private SuchComboBox<Kunde> suchComboBox;
	private DatenModell datenModell;

	public ReservierungsController(DatenModell datenModell) {
		this.datenModell = datenModell;
		this.datenModell.getTischverwaltung().fuegeObserverHinzu(this);
		this.datenModell.getKundenverwaltung().fuegeObserverHinzu(this);
		kunden = new ArrayList<>();
		tische = new ArrayList<>();
	}

	@FXML
	public void initialize() {
		// Auswahl Boxen initialisieren
		cbStunde.getItems().addAll("12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
		cbStunde.getSelectionModel().select(0);
		cbMinute.getItems().addAll("00", "15", "30", "45");
		cbMinute.getSelectionModel().select(0);

		dpDatum.setValue(LocalDate.now());

		tfPersonen.textProperty().addListener((observable, oldText, newText) -> {
			String regex = "\\d{0,1}";// Einstellige Ziffer
			if (Pattern.matches(regex, newText)) {

				if (!newText.isEmpty())
					bPersonen = true;
				else
					bPersonen = false;

				eingabenPruefen();

			} else
				tfPersonen.setText(oldText);
		});

		cbStunde.valueProperty().addListener((observable, oldValue, newValue) -> {
			eingabenPruefen();
		});

		cbMinute.valueProperty().addListener((observable, oldValue, newValue) -> {
			eingabenPruefen();
		});

		dpDatum.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null)
				bDatum = true;
			else
				bDatum = false;

			eingabenPruefen();
		});

		// Kunden ComboBox
		cbKunde.setTooltip(new Tooltip());
		cbKunde.setCellFactory(new Callback<ListView<Kunde>, ListCell<Kunde>>() {

			@Override
			public ListCell<Kunde> call(ListView<Kunde> k) {
				return new ListCell<Kunde>() {
					@Override
					protected void updateItem(Kunde kunde, boolean empty) {
						super.updateItem(kunde, empty);

						if (kunde == null || empty) {
							setText("");
						} else {
							String str = kunde.getVorname() + " " + kunde.getNachname();
							setText(str);
						}
					}
				};
			}
		});

		cbKunde.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null)
				bName = true;
			else
				bName = false;

			eingabenPruefen();
		});

		aktualiseren();
	}

	public void reservierungAktivieren() {
		freieTischeAktualisieren();
		
		btnReservieren.setDisable(false);
	}

	public void freieTischeAktualisieren() {
		// Daten aus den Controls holen
		LocalDate datum = dpDatum.getValue();
		Uhrzeit uhrzeit = new Uhrzeit(Integer.parseInt(cbStunde.getSelectionModel().getSelectedItem()),
				Integer.parseInt(cbMinute.getSelectionModel().getSelectedItem()));
		int personen = Integer.parseInt(tfPersonen.getText());
		
		// Freie Tische holen
		tische = datenModell.getTischverwaltung().getFreieTische(datum, uhrzeit, personen);
		cbTisch.getItems().clear();

		// Liste mit verf�gbaren Tischen f�llen und Buttons freischalten
		if (tische.size() > 0) {
			for (int tisch : tische)
				cbTisch.getItems().add("" + tisch);

			cbTisch.getSelectionModel().select(0);

			cbTisch.setDisable(false);
		}
	}

	public void reservierungDeaktivieren() {
		cbTisch.setDisable(true);
		btnReservieren.setDisable(true);
	}

	public void eingabenPruefen() {
		if (istReservierbar())
			reservierungAktivieren();
		else
			reservierungDeaktivieren();
	}

	public Boolean istReservierbar() {
		return bName && bPersonen && bDatum;
	}

	public void onReservieren() {
		// Uhrzeit aus den Controls holen
		Uhrzeit uhrzeit = new Uhrzeit(Integer.parseInt(cbStunde.getSelectionModel().getSelectedItem()),
				Integer.parseInt(cbMinute.getSelectionModel().getSelectedItem()));

		// Reservierung erstellen
		Reservierung reservierung = new Reservierung(dpDatum.getValue(), uhrzeit, tfPersonen.getText(),
				cbKunde.getSelectionModel().getSelectedItem(),
				Integer.parseInt(cbTisch.getSelectionModel().getSelectedItem()));

		// Reservieren
		datenModell.getTischverwaltung().reservieren(reservierung);

		// Dialog schlie�en
		tfPersonen.getScene().getWindow().hide();
	}

	public void aktualisiereKunden() {
		kunden = datenModell.getKundenverwaltung().getKunden();

		cbKunde.getItems().clear();

		for (Kunde kunde : kunden)
			cbKunde.getItems().add(kunde);

		suchComboBox = new SuchComboBox<>(cbKunde);
	}

	public void onNeuerKunde() {
		try {
			new KundenView(datenModell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void aktualiseren() {
		aktualisiereKunden();
	}
}
