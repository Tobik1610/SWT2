package Restaurant.Datenhaltung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;

public class KundenDao implements IKundenDao{

	private final String dateiName = "Kunden.ser";

	@Override
	public void speichern(ArrayList<Kunde> kunden) {

		try {
			FileOutputStream fos = new FileOutputStream(dateiName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeInt(kunden.size());
			
			for(Kunde kunde : kunden)
				oos.writeObject(kunde);
			
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
	public ArrayList<Kunde> laden() {
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		
		try {
			FileInputStream fis = new FileInputStream(dateiName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			int anzahl = ois.readInt();
			
			for(int i = 0; i < anzahl; i++)
				kunden.add((Kunde) ois.readObject());
			
			ois.close();
			fis.close();
			
		} catch (Exception e)  {
			System.out.println("Keine Kunden ladbar");
		}
		return kunden;
	}

}
