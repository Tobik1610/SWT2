package Restaurant.UI;

import Restaurant.Datenhaltung.DataModel;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;

public class MainVC {
	
	private DataModel dataModel;
	private MainView mainView;
	
	public MainVC(DataModel dataModel) {
		this.dataModel = dataModel;
		this.mainView = new MainView();
	}
	
	public void show() {
		mainView.show(dataModel.getPrimaryStage());
		
		dataModel.getTischVerwaltung().ladeDaten();
	}

}
