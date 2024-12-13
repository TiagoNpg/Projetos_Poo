package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.List;


public class Banana extends Item implements Interactable, Tickable {

    public Banana(Point2D position) {
        super("Banana", position, 3, false, false, 10);
    }

    public void move() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        Point2D goDown = getPosition().plus(Direction.DOWN.asVector());

        List<GameObject> objects = room.getObjectsInPosition(goDown);
        for (GameObject object : objects) {
            if (object instanceof JumpMan) {
                interactsWithHero();
                ImageGUI.getInstance().removeImage(this);
                GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
            }
        }
        setPosition(goDown); // Atualiza a posição
    }


    @Override
    public void updateTick() {
        //Verificar se a nova posicao ficou fora
        Room currentRoom = GameEngine.getInstance().getCurrentRoom();
        Point2D newPosition = getPosition().plus(Direction.DOWN.asVector());
        if (!currentRoom.isInsideRoom(newPosition)) {
            currentRoom.addToRemoveQueue(this);
            return;
        }
        move();
    }

    @Override
    public void interactsWithHero() {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
        System.out.println("Vida atual "+ JumpMan.getHealth());
    }

    @Override
    public void interaction() {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
        System.out.println("Vida atual (2): "+ JumpMan.getHealth());
    }
}
