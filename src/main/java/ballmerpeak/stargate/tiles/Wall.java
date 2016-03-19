package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Wall extends Tile {
	
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Wall#canPlayerMoveHere");
		leave();
		return false;
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Wall#shootIt");
		leave();
		// wall absorbs shots
	}
}