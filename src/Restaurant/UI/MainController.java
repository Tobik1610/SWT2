package Restaurant.UI;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainController {
	
	@FXML
	private Button btnT1, btnT2, btnT3, btnT4, btnT5, btnT6, btnT7;
	
	@FXML
	private ListView<Reservierung> tischListView;
	
	private Tischverwaltung tischverwaltung;
	
	public MainController() {
		System.out.println("Start");
		tischverwaltung = new Tischverwaltung(new TischDao(), new ReservierungDao());
		tischverwaltung.ladeDaten();
		
		//Liste mit Reservierungen initialisieren
		tischListView = new ListView<Reservierung>();
		tischListView.getItems().addAll(tischverwaltung.getReservierungen());
		tischListView.getItems().add(new Reservierung(new Date(1,12,2019), new Time(17,0,0), 2, "Krämer", 1));
	}

	public void onReservieren() {
		System.out.println("Reservieren");
		try {
			new Reservierungsfenster();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
