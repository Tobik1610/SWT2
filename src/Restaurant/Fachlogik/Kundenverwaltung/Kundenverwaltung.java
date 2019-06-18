package Restaurant.Fachlogik.Kundenverwaltung;

import java.util.ArrayList;
import Restaurant.Fachlogik.Subject;
import Restaurant.Datenhaltung.IKundenDao;
import Restaurant.Datenhaltung.KundenDao;

public class Kundenverwaltung extends Subject{
	
	private IKundenDao kundenDao;
	private ArrayList<Kunde> kunden;
	
	public Kundenverwaltung() {
		this.kundenDao = new KundenDao();
		ladeDaten();
	}
	
	public void ladeDaten() {
		kunden = kundenDao.laden();
		benachrichtige();
	}
	
	
	public ArrayList<Kunde> getKunden(){
		return kunden;
	}
	
	public ArrayList<Kunde> getKunden(String name){
		ArrayList<Kunde> k = new ArrayList<Kunde>();
		String ganzerName;
		for(Kunde kunde : kunden) {
			ganzerName = kunde.getVorname() + " " + kunde.getNachname();
			if(kunde.getVorname().contains(name) || kunde.getNachname().contains(name) || ganzerName.contains(name)) {
				k.add(kunde);
			}
		}
		return k;
	}
	
	public void kundeAnlegen(Kunde kunde) {
		kundenDao.speichern(kunde);
		ladeDaten();
	}

}
