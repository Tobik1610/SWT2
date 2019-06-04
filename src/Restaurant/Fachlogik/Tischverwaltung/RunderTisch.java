package Restaurant.Fachlogik.Tischverwaltung;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class RunderTisch extends Tisch {

	private double radius, umfang;
	private Circle kreis;
	private Pane pane;
	
	public RunderTisch(int tischNr, double x, double y, double radius) {
		super(tischNr, x, y+radius);
		this.radius = radius;
		this.umfang = 2 * Math.PI * radius;
		setPrefSize(radius, radius);
		erstelleDesign();
	}

	@Override
	public void erstelleDesign() {
		kreis = new Circle(rand / 2 + radius, radius, radius - rand / 2, farbe);
		pane = new Pane();
		pane.getChildren().add(kreis);
		pane.toFront();
		setGraphic(pane);
	}

	@Override
	public int getMaxSitzplaetze() {
		return (int) Math.floor(umfang / stuhlBreiteGes);
	}

	@Override
	public void verteileStuehle() {
		double winkel = 360 / sitzplaetze;
		double radwinkel, rotation;
		for (int i = 0; i < stuehle.length; i++) {
			rotation = winkel * i;
			radwinkel = rotation / 180 * Math.PI;

			stuehle[i].setX(Math.cos(radwinkel) * radius + radius);
			stuehle[i].setY(Math.sin(radwinkel) * radius + rand + stuhlBreite / 2);

			stuehle[i].setRotate(rotation);
		}
		pane.getChildren().addAll(stuehle);
		setGraphic(pane);
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}

}
