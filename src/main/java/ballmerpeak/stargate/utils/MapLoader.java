package ballmerpeak.stargate.utils;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
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
		gate.setYelloWall(wall2);
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