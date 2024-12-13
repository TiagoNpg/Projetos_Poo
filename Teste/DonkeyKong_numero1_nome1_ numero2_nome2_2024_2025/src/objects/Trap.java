package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends Structure implements Interactable {
//adicionar estado activated ou nao

    public Trap(Point2D position) {
        super("Trap", position, 1, true, false); //
    }


    @Override
    public void interactsWithHero() {
        return ;
    }

    @Override
    public void interaction() {

    }

    @Override
    public void heroStandsOn(Point2D position) {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        System.out.println("Standing on trap -20 health, curr health: " + JumpMan.getHealth());
    }
}
