package Restaurant.Datenhaltung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Restaurant.Fachlogik.Kundenverwaltung.Adresse;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;

public class KundenDao implements IKundenDao {

	private final String dateiName = "Kunden.ser";

	@Override
	public void speichern(Kunde kunde) {

		// Database
		String query_kunde = "";

		if (kunde.getAdresse() != null) {
			query_kunde = "insert into kunde (vorname, nachname, ort, strasse, plz, hausnr) values ('"
					+ kunde.getVorname() + "', '" + kunde.getNachname() + "', '" + kunde.getAdresse().getOrt() + "', '"
					+ kunde.getAdresse().getStrasse() + "', " + kunde.getAdresse().getPlz() + ", "
					+ kunde.getAdresse().getHausNr() + ");";
		} else {
			query_kunde = "insert into kunde (vorname, nachname, ort, strasse, plz, hausnr) values ('"
					+ kunde.getVorname() + "', '" + kunde.getNachname() + "', " + "null, null, null, null);";
		}

		System.out.println(query_kunde);

		try {
			DatabaseConnection.getDbCon().insert(query_kunde);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Kunde ladeKunde(int kunde_id)
	{
		String readKunde = "select * from kunde where kunde_id=" + kunde_id;

		try {
			ResultSet rs = DatabaseConnection.getDbCon().get(readKunde);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			while (rs.next()) {

				Adresse a = new Adresse();
				Kunde k = new Kunde();

				for (int x = 1; x <= spalten; x++) 
				{

					switch (x) {
					// Spalte ID
					case 1:
						k.setId(Integer.parseInt(rs.getString(x)));
						break;
					// Spalte Vorname
					case 2:
						k.setVorname(rs.getString(x));
						break;
					// Spalte Nachname
					case 3:
						k.setNachname(rs.getString(x));
						break;

					case 4: {
						// wenn Adresse ausgefüllt und Daten nicht null
						if (rs.getString(x) != null) {
							a.setOrt(rs.getString(x));
							break;
						}
						// ansonsten Abbrechen der Schleife bzw. DB-Spalte da Rest null-Einträge
						else {
							x = spalten + 1;
							break;
						}
					}

					case 5:
						a.setStrasse(rs.getString(x));
						break;
					case 6:
						a.setPlz(Integer.parseInt(rs.getString(x)));
						break;
					case 7:
						a.setHausNr(Integer.parseInt(rs.getString(x)));
						break;

					default:
						System.out.println("index out of bounce: außerhalb der max spaltenanzahl");

					}

				}
				if(a.getOrt() != null)
					k.setAdresse(a);
				return k;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public ArrayList<Kunde> laden() {

		// http://www.straub.as/java/jdbc/resultset.html

		ArrayList<Kunde> kunden = new ArrayList<Kunde>();

		String readKunden = "select * from kunde";

		try {
			ResultSet rs = DatabaseConnection.getDbCon().get(readKunden);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			while (rs.next()) {

				Adresse a = new Adresse();
				Kunde k = new Kunde();

				for (int x = 1; x <= spalten; x++) {

					switch (x) {
					// Spalte ID
					case 1:
						k.setId(Integer.parseInt(rs.getString(x)));
						break;
					// Spalte Vorname
					case 2:
						k.setVorname(rs.getString(x));
						break;
					// Spalte Nachname
					case 3:
						k.setNachname(rs.getString(x));
						break;

					case 4: {
						// wenn Adresse ausgefüllt und Daten nicht null
						if (rs.getString(x) != null) {
							a = new Adresse();
							k.setAdresse(a);
							a.setOrt(rs.getString(x));
							break;
						}
						// ansonsten Abbrechen der Schleife bzw. DB-Spalte da Rest null-Einträge
						else {
							x = spalten + 1;
							break;
						}
					}

					case 5:
						a.setStrasse(rs.getString(x));
						break;
					case 6:
						a.setPlz(Integer.parseInt(rs.getString(x)));
						break;
					case 7:
						a.setHausNr(Integer.parseInt(rs.getString(x)));
						break;

					default:
						System.out.println("index out of bounce: außerhalb der max spaltenanzahl");

					}

				}
				if(a.getOrt() != null)
					k.setAdresse(a);
				
				kunden.add(k);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return kunden;
	}
}
