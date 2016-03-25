package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 */
public class Door extends Tile {

	/** The open. */
	private boolean open = false;
	
	/**
	 * Instantiates a new door.
	 */
	public Door() {
		enter();
		log("Door#Door");
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#canPlayerMoveHere()
	 */
	@Override
	public boolean canPlayerMoveHere() {
		enter();
		log("Door#canPlayerMoveHere");
		boolean isopen = isOpen();
		leave();
		return isopen;
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#stepOnTile(ballmerpeak.stargate.Player)
	 */
	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Door#stepOnTile");
		if (!isOpen())
			player.kill();
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.Tile#shootIt(ballmerpeak.stargate.tiles.ShotColor, ballmerpeak.stargate.Direction)
	 */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		enter();
		log("Door#shootIt");
		if (isOpen())
			super.shootIt(color, dir);
		leave();
	}

	/**
	 * Checks if is open.
	 *
	 * @return true, if is open
	 */
	public boolean isOpen() {
		enter();
		log("Door#isOpen");
		boolean answer = ask("Is the door open?");
		leave();
		return answer;
	}

	/**
	 * Close.
	 */
	public void close() {
		enter();
		log("Door#close");
		leave();
	}

	/**
	 * Open.
	 */
	public void open() {
		enter();
		log("Door#open");
		leave();
	}
}
