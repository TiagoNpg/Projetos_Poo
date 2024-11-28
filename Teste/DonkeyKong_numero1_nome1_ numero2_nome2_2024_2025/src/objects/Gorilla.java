package objects;

import pt.iscte.poo.utils.Point2D;

public class Gorilla extends Personagem implements Interactable, Tickable{

    public Gorilla(Point2D position) {
        super("DonkeyKong", position, 1,false);
    }

    @Override
    public void moveGorilla() {
        super.moveGorilla();
    }


    @Override
    public void interactisWithHero() {
        return;
    }

    @Override
    public void interaction() {

    }

    @Override
    public  void updateTick(){

    }
}
