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
        if (isArmed)
            System.out.println(armed--);
        if (armed == 0) {
            Room currentRoom = GameEngine.getInstance().getCurrentRoom();
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
            Fire fire_center = new Fire(this.getPosition());
            currentRoom.addObject(fire_center);

            for (GameObject go : GameEngine.getInstance().getCurrentRoom().getGameObjects()) { //elimina tudo à volta
                for (Point2D point : armedPosition.getNeighbourhoodPoints()) {
                    if (go.getPosition().equals(point) && !(go instanceof JumpMan)){
                        ImageGUI.getInstance().removeImage(go);
                        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(go);
                        currentRoom.addObject(new Floor(go.getPosition()));
                        Fire fire = new Fire(go.getPosition());
                        currentRoom.addObject(fire);
                    }
                }
                if (go instanceof JumpMan){ //Explode e o heroi está lá
                    JumpMan.setHealth(JumpMan.getHealth() - 20);
                    System.out.println("FIRE! BOMB! -20 health, curr health: " + JumpMan.getHealth());
                }
            }
        }
    }
}



