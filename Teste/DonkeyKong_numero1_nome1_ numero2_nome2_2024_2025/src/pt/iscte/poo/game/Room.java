package pt.iscte.poo.game;

import objects.*;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Room {
	private static final int roomWidth = 10;
	private static final int roomHeight = 10;
	private String nextRoomFile; // Nome do próximo ficheiro de sala
	private Manel manel;        // Referência ao jogador
	private Gorilla gorilla;
	private List<GameObject> gameObjects = new ArrayList<>();
	private boolean isDrawn = false; // Indica se a sala já foi desenhada
	private List<GameObject> objectsToRemove = new ArrayList<>();
	private List<GameObject> pendingAdditions = new ArrayList<>();


	public Room(String fileName) {
		loadRoom(fileName); // Carrega e desenha a sala inicial
	}

	public void drawRoom() {
		if (!isDrawn) {
			// Atualiza a interface gráfica com os objetos da sala
			ImageGUI.getInstance().clearImages();
			for (GameObject obj : gameObjects) {
				ImageGUI.getInstance().addImage(obj);
			}
			ImageGUI.getInstance().update();
			isDrawn = true; // Marca a sala como desenhada
		}
	}

	public void loadRoom(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			// Verifica se o arquivo é o room2.txt e pula a primeira linha
			String firstLine = null;

			// Verifica se o arquivo é room2.txt, e não lê a primeira linha se for
			if (!fileName.endsWith("room2.txt")){
				firstLine = reader.readLine();
				// Verifica se a primeira linha é válida
				if (firstLine == null || !firstLine.contains(";")) {
					throw new IllegalArgumentException("Formato inválido na primeira linha do ficheiro: " + fileName);
				}

				String[] parts = firstLine.split(";");
				if (parts.length < 2) {
					throw new IllegalArgumentException("Faltam informações na primeira linha do ficheiro: " + fileName);
				}

				nextRoomFile = parts[1]; // Armazena o próximo arquivo da sala
			}


			// Limpa a interface gráfica e os objetos carregados
			ImageGUI.getInstance().clearImages();
			gameObjects.clear();

			// Preenche a sala inteira com Floor por padrão
			for (int y = 0; y < roomHeight; y++) {
				for (int x = 0; x < roomWidth; x++) {
					Point2D position = new Point2D(x, y);
					ImageGUI.getInstance().addImage(new Floor(position)); // Adiciona o chão
				}
			}

			String line;
			int y = 0; // Linha da grelha
			while ((line = reader.readLine()) != null) {
				for (int x = 0; x < line.length(); x++) {
					char symbol = line.charAt(x);
					Point2D position = new Point2D(x, y);

					// Criação dinâmica dos objetos com base nos símbolos
					switch (symbol) {
						case 'W': // Wall
							Wall wall = new Wall(position);
							ImageGUI.getInstance().addImage(wall);
							gameObjects.add(wall);
							break;
						case 'S': // Stairs
							Stairs stairs = new Stairs(position);
							ImageGUI.getInstance().addImage(stairs);
							gameObjects.add(stairs);
							break;
						//Alteracao meat ************************
						case 'm': // meat
							Meat meat = new Meat(position);
							ImageGUI.getInstance().addImage(meat);
							gameObjects.add(new Floor(position));
							gameObjects.add(meat);
							break;
						case 'P':// Princesa
							Princess princesa = new Princess(position);
							ImageGUI.getInstance().addImage(princesa);
							gameObjects.add(new Floor(position));
							gameObjects.add(princesa);
							break;
						case 'G': // Gorilla
							gorilla= new Gorilla(position);
							ImageGUI.getInstance().addImage(gorilla);
							gameObjects.add(new Floor(position));
							gameObjects.add(gorilla);
							break;
						case 'H': // Hero (Manel)
							manel = new Manel(position);
							ImageGUI.getInstance().addImage(manel);
							gameObjects.add(new Floor(position));
							gameObjects.add(manel);
							break;
						case '0': // Porta para o próximo nível
							Door door = new Door(position);
							ImageGUI.getInstance().addImage(door); // Cria a porta fechada
							gameObjects.add(door);
							break;
						case 't': // Trap
							Trap trap = new Trap(position);
							ImageGUI.getInstance().addImage(trap); // Cria uma armadilha
							gameObjects.add(trap);
							break;
						case 's': // Sword
							Sword sword = new Sword(position);
							ImageGUI.getInstance().addImage(sword); // Cria uma espada
							gameObjects.add(new Floor(position));
							gameObjects.add(sword);
							break;
						case ' '://Floor
							Floor floor = new Floor(position);
							gameObjects.add(floor);
							break;
						default:
							break;
					}
				}
				y++; // Incrementa a linha da grelha
			}

			// Atualiza a GUI
			ImageGUI.getInstance().update();
		} catch (IOException e) {
			System.err.println("Erro ao carregar o ficheiro: " + fileName);
			e.printStackTrace();
		}
	}

	public void moveManel(Direction direction) {
		if (manel!=null){
			manel.move(direction);
			ImageGUI.getInstance().update();
		}
	}

	public void addBanana(Banana banana) {
		pendingAdditions.add(banana);
		ImageGUI.getInstance().addImage(banana);
	}


	public List<GameObject> getObjectsInPosition(Point2D position) {
		List<GameObject> objectsInPosition = new ArrayList<>();
		GameObject floor = null;

		for(GameObject go : gameObjects){
			if(position.equals(go.getPosition())) {
//				if (go instanceof Floor) floor = go;
				objectsInPosition.add(go);
			}
		}
		//DUVIDAS PARA O STOR																// talvez com o layer???
		//objectsInPosition.forEach(o -> System.out.println(o.getPosition() + o.getName())); //COMO N UTILIZAR O NEW FLOOR
//		System.out.println(objectsInPosition.stream().filter(o -> !(o instanceof Manel) && !(o instanceof Floor)).findFirst().orElse(floor));
//		return objectsInPosition.stream().filter(o -> !(o instanceof Manel) && !(o instanceof Floor) ).findFirst().orElse(floor);
		return objectsInPosition;
	}

	public GameObject getObjectManel(Point2D position){
		List<GameObject> gameObjectsInPosition = getObjectsInPosition(position);
		GameObject floor = null;

		for (GameObject go : gameObjectsInPosition){
			if(!(go instanceof Manel) && !(go instanceof Floor)){
				return go;
			}
			if (go instanceof Floor) floor = go;
		}
		return floor;
	}

	public GameObject getObjectForGorilla(Point2D position){
		List<GameObject> gameObjectsInPosition = getObjectsInPosition(position);
		GameObject floor = null;

		for (GameObject go : gameObjectsInPosition){
			if(!(go instanceof Floor)){
				return go;
			}
			floor = go;
		}
		return floor;
	}

	public void draw() {
		for (GameObject go : gameObjects) {
			ImageGUI.getInstance().addImage(go);
		}
	}

	public List<GameObject> getGameObjects(){
		return this.gameObjects;
	}

	public boolean isInsideRoom(Point2D position) { //verificar se posicao esta dentro dos limites da room
			return position.getX() >= 0 && position.getX() <= roomWidth &&
					position.getY() >= 0 && position.getY() <= roomHeight;
	}

	public void addToRemoveQueue(GameObject obj) {
		objectsToRemove.add(obj);
	}

	public void processRemovals() { //limpar objetos a remover que foram adicionados ao array removals
		//nomeadamente bananas que saiam da janela do programa
		for (GameObject obj : objectsToRemove) {
			gameObjects.remove(obj);
			ImageGUI.getInstance().removeImage(obj);
		}
		objectsToRemove.clear(); // limpa a fila de remoções
	}


	public void processAdditions() {
		gameObjects.addAll(pendingAdditions);
		pendingAdditions.clear();
	}

}