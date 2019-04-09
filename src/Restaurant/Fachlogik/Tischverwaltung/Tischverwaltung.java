package Restaurant.Fachlogik.Tischverwaltung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Restaurant.Ausnahmen.TischNichtVorhandenException;
import Restaurant.Datenhaltung.IReservierungDao;
import Restaurant.Datenhaltung.ITischDao;

public class Tischverwaltung {
	
	private ITischDao tischDao;
	private IReservierungDao reservierungDao;
	private HashMap<Tisch, ArrayList<Reservierung>> tischReservierungen;
	
	public Tischverwaltung(ITischDao tischDao, IReservierungDao reservierungDao) {
		this.tischDao = tischDao;
		this.reservierungDao = reservierungDao;
		tischReservierungen = new HashMap<Tisch, ArrayList<Reservierung>>();
		ladeDaten();
	}
	
	public void ladeDaten() {
		ArrayList<Tisch> tische = tischDao.laden();
		ArrayList<Reservierung> reservierungen = reservierungDao.laden();
		
		for(Tisch tisch : tische)
			tischReservierungen.put(tisch, new ArrayList<Reservierung>());
		
		for(Reservierung reservierung : reservierungen) {
			if(tische.size() <= reservierung.getTischNr()) {
				tischReservierungen.get(tische.get(reservierung.getTischNr()-1)).add(reservierung);
			}
		}	
	}
	
	public void speicherDaten() {
		ArrayList<Reservierung> reservierungen = new ArrayList<Reservierung>();
		
		for(ArrayList<Reservierung> reservierung : tischReservierungen.values()) {
			reservierungen.addAll(reservierung);
		}
		
		reservierungDao.speichern(reservierungen);
	}
	
	public ArrayList<Tisch> getFreieTische(Reservierung reservierung){
		HashMap<Tisch, ArrayList<Reservierung>> freieTische = (HashMap<Tisch, ArrayList<Reservierung>>) tischReservierungen.clone();
		
		//TODO aussortieren der belegten Tische
		
		return new ArrayList(freieTische.keySet());
	}
	
	public void reservieren(int tischNr, Reservierung reservierung) throws TischNichtVorhandenException {
		ArrayList<Tisch> tische = tischDao.laden();
		
		if(tischReservierungen.size() <= tischNr)
			tischReservierungen.get(tische.get(tischNr)).add(reservierung);
		else
			throw new TischNichtVorhandenException(tischNr);
	}

}
