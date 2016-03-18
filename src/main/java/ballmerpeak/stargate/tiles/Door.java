package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Door extends Tile {

	private boolean open = false;

	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Door#canPlayerMoveHere");
		leave();
		return isOpen();
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Door#stepOnTile");
		player.setTile(this);
		if (!isOpen())
			player.kill();
		leave();
		super.stepOnTile(player);
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Door#shootIt");
		if (isOpen())
			super.shootIt(color, dir);
		leave();
	}

	public boolean isOpen() {
		enter();
		log("Door#isOpen");
		leave();
		return open;
	}

	public void close() {
		enter();
		log("Door#close");
		open = false;
		setDirty(true);
		leave();
	}

	public void open() {
		enter();
		log("Door#open");
		open = true;
		setDirty(true);
		leave();
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return open ? DrawableIndex.DOOR_OPEN : DrawableIndex.DOOR_CLOSED;
	}
}
