package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Banana extends Item implements Interactable, Tickable {

    public Banana(Point2D position) {
        super("Banana", position, 3, false, false, 10);
    }

    public void move() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        Point2D current = getPosition();
        Point2D goDown = getPosition().plus(Direction.DOWN.asVector());

        GameObject nextObject = room.getObjectForEnemy(goDown);
        if(nextObject instanceof JumpMan) {
            interactsWithHero();
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
        }

        setPosition(goDown); // Atualiza a posição
    }


    @Override
    public void updateTick() {
        //Verificar se a nova posicao ficou fora
        Room currentRoom = GameEngine.getInstance().getCurrentRoom();
        Point2D newPosition = getPosition().plus(Direction.DOWN.asVector());
        // Remove a banana se sair da sala
        if (!currentRoom.isInsideRoom(newPosition)) {
            currentRoom.addToRemoveQueue(this);
            return;
        }
        // Atualiza a posição da banana
        move();

    }

    @Override
    public void interactsWithHero() {
        return;
    }

    @Override
    public void interaction() {
        JumpMan.setHealth(JumpMan.getHealth() - Banana.getEffectValue());
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
    }
}
