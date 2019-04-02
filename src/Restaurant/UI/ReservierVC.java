package Restaurant.UI;

import Restaurant.Datenhaltung.DataModel;

public class ReservierVC {

	private DataModel dataModel;
	private ReservierView reservierView;
	
	public ReservierVC(DataModel dataModel) {
		this.dataModel = dataModel;
		this.reservierView = new ReservierView();
	}
	
	public void show() {
		reservierView.show(dataModel.getPrimaryStage());
	}
}
