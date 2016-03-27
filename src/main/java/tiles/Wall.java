package tiles;
import common.Direction;

import static skeleton.SkeletonIO.*;

/**
 * The Class Wall.
 */
public class Wall extends Tile {
	
	/**
	 * Instantiates a new wall.
	 */
	public Wall() {
		super();
		enter();
		log("Wall#Wall");
		leave();
	}
	
	/**
	 * @see ballmerpeak.stargate.tiles.Tile#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Wall#canPlayerMoveHere");
		leave();
		return false;
	}

	/**
	 * @see ballmerpeak.stargate.tiles.Tile#shootIt(ballmerpeak.stargate.tiles.ShotColor, ballmerpeak.stargate.Direction)
	 */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Wall#shootIt");
		leave();
		// wall absorbs shots
	}
}