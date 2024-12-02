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
	private Room currentRoom = new Room("rooms/room1.txt");


	private GameEngine() {
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

	public Room getRooms() {
		return currentRoom;
	}
}