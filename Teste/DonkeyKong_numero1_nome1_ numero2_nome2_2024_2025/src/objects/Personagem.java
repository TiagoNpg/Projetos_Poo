package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class Personagem extends GameObject{

    public Personagem(String name, Point2D position, int layer, boolean solid) {
        super(name, position, layer,solid);
    }

    public void moveManel(Direction d) {
        Point2D currentPos = getPosition();
        Point2D nextPos = getPosition().plus(d.asVector());

        GameObject currentObject = GameEngine.getInstance().getRooms().getObject(currentPos);
        GameObject nextObject = GameEngine.getInstance().getRooms().getObject(nextPos);

        if(nextObject == null)
            setPosition(nextPos);
        //CRIAR CLASSE PARA METER VARIAVEIS NO CU

        if (nextPos.getX() < 0 || nextPos.getY() < 0 || nextPos.getX() >= 10
                || nextPos.getY() >= 10 || nextObject.isSolid()) {
            //interaction(nextObject)
            //Temos de ter em conta que podem ser varios objetos no mesmo sitio
            return;
        }

        if(d.equals(Direction.UP))
            if(!((Structure) currentObject).isClimbable())
                return;

        setPosition(nextPos);
    }

    public void moveGorilla(){
        if(Math.random() < 0.5){
            moveManel(Direction.LEFT);
        }else {
            moveManel(Direction.RIGHT);
        }
    }

    public void gravity(){} //PARA PASSAR POR CIMA DE BURACOS IDEIA PALETE PARA UTILIZAR EM BURACOS OU OUTRA CENA
}
