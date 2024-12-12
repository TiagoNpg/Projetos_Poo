package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Meat extends Item implements Pickable, Tickable {

    private int tick = 10;
    private boolean good;


    public Meat(Point2D position) {
        super("GoodMeat", position, 1, false,false,20); // `true` indica que é um objeto interativo
        this.good = true;
    }

    @Override
    public void pickedByHero() {
        if (this.good) {
            JumpMan.setDamage(Meat.getEffectValue());
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().getGameObjects().remove(this);
        }
        else {
            JumpMan.setDamage(-(Meat.getEffectValue()));
            ImageGUI.getInstance().removeImage(this);
            GameEngine.getInstance().getCurrentRoom().getGameObjects().remove(this);
        }
    }

    @Override
    public void pickedByG() {

    }

    @Override
    public void updateTick() {
        this.tick--;
        if (this.tick == 0){
            this.good = false;
            this.setName("BadMeat");
        }
    }

}
