package Restaurant.Datenhaltung;

import java.util.ArrayList;
import java.util.List;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;

public class TischDao implements ITischDao{
	
	private List<Tisch> tische;
	
	public TischDao() {
		initDaten();
	}

	@Override
	public void speichern(List<Tisch> tische) {
		this.tische = tische;
		
	}

	@Override
	public List<Tisch> laden() {
		return tische;
	}
	
	public void initDaten() {
		tische = new ArrayList<>();
		
		tische.add(new Tisch(1,6));
		tische.add(new Tisch(2,2));
		tische.add(new Tisch(3,6));
		tische.add(new Tisch(4,6));
		tische.add(new Tisch(5,4));
		tische.add(new Tisch(6,10));
	}

}
