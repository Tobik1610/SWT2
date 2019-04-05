package Restaurant.Ausnahmen;

public class TischNichtVorhandenException extends Exception{
	
	public TischNichtVorhandenException(int tischNr) {
		super("Tisch " + tischNr + " nicht vorhanden!");
	}

}
