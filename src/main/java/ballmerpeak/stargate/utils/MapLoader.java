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

public class MapLoader {

	public final Game game;
	public final Player player;
	public Tile playerTile;
	
	public final Door door;
	public final Scale scale;
	
	public final Pit pit;
	
	public final Gate gate;
	
	public final SpecialWall wall1, wall2;
	
	public final Wall wall;
	
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
	
	public Game getGame() {
		enter();
		log("MapLoader#getGame");
		leave();
		return game;
	}
	
	public Tile getPlayerTile() {
		return playerTile;
	}
	
}