package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
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
	
	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Wall#canPlayerMoveHere");
		leave();
		return false;
	}

	/* (non-Javadoc)
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