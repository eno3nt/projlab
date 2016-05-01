package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Floor;

public class Oneill extends Player {

    /**
     * the zpm generation for oneil
     * calls the static generateNewZPM method on the Floor class
     * to create the new ZPM
     */
	@Override
	public void pickupZPM() {
		super.pickupZPM();
		if (getZPMsCarried() % 2 == 0)
			Floor.generateNewZPM();
	}
	
    /**
     * DrawableIndex for oneil
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.ONEILL_FACING_UP;
		case DOWN:
			return DrawableIndex.ONEILL_FACING_DOWN;
		case LEFT:
			return DrawableIndex.ONEILL_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.ONEILL_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}
}
