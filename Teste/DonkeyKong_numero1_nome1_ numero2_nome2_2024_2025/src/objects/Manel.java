package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends Personagem {

	public Manel(Point2D initialPosition) {
		super("JumpMan", initialPosition, 1,100,20,true,false);
	}


	@Override
	public void move(Direction d) {
		GameEngine gameEngine = GameEngine.getInstance();
		Room room = gameEngine.getCurrentRoom();
		Point2D currentPos = getPosition();
		Point2D nextPos = getPosition().plus(d.asVector());
		Point2D below = new Point2D(nextPos.getX(), nextPos.getY() + 1);

		GameObject currentObject = room.getObjectManel(currentPos);
		GameObject nextObject = room.getObjectManel(nextPos);
		GameObject belowObject = room.getObjectManel(below);

		// Efeito de gravidade: Se o herói está no ar e há um Floor abaixo
		if (belowObject instanceof Floor && (currentObject instanceof Floor)) {
			setPosition(below);
			System.out.println("Herói caiu devido à gravidade para " + below);
			return;
		}

		//Verificar se é possivel subir ou n escadas
		if(boundaries(nextPos) && !nextObject.isSolid()) {
			if (d == Direction.UP) {
				if(currentObject.isClimbable()){
					System.out.println(currentObject.isClimbable());
					setPosition(nextPos);
					return;
				}
				System.out.println("nao e climbable");
				return;
			}
			if (d == Direction.DOWN) {
				if(nextObject.isClimbable()){
					System.out.println(currentObject.isClimbable());
					setPosition(nextPos);
					return;
				}
				return;
			}
			System.out.println("Moving normally to: " + nextPos);
			setPosition(nextPos);

		// Movimenta o herói se o próximo objeto não for sólido
		if (boundaries(nextPos) && (nextObject == null || !nextObject.isSolid())) {
			setPosition(nextPos);
			System.out.println("Herói moveu para " + nextPos);
		} else {
			System.out.println("Movimento bloqueado para " + nextPos);
		}


		//INTERACAO GORILA
		// Se o próximo objeto for um gorila
		if (nextObject instanceof Gorilla) {
			Gorilla gorilla = (Gorilla) nextObject;
			gorilla.interaction(); // O herói ataca o gorila

			// Verifica se o gorila ainda está vivo após o ataque
			if (gorilla.getHealth() <= 0) {
				room.addToRemoveQueue(gorilla); // Remove o gorila
				System.out.println("Gorila removido!");
			} else {
				System.out.println("Gorila ainda vivo com " + gorilla.getHealth() + " de vida.");
				return; // Impede o movimento se o gorila ainda estiver vivo
			}
		}

		// Se próximo objeto for interactable - zimba na interacao.
		if(nextObject instanceof Interactable){
			((Interactable) nextObject).interaction();
		}
		// Caso heroi esteja numa estrutura, verificar se é uma trap
		if(belowObject instanceof Structure) {					// como esta sempre a verificar n faz sentido ser apenas estrutura
			((Structure) belowObject).heroStandsOn(nextPos); //definir isto para estar sempre a verificar Aka meter o metodo no GameObject em vez do structure
		}
		// Se for um pickable
		if(nextObject instanceof Pickable){
			((Pickable) nextObject).pickedByHero();
			System.out.println(Manel.getDamage());
		}


			// Verifica se o próximo objeto é uma porta
			if (nextObject instanceof Door) {
				System.out.println("Entrando na porta: " + nextObject);

				// Muda para a próxima sala
				if (gameEngine.advanceToNextRoom()) {
					System.out.println("Próxima sala carregada.");
					return;
				} else {
					System.out.println("Não há mais salas. Fim de jogo?");
					return;
				}
			}
		}
	}

	//GRAVIDADE IMPLEMENTAR NO PERSONAGENS

	//add health para a meat

}
