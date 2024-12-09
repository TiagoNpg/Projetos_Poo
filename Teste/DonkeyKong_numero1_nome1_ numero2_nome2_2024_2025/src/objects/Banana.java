package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Banana extends Item implements Interactable, Tickable {

    public Banana(Point2D position) {
        super("Banana", position, 3, false, false, true);
    }

    public void move() {
        Point2D goDown = getPosition().plus(Direction.DOWN.asVector());
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
    public void interactisWithHero() {

    }

    @Override
    public void interaction() {
        // Deixar vazio ou implementar interação específica
    }
}
