package Restaurant.UI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Restaurant.Ausnahmen.TischNichtVorhandenException;
import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ReservierungsController {

	@FXML
	private TextField tfName, tfPersonen;
	@FXML
	private ChoiceBox<String> cbStunde, cbMinute, cbTisch;
	@FXML
	private DatePicker dpDatum;
	@FXML
	private Button btnReservieren;

	private Tischverwaltung tischverwaltung;

	private Boolean bName = false, bPersonen = false, bDatum = true;

	public ReservierungsController() {
		this.tischverwaltung = new Tischverwaltung(new TischDao(), new ReservierungDao());
		tischverwaltung.ladeDaten();
	}

	@FXML
	public void initialize() {
		// Auswahl Boxen initialisieren
		cbStunde.getItems().addAll("12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
		cbStunde.getSelectionModel().select(0);
		cbMinute.getItems().addAll("00", "15", "30", "45");
		cbMinute.getSelectionModel().select(0);

		dpDatum.setValue(LocalDate.now());

		//Listener setzen
		tfName.textProperty().addListener((observable, oldText, newText) -> {
			if (!newText.isEmpty())
				bName = true;
			else
				bName = false;

			eingabenPruefen();
		});

		tfPersonen.textProperty().addListener((observable, oldText, newText) -> {
			String regex = "\\d{0,1}";//Einstellige Ziffer
			if(Pattern.matches(regex,newText)) {

				if (!newText.isEmpty())
					bPersonen = true;
				else
					bPersonen = false;

				eingabenPruefen();
	
			}else
				tfPersonen.setText(oldText);
		});
		
		cbStunde.valueProperty().addListener((observable, oldValue, newValue) -> {
			eingabenPruefen();
		});
		
		cbMinute.valueProperty().addListener((observable, oldValue, newValue) -> {
			eingabenPruefen();
		});
		
		dpDatum.valueProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null)
				bDatum = true;
			else
				bDatum = false;
			
			eingabenPruefen();
		});
	}

	public void reservierungAktivieren() {
		//Daten aus den Controls holen
		LocalDate datum = dpDatum.getValue();
		Uhrzeit uhrzeit = new Uhrzeit(Integer.parseInt(cbStunde.getSelectionModel().getSelectedItem()),
				Integer.parseInt(cbMinute.getSelectionModel().getSelectedItem())); 
		int personen = Integer.parseInt(tfPersonen.getText());
		
		//Freie Tische holen
		ArrayList<Integer> tische = tischverwaltung.getFreieTische(datum, uhrzeit, personen);
		cbTisch.getItems().clear();
		
		//Liste mit verf¸gbaren Tischen f¸llen und Buttons freischalten
		if(tische.size() > 0) {
			for (int tisch : tische)
				cbTisch.getItems().add("" + tisch);

			cbTisch.getSelectionModel().select(0);
			
			cbTisch.setDisable(false);
			btnReservieren.setDisable(false);
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

	public void onReservieren(){
		//Uhrzeit aus den Controls holen
		Uhrzeit uhrzeit = new Uhrzeit(Integer.parseInt(cbStunde.getSelectionModel().getSelectedItem()),
				Integer.parseInt(cbMinute.getSelectionModel().getSelectedItem()));
		
		//Reservierung erstellen
		Reservierung reservierung = new Reservierung(dpDatum.getValue(), uhrzeit, tfPersonen.getText(),
				tfName.getText(), Integer.parseInt(cbTisch.getSelectionModel().getSelectedItem()));
		
		// Reservieren
		tischverwaltung.reservieren(reservierung);
		tischverwaltung.speicherDaten();

		// Dialog schlieﬂen
		tfName.getScene().getWindow().hide();
	}
}
