package Restaurant.Fachlogik.Tischverwaltung;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class EckigerTisch extends Tisch {

	private double breite, laenge;
	private Rectangle rechteck;
	int[] anzahlStuehle = new int[4];
	double[] belegt = new double[4];
	private Pane pane;

	
	public EckigerTisch()
	{
		
	}
	
	public EckigerTisch(int tischNr, double x, double y, double breite, double laenge) {
		super(tischNr, x, y);
		this.breite = breite;
		this.laenge = laenge;
		setPrefSize(breite, laenge);
		erstelleDesign();
	}
	
	public EckigerTisch(int tischNr, double x, double y, double breite, double laenge, int sitzplaetze) {
		super(tischNr, x, y);
		this.breite = breite;
		this.laenge = laenge;
		this.setSitzplaetze(sitzplaetze);
		setPrefSize(breite, laenge);
		erstelleDesign();
	}

	@Override
	public void erstelleDesign() {
//		Text text = new Text("" + tischNr);
//		text.setX(rand+stuhlTiefe);
//		text.setY(rand+stuhlTiefe+10);
		rechteck = new Rectangle(rand, rand, breite - (rand * 2), laenge - (rand * 2));
		rechteck.setFill(farbe);
		pane = new Pane();
		pane.getChildren().add(rechteck);
//		pane.getChildren().addAll(rechteck, text);
		setGraphic(pane);
	}

	private void stuehleProSeiteBerechnen() {
		int seite = 0;// 0=links, 1=unten, 2=rechts, 3=oben
		for (int i = 0; i < stuehle.length; i++) {
			seite = stuhlHinzufuegen(seite);
		}
	}
	
	public void setBreite(double b)
	{
		this.breite = b;
	}
	
	public void setLaenge(double l)
	{
		this.laenge = l;
	}

	private int stuhlHinzufuegen(int seite) {
		if (seite > 3)
			seite = 0;

		belegt[seite] += stuhlBreiteGes;
		switch (seite) {
		case 0:
			if (belegt[seite] < laenge)
				anzahlStuehle[seite]++;
			else
				stuhlHinzufuegen(++seite);
			break;
		case 1:
			if (belegt[seite] < breite)
				anzahlStuehle[seite]++;
			else
				stuhlHinzufuegen(++seite);
			break;
		case 2:
			if (belegt[seite] < laenge)
				anzahlStuehle[seite]++;
			else
				stuhlHinzufuegen(++seite);
			break;
		case 3:
			anzahlStuehle[seite]++;
		}
		return seite + 2;
	}

	@Override
	public int getMaxSitzplaetze() {
		int stuhlAnzahl = 0;

		stuhlAnzahl += breite / stuhlBreiteGes;
		stuhlAnzahl += laenge / stuhlBreiteGes;
		stuhlAnzahl *= 2;

		return stuhlAnzahl;
	}

	@Override
	public void verteileStuehle() {
		stuehleProSeiteBerechnen();
		int plaziert = 0;
		for (int i = 0; i < anzahlStuehle.length; i++) {
			double start;
			if (i == 0 || i == 2)
				start = (laenge - rand - anzahlStuehle[i] * stuhlBreiteGes) / 2;
			else
				start = (breite + rand - anzahlStuehle[i] * stuhlBreiteGes) / 2;

			for (int j = 0; j < anzahlStuehle[i]; j++) {
				switch (i) {
				case 0:
					stuehle[plaziert].setX(0);
					stuehle[plaziert].setY(rand + start + j * stuhlBreiteGes);
					break;
				case 1:
					stuehle[plaziert].setX(rand + start + j * stuhlBreiteGes);
					stuehle[plaziert].setY(-stuhlTiefe);
					stuehle[plaziert].setRotate(90);
					break;
				case 2:
					stuehle[plaziert].setX(breite - rand);
					stuehle[plaziert].setY(start + rand + j * stuhlBreiteGes);
					break;
				case 3:
					stuehle[plaziert].setX(start + rand + j * stuhlBreiteGes);
					stuehle[plaziert].setY(laenge - rand * 2);
					stuehle[plaziert].setRotate(90);
					break;
				}
				plaziert++;
			}
		}
		pane.getChildren().addAll(stuehle);
		setGraphic(pane);
	}

}
