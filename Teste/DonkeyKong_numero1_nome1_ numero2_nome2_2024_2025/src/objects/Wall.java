package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends Structure {

	public Wall(Point2D position) {
		super("Wall", position, 2, true, false);
	}

	@Override
	public void heroStandsOn(Point2D position) {
		return;
	}
}
