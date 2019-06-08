package Restaurant;
	
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Restaurant.Datenhaltung.DatabaseConnection;
import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Kundenverwaltung.Adresse;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import Restaurant.Fachlogik.Tischverwaltung.EckigerTisch;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
import Restaurant.UI.MainView;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new MainView(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


/*
// TEST DB Reservierung SQL Abfragen
public class Main {
	public static void main (String[] args)
	{
		
		ReservierungDao rdao = new ReservierungDao();
		ArrayList<Reservierung> reservierungen = rdao.laden();
		
		System.out.println(reservierungen.get(0).getKunde().getVorname());
	
	}
}
*/

// DATABASE CONNECTION TEST

/*
public class Main {
	
	public static void main(String[] args) 
	{
		
		// Tisch READ
		
		
		// Tisch INSERT
		/*
		String insert = "INSERT INTO eckigerTisch (sitzplaetze, "
				+ "anzReservierungen, x, y, breite, laenge) "
				+ "VALUES (2,0,0,0,"
				+ "80,80)";
		
		try {
			DatabaseConnection.getDbCon().insert(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
*/
	
