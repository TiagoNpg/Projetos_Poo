package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends Personagem implements Interactable, Tickable {

    public Bat(Point2D position) {
        super("Bat", position, 1, 150, 15, true, false);
    }

    @Override
    public void move() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        Point2D currentPos = getPosition();
        Point2D below = new Point2D(currentPos.getX(), currentPos.getY() + 1);

        Point2D goLeft = getPosition().plus(Direction.LEFT.asVector());
        Point2D goRight = getPosition().plus(Direction.RIGHT.asVector());
        Point2D goDown = getPosition().plus(Direction.DOWN.asVector());

        GameObject nextObject = null;

        if ((room.getObjectForEnemy(below).isClimbable()) || (room.getObjectForEnemy(currentPos).isClimbable())) { //se for climbable, desce
            nextObject = room.getObjectForEnemy(goDown);
            if (!(nextObject instanceof JumpMan)) {
                setPosition(getPosition().plus(Direction.DOWN.asVector()));
            } else {
                interactsWithHero();
            }
        } else if (Math.random() < 0.5) {
            if (boundaries(goLeft)) {
                nextObject = room.getObjectForEnemy(goLeft);
                if (!(nextObject instanceof JumpMan)) {
                    setPosition(getPosition().plus(Direction.LEFT.asVector()));
                } else {
                    interactsWithHero();
                }
            }
        } else {
            if (boundaries(goRight)) {
                nextObject = room.getObjectForEnemy(goRight);
                if (!(nextObject instanceof JumpMan)) {
                    setPosition(getPosition().plus(Direction.RIGHT.asVector()));
                } else {
                    interactsWithHero();
                }
            }
        }
    }

    @Override
    public void updateTick() {
        move();
    }

    @Override
    public void interactsWithHero() {
        JumpMan.setHealth(getHealth() - Gorilla.getDamage());
        System.out.println("Ataquei o heroi " + JumpMan.getHealth());
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
    }

    @Override
    public void interaction() {
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this); // Remove o bat
    }

}