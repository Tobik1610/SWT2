package Restaurant.Datenhaltung;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public class ReservierungDao implements IReservierungDao {
	
	private ArrayList<Reservierung> reservierungen;
	
	public ReservierungDao() {
		reservierungen = new ArrayList<Reservierung>();
		initDaten();
	}

	@Override
	public void speichern(ArrayList<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
		
	}

	@Override
	public ArrayList<Reservierung> laden() {
		return reservierungen;
	}
	
	public void initDaten() {
		Date datum = new Date();
		Time uhrzeit = new Time(10000000);
		reservierungen.add(new Reservierung(datum, uhrzeit, 4, "Schmidt", 1));
		reservierungen.add(new Reservierung(datum, uhrzeit, 2, "Schmidt", 2));
		reservierungen.add(new Reservierung(datum, uhrzeit, 6, "Schmidt", 3));
		reservierungen.add(new Reservierung(datum, uhrzeit, 5, "Schmidt", 4));
		reservierungen.add(new Reservierung(datum, uhrzeit, 4, "Schmidt", 5));
		reservierungen.add(new Reservierung(datum, uhrzeit, 9, "Schmidt", 6));
	}

}
