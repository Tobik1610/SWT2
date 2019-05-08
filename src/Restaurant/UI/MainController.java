package Restaurant.UI;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class MainController {
	
	@FXML
	private Button btnT1, btnT2, btnT3, btnT4, btnT5, btnT6, btnT7, btnZurueck, btnLoeschen;
	
	private int aktiverTisch;
	
	@FXML
	private ListView<String> tischListView;
	
	@FXML 
	private DatePicker dpDatum;
	
	@FXML
	private Label tischLabel;
	
	@FXML
	private ImageView imgLoeschen;
	
	private Tischverwaltung tischverwaltung;
	
	private ArrayList<Reservierung> reservierungen;
	
	public MainController() {
		System.out.println("Start");		
	}
	
	@FXML
	public void initialize() {
		dpDatum.setValue(LocalDate.now());
		dpDatum.valueProperty().addListener((observable, oldDate, newDate)->{ 
			reservierungenAktualisieren();
		});
		
		TischHandler tischHandler = new TischHandler();
		btnT1.setOnAction(tischHandler);
		btnT2.setOnAction(tischHandler);
		btnT3.setOnAction(tischHandler);
		btnT4.setOnAction(tischHandler);
		btnT5.setOnAction(tischHandler);
		btnT6.setOnAction(tischHandler);
		btnT7.setOnAction(tischHandler);
		
		btnZurueck.setVisible(false);
	}
	
	public void setTischverwaltung(Tischverwaltung tischverwaltung) {
		this.tischverwaltung = tischverwaltung;
		reservierungenAktualisieren();
	}
	
	public void reservierungenAktualisieren() {
		LocalDate datum = dpDatum.getValue();
		if(tischverwaltung != null) {
			tischverwaltung.ladeDaten();
			tischListView.getItems().clear();
			if(aktiverTisch == 0) {
				reservierungen = tischverwaltung.getReservierungen(datum);
				for(Reservierung reservierung : reservierungen)
					tischListView.getItems().add(reservierung.toString());	
			}else {
				reservierungen = tischverwaltung.getReservierungen(datum, aktiverTisch);
				for(Reservierung reservierung : reservierungen)
					tischListView.getItems().add(reservierung.toStringOhneTisch());	
			}
		}
	}

	public void onReservieren() {
		try {
			new Reservierungsfenster();
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
		if(index != -1) {
			tischverwaltung.loescheReservierung(reservierungen.get(index));
			reservierungenAktualisieren();
		}
	}
	
	public class TischHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Button btn = (Button) event.getSource();
			aktiverTisch = Integer.parseInt(btn.getId().substring(4));
			String ausgabe = "Tisch "+ aktiverTisch + ":";
			tischLabel.setText(ausgabe);
			reservierungenAktualisieren();	
			btnZurueck.setVisible(true);
		}
		
	}
}
