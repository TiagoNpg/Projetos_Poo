package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Structure extends GameObject{

    private boolean climbable;
    //private boolean solid;

    public Structure(String name, Point2D position, int layer, boolean climbable) {
        super(name, position, layer);
        this.climbable = climbable;
      //  this.solid = solid;
    }

    public boolean isClimbable() {
        return climbable;
    }

    public void setClimbable(boolean climbable) {
        climbable = climbable;
    }

    //public boolean isSolid() {
    //    return solid;
    //}
}
