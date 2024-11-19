package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Personagens extends GameObject implements Colision{

    public Personagens(String name, Point2D position, int layer) {
        super(name, position, layer);
    }

    @Override
    public boolean interactionStructure(Structure s1) {
        return s1.isClimbable();
    }

    @Override
    public boolean interactItems(Item item) {
        return item.isUsable();
    }
}
