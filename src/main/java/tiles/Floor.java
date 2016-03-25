package tiles;
import common.Player;

import static skeleton.SkeletonIO.*;
// TODO: Auto-generated Javadoc
/**
 * The Class Floor.
 */
public class Floor extends Tile {
	
	/** The has crate. */
	private boolean hasCrate;
	
	/** The zpm. */
	private boolean ZPM;

	/**
	 * Instantiates a new floor.
	 */
	public Floor() {
		enter();
		log("Floor#Floor");
		hasCrate = ZPM = false;
		leave();
	}
	
	/**
	 * Floor with zpm.
	 *
	 * @return the floor
	 */
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}
	
	/**
	 * Floor with crate.
	 *
	 * @return the floor
	 */
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.hasCrate = true;
		return floor;
	}
	
	/**
	 * Checks for crate.
	 *
	 * @return true, if successful
	 */
	protected boolean hasCrate() {
		enter();
		log("Floor#hasCrate");
		boolean answer = ask("Is there a crate on the floor?");
		leave();
		return answer;
	}
	
	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#dropCrateHere(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Floor#dropCrateHere");
		if (!hasCrate()) {
			player.setCarrying(false);
			leave();
			return true;
		}
		leave();
		return false;
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#pickupCrate(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean pickupCrate(Player player) {
		enter();
		log("Floor#pickupCrate");
		if (hasCrate()) {
			player.setCarrying(true);
			leave();
			return true;
		}
		leave();
		return false;
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Floor#stepOnTile");
		boolean answer = ask("Is there a ZPM on the floor?");
		if (answer) {
			player.pickupZPM();
		}
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Floor#canPlayerMoveHere");
		leave();
		return true;
	}
}
