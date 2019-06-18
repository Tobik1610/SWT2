package Restaurant.Datenhaltung;

import java.util.ArrayList;

import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public interface IReservierungDao {
	
	public void speichern(Reservierung reservierung);
	public ArrayList<Reservierung> laden();
	public void loeschen(Reservierung reservierung);

}
