package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Pit extends Floor {

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Pit#stepOnTile");
		player.kill();
		super.stepOnTile(player);
		leave();
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Pit#dropCrateHere");
		leave();
		return true;
	}

	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.PIT;
	}
}
