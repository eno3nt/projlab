package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Player implements Drawable {

	private Direction direction = Direction.UP;
	private boolean carrying;
	private boolean alive;
	private int ZPMsCarried;
	private Tile tile;

	public Player() {
		enter();
		log("Player#player");
		alive = true;
		ZPMsCarried = 0;
		carrying = false;
		leave();
	}

	public boolean isAlive() {
		enter();
		log("Player#isAlive");
		leave();
		return alive;
	}

	public void kill() {
		enter();
		log("Player#kill");
		alive = false;
		leave();
	}

	public void setDirection(Direction direction) {
		enter();
		log("Player#setDirection");
		this.direction = direction;
		setDirty(true);
		leave();
	}

	public int getZPMsCarried() {
		enter();
		log("Player#getZPMSCarried");
		leave();
		return ZPMsCarried;
	}

	public void pickupZPM() {
		enter();
		log("Player#pickupZPM");
		ZPMsCarried++;
		leave();
	}

	public Tile getTile() {
		enter();
		log("Player#getTile");
		leave();
		return tile;
	}

	public void setTile(Tile tile) {
		enter();
		log("Player#setTile");
		this.tile = tile;
		leave();
	}

	public void move(Direction direction) {
		enter();
		log("Player#move");
		if (this.direction != direction) {
			this.direction = direction;
		} else {
			Tile currentTile = tile;
			Tile nextTile = currentTile.getNeighborForDirection(direction);
			if (nextTile.canPlayerMoveHere()) {
				currentTile.leaveTile(this);
				nextTile.stepOnTile(this);
			}
		}
		setDirty(true);
		leave();
	}

	public void pickupCrate() {
		enter();
		log("Player#pickupCrate");
		Tile nextTile = getTileFrontOfPlayer();
		if (carrying) {
			if (nextTile.dropCrateHere(this))
				carrying = false;
		} else {
			if (nextTile.pickupCrate(this))
				carrying = true;
		}
		nextTile.setDirty(true);
		setDirty(true);
		leave();
	}

	public void shoot(ShotColor color) {
		enter();
		log("Player#shoot");
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
		leave();
	}

	@Override
	public boolean isDirty() {
		return tile.isDirty();
	}

	@Override
	public void setDirty(boolean b) {
		tile.setDirty(b);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.PLAYER_FACING_UP;
		case DOWN:
			return DrawableIndex.PLAYER_FACING_DOWN;
		case LEFT:
			return DrawableIndex.PLAYER_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.PLAYER_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}

	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}
}
