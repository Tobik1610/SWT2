package Restaurant.Datenhaltung;

import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;

public class TischDao implements ITischDao{
	
	private ArrayList<Tisch> tische;
	
	public TischDao() {
		tische = new ArrayList<Tisch>();
		initDaten();
	}

	@Override
	public void speichern(ArrayList<Tisch> tische) {
		this.tische = tische;
	}

	@Override
	public ArrayList<Tisch> laden() {
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
