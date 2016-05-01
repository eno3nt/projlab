package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Door extends Floor {

    /**
     * is the door open
     */
	private boolean open;

	public Door() {
		super();
		open = false;
	}

    /**
     * return true, if the door is open
     */
	@Override
	public boolean canPlayerMoveHere() {
		return isOpen();
	}

    /**
     * delegates to the base class (Floor)
     * if the door is closed it kills the player
     * it can happen, if the previous tile was the scale holding it open
     */
	@Override
	public void stepOnTile(Entity player) {
		super.stepOnTile(player);
		if (!isOpen())
			killEntities();
	}

    /**
     * lets through the shot if it is open
     */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		if (isOpen())
			super.shootIt(color, dir);
	}

	public boolean isOpen() {
		return open;
	}

    /**
     * kills all the entities standing on it
     */
	public void close() {
		open = false;
		killEntities();
		setDirty(true);
	}

	public void open() {
		open = true;
		setDirty(true);
	}

    /**
     * returns the DrawableIndex based on its state
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		return !entities.isEmpty() ? super.getDrawableIndex()
				: open ? DrawableIndex.DOOR_OPEN : DrawableIndex.DOOR_CLOSED;
	}

    /**
     * kill all the entities standing on it
     * called by close()
     */
	private void killEntities() {
		for (Entity entity : entities)
			entity.kill();
	}
}
