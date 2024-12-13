package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends Structure implements Interactable {
//adicionar estado activated ou nao

    public Trap(Point2D position) {
        super("Trap", position, 1, true, false); //
    }


    @Override
    public void interactsWithHero() {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        System.out.println("Vida atual (TRAP! 1)"+ JumpMan.getHealth());
    }

    @Override
    public void interaction() {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        System.out.println("Vida atual (TRAP! 2)"+ JumpMan.getHealth());
    }

    @Override
    public void heroStandsOn(Point2D position) {
        JumpMan.setHealth(JumpMan.getHealth() - 20);
        System.out.println("Vida atual (TRAP!)"+ JumpMan.getHealth());
    }
}
