package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Scale extends Floor {

	private Door door;
	
	@Override
	public boolean pickupCrate(Player player) {
		enter();
		log("Scale#pickupCrate");
		boolean didPickUpCrate = super.pickupCrate(player);
		if (didPickUpCrate) {
			door.close();
			if (player.getTile() == door) {
				player.kill();
			}
			leave();
			return true;
		}
		leave();
		return false;
	}

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

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Scale#stepOnTile");
		door.open();
		super.stepOnTile(player);
		leave();;
	}

	@Override
	public void leaveTile(Player player) {
		enter();
		log("Scae#leaveTile");
		super.leaveTile(player);
		if (!hasCrate())
			door.close();
		if (!hasCrate() && player.getTile() == door) {
			player.kill();
		}
		leave();
	}

	public void setDoor(Door door) {
		enter();
		log("Scale#setDoor");
		this.door = door;
		leave();
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return hasCrate() ? DrawableIndex.SCALE_WITH_CRATE : DrawableIndex.SCALE; 
	}
}
