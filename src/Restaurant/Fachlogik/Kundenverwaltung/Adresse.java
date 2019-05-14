package Restaurant.Fachlogik.Kundenverwaltung;

import java.io.Serializable;

public class Adresse implements Serializable {
	
	private String ort, strasse;
	private int plz, hausNr;
	
	public Adresse(String ort, int plz, String strasse, int hausNr) {
		this.ort = ort;
		this.plz = plz;
		this.strasse = strasse;
		this.hausNr = hausNr;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public int getHausNr() {
		return hausNr;
	}

	public void setHausNr(int hausNr) {
		this.hausNr = hausNr;
	}

}
