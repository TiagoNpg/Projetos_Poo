package objects;

import pt.iscte.poo.utils.Point2D;

public class Fire extends GameObject implements Interactable, Tickable {

    public Fire(Point2D position) {
        super("Fire_old", position, 1, false,false);
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
    public void updateTick() {
        return;
    }
}
