package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Structure extends GameObject{

    private boolean climbable;

    public Structure(String name, Point2D position, int layer, boolean solid, boolean climbable) {
        super(name, position, layer,solid);
        this.climbable = climbable;
    }

    public boolean isClimbable() {
        return climbable;
    }
}
