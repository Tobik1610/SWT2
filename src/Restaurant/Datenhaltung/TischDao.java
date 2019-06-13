package Restaurant.Datenhaltung;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.EckigerTisch;
import Restaurant.Fachlogik.Tischverwaltung.RunderTisch;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;

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
		ArrayList<Tisch> tische = new ArrayList<>();
		String readEckigeTische = "select * from eckigerTisch";
		String readRundeTische = "select * from runderTisch";

		// Variabeln zum Zwischenspeichern
		int tischNr, sitzplaetze;
		double x, y, breite, laenge, radius, rotation;

		// fülle ArrayList "tische" mit eckigenTischen
		try {
			ResultSet rs = DatabaseConnection.getDbCon().get(readEckigeTische);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			while (rs.next()) {
				tischNr = 0;
				sitzplaetze = 0;
				x = 0;
				y = 0;
				breite = 0;
				laenge = 0;
				rotation = 0;
				for (int i = 1; i <= spalten; i++) {
					switch (i) {
					case 1:
						tischNr = Integer.parseInt(rs.getString(i));
						break;
					case 2:
						x = Double.parseDouble(rs.getString(i));
						break;
					case 3:
						y = Double.parseDouble(rs.getString(i));
						break;
					case 4:
						breite = Double.parseDouble(rs.getString(i));
						break;
					case 5:
						laenge = Double.parseDouble(rs.getString(i));
						break;
					case 6:
						rotation = Double.parseDouble(rs.getString(i));
						break;
					case 7:
						sitzplaetze = Integer.parseInt(rs.getString(i));
						break;

					default:
						System.out.println("index out of bounce: > max spaltenanzahl");
					}
				}
				EckigerTisch t = new EckigerTisch(tischNr, x, y, breite, laenge, rotation);
				t.setSitzplaetze(sitzplaetze);
				tische.add(t);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// fülle ArrayList "tische" mit rundenTischen
		try {
			ResultSet rs = DatabaseConnection.getDbCon().get(readRundeTische);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			while (rs.next()) {
				tischNr = 0;
				rotation = 0;
				sitzplaetze = 0;
				x = 0;
				y = 0;
				radius = 0;
				for (int i = 1; i <= spalten; i++) {
					switch (i) {
					case 1:
						tischNr = Integer.parseInt(rs.getString(i));
						break;
					case 2:
						x = Double.parseDouble(rs.getString(i));
						break;
					case 3:
						y = Double.parseDouble(rs.getString(i));
						break;
					case 4:
						radius = Double.parseDouble(rs.getString(i));
						break;
					case 5:
						rotation = Double.parseDouble(rs.getString(i));
						break;
					case 6:
						sitzplaetze = Integer.parseInt(rs.getString(i));
						break;
					default:
						System.out.println("index out of bounce: max spaltenanzahl");
					}
				}
				RunderTisch t = new RunderTisch(tischNr, x, y, radius, rotation);
				t.setSitzplaetze(sitzplaetze);
				tische.add(t);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return tische;
	}

}
