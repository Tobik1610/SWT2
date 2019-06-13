package Restaurant;

import java.util.ArrayList;

public abstract class Subject {
	
	private ArrayList<Observer> obs;
	
	public Subject() {
		obs = new ArrayList<>();
	}
	
	public void entferenObserver(Observer o) {
		obs.remove(o);
	}
	
	public void fuegeObserverHinzu(Observer o) {
		obs.add(o);
	}
	
	public void benachrichtige() {
		for(Observer o : obs)
			o.aktualiseren();
	}

}
