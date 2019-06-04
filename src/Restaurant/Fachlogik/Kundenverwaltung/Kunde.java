package Restaurant.Fachlogik.Kundenverwaltung;

public class Kunde  {
	
	private int id;
	private String vorname, nachname;
	private Adresse adresse;
	
	public int getId() {
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Kunde()
	{
		
	}
	
	public Kunde(String vorname, String nachname) {	
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public Kunde(int id, String vorname, String nachname, Adresse adresse) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
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
