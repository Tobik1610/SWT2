package Restaurant.UI;

import java.net.URL;
import java.util.ResourceBundle;

import Restaurant.Datenhaltung.DatenModel;
import javafx.fxml.Initializable;

public class MainVC implements Initializable{
	
	private DatenModel datenModel;
	private MainView mainView;
	
	public MainVC(DatenModel datenModel) {
		this.datenModel = datenModel;
	}
	
	public void show() {
		mainView.show(datenModel.getPrimaryStage());
		
		datenModel.getTischVerwaltung().ladeDaten();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mainView = new MainView();
	}

}
