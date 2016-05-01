package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Tile;

/**
 * @author ballmerpeak
 */
public class Replicator extends Entity {

    /**
     * the replicator moves without considering it's direction
     * this is to prevent it from "spinning" in place for extended periods
     */
	@Override
	public void move(Direction direction) {
		this.direction = direction;
		Tile currentTile = tile;
		Tile nextTile = currentTile.getNeighborForDirection(direction);
		if (nextTile.canPlayerMoveHere()) {
			currentTile.leaveTile(this);
			nextTile.stepOnTile(this);
		}
		setDirty(true);
	}

    /**
     * return the DrawableIndex for the replicator
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.REPLICATOR_FACING_UP;
		case DOWN:
			return DrawableIndex.REPLICATOR_FACING_DOWN;
		case LEFT:
			return DrawableIndex.REPLICATOR_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.REPLICATOR_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}

    /**
     * if a shot hits the replicator, it dies
     */
	@Override
	public void shootIt() {
		kill();
	}

    /**
     * called by the pit
     * it dies and fills up the pit
     */
	@Override
	public void fallInPit(Pit pit) {
		super.fallInPit(pit);
		pit.setFilled();
	}
}
