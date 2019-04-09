package Restaurant.UI;

import Restaurant.Datenhaltung.DatenModel;

public class MainVC {
	
	private DatenModel datenModel;
	private MainView mainView;
	
	public MainVC(DatenModel datenModel) {
		this.datenModel = datenModel;
		this.mainView = new MainView();
	}
	
	public void show() {
		mainView.show(datenModel.getPrimaryStage());
		
		datenModel.getTischVerwaltung().ladeDaten();
	}

}
