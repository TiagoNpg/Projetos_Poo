package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.List;

public class Bat extends Personagem implements Interactable, Tickable {

    public Bat(Point2D position) {
        super("Bat", position, 1, 150, 15, 1, true, false);
    }

    @Override
    public void move() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        Point2D currentPos = getPosition();

        // Direções possíveis
        Point2D goLeft = currentPos.plus(Direction.LEFT.asVector());
        Point2D goRight = currentPos.plus(Direction.RIGHT.asVector());
        Point2D goDown = currentPos.plus(Direction.DOWN.asVector());

        Point2D targetPosition;

        // Prioriza mover para baixo se o próximo objeto for escalável
        if (boundaries(goDown)) {
            List<GameObject> objectsDown = room.getObjectsInPosition(goDown);
            for (GameObject object : objectsDown) {
                if (object.isClimbable()) {
                    if (object instanceof JumpMan) {
                        interactsWithHero();
                        return;
                    }
                    setPosition(goDown);
                    return;
                }
            }
        }

        // Escolhe aleatoriamente entre esquerda ou direita
        targetPosition = Math.random() < 0.5 ? goLeft : goRight;

        if (boundaries(targetPosition)) {
            List<GameObject> nextObjects = room.getObjectsInPosition(targetPosition);
            for (GameObject object : nextObjects) {
                if (object instanceof JumpMan) {
                    interactsWithHero();
                    return;
                }
            }
            setPosition(targetPosition);
        }
    }

    @Override
    public void updateTick() {
        move();
    }

    @Override
    public void interactsWithHero() {
        Room currentRoom = GameEngine.getInstance().getCurrentRoom();
        currentRoom.getJumpMan().setHealth(currentRoom.getJumpMan().getHealth() - this.getDamage());
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
    }

    @Override
    public void interaction() {
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this); // Remove o bat
    }

}