package Restaurant.Datenhaltung;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection 
{
	private Connection conn;
	private Statement statement;
	public static DatabaseConnection db;
	
	public DatabaseConnection()
	{
		try 
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:9910/swt2", "root", "root");
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

}
