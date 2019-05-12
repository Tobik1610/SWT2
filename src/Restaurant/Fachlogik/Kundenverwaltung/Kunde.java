package Restaurant.Fachlogik.Kundenverwaltung;

import java.io.Serializable;

public class Kunde implements Serializable {
	
	private static int ID = 1;
	private int id;
	private String vorname, nachname;
	private Adresse adresse;
	
	public int getId() {
		return id;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Kunde(String vorname, String nachname) {
		this.id = ID++;
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String toString() {
		return "" + vorname + " " + nachname;
	}

}
