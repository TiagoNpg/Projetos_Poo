package objects;

import pt.iscte.poo.utils.Point2D;

public class Bomb extends Item implements Pickable,Tickable{

    public Bomb(Point2D position) {
        super("Bomb", position, 1, true, false, 300);
    }

    @Override
    public void pickedByHero() {

    }

    @Override
    public void pickedByG() {

    }

    @Override
    public void updateTick() {

    }
}
