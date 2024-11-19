package objects;

import pt.iscte.poo.utils.Point2D;

public interface Colision {

    boolean interactionStructure(Structure s1);

    boolean interactItems(Item item);


}
