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
		// enter();
		// log("SpecialWall#SpecialWall");
		color = ShotColor.INACTIVE;
		direction = dir;
		this.gate = gate;
		// leave();
	}

	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("SpecialWall#canPlayerMoveHere");
		boolean gateActive = gate.isActive();
		boolean wallActive = ;
		leave();
		return (gateActive && wallActive);
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("SpecialWall#stepOnTile");
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
		this.color = color;
		leave();
	}
	
	public boolean hasPortal() {
		enter();
		log("SpecialWall#hasPortal");
		boolean ans = yesNo("Is there a portal on the wall?");
		return ans;
	}
}
