package Restaurant.UI;

import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;

public class Controller {
	
	private Tischverwaltung tischverwaltung;
	private MainView mainView;
	
	public Controller(Tischverwaltung tischverwaltung) {
		this.tischverwaltung = tischverwaltung;
	}
	
	public void start() {
		mainView = new MainView();
	}

}
