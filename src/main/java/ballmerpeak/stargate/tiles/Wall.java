package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.gui.DrawableIndex;

/**
 * impassable walls on the map
 */
public class Wall extends Tile {
	
    /**
     * players should never be able to move into walls
     */
	@Override
	public boolean canPlayerMoveHere() {
		return false;
	}

    /**
     * does nothing (absorbs the shot)
     */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		// wall absorbs shots
	}
	
    /**
     * always returns the index representing a Wall
     */
	@Override
	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.WALL;
	}
}
