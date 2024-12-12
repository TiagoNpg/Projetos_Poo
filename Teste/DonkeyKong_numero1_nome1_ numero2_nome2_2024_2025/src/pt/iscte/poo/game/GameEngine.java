package pt.iscte.poo.game;

import objects.GameObject;
import objects.JumpMan;
import objects.Tickable;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class GameEngine implements Observer {

	private static GameEngine INSTANCE;
	private int lastTickProcessed = 0;
	private List<Room> rooms = new ArrayList<>();
	private Room currentRoom;


	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	private GameEngine() {
		loadRooms("rooms/"); // Carrega todas as salas da pasta "rooms/"
		if (!rooms.isEmpty()) {
			currentRoom = rooms.get(0); // Define a sala inicial
			currentRoom.drawRoom(); // Desenha a sala inicial
		}
		ImageGUI.getInstance().update();
	}

	public void setCurrentRoom(int roomIndex) {
		if (roomIndex >= 0 && roomIndex < rooms.size()) {
			currentRoom = rooms.get(roomIndex);
			currentRoom.drawRoom(); // Desenha a nova sala
			System.out.println("Sala atual alterada para: " + roomIndex);
		} else {
			System.err.println("Índice de sala inválido!");
		}
	}

	@Override
	public void update(Observed source) {
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			if (Direction.isDirection(k)) {
				currentRoom.moveManel(Direction.directionFor(k));
			}
			if(ImageGUI.getInstance().keyPressed() == KeyEvent.VK_B){
				GameEngine.getInstance().getCurrentRoom().getJumpMan().armBomb();
			}
		}
		// atualizar estado do jogo a cada tick
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();}
	}


	private void processTick() {
		for (GameObject obj : currentRoom.getGameObjects()) { //correr lista objetos para identificar tickables
			if (obj instanceof Tickable) {
				((Tickable) obj).updateTick();
			}
		}
		currentRoom.processAdditions(); //Adiciona os objetos pendentes
		currentRoom.processRemovals(); // Remove os objetos pendentes
		ImageGUI.getInstance().update();
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;

	}
	//E caso granel
	public Room getCurrentRoom() {
		return currentRoom;
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

	public boolean advanceToNextRoom() {
		int currentRoomIndex = rooms.indexOf(currentRoom);
		// Verifica se há uma próxima sala no array
		if (currentRoomIndex + 1 < rooms.size()) {
			currentRoom = rooms.get(currentRoomIndex + 1);
			// Limpa e desenha a nova sala
			ImageGUI.getInstance().clearImages();
			currentRoom.draw();
			return true; // Mudança bem-sucedida
		}
		return false; // Não há mais salas
	}



}

