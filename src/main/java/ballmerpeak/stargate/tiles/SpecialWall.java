package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class SpecialWall extends Wall {

	private ShotColor color;
	private final Direction direction;

	private final Gate gate;

	public SpecialWall(Direction dir, Gate gate) {
		enter();
		log("SpecialWall#SpecialWall");
		color = ShotColor.INACTIVE;
		direction = dir;
		this.gate = gate;
		leave();
	}

	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("SpecialWall#canPlayerMoveHere");
		boolean active = gate.isActive();
		leave();
		return (color != ShotColor.INACTIVE && active);
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("#Scale#stepOnTile");
		SpecialWall distantWall = (color == ShotColor.BLUE) ? gate.getYellowWall() : gate.getBlueWall();
		Tile nextTile = distantWall.getNextTile();
		player.setDirection(distantWall.direction);
		nextTile.stepOnTile(player);
		super.stepOnTile(player);
		leave();
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("#Scale#shootIt");
		gate.wallShot(this, color);
		leave();
	}

	public void setColor(ShotColor color) {
		enter();
		log("#Scae#setColor");
		this.color = color;
		setDirty(true);
		leave();
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return color == ShotColor.BLUE ? DrawableIndex.SPECIAL_WALL_BLUE
				: color == ShotColor.YELLOW ? DrawableIndex.SPECIAL_WALL_YELLOW : DrawableIndex.SPECIAL_WALL_INACTIVE;
	}

	private Tile getNextTile() {
		return getNeighborForDirection(direction);
	}

}
