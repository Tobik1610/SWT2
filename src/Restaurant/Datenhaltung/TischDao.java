package Restaurant.Datenhaltung;

import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.EckigerTisch;
import Restaurant.Fachlogik.Tischverwaltung.RunderTisch;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;

public class TischDao implements ITischDao {

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

		tische.add(new EckigerTisch(1, 0, 0, 80, 80));
		tische.get(0).setSitzplaetze(2);
		tische.add(new RunderTisch(2, 200, 0, 40));
		tische.get(1).setSitzplaetze(5);
		tische.add(new EckigerTisch(3, 100, 50, 80, 80));
		tische.get(2).setSitzplaetze(2);
		tische.add(new EckigerTisch(4, 0, 135, 100, 100));
		tische.get(3).setSitzplaetze(4);
		tische.add(new EckigerTisch(5, 180, 135, 100, 100));
		tische.get(4).setSitzplaetze(4);
		tische.add(new EckigerTisch(6, 20, 230, 100, 160));
		tische.get(5).setSitzplaetze(6);
		tische.get(5).setRotate(90);
		tische.add(new EckigerTisch(6, 190, 230, 100, 160));
		tische.get(6).setSitzplaetze(6);
		tische.get(6).setRotate(90);
	}

}
