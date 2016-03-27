package utils;
import common.Direction;
import common.Game;
import common.Gate;
import common.Player;
import tiles.Door;
import tiles.Floor;
import tiles.Pit;
import tiles.Scale;
import tiles.SpecialWall;
import tiles.Tile;
import tiles.Wall;

import static skeleton.SkeletonIO.*;

/**
 * The Class MapLoader.
 */
public class MapLoader {

	/** The game. */
	public final Game game;
	
	/** The player. */
	public final Player player;
	
	/** The player tile. */
	public Tile playerTile;
	
	/** The door. */
	public final Door door;
	
	/** The scale. */
	public final Scale scale;
	
	/** The pit. */
	public final Pit pit;
	
	/** The gate. */
	public final Gate gate;
	
	/** The wall2. */
	public final SpecialWall wall1, wall2;
	
	/** The wall. */
	public final Wall wall;
	
	/**
	 * Instantiates a new map loader.
	 */
	public MapLoader() {
		enter();
		log("MapLoader#MapLoader");
		gate = new Gate();
		wall1 = new SpecialWall(Direction.UP, gate);		
		wall2 = new SpecialWall(Direction.UP, gate);		
		gate.setBlueWall(wall1);
		gate.setYellowWall(wall2);
		door = new Door();
		scale = new Scale();
		scale.setDoor(door);
		
		pit = new Pit();
		
		wall = new Wall();
		
		player = new Player();
		playerTile = new Floor();
		player.setTile(playerTile);
		
		game = new Game(player, 0);
		leave();
	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		enter();
		log("MapLoader#getGame");
		leave();
		return game;
	}
	
	/**
	 * Gets the player tile.
	 *
	 * @return the player tile
	 */
	public Tile getPlayerTile() {
		return playerTile;
	}
	
}