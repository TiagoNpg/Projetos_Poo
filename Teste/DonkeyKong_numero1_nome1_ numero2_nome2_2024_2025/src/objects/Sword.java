package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Sword extends Item implements Pickable{

    public Sword(Point2D position) {
        super("Sword", position, 1, false,false,20); // `true` indica que é um objeto interativo
    }


    @Override
    public void pickedByHero() {
        JumpMan.setDamage(Sword.getEffectValue());
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().addToRemoveQueue(this);
    }

    @Override
    public void pickedByG() {

    }
}
