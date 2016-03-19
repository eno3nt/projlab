package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Pit extends Floor {

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Pit#stepOnTile");
		player.kill();
		leave();
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Pit#dropCrateHere");
		leave();
		return true;
	}

	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Pit#canPlayerMoveHere");
		leave();
		return true;
	}

}
