package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Gorilla extends Personagem implements Interactable, Tickable {

    public Gorilla(Point2D position) {
        super("DonkeyKong", position, 1, 150, 15, true, false);
    }

    @Override
    public void move() {
        GameEngine gameEngine = GameEngine.getInstance();
        Room room = gameEngine.getCurrentRoom();

        Point2D goLeft = getPosition().plus(Direction.LEFT.asVector());
        Point2D goRight = getPosition().plus(Direction.RIGHT.asVector());

        GameObject nextObject = null;

        if (Math.random() < 0.5 ) {
            if (boundaries(goLeft)){
                nextObject = room.getObjectForGorilla(goLeft);
                System.out.println(nextObject);

                if (!(nextObject instanceof Manel)) {
                    setPosition(getPosition().plus(Direction.LEFT.asVector()));
                }else {
                    interactisWithHero();
                }
            }
        } else {
            if (boundaries(goRight)){
                nextObject = room.getObjectForGorilla(goRight);
                System.out.println(nextObject);

                if (!(nextObject instanceof Manel)) {
                    setPosition(getPosition().plus(Direction.RIGHT.asVector()));
                }else {
                    interactisWithHero();
                }
            }
        }

    }

    @Override
    public void updateTick() {
        move();
        // Probabilidade de 30% para lançar uma banana
        if (Math.random() < 0.3) {
            Point2D bananaPosition = getPosition().plus(Direction.DOWN.asVector());
            Room currentRoom = GameEngine.getInstance().getCurrentRoom();
            Banana banana = new Banana(bananaPosition);
            currentRoom.addBanana(banana);
        }
    }

    @Override
    public void interactisWithHero() {
        Manel.setHealth(getHealth()-Gorilla.getDamage());
        System.out.println("Ataquei o heroi " + Manel.getHealth());
    }

    @Override
    public void interaction() {
        Gorilla.setHealth(getHealth()-Manel.getDamage());
        System.out.println("Ataquei o macaco " + Gorilla.getHealth());
        if(getHealth() <= 0) // Verifica se o gorila ainda está vivo após o ataque
            GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this); // Remove o gorila
    }

}
