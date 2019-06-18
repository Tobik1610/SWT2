import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import Restaurant.Datenhaltung.DatabaseConnection;

class DbTest {
	
	@Test
	void tesDBConnection() {
		assertEquals(DatabaseConnection.class, DatabaseConnection.getDbCon().getClass());
	}
	
	@Test
	void testInsert() {
		try {
			assertEquals(1, DatabaseConnection.getDbCon().insert("INSERT INTO `kunde`( `vorname`, `nachname`) VALUES ('Paula', 'Prada')"));

			DatabaseConnection.getDbCon().delete("delete from kunde where nachname ='prada'");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
