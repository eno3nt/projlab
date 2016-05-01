package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.gui.DrawableIndex;

/**
 * the walls which can have gates on them
 */
public class SpecialWall extends Wall {

    /**
     * the color of the wall (inactive, if it has no gate on it)
     */
	private ShotColor color;

    /**
     * the direction the player is moved towards after teleportin on it
     */
	private final Direction direction;

    /**
     * reference to the gate object managing all the special walls
     */
	private final Gate gate;

	public SpecialWall(Direction dir, Gate gate) {
		color = ShotColor.INACTIVE;
		direction = dir;
		this.gate = gate;
	}

    /**
     * return true if it has a portal on it and the other end of the gate is active
     */
	@Override
	public boolean canPlayerMoveHere() {
		return hasPortal() && gate.isActiveForColor(color);
	}

    /**
     * calls the gate's teleport method with its color and the player
     */
	@Override
	public void stepOnTile(Entity player) {
		setDirty(true);
		gate.teleport(player, color);
	}

    /**
     * notifies the gate object by passing itself and the color of the shot
     */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		gate.wallShot(this, color);
	}

    /**
     * called by the gate object
     */
	public void setColor(ShotColor color) {
		this.color = color;
		setDirty(true);
	}

    /**
     * return the DrawableIndex based on its state (color)
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		return color == ShotColor.BLUE ? DrawableIndex.SPECIAL_WALL_BLUE
				: color == ShotColor.YELLOW ? DrawableIndex.SPECIAL_WALL_YELLOW
						: color == ShotColor.GREEN ? DrawableIndex.SPECIAL_WALL_GREEN
								: color == ShotColor.RED ? DrawableIndex.SPECIAL_WALL_RED
										: DrawableIndex.SPECIAL_WALL_INACTIVE;
	}

    /**
     * returns its neighbor towards the direction its facing
     * this is the tile the player moves to after teleporting on the wall
     */
	private Tile getNextTile() {
		return getNeighborForDirection(direction);
	}

    /**
     * called by the gate
     */
	public void teleport(Entity player) {
		player.setDirection(direction);
		getNextTile().stepOnTile(player);
	}

	private boolean hasPortal() {
		return color != ShotColor.INACTIVE;
	}

	public ShotColor getColor() {
		return color;
	}

}
