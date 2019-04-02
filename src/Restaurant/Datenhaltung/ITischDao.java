package Restaurant.Datenhaltung;

import java.util.List;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;

public interface ITischDao {

	public void speichern(List<Tisch> tische);
	public List<Tisch> laden();
}
