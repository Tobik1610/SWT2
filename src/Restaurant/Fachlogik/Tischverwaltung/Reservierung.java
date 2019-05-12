package Restaurant.Fachlogik.Tischverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;

public class Reservierung implements Serializable {

	private static int ID = 1;
	private int id;
	private LocalDate datum;
	private Uhrzeit uhrzeit;
	private String personen;
	private Kunde kunde;
	private int tischNr;

	public Reservierung(LocalDate datum, Uhrzeit uhrzeit, String personen, Kunde kunde, int tischNr) {
		this.id = ID++;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.personen = personen;
		this.kunde = kunde;
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

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public String toString() {
		return "Tisch: " + tischNr + " " + kunde + " " + uhrzeit + " (" + personen + ")";
	}

	public String toStringOhneTisch() {
		return "" + kunde + " " + uhrzeit + " (" + personen + ")";
	}

}
