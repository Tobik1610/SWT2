package Restaurant.Fachlogik.Tischverwaltung;

import java.util.HashMap;
import java.util.List;

import Restaurant.Datenhaltung.IReservierungDao;
import Restaurant.Datenhaltung.ITischDao;

public class Tischverwaltung {
	
	private ITischDao tischDao;
	private IReservierungDao reservierungDao;
	private HashMap<Tisch, List<Reservierung>> tischReservierungen;
	
	public Tischverwaltung(ITischDao tischDao, IReservierungDao reservierungDao) {
		this.tischDao = tischDao;
		this.reservierungDao = reservierungDao;
	}
	
	public HashMap<Tisch, List<Reservierung>> ladeDaten() {
		return null;
	}
	
	public List<Tisch> getFreieTische(Reservierung reservierung){
		return null;
	}
	
	public void reservieren(int tischNr, Reservierung reservierung) {
		
	}

}
