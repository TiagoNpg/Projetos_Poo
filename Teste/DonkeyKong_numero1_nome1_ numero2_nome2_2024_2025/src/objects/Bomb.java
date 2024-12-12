package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends Item implements Pickable,Tickable{

    private boolean isArmed = false;
    private int armed = 5;

    public Bomb(Point2D position) {
        super("Bomb", position, 1, false, false, 300);
    }

    public void setArmed() {
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
        if(isArmed)
            System.out.println(armed--);
        if(armed == 0){
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);

//            Point2D currentPos = getPosition();
//            List<Point2D> pontosAVolta = this.getPosition().getNeighbourhoodPoints();
//            for(Point2D point: pontosAVolta){
//                GameEngine.getInstance().getCurrentRoom().getObjectForEnemy(point);
//                if(!(go instanceof JumpMan || go instanceof Floor || go instanceof Stairs || go instanceof Wall)){
//                    if(go.getPosition() == point){
//                        ImageGUI.getInstance().removeImage(go);
//                        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(go);
//                    }
//            }


            for(GameObject go : GameEngine.getInstance().getCurrentRoom().getGameObjects()){
                for (Point2D point: this.getPosition().getNeighbourhoodPoints()){
                    if(!(go instanceof JumpMan || go instanceof Floor || go instanceof Stairs || go instanceof Wall)){
                        if(go.getPosition() == point){
                            ImageGUI.getInstance().removeImage(go);
                            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(go);
                        }
                    }
                }
            }
        }
    }
}
