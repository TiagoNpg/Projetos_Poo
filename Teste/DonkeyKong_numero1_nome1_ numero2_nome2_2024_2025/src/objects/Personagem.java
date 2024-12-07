package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class Personagem extends GameObject{

    private static int health;
    private static int damage;

    public Personagem(String name, Point2D position, int layer,int health,int damage, boolean solid, boolean climbable) {
        super(name, position, layer,solid,climbable);
        Personagem.health = health;
        Personagem.damage = damage;
    }

    public void move(Direction d) {}

    public void move(){}


    public boolean boundaries(Point2D position){
        if(position.getX() < 0 || position.getY() < 0 || position.getX() > 9 || position.getY() > 9)
            return false;
        return true;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int newHealth){
        health = newHealth;
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int newDamage) {
        damage = newDamage;
    }
    //PARA PASSAR POR CIMA DE BURACOS IDEIA PALETE PARA UTILIZAR EM BURACOS OU OUTRA CENA
}