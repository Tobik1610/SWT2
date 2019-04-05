package Restaurant.Datenhaltung;

import java.util.ArrayList;

import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public interface IReservierungDao {
	
	public void speichern(ArrayList<Reservierung> reservierungen);
	public ArrayList<Reservierung> laden();

}
