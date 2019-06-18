package Restaurant.Datenhaltung;

import java.sql.SQLException;
import java.util.ArrayList;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;

public interface IKundenDao {

	public void speichern(Kunde kunde);
	public ArrayList<Kunde> laden();
}
