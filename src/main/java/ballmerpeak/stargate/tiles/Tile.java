package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.Drawable;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public abstract class Tile implements Drawable {
	private List<Tile> neighbors;
	private boolean isDirty = true;
	
	public Tile() {
		enter();
		log("Tile#Tile");
		neighbors = new ArrayList<Tile>(Direction.values().length);
		for (int i = 0; i < Direction.values().length; i++) {
			neighbors.add(null);
		}
		leave();
	}
	
	public Tile getNeighborForDirection(Direction dir) {
		enter();
		log("Tile#getNeighborForDirection");
		leave();
		return neighbors.get(dir.ordinal());
	}
	
	public void setNeightborForDirection(Direction dir, Tile tile) {
		enter();
		log("Tile#setNeighborForDirection");
		neighbors.set(dir.ordinal(), tile);
		leave();
	}

	public boolean canPlayerMoveHere() {
		enter();
		log("Tile#canPlayerMoveHere");
		leave();
		return true;
	}
	
	public void stepOnTile(Player player) {
		enter();
		log("Tile#stepOnTile");
		setDirty(true);
		leave();
	}
	
	public void leaveTile(Player player) {
		enter();
		log("Tile#leaveTile");
		setDirty(true);
		leave();
	}
	
	public boolean dropCrateHere(Player player) {
		enter();
		log("Tile#dropCrateHere");
		leave();
		return false;
	}
	
	public boolean pickupCrate(Player player) {
		enter();
		log("Tile#pickupCrate");
		leave();
		return false;
	}
	
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Tile#shootIt");
		Tile nextTile = getNeighborForDirection(dir);
		nextTile.shootIt(color, dir);
		leave();
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}
}
