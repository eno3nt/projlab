package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Tile;

/**
 * @author ballmerpeak
 *
 * base class for things that can move
 * and interact with the game world
 */
public abstract class Entity implements Drawable {

    /**
     * the direction the entity is facing
     */
	protected Direction direction;

    /**
     * is it alive
     */
	protected boolean alive;

    /**
     * the tile it is currently occupying
     */
	protected Tile tile;

    /**
     * constructor
     */
	public Entity() {
		alive = true;
		direction = Direction.UP;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
    /**
     * called by the floor object when the entity steps
     * on a ZPM.
     * to be overriden by the player object
     */
	public void steppedOnZPM(Floor floor) {
		
	}

	@Override
	public boolean isDirty() {
		return tile.isDirty();
	}

	@Override
	public void setDirty(boolean b) {
		tile.setDirty(b);
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
		setDirty(true);
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		setDirty(true);
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

    /**
     * called by MoveCommand#execute
     * sets the direction, if not facing in the given dir
     * else checks whether the next tile can be stepped on
     * if so, leaves the current tile and steps on the next one
     */
	public void move(Direction direction) {
		setDirty(true);
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
	}
	
    /**
     * gets called when the tile object it is occupying gets shot
     * Replicator overrides it
     */
	public void shootIt() {
		// override in Replicator
	}

    /**
     * called by the pit when the entity steps on it
     * Replicator overrides it
     */
	public void fallInPit(Pit pit) {
		kill();		
	}
}
