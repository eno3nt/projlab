package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Door extends Tile {

	private boolean open = false;
	
	public Door() {
		enter();
		log("Door#Door");
		leave();
	}

	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Door#canPlayerMoveHere");
		boolean isopen = isOpen();
		leave();
		return isopen;
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Door#stepOnTile");
		if (!isOpen())
			player.kill();
		leave();
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Door#shootIt");
		if (isOpen())
			super.shootIt(color, dir);
		leave();
	}

	public boolean isOpen() {
		enter();
		log("Door#isOpen");
		boolean answer = ask("Is the door open?");
		leave();
		return answer;
	}

	public void close() {
		enter();
		log("Door#close");
		leave();
	}

	public void open() {
		enter();
		log("Door#open");
		leave();
	}
}
