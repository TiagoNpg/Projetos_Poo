package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends Personagem {

	public Manel(Point2D initialPosition) {
		super("JumpMan", initialPosition, 1,100,20,false,false);
	}

	@Override
	public void move(Direction d) {

		GameEngine gameEngine = GameEngine.getInstance();
		Room room = gameEngine.getRooms();

		Point2D currentPos = getPosition();
		Point2D nextPos = getPosition().plus(d.asVector());
		Point2D below = new Point2D(getPosition().getX(), getPosition().getY() - 1);

		GameObject currentObject = room.getObject(currentPos);
		GameObject nextObject = room.getObject(nextPos);
		GameObject standingOnObject = room.getObject(below);

//		if(standingOnObject instanceof Structure) {					// como esta sempre a verificar n faz sentido ser apenas estrutura
//			((Structure) standingOnObject).heroStandsOn(nextPos); //definir isto para estar sempre a verificar Aka meter o metodo no GameObject em vez do structure
//		}

		// Verificar se é possível subir ou descer escadas

		if(boundaries(nextPos) && !nextObject.isSolid()) {
			if (d == Direction.UP && currentObject.isClimbable()) {
				System.out.println(currentObject.isClimbable());
				setPosition(nextPos);
				return;
			}
			if (d == Direction.DOWN && nextObject.isClimbable()) {
				System.out.println(nextObject);
				setPosition(nextPos);
				return;
			}
			if (!(currentObject.isClimbable())) {
				System.out.println("Moving normally to: " + nextPos);
				setPosition(nextPos);
			}
		}

		//Stairs talvez tenha de ser um metodo a parte com o climbable, caso true ent pode, se n n


		//update dps de atualizar a posição

	}

	//GRAVIDADE IMPLEMENTAR NO PERSONAGENS

}
