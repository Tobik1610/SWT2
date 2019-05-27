package Restaurant;
	
import java.sql.SQLException;

import Restaurant.Datenhaltung.DatabaseConnection;
import Restaurant.UI.MainView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new MainView(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


// DATABASE CONNECTION TEST
/*
public class Main {
	
	public static void main(String[] args) 
	{
		DatabaseConnection.getDbCon();
		String adresse = "insert into adresse (ort, strasse, plz, hausnr) values ('Selm', 'Lange Straße', 59379, 10)";
		try 
		{
			DatabaseConnection.getDbCon().insert(adresse);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
	
*/