package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Floor extends Tile {
	
	protected boolean occupied;
	private boolean ZPM;

	public Floor() {
		occupied = ZPM = false;
	}
	
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}
	
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.occupied = true;
		return floor;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		if (!isOccupied()) {
			occupied = true;
			player.unsetCarrying();
			return true;
		}
		return false;
	}

	@Override
	public boolean pickupCrate(Player player) {
		if (occupied) {
			occupied = false;
			player.setCarrying();
			return true;
		}
		return false;
	}

	@Override
	public void stepOnTile(Player player) {
		if (ZPM) {
			player.pickupZPM();
			ZPM = false;
		}
		player.setTile(this);
	}

	public DrawableIndex getDrawableIndex() {
		return ZPM ? DrawableIndex.FLOOR_WITH_ZPM :
			occupied ? DrawableIndex.FLOOR_WITH_CRATE :
				DrawableIndex.FLOOR;
	}
}
