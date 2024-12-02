package objects;

import pt.iscte.poo.utils.Point2D;

public class Sword extends Item {

    public Sword(Point2D position) {
        super("Sword", position, 1, false,false,true); // `true` indica que é um objeto interativo
    }
}
