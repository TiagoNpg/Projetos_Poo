package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.List;

public class JumpMan extends Personagem implements Tickable {

	private int bombs = 0;
	private Point2D startPos;
	private int lives = 3; //3 vidas


	public JumpMan(Point2D initialPosition) {
		super("JumpMan", initialPosition, 2,60,20,3, true,false);
		this.startPos = initialPosition;
	}

	@Override
	public void move(Direction d) {
		GameEngine gameEngine = GameEngine.getInstance();
		Room room = gameEngine.getCurrentRoom();

		Point2D currentPos = getPosition();
		Point2D nextPos = getPosition().plus(d.asVector());
		Point2D below = new Point2D(nextPos.getX(), nextPos.getY() + 1);

		List<GameObject> currentObjects = room.getObjectsInPosition(currentPos);
		List<GameObject> nextObjects = room.getObjectsInPosition(nextPos);
		List<GameObject> standingOnObjects = room.getObjectsInPosition(below);

		for (GameObject object : nextObjects) {
			if (object instanceof Pickable) {
				((Pickable) object).pickedByHero();
				setPosition(nextPos);
				return;}
		}

		for (GameObject object : nextObjects) {
			if (object instanceof Interactable) {
				((Interactable) object).interaction();
				return;}
		}

		for (GameObject object : standingOnObjects) {
			if (object instanceof Structure) {
				((Structure) object).heroStandsOn(nextPos);}
		}

		for (GameObject object : nextObjects) {
			if (boundaries(nextPos) && !object.isSolid()) {
				if (d == Direction.UP) {
					for (GameObject objectCurr : currentObjects) {
						if (objectCurr.isClimbable()) {
							setPosition(nextPos);
							return;}
					}
					return;}
				if (d == Direction.DOWN) {
					if (object.isClimbable()) {
						setPosition(nextPos);
						return;}
					return;}
				setPosition(nextPos);
				return;}
		}
	}

	@Override
	public void checkDead(){
		if (getHealth() <= 0){
			System.out.println("Morreste!");
			lives--;
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

		List<GameObject> currentObjects = room.getObjectsInPosition(currentPos);
		List<GameObject> standingOnObjects = room.getObjectsInPosition(below);

		for (GameObject object1 : currentObjects){
			for (GameObject object2 : standingOnObjects) {
				if ((object1 instanceof Floor || object1 instanceof Trap) && (object2 instanceof Trap || object2 instanceof Floor)) {
					setPosition(getPosition().plus(Direction.DOWN.asVector()));
				}
			}
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

	public int getBombs(){
		return bombs;
	}


}