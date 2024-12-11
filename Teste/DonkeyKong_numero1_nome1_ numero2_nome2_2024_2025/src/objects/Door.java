package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Door extends Structure implements Interactable {

    private static boolean open;

    public Door(Point2D position) {
        super("DoorClosed", position, 0, false, false);
        this.open = false;
    }


    @Override
    public void interactsWithHero() {
        return;
    }

    @Override
    public void interaction() {
        System.out.println("Entrando na porta: ");
        if (this.open){
            // Muda para a próxima sala
            if (GameEngine.getInstance().advanceToNextRoom()) {
                System.out.println("Próxima sala carregada.");
                return;
            } else {
                System.out.println("Não há mais salas. Fim de jogo?");
                return;
            }
        }
    }

    @Override
    public void heroStandsOn(Point2D position) {
        return;
    }

     public static boolean isOpen(){
        return open;
     }
}