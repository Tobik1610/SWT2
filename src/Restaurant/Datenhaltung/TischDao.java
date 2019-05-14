package Restaurant.Datenhaltung;

import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.EckigerTisch;
import Restaurant.Fachlogik.Tischverwaltung.RunderTisch;
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
		tische = new ArrayList<Tisch>();
		
		tische.add(new EckigerTisch(1,2));
		tische.add(new RunderTisch(2,3));
		tische.add(new EckigerTisch(3,2));
		tische.add(new EckigerTisch(4,4));
		tische.add(new EckigerTisch(5,4));
		tische.add(new EckigerTisch(6,6));
		tische.add(new EckigerTisch(7,6));
	}

}
