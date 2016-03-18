package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Floor extends Tile {
	
	private boolean hasCrate;
	private boolean ZPM;

	public Floor() {
		enter();
		log("Floor#Floor");
		hasCrate = ZPM = false;
		leave();
	}
	
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}
	
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.hasCrate = true;
		return floor;
	}
	
	public boolean hasCrate() {
		enter();
		log("Floor#hasCrate");
		leave();
		return hasCrate;
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Floor#dropCrateHere");
		if (!hasCrate()) {
			hasCrate = true;
			leave();
			return true;
		}
		leave();
		return false;
	}

	@Override
	public boolean pickupCrate(Player player) {
		enter();
		log("Floor#pickupCrate");
		if (hasCrate) {
			hasCrate = false;
			leave();
			return true;
		}
		leave();
		return false;
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Floor#stepOnTile");
		if (ZPM) {
			player.pickupZPM();
			ZPM = false;
		}
		player.setTile(this);
		super.stepOnTile(player);
		leave();
	}

	public DrawableIndex getDrawableIndex() {
		return ZPM ? DrawableIndex.FLOOR_WITH_ZPM :
			hasCrate ? DrawableIndex.FLOOR_WITH_CRATE :
				DrawableIndex.FLOOR;
	}
}
