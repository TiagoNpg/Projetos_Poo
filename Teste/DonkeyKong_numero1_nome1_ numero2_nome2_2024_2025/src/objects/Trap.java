package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Trap extends Structure implements Interactable {

    public Trap(Point2D position) {
        super("Trap", position, 1, true, false); //
    }


    @Override
    public void interactsWithHero() {
        return;
    }

    @Override
    public void interaction() {
        return;
    }

    @Override
    public void heroStandsOn(Point2D position) {
        Room currentRoom = GameEngine.getInstance().getCurrentRoom();
        JumpMan jumpMan = currentRoom.getJumpMan();
        jumpMan.setHealth(jumpMan.getHealth() - 20);
    }
}
