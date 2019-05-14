package Restaurant.Fachlogik.Tischverwaltung;

public abstract class Tisch {
	
	private int tischNr;
	private int sitzplaetze;
	
	public Tisch(int tischNr, int sitzplaetze) {
		this.tischNr = tischNr;
		this.sitzplaetze = sitzplaetze;
	}

	public int getTischNr() {
		return tischNr;
	}

	public int getSitzplaetze() {
		return sitzplaetze;
	}

}
