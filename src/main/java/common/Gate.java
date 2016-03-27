package common;
import tiles.ShotColor;
import tiles.SpecialWall;

import static skeleton.SkeletonIO.*;

/**
 * The Class Gate.
 */
public class Gate {

	/** The yellow wall. */
	private SpecialWall yellowWall;
	
	/** The blue wall. */
	private SpecialWall blueWall;
	
	/**
	 * Instantiates a new gate.
	 */
	public Gate() {
//		enter();
//		log("Gate#Gate");
		yellowWall = blueWall = null;
//		leave();
	}

	/**
	 * Gets the blue wall.
	 *
	 * @return the blue wall
	 */
	public SpecialWall getBlueWall() {
		enter();
		log("Gate#getBlueWall");
		leave();
		return blueWall;
	}
	
	/**
	 * Gets the yellow wall.
	 *
	 * @return the yellow wall
	 */
	public SpecialWall getYellowWall() {
		enter();
		log("Gate#getYellowWall");
		leave();
		return yellowWall;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		enter();
		log("Gate#isActive");
		boolean answer = ask("Is the gate active?");
		leave();
		return answer;
	}
	
	/**
	 * Wall shot.
	 *
	 * @param wall the wall
	 * @param color the color
	 */
	public void wallShot(SpecialWall wall, ShotColor color) {
		enter();
		log("Gate#wallShot");
		leave();
	}
	
	/**
	 * Sets the blue wall.
	 *
	 * @param wall the new blue wall
	 */
	public void setBlueWall(SpecialWall wall) {
		blueWall = wall;
	}
	
	/**
	 * Sets the yello wall.
	 *
	 * @param wall the new yellow wall
	 */
	public void setYellowWall(SpecialWall wall) {
		yellowWall = wall;
	}
}
