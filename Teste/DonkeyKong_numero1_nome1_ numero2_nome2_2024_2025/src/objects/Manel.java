package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends Personagem {

	public Manel(Point2D initialPosition) {
		super("JumpMan", initialPosition, 1,100,20,true,false);
	}

	@Override
	public void move(Direction d) {

		GameEngine gameEngine = GameEngine.getInstance();
		Room room = gameEngine.getCurrentRoom();

		Point2D currentPos = getPosition();
		Point2D nextPos = getPosition().plus(d.asVector());
		Point2D below = new Point2D(nextPos.getX(), nextPos.getY() + 1);

		GameObject currentObject = room.getObjectManel(currentPos);
		GameObject nextObject = room.getObjectManel(nextPos);
		GameObject standingOnObject = room.getObjectManel(below);

		if(nextObject instanceof Interactable){
			((Interactable) nextObject).interaction();
		}

		if(standingOnObject instanceof Structure) {					// como esta sempre a verificar n faz sentido ser apenas estrutura
			((Structure) standingOnObject).heroStandsOn(nextPos); //definir isto para estar sempre a verificar Aka meter o metodo no GameObject em vez do structure
		}

		if(nextObject instanceof Pickable){
			((Pickable) nextObject).pickedByHero();
		}

		// Verificar se é possível subir ou descer escadas


		if(boundaries(nextPos) && !nextObject.isSolid()) {
			if (d == Direction.UP) {
				if(currentObject.isClimbable()){
					setPosition(nextPos);
					return;
				}
				System.out.println("nao e climbable");
				return;
			}
			if (d == Direction.DOWN) {
				if(nextObject.isClimbable()){
					setPosition(nextPos);
					return;
				}
				return;
			}
			setPosition(nextPos);
		}
		//update dps de atualizar a posição

	}

}
