package common;
import tiles.ShotColor;
import tiles.Tile;

import static skeleton.SkeletonIO.*;

/**
 * The Class Player.
 */
public class Player {

	/** The direction. */
	private Direction direction = Direction.UP;
	
	/** The carrying. */
	private boolean carrying;
	
	/** The alive. */
	private boolean alive;
	
	/** The ZP ms carried. */
	private int ZPMsCarried;
	
	/** The tile. */
	private Tile tile;

	/**
	 * Instantiates a new player.
	 */
	public Player() {
		enter();
		log("Player#player");
		alive = true;
		ZPMsCarried = 0;
		carrying = false;
		leave();
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		enter();
		log("Player#isAlive");
		boolean ans = ask("Is Player alive?");
		leave();
		return ans;
	}

	/**
	 * Kill.
	 */
	public void kill() {
		enter();
		log("Player#kill");
		leave();
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(Direction direction) {
		enter();
		log("Player#setDirection");
		this.direction = direction;
		leave();
	}

	/**
	 * Gets the ZP ms carried.
	 *
	 * @return the ZP ms carried
	 */
	public int getZPMsCarried() {
		enter();
		log("Player#getZPMSCarried");
		leave();
		return ZPMsCarried;
	}

	/**
	 * Pickup zpm.
	 */
	public void pickupZPM() {
		enter();
		log("Player#pickupZPM");
		leave();
	}

	/**
	 * Gets the tile.
	 *
	 * @return the tile
	 */
	public Tile getTile() {
		enter();
		log("Player#getTile");
		leave();
		return tile;
	}

	/**
	 * Sets the tile.
	 *
	 * @param tile the new tile
	 */
	public void setTile(Tile tile) {
		enter();
		log("Player#setTile");
		this.tile = tile;
		leave();
	}

	/**
	 * Move.
	 *
	 * @param direction the direction
	 */
	public void move(Direction direction) {
		enter();
		log("Player#move");
		Tile currentTile = tile;
		Tile nextTile = currentTile.getNeighborForDirection(direction);
		if (nextTile.canPlayerMoveHere()) {
			currentTile.leaveTile(this);
			nextTile.stepOnTile(this);
		}
		leave();
	}

	/**
	 * Pickup crate.
	 */
	public void pickupCrate() {
		enter();
		log("Player#pickupCrate");
		Tile nextTile = getTileFrontOfPlayer();
		boolean answer = ask("Is the player carrying a crate?");
		if (answer) {
			if (nextTile.dropCrateHere(this))
				carrying = false;
		} else {
			if (nextTile.pickupCrate(this))
				carrying = true;
		}
		leave();
	}

	/**
	 * Shoot.
	 *
	 * @param color the color
	 */
	public void shoot(ShotColor color) {
		enter();
		log("Player#shoot");
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
		leave();
	}
	
	/**
	 * Sets the carrying.
	 *
	 * @param b the new carrying
	 */
	public void setCarrying(boolean b) {
		enter();
		log("Player#setCarrying");
		leave();
	}

	/**
	 * Gets the tile front of player.
	 *
	 * @return the tile front of player
	 */
	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}
}
