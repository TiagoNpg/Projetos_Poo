package objects;

import pt.iscte.poo.utils.Point2D;

public class Door extends Structure implements Interactable {
//adicionar estado aberta ou fechada

    public Door(Point2D position) {
        super("DoorClosed", position, 0, false, false);
    }


    @Override
    public void interactisWithHero() {
        return;
    }

    @Override
    public void interaction() {

    }
}