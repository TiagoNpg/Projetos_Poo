package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Structure extends GameObject{

    private boolean climbable;
    //private boolean solid;

    public Structure(String name, Point2D position, int layer, boolean solid) {
        super(name, position, layer,solid);
    }

}
