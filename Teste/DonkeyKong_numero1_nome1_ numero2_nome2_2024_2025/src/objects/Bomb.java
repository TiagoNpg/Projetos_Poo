package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends Item implements Pickable,Tickable {

    private boolean isArmed = false;
    private int armed = 5;
    private Point2D armedPosition;

    public Bomb(Point2D position) {
        super("Bomb", position, 1, false, false, 300);
    }

    public void setArmed() {
        this.armedPosition = new Point2D(this.getPosition().getX(), this.getPosition().getY());
        this.isArmed = true;
    }

    @Override
    public void pickedByHero() {
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
        GameEngine.getInstance().getCurrentRoom().getJumpMan().addBombs();
    }

    @Override
    public void pickedByG() {
        return;
    }

    @Override
    public void updateTick() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        if (isArmed)
            System.out.println(armed--);
        if (armed == 0) {
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);

            for (GameObject go : GameEngine.getInstance().getCurrentRoom().getGameObjects()) { //elimina tudo à volta
                for (Point2D point : armedPosition.getNeighbourhoodPoints()) {
                    if (go.getPosition().equals(point)){
                        ImageGUI.getInstance().removeImage(go);
                        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(go);

                        Room currentRoom = GameEngine.getInstance().getCurrentRoom();
                        Fire fire = new Fire(go.getPosition());
                        currentRoom.addObject(fire);
                    }
                }
            }
        }
    }
}



