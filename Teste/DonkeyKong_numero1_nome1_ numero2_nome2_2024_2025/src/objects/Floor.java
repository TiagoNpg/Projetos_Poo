package objects;

import pt.iscte.poo.utils.Point2D;

public class Floor extends Structure{

	public Floor(Point2D position) {
		super("Floor", position, 0, false, false);
	}

	@Override
	public void heroStandsOn(Point2D position) {
		return; //implementar o gravity
	}

	// Efeito de gravidade: Se o herói está no ar e há um Floor abaixo
//		if (belowObject instanceof Floor && (currentObject instanceof Floor)) {
//			setPosition(below);
//			System.out.println("Herói caiu devido à gravidade para " + below);
//			return;
//		}
}
