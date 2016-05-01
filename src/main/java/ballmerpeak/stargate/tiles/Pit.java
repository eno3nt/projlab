package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Floor {

    /**
     * if true behavese like a Floor
     * can be true if a replicator falls into it
     */
	private boolean filled;

	public Pit() {
		super();
		filled = false;
	}

    /**
     * called by the replicator's fallInPit method
     */
	public void setFilled() {
		filled = true;
	}
	
    /**
     * if it isn't filled it calls the entity's fallInPit
     * else behaves like a Floor
     */
	@Override
	public void stepOnTile(Entity entity) {
		super.stepOnTile(entity);
		if (!filled) {
			entity.fallInPit(this);
		}
		cleanupDeadEntities();
	}

    /**
     * if it is filled behaves like a Floor
     * else it just destorys the crate
     */
	@Override
	public void dropCrateHere(Player player) {
		if (filled) {
			super.dropCrateHere(player);
		} else {
			player.setCarrying(false);
		}
	}

    /**
     * returns the DrawableIndex based on its state
     */
	public DrawableIndex getDrawableIndex() {
		return !filled ? DrawableIndex.PIT : super.getDrawableIndex();
	}
}
