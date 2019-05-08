package Restaurant.Fachlogik.Tischverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import Restaurant.Fachlogik.Uhrzeit;

public class Reservierung implements Serializable{
	
	private static int anzahl = 1;
	private int id;
	private LocalDate datum;
	private Uhrzeit uhrzeit;
	private String personen;
	private String name;
	private int tischNr;
	
	public Reservierung(LocalDate datum, Uhrzeit uhrzeit, String personen, String name, int tischNr) {
		this.id = anzahl++;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.personen = personen;
		this.name = name;
		this.tischNr = tischNr;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getTischNr() {
		return tischNr;
	}
	
	public void setTischNr(int tischNr) {
		this.tischNr = tischNr;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Uhrzeit getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(Uhrzeit uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public String getPersonen() {
		return personen;
	}

	public void setPersonen(String personen) {
		this.personen = personen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Tisch: "+tischNr+" "+name+" "+uhrzeit+" ("+personen+")";
	}
	
	public String toStringOhneTisch() {
		return ""+name+" "+uhrzeit+" ("+personen+")";
	}

}
