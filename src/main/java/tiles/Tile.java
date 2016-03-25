package tiles;


import java.util.ArrayList;
import java.util.List;

import common.Direction;
import common.Player;

import static skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Tile.
 */
public abstract class Tile {
	
	/** The neighbors. */
	private List<Tile> neighbors;
	
	/**
	 * Instantiates a new tile.
	 */
	public Tile() {
//		enter();
//		log("Tile#Tile");
		neighbors = new ArrayList<Tile>(Direction.values().length);
		for (int i = 0; i < Direction.values().length; i++) {
			neighbors.add(null);
		}
//		leave();
	}
	
	/**
	 * Gets the neighbor for direction.
	 *
	 * @param dir the dir
	 * @return the neighbor for direction
	 */
	public Tile getNeighborForDirection(Direction dir) {
		enter();
		log("Tile#getNeighborForDirection");
		leave();
		return neighbors.get(dir.ordinal());
	}
	
	/**
	 * Sets the neightbor for direction.
	 *
	 * @param dir the dir
	 * @param tile the tile
	 */
	public void setNeightborForDirection(Direction dir, Tile tile) {
		enter();
		log("Tile#setNeighborForDirection");
		neighbors.set(dir.ordinal(), tile);
		leave();
	}

	/**
	 * Can player move here.
	 *
	 * @return true, if successful
	 */
	public abstract boolean canPlayerMoveHere();
	
	/**
	 * Step on tile.
	 *
	 * @param player the player
	 */
	public void stepOnTile(Player player) {
		enter();
		log("Tile#stepOnTile");
		leave();
	}
	
	/**
	 * Leave tile.
	 *
	 * @param player the player
	 */
	public void leaveTile(Player player) {
		enter();
		log("Tile#leaveTile");
		leave();
	}
	
	/**
	 * Drop crate here.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean dropCrateHere(Player player) {
		enter();
		log("Tile#dropCrateHere");
		leave();
		return false;
	}
	
	/**
	 * Pickup crate.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean pickupCrate(Player player) {
		enter();
		log("Tile#pickupCrate");
		leave();
		return false;
	}
	
	/**
	 * Shoot it.
	 *
	 * @param color the color
	 * @param dir the dir
	 */
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Tile#shootIt");
		Tile nextTile = getNeighborForDirection(dir);
		nextTile.shootIt(color, dir);
		leave();
	}
}
