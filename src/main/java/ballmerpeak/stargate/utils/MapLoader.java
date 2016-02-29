package ballmerpeak.stargate.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

public class MapLoader {

	Gate gate;

	Map<Character, Door> doors;
	Map<Character, Scale> scales;
	
	int zpms;
	Labyrinth labyrinth = null;

	List<SpecialWall> specialWalls;
	
	public Labyrinth loadLabyrinth(String filename) throws FileNotFoundException, IOException {
		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		specialWalls = new ArrayList<>();
		zpms = 0;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			int height = Integer.parseInt(lineOne);
			int width = Integer.parseInt(lineTwo);

			labyrinth = new Labyrinth(height, width);
			this.gate = labyrinth.getGate();
			
			// get empty line between header and body
			br.readLine();
			
			for (int i = 0; i < height; i++) {
				String line = br.readLine();
				for (int j = 0; j < width; j++) {
					
					if ((i == 0 || i == height - 1 || j == 0 || j == width - 1) && (line.charAt(j) != '#'))
						throw new InvalidMapFileException("edge of map has to be walled");
					
					Tile tile = createTile(line.charAt(j), new Position(i, j));
					labyrinth.setTile(i, j, tile);
				}
			}
		}
		setupDoors();
		setupSpecialWalls();
		labyrinth.setNumberOfZPMs(zpms);
		return labyrinth;
	}
	
	private void setupDoors() {
		for (Character c: scales.keySet()) {
			Scale scale = scales.get(c);
			Door door = doors.get(Character.toLowerCase(c));
			scale.setDoor(door);
		}
	}
	
	private void setupSpecialWalls() {
		for (SpecialWall wall : specialWalls) {
			Direction dir = wall.getDirection();
			Position pos = wall.getPosition();
			Position nextPos = pos.plusDir(dir);
			Tile nextTile = labyrinth.getTile(nextPos.y, nextPos.x);
			wall.setNextTile(nextTile);
		}
	}

	private Tile createTile(char c, Position pos) {

		switch (c) {

		case '@':
			labyrinth.setPlayerPos(pos);
		case ' ':
			return new Floor(pos);
		case '#':
			return new Wall(pos);
		case '0':
			return new Pit(pos);
		case '$':
			zpms++;
			return Floor.floorWithZPM(pos);
		case '%':
			return Floor.floorWithCrate(pos);

		case '>':
			SpecialWall rightWall = new SpecialWall(pos, Direction.RIGHT, gate);
			specialWalls.add(rightWall);
			return rightWall;
		case '<':
			SpecialWall leftWall = new SpecialWall(pos, Direction.LEFT, gate);
			specialWalls.add(leftWall);
			return leftWall;
		case '^':
			SpecialWall upWall = new SpecialWall(pos, Direction.UP, gate);
			specialWalls.add(upWall);
			return upWall;
		case '/':
			SpecialWall downWall = new SpecialWall(pos, Direction.DOWN, gate);
			specialWalls.add(downWall);
			return downWall;

		}

		if (Character.isAlphabetic(c)) {
			if (Character.isLowerCase(c)) {
				Door door = new Door(pos);
				doors.put(c, door);
				return door;
			} else {
				Scale scale = new Scale(pos);
				scales.put(c,  scale);
				return scale;
			}
		}
		
		throw new InvalidMapFileException("bad character read");

	}
}
