package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

/**
 * base class for the two players (jaffa, oneil)
 */
public abstract class Player extends Entity {

    /**
     * number of ZPMs carried
     */
	private int ZPMsCarried;

    /**
     * is it carrying a crate
     */
	private boolean carrying;
	
	public Player() {
		super();
		ZPMsCarried = 0;
		carrying = false;
	}

	public boolean isCarrying() {
		return carrying;
	}
	
	public int getZPMsCarried() {
		return ZPMsCarried;
	}

    /**
     * pick up a zpm, increasing the count
     */
	public void pickupZPM() {
		ZPMsCarried++;
	}

    /**
     * called by the floor when the player steps on a zpm
     * calls pickupZPM and clears the floor
     */
	@Override
	public void steppedOnZPM(Floor floor) {
		pickupZPM();
		floor.setZPM(false);
	}

    /**
     * called by the PickupCommand
     * one method that handles the attempt both to pickup and drop a crate
     * if carrying it attempts to drop the crate
     * else attempts to pick up a crate
     * the methods on the next tile get the player as a parameter,
     * updating it if necessary (drop or pickup actually occurs)
     */
	public void pickupCrate() {
		Tile nextTile = getTileFrontOfPlayer();
		if (carrying) {
			nextTile.dropCrateHere(this);
		} else {
			nextTile.pickupCrate(this);
		}
		nextTile.setDirty(true);
		setDirty(true);
	}

    /**
     * returns the tile directly in front of the player
     */
	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}

    /**
     * called by the ShootCommand
     * gets the next tile and shoots it with the given color
     */
	public void shoot(ShotColor color) {
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
	}
	
	public void setCarrying(boolean c) {
		this.carrying = c;
	}
}
