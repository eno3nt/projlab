package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Gate {

	private SpecialWall yellowWall;
	private SpecialWall blueWall;
	
	public Gate() {
		enter();
		log("Gate#Gate");
		yellowWall = blueWall = null;
		leave();
	}

	public SpecialWall getBlueWall() {
		return blueWall;
	}
	
	public SpecialWall getYellowWall() {
		return yellowWall;
	}
	
	public boolean isActive() {
		enter();
		log("Gate#isActive");
		boolean answer = yesNo("Is the gate active?");
		leave();
		return answer;
	}
	
	public void wallShot(SpecialWall wall, ShotColor color) {
		enter();
		log("Gate#wallShot");
		leave();
	}
}
