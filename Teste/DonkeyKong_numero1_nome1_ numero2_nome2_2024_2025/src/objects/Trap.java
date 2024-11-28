package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends Structure implements Interactable {
//adicionar estado activated ou nao

    public Trap(Point2D position) {
        super("Trap", position, 2, false, false); //
    }


    @Override
    public void interactisWithHero() {
        return ;
    }

    @Override
    public void interaction() {

    }
}
