package objects;

import pt.iscte.poo.utils.Point2D;

public class Door extends Structure {

    private boolean open;

    public Door(String name,Point2D position, int layer, boolean climbable, boolean open) {
        super("DoorClosed", position, 0, false);
        this.open = open;
    }

}