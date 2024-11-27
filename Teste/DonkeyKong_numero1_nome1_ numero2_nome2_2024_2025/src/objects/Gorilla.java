package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;
import java.util.Random;

public class Gorilla extends Personagem implements Colision, Tickable{

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
