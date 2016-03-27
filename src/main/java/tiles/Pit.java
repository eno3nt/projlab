package tiles;
import common.Player;

import static skeleton.SkeletonIO.*;

/**
 * The Class Pit.
 */
public class Pit extends Floor {
	
	/**
	 * Instantiates a new pit.
	 */
	public Pit() {
		super();
		enter();
		log("Pit#Pit");
		leave();
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Floor#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Pit#stepOnTile");
		player.kill();
		leave();
	}
	
	/**
	 * @see ballmerpeak.stargate.tiles.Floor#dropCrateHere(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Pit#dropCrateHere");
		leave();
		return true;
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Floor#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Pit#canPlayerMoveHere");
		leave();
		return true;
	}

}
