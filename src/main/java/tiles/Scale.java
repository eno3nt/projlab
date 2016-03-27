package tiles;
import common.Player;

import static skeleton.SkeletonIO.*;

/**
 * The Class Scale.
 */
public class Scale extends Floor {

	/** The door. */
	private Door door;
	
	/**
	 * Instantiates a new scale.
	 */
	public Scale() {
		super();
		enter();
		log("Scale#Scale");
		leave();
	}
	
	/**
	 * @see ballmerpeak.stargate.tiles.Floor#pickupCrate(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean pickupCrate(Player player) {
		enter();
		log("Scale#pickupCrate");
		boolean didPickUpCrate = super.pickupCrate(player);
		
		if (didPickUpCrate) {
			door.close();
			boolean answer = ask("Is the player standing on the door?");
			if (answer) {
				player.kill();
			}
		}
		leave();
		return didPickUpCrate;
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Floor#dropCrateHere(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Scale#dropCrateHere");
		boolean didDropCrate = super.dropCrateHere(player);
		if (didDropCrate) {
			door.open();
		}
		leave();
		return didDropCrate;
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Floor#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Scale#stepOnTile");
		door.open();
		leave();;
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Tile#leaveTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void leaveTile(Player player) {
		enter();
		log("Scae#leaveTile");
		boolean hasCrate = ask("Is there a crate on the scale?");
		if (!hasCrate)
			door.close();
		leave();
	}

	/**
	 * Sets the door.
	 *
	 * @param door the new door
	 */
	public void setDoor(Door door) {
		enter();
		log("Scale#setDoor");
		this.door = door;
		leave();
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Floor#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Scale#canPlayerMoveHere");
		leave();
		return true;
	}
	
	
}
