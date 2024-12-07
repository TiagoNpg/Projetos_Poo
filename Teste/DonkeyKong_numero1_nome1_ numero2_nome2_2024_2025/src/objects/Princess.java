package objects;

import pt.iscte.poo.utils.Point2D;

public class Princess extends Item {

    public Princess(Point2D position) {
        super("Princess", position, 1, false,false,true); // `true` indica que é um objeto interativo
    }
}
