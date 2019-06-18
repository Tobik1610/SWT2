import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Restaurant.Fachlogik.Uhrzeit;
import Restaurant.Fachlogik.Kundenverwaltung.Kunde;
import Restaurant.Fachlogik.Kundenverwaltung.Kundenverwaltung;
import Restaurant.Fachlogik.Tischverwaltung.Reservierung;
import Restaurant.Fachlogik.Tischverwaltung.Tisch;
import Restaurant.Fachlogik.Tischverwaltung.Tischverwaltung;

class FachlogikTest {

	Kundenverwaltung kundenver;
	Tischverwaltung tischver;
	Reservierung res;
	@Test
	void test() {
		kundenver = new Kundenverwaltung();
		ArrayList<Kunde> k = new ArrayList<Kunde>();
	    k = kundenver.getKunden();
	    
	    assertEquals("Kevin", k.get(0).getVorname());
	}
	
	@Test
	void testeReservierung() {
		Kunde kunde = new Kunde("Olaf", "Kunz");
		LocalDate date = LocalDate.now();
		
		Uhrzeit zeit = new Uhrzeit(13, 00);
		res = new Reservierung(1, date, zeit,"2", kunde, 2);
		
		assertEquals("Olaf", res.getKunde().getVorname());
		assertEquals("Kunz", res.getKunde().getNachname());
		assertEquals(2, res.getTischNr());
	}
	
	

}
