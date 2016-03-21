package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import org.omg.PortableInterceptor.INACTIVE;

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
		boolean gateActive = gate.isActive();
		boolean wallActive = ask("Is there a portal on the wall?");
		leave();
		return (gateActive && wallActive);
	}

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

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("SpecialWall#shootIt");
		gate.wallShot(this, color);
		leave();
	}

	public void setColor(ShotColor color) {
		enter();
		log("SpecialWall#setColor");
		leave();
	}

	private void teleport(Player player) {
		enter();
		log("SpecialWall#teleport");
		leave();
	}
}
