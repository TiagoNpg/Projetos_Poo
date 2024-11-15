package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel implements ImageTile {

	private Point2D position;
	
	public Manel(Point2D initialPosition){
		position = initialPosition;
	}
	
	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 1;
	}

	public void move(Direction d) {
		Point2D nextPos = position.plus(d.asVector());
		if(nextPos.getX() < 0 || nextPos.getY() < 0 || nextPos.getY() >= 10 || nextPos.getX() >= 10)
			return;
		position = nextPos;
	}
	
}
