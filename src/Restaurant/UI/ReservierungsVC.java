package Restaurant.UI;

import java.net.URL;
import java.util.ResourceBundle;

import Restaurant.Datenhaltung.DatenModel;
import javafx.fxml.Initializable;

public class ReservierungsVC implements Initializable{

	private DatenModel dataModel;
	private ReservierungsView reservierView;
	
	public ReservierungsVC(DatenModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public void show() {
		reservierView.show(dataModel.getPrimaryStage());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.reservierView = new ReservierungsView();
	}
}
