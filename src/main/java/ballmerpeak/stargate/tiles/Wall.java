package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

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
	
	@Override
	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.WALL;
	}
}