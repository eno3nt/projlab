package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Player {

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
		boolean ans = yesNo("Is Player alive?");
		leave();
		return ans;
	}

	public void kill() {
		enter();
		log("Player#kill");
		leave();
	}

	public void setDirection(Direction direction) {
		enter();
		log("Player#setDirection");
		this.direction = direction;
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
		Tile currentTile = tile;
		Tile nextTile = currentTile.getNeighborForDirection(direction);
		if (nextTile.canPlayerMoveHere()) {
			currentTile.leaveTile(this);
			nextTile.stepOnTile(this);
		}
		leave();
	}

	public void pickupCrate() {
		enter();
		log("Player#pickupCrate");
		Tile nextTile = getTileFrontOfPlayer();
		boolean answer = yesNo("Is the player carrying a crate?");
		if (answer) {
			if (nextTile.dropCrateHere(this))
				carrying = false;
		} else {
			if (nextTile.pickupCrate(this))
				carrying = true;
		}
		leave();
	}

	public void shoot(ShotColor color) {
		enter();
		log("Player#shoot");
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
		leave();
	}

	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}
}
