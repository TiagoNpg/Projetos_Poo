package objects;

import pt.iscte.poo.game.GameEngine;
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
        System.out.println("Entrando na porta: ");
        // Muda para a próxima sala
        if (GameEngine.getInstance().advanceToNextRoom()) {
            System.out.println("Próxima sala carregada.");
            return;
        } else {
            System.out.println("Não há mais salas. Fim de jogo?");
            return;
        }
    }

    @Override
    public void heroStandsOn(Point2D position) {
        return;
    }
}