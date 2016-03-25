package tiles;


import static skeleton.SkeletonIO.*;

import common.Direction;
import common.Gate;
import common.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class SpecialWall.
 */
public class SpecialWall extends Wall {

	/** The color. */
	private ShotColor color;
	
	/** The direction. */
	private final Direction direction;

	/** The gate. */
	private final Gate gate;

	/**
	 * Instantiates a new special wall.
	 *
	 * @param dir the dir
	 * @param gate the gate
	 */
	public SpecialWall(Direction dir, Gate gate) {
		enter();
		log("SpecialWall#SpecialWall");
		color = ShotColor.INACTIVE;
		direction = dir;
		this.gate = gate;
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Wall#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("SpecialWall#canPlayerMoveHere");
		boolean gateActive = gate.isActive();
		boolean wallActive = ask("Is there a portal on the wall?");
		leave();
		return (gateActive && wallActive);
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("SpecialWall#stepOnTile");
		SpecialWall otherWall;
		boolean ans = ask("Is the portal blue?");
		if (ans) {
			otherWall = gate.getYellowWall();
		} else {
			otherWall = gate.getBlueWall();
		}
		otherWall.teleport(player);
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Wall#shootIt(ballmerpeak.stargate.tiles.ShotColor, ballmerpeak.stargate.Direction)
	 */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("SpecialWall#shootIt");
		gate.wallShot(this, color);
		leave();
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(ShotColor color) {
		enter();
		log("SpecialWall#setColor");
		leave();
	}

	/**
	 * Teleport.
	 *
	 * @param player the player
	 */
	private void teleport(Player player) {
		enter();
		log("SpecialWall#teleport");
		leave();
	}
}
