package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends Personagem {

	public Manel(Point2D initialPosition) {
		super("JumpMan", initialPosition, 1,false);
	}

	@Override
	public void moveManel(Direction d) {
		super.moveManel(d);
	}



	//GRAVIDADE IMPLEMENTAR NO PERSONAGENS

}
