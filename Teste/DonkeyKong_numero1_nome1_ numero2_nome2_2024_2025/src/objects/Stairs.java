package objects;

import pt.iscte.poo.utils.Point2D;

public class Stairs extends Structure implements Colision{
    public Stairs(Point2D position) {
        super("Stairs", position, 0, false, true); // Stairs são "climbable"
    }

    @Override
    public void interactisWithHero() {
        return;
    }

    @Override
    public void interaction() {
        return;
    }
}
