package Restaurant.Datenhaltung;

import java.util.ArrayList;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;

public interface IKundenDao {

	public void speichern(ArrayList<Kunde> kunden);
	public ArrayList<Kunde> laden();
}
