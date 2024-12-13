package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class JumpMan extends Personagem implements Tickable {

	private int bombs = 0;
	private Point2D startPos;
	private int lives = 3; //3 vidas

	public JumpMan(Point2D initialPosition) {
		super("JumpMan", initialPosition, 2,60,20,true,false);
		this.startPos = initialPosition;
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

		if(standingOnObject instanceof Structure) {
			((Structure) standingOnObject).heroStandsOn(nextPos);
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

	@Override
	public void checkDead(){
		if (getHealth() <= 0){
			System.out.println("Morreste!");
			this.lives--;
			if (lives > 0){
				setPosition(startPos);
				setHealth(60);
			}
			else {
				//game over
			}
		}
	}

	@Override
	public void updateTick() {
		GameEngine gameEngine = GameEngine.getInstance();
		Room room = gameEngine.getCurrentRoom();
		this.checkDead();

		Point2D currentPos = getPosition();

		Point2D below = new Point2D(currentPos.getX(), currentPos.getY() + 1);


		GameObject standingOnObject = room.getObjectManel(currentPos);


		if((standingOnObject instanceof Floor || standingOnObject instanceof Trap) && (room.getObjectManel(below) instanceof Trap || room.getObjectManel(below) instanceof Floor)){
			setPosition(getPosition().plus(Direction.DOWN.asVector()));
		}
	}

	public void addBombs() {
		this.bombs++;
	}

	public void armBomb(){
		if(bombs > 0) {
			Bomb bomb = new Bomb(getPosition());
			ImageGUI.getInstance().addImage(bomb);
			bomb.setArmed();
			GameEngine.getInstance().getCurrentRoom().getGameObjects().add(bomb);
			bombs--;
		}
	}

}