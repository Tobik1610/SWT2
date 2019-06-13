package Restaurant.Datenhaltung;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public class ReservierungDao implements IReservierungDao {

	@Override
	public void speichern(Reservierung reservierung) {
		

		try 
		{
			
			String query = "insert into reservierung (datum, uhrzeit, personen, kunde_id, tischNr) values ("
					+ "\"" + reservierung.getDatum().toString() + "\", "
					+ "\"" + reservierung.getUhrzeit().toString() + "\", "
					//+ reservierung.getUhrzeit().getStunde() + "." + reservierung.getUhrzeit().getMinute() + ", "
					+ "\"" + reservierung.getPersonen() + "\", "
					//+ "null, "
					+ reservierung.getKunde().getId() + ", "
					+ reservierung.getTischNr() + "); ";
			
			System.out.println(query);
			DatabaseConnection.getDbCon().insert(query);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}
	
	public void loeschen(Reservierung reservierung)
	{
		String query = "delete from reservierung where reservierung_id =" + reservierung.getId();
		
		try 
		{
			
			DatabaseConnection.getDbCon().delete(query);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<Reservierung> laden() {
		
		ArrayList<Reservierung> reservierungen = new ArrayList<Reservierung>();
		
		String readReservierungen = "select * from reservierung";
		
		// Variablen zum Zwischenspeichern
		int reservierung_id = 0, kunde_id = 0, tischNr = 0;
		String datum = "", uhrzeit = "", personen = "";
		
		try 
		{
		
			ResultSet rs = DatabaseConnection.getDbCon().get(readReservierungen);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();
			
			while(rs.next())
			{
				
				
				for (int i = 1; i <= spalten; i++) 
				{
					switch (i) 
					{
						case 1: reservierung_id = Integer.parseInt(rs.getString(i)); break;
						case 2: datum = rs.getString(i); break;
						case 3: uhrzeit = rs.getString(i); break;
						case 4: personen = rs.getString(i); break;
						case 5: kunde_id = Integer.parseInt(rs.getString(i)); break;
						case 6: tischNr = Integer.parseInt(rs.getString(i)); break;
						default: System.out.println("bla bla ...");
					}
				}
				
				// erstelle LocalDate Objekt
				LocalDate date = LocalDate.parse(datum);
				
				// erstelle Uhrzeit Objekt
				Uhrzeit zeit = new Uhrzeit(Integer.parseInt(uhrzeit.toString().substring(0, 2)), 
						Integer.parseInt(uhrzeit.toString().substring(3)));
				
				// lade kompletten Kunden
				KundenDao kDao = new KundenDao();
				Kunde k = kDao.ladeKunde(kunde_id);
				
				Reservierung res = new Reservierung(reservierung_id, date, zeit, personen, k, tischNr);
				reservierungen.add(res);
			}
				
		}
		catch (Exception e)  
		{
			System.out.println("Keine Reservierungen ladbar");
		}
		
		return reservierungen;
	}

}
