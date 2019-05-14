package Restaurant.UI;

import java.util.regex.Pattern;

import Restaurant.Fachlogik.Kundenverwaltung.Adresse;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import Restaurant.Fachlogik.Kundenverwaltung.Kundenverwaltung;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class KundenController {

	@FXML
	private TextField tfVorname, tfNachname, tfOrt, tfPlz, tfStrasse, tfHausNr;
	@FXML
	private Button btnAnlegen, btnAdresse;

	private Kundenverwaltung kundenverwaltung;
	private Boolean bVorname = false, bNachname = false;
	private Boolean bOrt = false, bPlz = false, bStrasse = false, bHausNr = false;
	private Boolean bErweitert = false;

	@FXML
	public void initialize() {
		kundenverwaltung = new Kundenverwaltung();

		// Listener setzen
		tfVorname.textProperty().addListener((observable, oldText, newText) -> {
			if (!newText.isEmpty())
				bVorname = true;
			else
				bVorname = false;

			eingabenPruefen();
		});

		tfNachname.textProperty().addListener((observable, oldText, newText) -> {
			if (!newText.isEmpty())
				bNachname = true;
			else
				bNachname = false;

			eingabenPruefen();
		});

		tfOrt.textProperty().addListener((observable, oldText, newText) -> {
			if (!newText.isEmpty())
				bOrt = true;
			else
				bOrt = false;

			eingabenPruefen();
		});

		tfPlz.textProperty().addListener((observable, oldText, newText) -> {
			String regex = "\\d*";// Ziffer
			if (Pattern.matches(regex, newText)) {
				if (newText.length() > 4)
					bPlz = true;
				else
					bPlz = false;

				eingabenPruefen();
			}else
				tfPlz.setText(oldText);
		});

		tfStrasse.textProperty().addListener((observable, oldText, newText) -> {
			if (!newText.isEmpty())
				bStrasse = true;
			else
				bStrasse = false;

			eingabenPruefen();
		});

		tfHausNr.textProperty().addListener((observable, oldText, newText) -> {
			String regex = "\\d*";// Ziffer
			if (Pattern.matches(regex, newText)) {
				if (!newText.isEmpty())
					bHausNr = true;
				else
					bHausNr = false;

				eingabenPruefen();
			}else
				tfHausNr.setText(oldText);
		});
	}

	public void eingabenPruefen() {
		if (istAnlegbar())
			anlageAktivieren();
		else
			anlageDeaktivieren();
	}

	public Boolean istAnlegbar() {
		if (!bErweitert)
			return bVorname && bNachname;
		else
			return bVorname && bNachname && bOrt && bPlz && bStrasse && bHausNr;
	}

	public void anlageAktivieren() {
		btnAnlegen.setDisable(false);
	}

	public void anlageDeaktivieren() {
		btnAnlegen.setDisable(true);
	}

	public void onAnlegen() {
		Kunde kunde = new Kunde(tfVorname.getText(), tfNachname.getText());
		if (bErweitert) {
			int plz = Integer.parseInt(tfPlz.getText());
			int hausNr = Integer.parseInt(tfHausNr.getText());
			Adresse adresse = new Adresse(tfOrt.getText(), plz, tfStrasse.getText(), hausNr);
			kunde.setAdresse(adresse);
		}
		kundenverwaltung.kundeAnlegen(kunde);
		kundenverwaltung.speicherDaten();

		// Dialog schlieﬂen
		tfVorname.getScene().getWindow().hide();
	}

	public void onAdresseEinblenden() {
		if (!bErweitert)
			tfVorname.getScene().getWindow().setWidth(850);
		else
			tfVorname.getScene().getWindow().setWidth(400);

		bErweitert = !bErweitert;
		eingabenPruefen();
	}

}
