package tiles;
import common.Player;

import static skeleton.SkeletonIO.*;
// TODO: Auto-generated Javadoc
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

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Floor#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Pit#stepOnTile");
		player.kill();
		leave();
	}
	
	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Floor#dropCrateHere(ballmerpeak.stargate.Player)
	 */
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Pit#dropCrateHere");
		leave();
		return true;
	}

	/* (non-Javadoc)
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
