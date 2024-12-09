package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Item extends GameObject {

    private boolean usable;

    public Item(String name, Point2D position, int layer,boolean solid, boolean climbable,boolean usable) {
        super(name, position, layer, solid, climbable);
        this.usable = usable;
    }

    public boolean isUsable(){
        return usable;
    }

}
