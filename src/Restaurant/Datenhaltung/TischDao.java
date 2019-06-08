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
		initDaten();
	}

	@Override
	public void speichern(ArrayList<Tisch> tische) {
		this.tische = tische;

		// INSERT INTO `runderTisch`(`x`, `y`, `radius`, `anzReservierungen`,
		// `sitzplaetze`) VALUES (200,0,40,0,5)
		// INSERT INTO eckigerTisch (sitzplaetze, anzReservierungen, x, y, breite,
		// laenge) VALUES (2,0,0,0,80,80)
	}

	@Override
	public ArrayList<Tisch> laden() {
		ArrayList<Tisch> tische = new ArrayList();
		String readEckigeTische = "select * from eckigerTisch";
		String readRundeTische = "select * from runderTisch";

		// Variabeln zum Zwischenspeichern
		int tischNr = 0, anzahlReservierungen = 0, sitzplaetze = 0;
		double x = 0, y = 0, breite = 0, laenge = 0, radius = 0;

		// fülle ArrayList "tische" mit eckigenTischen
		try {
			ResultSet rs = DatabaseConnection.getDbCon().get(readEckigeTische);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			while (rs.next()) {
				tischNr = 0; 
				anzahlReservierungen = 0; 
				sitzplaetze = 0;
				x = 0;
				y = 0; 
				breite = 0; 
				laenge = 0; 
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
						anzahlReservierungen = Integer.parseInt(rs.getString(i));
						break;
					case 7:
						sitzplaetze = Integer.parseInt(rs.getString(i));
						break;

					default:
						System.out.println("index out of bounce: > max spaltenanzahl");
					}
				}
				EckigerTisch t = new EckigerTisch(tischNr, x, y, breite, laenge);
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
				anzahlReservierungen = 0; 
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
						anzahlReservierungen = Integer.parseInt(rs.getString(i));
						break;
					case 6:
						sitzplaetze = Integer.parseInt(rs.getString(i));
						break;
					default:
						System.out.println("index out of bounce: max spaltenanzahl");
					}
				}
				RunderTisch t = new RunderTisch(tischNr, x, y, radius); 
				t.setSitzplaetze(sitzplaetze);
				tische.add(t);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return tische;
	}

	public void initDaten() {
		tische = laden();
		
		tische.get(5).setRotate(90); 
		tische.get(6).setRotate(90);
		
		/*
		 * tische = new ArrayList<Tisch>();
		 * 
		 * tische.add(new EckigerTisch(1, 0, 0, 80, 80));
		 * tische.get(0).setSitzplaetze(2);
		 */
		/*
		 * tische.add(new RunderTisch(2, 200, 0, 40)); tische.get(1).setSitzplaetze(5);
		 * 
		 * public EckigerTisch(int tischNr, double x, double y, double breite, double laenge)
		 * tische.add(new EckigerTisch(3, 100, 50, 80, 80));
		 * tische.get(2).setSitzplaetze(2); 
		 * tische.add(new EckigerTisch(4, 0, 135, 100,
		 * 100)); tische.get(3).setSitzplaetze(4); 
		 * tische.add(new EckigerTisch(5, 180,
		 * 135, 100, 100)); tische.get(4).setSitzplaetze(4); 
		 * tische.add(newEckigerTisch(6, 20, 230, 100, 160)); 
		 * tische.get(5).setSitzplaetze(6);
		 * 
		 * tische.get(5).setRotate(90); 
		 * tische.add(new EckigerTisch(6, 190, 230, 100,
		 * 160)); 
		 * tische.get(6).setSitzplaetze(6); 
		 * tische.get(6).setRotate(90);
		 */
	}

}
