package pt.iscte.poo.game;

import objects.*;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
	private String nextRoomFile; // Nome do próximo ficheiro de sala
	private Manel manel;        // Referência ao jogador


	public Room(String fileName) {
		loadRoom(fileName); // Carrega e desenha a sala inicial
	}

	private void loadRoom(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			// Lê a primeira linha: Número da sala e próximo ficheiro
			String[] firstLine = reader.readLine().split(";");
			int roomNumber = Integer.parseInt(firstLine[0].substring(1)); // Ex: "#0" -> 0
			nextRoomFile = firstLine[1];

			// Limpa a interface gráfica para o novo nível
			ImageGUI.getInstance().clearImages();

			// Define o tamanho da sala (10x10)
			int roomWidth = 10;
			int roomHeight = 10;

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
							break;
						case 'S': // Stairs
							Stairs stairs = new Stairs(position);
							ImageGUI.getInstance().addImage(stairs);
							break;
						case 'G': // Gorilla
							Gorilla gorilla= new Gorilla(position);
							ImageGUI.getInstance().addImage(gorilla);
							break;
						case 'H': // Hero (Manel)
							manel = new Manel(position);
							ImageGUI.getInstance().addImage(manel);
							break;
						case '0': // Porta para o próximo nível
							Door door = new Door(position);
							ImageGUI.getInstance().addImage(door); // Cria a porta fechada
							break;
						case 't': // Trap
							Trap trap = new Trap(position);
							ImageGUI.getInstance().addImage(trap); // Cria uma armadilha
							break;
						case 's': // Sword
							Sword sword = new Sword(position);
							ImageGUI.getInstance().addImage(sword); // Cria uma espada
							break;
						default:
							// Outros símbolos ignorados ou não definidos
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

	public void loadNextRoom() {
		if (nextRoomFile != null) {
			loadRoom(nextRoomFile); // Carrega a próxima sala
		}
	}
}

