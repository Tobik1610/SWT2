package Restaurant.Fachlogik.Tischverwaltung;

import java.util.ArrayList;

import Restaurant.Datenhaltung.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TischFabrik {

	public ArrayList<Tisch> erstelleTische(ResultSet rs) throws SQLException {
		ArrayList<Tisch> tische = new ArrayList<>();

		// Variabeln zum Zwischenspeichern
		int tischNr, sitzplaetze;
		double x, y, breite, laenge, radius, rotation;
		String typ;

		ResultSetMetaData rsmd = rs.getMetaData();
		int spalten = rsmd.getColumnCount();

		while (rs.next()) {
			// Variabeln initialiseren
			tischNr = 0;
			sitzplaetze = 0;
			x = 0;
			y = 0;
			breite = 0;
			laenge = 0;
			radius = 0;
			rotation = 0;
			typ = "";

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
					typ = rs.getString(i);
					break;
				case 5:
					breite = Double.parseDouble(rs.getString(i));
					break;
				case 6:
					laenge = Double.parseDouble(rs.getString(i));
					break;
				case 7:
					radius = Double.parseDouble(rs.getString(i));
					break;
				case 8:
					rotation = Double.parseDouble(rs.getString(i));
					break;
				case 9:
					sitzplaetze = Integer.parseInt(rs.getString(i));
					break;

				default:
					System.out.println("index out of bounce: > max spaltenanzahl");
				}
			}
			if (typ.equals("eckig"))
				tische.add(new EckigerTisch(tischNr, x, y, breite, laenge, rotation, sitzplaetze));
			else if (typ.equals("rund"))
				tische.add(new RunderTisch(tischNr, x, y, radius, rotation, sitzplaetze));
		}

		return tische;
	}

}
