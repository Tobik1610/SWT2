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
		
		// INSERT INTO `runderTisch`(`x`, `y`, `radius`, `anzReservierungen`, `sitzplaetze`) VALUES (200,0,40,0,5)
		// INSERT INTO eckigerTisch (sitzplaetze, anzReservierungen, x, y, breite, laenge) VALUES (2,0,0,0,80,80)
	}

	@Override
	public  ArrayList<Tisch> laden() 
	{
		ArrayList<Tisch> tische = new ArrayList();	
		String readEckigeTische = "select * from eckigerTisch";
		String readRundeTische = "select * from runderTisch";
		
		
		// fülle ArrayList "tische" mit eckigenTischen
		try 
		{
			ResultSet rs = DatabaseConnection.getDbCon().get(readEckigeTische);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			
			while (rs.next()) 
			{
				EckigerTisch t = new EckigerTisch();
				
				for (int x = 1; x <= spalten; x++) 
				{
					System.out.println("x: " + x);
					System.out.println("inhalt resultset: " + rs.getString(x));
					
					switch(x)
					{
						case 1: t.setTischNr(Integer.parseInt(rs.getString(x))); break;
						case 2: t.setX(Double.parseDouble(rs.getString(x))); break;
						case 3: t.setY(Double.parseDouble(rs.getString(x))); break;
						case 4: t.setBreite(Double.parseDouble(rs.getString(x))); break;
						case 5: t.setLaenge(Double.parseDouble(rs.getString(x))); break;
						case 6: t.setAnzahlReservierungen(Integer.parseInt(rs.getString(x))); break;
						// TODO funktioniert nicht
						case 7: t.setSitzplaetze(Integer.parseInt(rs.getString(x))); break;

						default: System.out.println("index out of bounce: > max spaltenanzahl");	
					}
				}
				tische.add(t);
			}	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		// fülle ArrayList "tische" mit rundenTischen
		try
		{
			ResultSet rs = DatabaseConnection.getDbCon().get(readRundeTische);

			ResultSetMetaData rsmd = rs.getMetaData();
			int spalten = rsmd.getColumnCount();

			
			while (rs.next()) 
			{
				RunderTisch t = new RunderTisch();
				
				for (int x = 1; x <= spalten; x++) 
				{
					System.out.println("x: " + x);
					System.out.println("Inhalt ResultSet: " + rs.getString(x));
					switch(x)
					{
						case 1: t.setTischNr(Integer.parseInt(rs.getString(x))); 				break;
						case 2: t.setX(Double.parseDouble(rs.getString(x)));					break;
						case 3: t.setY(Double.parseDouble(rs.getString(x)));					break;
						case 4: t.setRadius(Double.parseDouble(rs.getString(x)));				break;
						case 5: t.setAnzahlReservierungen(Integer.parseInt(rs.getString(x))); 	break;
						// TODO funktioniert nicht 
						//case 6: t.setSitzplaetze(Integer.parseInt(rs.getString(x)));			break;
						default: System.out.println("index out of bounce: max spaltenanzahl");
					}
				}
				tische.add(t);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return tische;
	}

	public void initDaten() {
		tische = laden();
		/*
		tische = new ArrayList<Tisch>();

		tische.add(new EckigerTisch(1, 0, 0, 80, 80));
		tische.get(0).setSitzplaetze(2);
		*/
		/*
		tische.add(new RunderTisch(2, 200, 0, 40));
		tische.get(1).setSitzplaetze(5);
		tische.add(new EckigerTisch(3, 100, 50, 80, 80));
		tische.get(2).setSitzplaetze(2);
		tische.add(new EckigerTisch(4, 0, 135, 100, 100));
		tische.get(3).setSitzplaetze(4);
		tische.add(new EckigerTisch(5, 180, 135, 100, 100));
		tische.get(4).setSitzplaetze(4);
		tische.add(new EckigerTisch(6, 20, 230, 100, 160));
		tische.get(5).setSitzplaetze(6);
		tische.get(5).setRotate(90);
		tische.add(new EckigerTisch(6, 190, 230, 100, 160));
		tische.get(6).setSitzplaetze(6);
		tische.get(6).setRotate(90);
		*/
	}

}
