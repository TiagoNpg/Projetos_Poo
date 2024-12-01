package pt.iscte.poo.game;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class GameEngine implements Observer {

	private static GameEngine INSTANCE;
	private int lastTickProcessed = 0;
	private List<Room> rooms = new ArrayList<>();
	private Room currentRoom; //eliminar caso granel
	//Caso haja granel
	//private Room currentRoom = new Room("rooms/room0.txt");


	private GameEngine() {
		//E caso granel
		loadRooms("rooms/"); // Carrega todas as salas da pasta "rooms/"
		if (!rooms.isEmpty()) {
			currentRoom = rooms.get(0); // Define a sala inicial
		}
		//E caso granel
		ImageGUI.getInstance().update();
	}

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	@Override
	public void update(Observed source) {

		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			if (Direction.isDirection(k)) {
				currentRoom.moveManel(Direction.directionFor(k));
			}
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			currentRoom.moveGorilla(); //será q é aqui???
			//fazer metodo que abrange todos tickables
			processTick();
		}
	}


	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;
	}
	//E caso granel
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(int roomIndex) { //para definir current room!!
		if (roomIndex >= 0 && roomIndex < rooms.size()) { //intervalo decente
			currentRoom = rooms.get(roomIndex);
		}
	}

	private void loadRooms(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.isDirectory()) {
			File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
			if (files != null) {
				for (File file : files) {
					rooms.add(new Room(file.getPath()));
				} //salas carregadas
			} else { //caso n leia nada
				System.err.println("Nenhuma sala encontrada em " + directoryPath);
			}
		} else { //caminho errado
			System.err.println("Caminho inválido: " + directoryPath);
		}
	}
	//E caso granel
}