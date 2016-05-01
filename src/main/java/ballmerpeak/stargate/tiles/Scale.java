package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

    /**
     * the door it opens
     */
	private Door door;

	public Scale() {
		super();
	}
	
    /**
     * returns true if it has a crate or an entity standing on it
     */
	private boolean isPressed() {
		return hasCrate() || hasEntity();
	}
	
    /**
     * if it isn't pressed after the pickup closes its door
     */
	@Override
	public void pickupCrate(Player player) {
		super.pickupCrate(player);
		if (!isPressed()) {
			door.close();
		}
	}

    /**
     * opens the door
     */
	@Override
	public void dropCrateHere(Player player) {
		super.dropCrateHere(player);
		if (isPressed()) {
			door.open();
		}
	}

    /**
     * opens the door
     */
	@Override
	public void stepOnTile(Entity player) {
		super.stepOnTile(player);
		door.open();
	}

    /**
     * if no more crates or entities are on the Scale, it closes the door
     */
	@Override
	public void leaveTile(Entity player) {
		super.leaveTile(player);
		if (!hasCrate() && !hasEntity())
			door.close();
	}

	public void setDoor(Door door) {
		this.door = door;
	}

    /**
     * returns the DrawableIndex based on its state
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		return !entities.isEmpty() ? super.getDrawableIndex()
				: hasCrate() ? DrawableIndex.SCALE_WITH_CRATE : DrawableIndex.SCALE;
	}
}
