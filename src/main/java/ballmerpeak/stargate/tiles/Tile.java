package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.Drawable;

/**
 * base class for all the different tiles in the game
 */
public abstract class Tile implements Drawable {
    /**
     * list of neighbors (2-4 for each Tile)
     */
	private List<Tile> neighbors;

    /**
     * flag for the Drawable, indicating if it needs redrawing
     * will be used for the graphical version
     */
	private boolean isDirty = true;
	
	public Tile() {
		neighbors = new ArrayList<Tile>(Direction.values().length);
		for (int i = 0; i < Direction.values().length; i++) {
			neighbors.add(null);
		}
	}
	
    /**
     * return the neighbor for the given direction
     */
	public Tile getNeighborForDirection(Direction dir) {
		return neighbors.get(dir.ordinal());
	}
	
    /**
     * called by the maploader
     */
	public void setNeightborForDirection(Direction dir, Tile tile) {
		neighbors.set(dir.ordinal(), tile);
	}

    /**
     * by default entities can move on tiles
     */
	public boolean canPlayerMoveHere() {
		return true;
	}
	
    /**
     * sets the dirty flag
     */
	public void stepOnTile(Entity player) {
		setDirty(true);
	}
	
    /**
     * sets the dirty flag
     */
	public void leaveTile(Entity player) {
		setDirty(true);
	}
	
    /**
     * does nothing, to be overriden
     */
	public void dropCrateHere(Player player) {
		
	}
	
    /**
     * does nothing, to be overriden
     */
	public void pickupCrate(Player player) {

	}
	
    /**
     * by default transmits the shot in the given direction
     * calling the neighbors shootIt method
     */
	public void shootIt(ShotColor color, Direction dir) {
		Tile nextTile = getNeighborForDirection(dir);
		nextTile.shootIt(color, dir);
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
