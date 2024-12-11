package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Meat extends Item implements Pickable {

    public Meat(Point2D position) {
        super("GoodMeat", position, 1, false,false,20); // `true` indica que é um objeto interativo
    }

    @Override
    public void pickedByHero() {
        GameEngine.getInstance().getCurrentRoom().getJumpMan().setDamage(Meat.getEffectValue());
        ImageGUI.getInstance().removeImage(this);
        GameEngine.getInstance().getCurrentRoom().getGameObjects().remove(this);
    }



}
