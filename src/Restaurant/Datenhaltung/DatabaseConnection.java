package Restaurant.Datenhaltung;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection 
{
	private Connection conn;
	private Statement statement;
	public static DatabaseConnection db;
	
	private DatabaseConnection()
	{
		try 
		{
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:8888/easyReserve?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyReserve?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Tobias1610");
			System.out.println("Connection erfolgreich");	
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	
	public static synchronized DatabaseConnection getDbCon() {
        if ( db == null ) {
            db = new DatabaseConnection();
        }
        return db;
    }
	
	
	public int insert(String insertQuery) throws SQLException
	{
		statement = db.conn.createStatement();
		return statement.executeUpdate(insertQuery);
	}
	
	public ResultSet get(String getQuery) throws SQLException
	{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(getQuery);
        return res;
	}

	public boolean delete(String query) throws SQLException
	{
        statement = db.conn.createStatement();
        return statement.execute(query);
	}
	
}
