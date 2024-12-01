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

        GameObject nextObject;

        //FALTA FAZER IFS PARA VERIFICAR STAIRS JUNTAR TUDO PARA EVITAR O CURRENT
        //UTILIZAR APENAS UMA POSIÇÃO
        //IMPLEMENTAR PARA SE MEXER NO FLOOR

        for (GameObject go: GameEngine.getInstance().getCurrentRoom().getObjects(nextPos)){
            if(go instanceof Interactable) {
                nextObject = go;
                ((Interactable) go).interaction();
                setPosition(nextPos);
            }
            return;
        }


        //CRIAR CLASSE PARA METER VARIAVEIS NO CU


//        if(d.equals(Direction.UP))
//            if(!((Structure) nextObject).isClimbable())
//                return;

        setPosition(nextPos);
        //GameEngine.getInstance().getRooms().
        //update dps de atualizar a posição

    }

    public void moveGorilla(){
        if(Math.random() < 0.5){
            moveManel(Direction.LEFT);
        }else {
            moveManel(Direction.RIGHT);
        }
    }

    public boolean boundaries(Point2D position){
        if(position.getX() < 0 || position.getY() < 0 || position.getX() >= 9
                || position.getY() >= 9)
            return false;
        return true;
    }

    public void gravity(){} //PARA PASSAR POR CIMA DE BURACOS IDEIA PALETE PARA UTILIZAR EM BURACOS OU OUTRA CENA
}
