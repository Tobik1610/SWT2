package Restaurant.Datenhaltung;

import java.util.List;

import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public interface IReservierungDao {
	
	public void speichern(List<Reservierung> reservierungen);
	public List<Reservierung> laden();

}
