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
	private Room currentRoom = new Room("rooms/room0.txt");


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
			System.out.println("Keypressed " + k);
			if (Direction.isDirection(k)) {
				System.out.println("Direction! ");
				currentRoom.moveManel(Direction.directionFor(k));
			}
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}


	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;
	}

	public Room getRooms() {
		return currentRoom;
	}
}
