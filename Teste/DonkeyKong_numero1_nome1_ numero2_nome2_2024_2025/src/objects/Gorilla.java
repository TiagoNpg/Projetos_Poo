package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Gorilla extends Personagem implements Interactable, Tickable{

    public Gorilla(Point2D position) {
        super("DonkeyKong", position, 1,150,15,false,false);
    }

    @Override
    public void move() {
        Point2D goLeft = getPosition().plus(Direction.LEFT.asVector());
        Point2D goRight = getPosition().plus(Direction.RIGHT.asVector());

        if (Math.random() < 0.5) {
            if (boundaries(goLeft))
                setPosition(getPosition().plus(Direction.LEFT.asVector()));
            return;
        } else {
            if(boundaries(goRight))
                setPosition(getPosition().plus(Direction.RIGHT.asVector()));
        }

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
