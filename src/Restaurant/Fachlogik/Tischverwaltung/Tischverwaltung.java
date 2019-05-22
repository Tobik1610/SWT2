package Restaurant.Fachlogik.Tischverwaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import Restaurant.Datenhaltung.IReservierungDao;
import Restaurant.Datenhaltung.ITischDao;
import Restaurant.Datenhaltung.ReservierungDao;
import Restaurant.Datenhaltung.TischDao;
import Restaurant.Fachlogik.Uhrzeit;

public class Tischverwaltung {
	
	private ITischDao tischDao;
	private IReservierungDao reservierungDao;
	private HashMap<Integer, ArrayList<Reservierung>> tischReservierungen;
	
	public Tischverwaltung() {
		this.tischDao = new TischDao();
		this.reservierungDao = new ReservierungDao();
		tischReservierungen = new HashMap<Integer, ArrayList<Reservierung>>();
		ladeDaten();
	}
	
	public void loescheReservierung(Reservierung reservierung) {
		ArrayList<Reservierung> reservierungen = tischReservierungen.get(reservierung.getTischNr());
		Reservierung r = null;
		for(Reservierung res : reservierungen) {
			if(res.getId() == reservierung.getId()) {
				r = res;
				break;
			}
		}
		tischReservierungen.get(reservierung.getTischNr()).remove(r);
		getTisch(reservierung.getTischNr()).veringereAnzReservierungen();
			
		speicherDaten();
	}
	
	public void ladeDaten() {
		ArrayList<Tisch> tische = tischDao.laden();
		ArrayList<Reservierung> reservierungen = reservierungDao.laden();
		
		for(Tisch tisch : tische)
			tischReservierungen.put(tisch.getTischNr(), new ArrayList<Reservierung>());
		
		for(Reservierung reservierung : reservierungen) {
			if(reservierung.getTischNr() <= tische.size()) {
				tischReservierungen.get(reservierung.getTischNr()).add(reservierung);
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
	
	public ArrayList<Integer> getFreieTische(LocalDate datum, Uhrzeit uhrzeit, int personen){
		Boolean[] freie = new Boolean[getTische().size()];
		Arrays.fill(freie, true);
		ArrayList<Integer> tischNummern = new ArrayList<Integer>();
		ArrayList<Reservierung> reservierungen = getReservierungen(datum);
		
		//Auf Freiheit überprüfen
		for(Reservierung res : reservierungen) {
			if(freie[res.getTischNr()-1]) {
				int zeit1 = res.getUhrzeit().getStunde() * 60 + res.getUhrzeit().getMinute();
				int zeit2 = uhrzeit.getStunde() * 60 + uhrzeit.getMinute();
				int differenz = zeit1 - zeit2;
				if(differenz < 120 && differenz > -120)
					freie[res.getTischNr()-1] = false;
			}			
		}
		//Auf Personenanzahl überprüfen
		ArrayList<Tisch> tische = getTische();
		
		for(int i = 0; i < freie.length; i++)
			if(freie[i] && tische.get(i).getSitzplaetze() >= personen)
				tischNummern.add(i+1);
		
		return tischNummern;
	}
	
	public ArrayList<Tisch> getTische(){
		return tischDao.laden();
	}
	
	public Tisch getTisch(int tischNr) {
		ArrayList<Tisch> tische = getTische();
		for(Tisch t : tische) {
			if(t.getTischNr() == tischNr)
				return t;
		}
		return null;
	}
	
	public void reservieren(Reservierung reservierung){
		int tischNr = reservierung.getTischNr();
		ArrayList<Tisch> tische = tischDao.laden();
		
		if(tischNr <= tischReservierungen.size())
			tischReservierungen.get(tischNr).add(reservierung);
	}
	
	public ArrayList<Reservierung> getReservierungen(){
		
		//Alle Reservierungen laden
		return reservierungDao.laden();
	}
	
	public ArrayList<Reservierung> getReservierungen(LocalDate datum){
		ArrayList<Reservierung> reservierungen = reservierungDao.laden();
		ArrayList<Reservierung> res = new ArrayList<Reservierung>();
		
		//Alle Reservierungen zum Datum heraus filtern
		for(Reservierung reservierung : reservierungen) {
			if(reservierung.getDatum().equals(datum))
				res.add(reservierung);
		}
		return res;
	}
	
	public ArrayList<Reservierung> getReservierungen(LocalDate datum, int tischNr){
		ArrayList<Reservierung> reservierungen = getReservierungen(datum);
		ArrayList<Reservierung> res = new ArrayList<Reservierung>();
		
		//Alle Reservierungen zum Tisch heraus filtern
		for(Reservierung reservierung : reservierungen) {
			if(reservierung.getTischNr() == tischNr)
				res.add(reservierung);
		}
		return res;
	}

}
