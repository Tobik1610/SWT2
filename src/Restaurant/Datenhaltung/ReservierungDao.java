package Restaurant.Datenhaltung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;

public class ReservierungDao implements IReservierungDao {
	
	private final String dateiName = "Reservierungen.ser";

	@Override
	public void speichern(ArrayList<Reservierung> reservierungen) {

		try {
			FileOutputStream fos = new FileOutputStream(dateiName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeInt(reservierungen.size());
			
			for(Reservierung reservierung : reservierungen)
				oos.writeObject(reservierung);
			
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Reservierung> laden() {
		ArrayList<Reservierung> reservierungen = new ArrayList<Reservierung>();
		
		try {
			FileInputStream fis = new FileInputStream(dateiName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			int anzahl = ois.readInt();
			
			for(int i = 0; i < anzahl; i++)
				reservierungen.add((Reservierung) ois.readObject());
			
			ois.close();
			fis.close();
			
		} catch (Exception e)  {
			System.out.println("Keine Reservierungen ladbar");
		}
		return reservierungen;
	}

}
