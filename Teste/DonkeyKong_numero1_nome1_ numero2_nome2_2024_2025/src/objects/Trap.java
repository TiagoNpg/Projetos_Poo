package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends Structure {

    public Trap(String name,Point2D position, int layer, int climbable) {
        super("Trap", position, 2, false); //
    }
}
