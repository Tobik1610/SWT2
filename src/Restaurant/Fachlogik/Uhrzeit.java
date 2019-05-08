package Restaurant.Fachlogik;

import java.io.Serializable;

public class Uhrzeit implements Serializable{
	
	private int stunde, minute;
	
	public Uhrzeit(int stunde, int minute) {
		if(stunde >= 0 && stunde <=24 && minute >= 0 && minute < 60) {
			this.stunde = stunde;
			this.minute = minute;
		}
		else
			throw new IllegalArgumentException("Keine gültige Uhrzeit!");
	}
	
	public int getStunde() {
		return stunde;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public String toString() {
		String s,m; 
		if(stunde < 10)
			s = "0"+stunde;
		else 
			s = ""+stunde;
		
		if(minute < 10)
			m = "0"+minute;
		else
			m = ""+minute;
		
		return s+":"+m;
	}

}
