package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;
import java.util.List;

public class Manel extends Personagens {

	public Manel(Point2D initialPosition) {
		super("JumpMan", initialPosition, 1,false);
	}

	public void move(Direction d) {
		Point2D nextPos = getPosition().plus(d.asVector());

		GameObject nextObject = GameEngine.getInstance().getRooms().getObject(nextPos);

		if (nextPos.getX() < 0 || nextPos.getY() < 0 || nextPos.getX() >= 10
				|| nextPos.getY() >= 10 || nextObject.isSolid()) {
			return; //sou um macaco
		}

		setPosition(nextPos);
	}

}
