package Restaurant.Datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
import Restaurant.Fachlogik.Tischverwaltung.TischFabrik;

public class TischDao implements ITischDao {

	private ArrayList<Tisch> tische;

	public TischDao() {
		tische = new ArrayList<Tisch>();
	}

	@Override
	public void speichern(ArrayList<Tisch> tische) {
		this.tische = tische;
	}

	@Override
	public ArrayList<Tisch> laden() {
		String readTische = "select * from tisch";
		
		TischFabrik tf = new TischFabrik();
		
		ResultSet rs;
		try {
			rs = DatabaseConnection.getDbCon().get(readTische);
			return tf.erstelleTische(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Tisch>();
		
	}

}
