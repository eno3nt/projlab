package ballmerpeak.stargate.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Oneill;
import ballmerpeak.stargate.Jaffa;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.RandomReplicatorMovement;
import ballmerpeak.stargate.Replicator;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

/**
 * 
 * @author ballmerpeak
 * 
 * Used by the GameWindow to read in a map
 * 
 */
public class MapLoader {

	/**
	 * the game object to return
	 */
	private Game game;
	
	/**
	 * game entities
	 */
	private Player oneil;
	private Player jaffa;
	private Replicator replicator;
	
	/**
	 * the Gate object of the game
	 */
	private Gate gate;

	/**
	 * used to setup the door-scale connections
	 */
	private Map<Character, Door> doors;
	private Map<Character, Scale> scales;

	/**
	 * all the SpecialWalls on the map
	 */
	private List<SpecialWall> specialWalls;

	/**
	 * used to setup neighbors
	 */
	private Tile tiles[][];

	/**
	 * map dimensions in tiles
	 */
	private int height;
	private int width;

	/**
	 * the (optional) helper object
	 * this generates the DrawableSource for the GameRenderer
	 */
	private MapLoaderHelper helper;

	/**
	 * the filename of the map
	 */
	private String filename;

	public MapLoader(String filename) {
		this.filename = filename;
	}

	/**
	 * called by the GameWindow to set the mapLoaderHelper
	 * @param helper
	 */
	public void setHelper(MapLoaderHelper helper) {
		this.helper = helper;
	}

	/**
	 * lazily initializes the game object
	 * 
	 * @return the game object
	 * @throws Exception
	 */
	public Game getGame() throws Exception {
		if (game != null)
			return game;

		// create the objects
		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		specialWalls = new ArrayList<>();
		gate.setSpecialWalls(specialWalls);
		oneil = new Oneill();
		jaffa = new Jaffa();
		replicator = new Replicator();
		
		// read in the file
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			
			// setup the dimensions
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			height = Integer.parseInt(lineOne);
			width = Integer.parseInt(lineTwo);
			tiles = new Tile[height][width];
			
			// notify the helper
			if (helper != null)
				helper.dimensionsRead(height, width);

			// get empty line between header and body
			br.readLine();

			// iterate through the tiles
			for (int y = 0; y < height; y++) {
				String line = br.readLine();
				for (int x = 0; x < width; x++) {

					// check for valid map
					if ((y == 0 || y == height - 1 || x == 0 || x == width - 1) && (line.charAt(x) != '#'))
						throw new InvalidMapFileException("edge of map has to be walled");

					// create the tile
					Tile tile = createTile(line.charAt(x));
					tiles[y][x] = tile;
					
					// notify helper
					if (helper != null) {
						helper.tileGenerated(tile, y, x);
					}
				}
			}
		}
		
		// additional setup
		setupDoors();
		setupNeighbors();
		game = new Game(oneil, jaffa, replicator);
		game.setReplicatorMovementStrategy(new RandomReplicatorMovement());
		Floor.setZPMGeneratingStrategy(new RandomZPM());
		
		return game;
	}

	/**
	 * used to setup door-scale pairs
	 */
	private void setupDoors() {
		for (Character c : scales.keySet()) {
			Scale scale = scales.get(c);
			Door door = doors.get(Character.toLowerCase(c));
			scale.setDoor(door);
		}
	}

	/**
	 * setup the neighbors (up-down-left-right)
	 */
	private void setupNeighbors() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Tile tile = tiles[y][x];
				if (y != 0)
					tile.setNeightborForDirection(Direction.UP, tiles[y - 1][x]);
				if (x != 0)
					tile.setNeightborForDirection(Direction.LEFT, tiles[y][x - 1]);
				if (y != height - 1)
					tile.setNeightborForDirection(Direction.DOWN, tiles[y + 1][x]);
				if (x != width - 1)
					tile.setNeightborForDirection(Direction.RIGHT, tiles[y][x + 1]);
			}
		}
	}

	/**
	 * the Tile factory method
	 * @param c the character read
	 * @return the Tile object
	 */
	private Tile createTile(char c) {
		switch (c) {

		case '@':
			Tile floorWithPlayer1 = new Floor();
			floorWithPlayer1.stepOnTile(oneil);
			Floor.addFloor((Floor) floorWithPlayer1);
			return floorWithPlayer1;
		case '?':
			Tile floorWithPlayer2 = new Floor();
			floorWithPlayer2.stepOnTile(jaffa);
			Floor.addFloor((Floor) floorWithPlayer2);
			return floorWithPlayer2;
		case '*':
			Tile floorWithReplicator = new Floor();
			floorWithReplicator.stepOnTile(replicator);
			Floor.addFloor((Floor) floorWithReplicator);
			return floorWithReplicator;
		case ' ':
			Tile floor = new Floor();
			Floor.addFloor((Floor) floor);
			return floor;
		case '#':
			return new Wall();
		case '0':
			return new Pit();
		case '$':
			Tile floorWithZPM = Floor.floorWithZPM();
			Floor.addFloor((Floor) floorWithZPM);
			return floorWithZPM;
		case '%':
			Tile floorWithCrate = Floor.floorWithCrate();
			Floor.addFloor((Floor) floorWithCrate);
			return floorWithCrate;

		case '>':
			SpecialWall rightWall = new SpecialWall(Direction.RIGHT, gate);
			specialWalls.add(rightWall);
			return rightWall;
		case '<':
			SpecialWall leftWall = new SpecialWall(Direction.LEFT, gate);
			specialWalls.add(leftWall);
			return leftWall;
		case '^':
			SpecialWall upWall = new SpecialWall(Direction.UP, gate);
			specialWalls.add(upWall);
			return upWall;
		case '/':
			SpecialWall downWall = new SpecialWall(Direction.DOWN, gate);
			specialWalls.add(downWall);
			return downWall;

		}

		if (Character.isAlphabetic(c)) {
			if (Character.isLowerCase(c)) {
				Door door = new Door();
				doors.put(c, door);
				return door;
			} else {
				Scale scale = new Scale();
				scales.put(c, scale);
				return scale;
			}
		}

		// if the character is not recognized
		throw new InvalidMapFileException("bad character read");

	}
}
