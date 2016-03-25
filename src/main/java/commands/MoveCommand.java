package commands;
import static skeleton.SkeletonIO.*;

import common.Direction;
import common.Player;
// TODO: Auto-generated Javadoc
/**
 * The Class MoveCommand.
 */
public class MoveCommand implements InputCommand {
	
	/** The dir. */
	private Direction dir;
	
	/**
	 * Instantiates a new move command.
	 *
	 * @param dir the dir
	 */
	public MoveCommand(Direction dir) {
		enter();
		log("MoveCommand#MoveCommand");
		this.dir = dir;
		leave();
	}
	
	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.commands.InputCommand#execute(ballmerpeak.stargate.Player)
	 */
	@Override
	public void execute(Player player) {
		enter();
		log("MoveCommand#execute");
		player.move(dir);
		leave();
	}

}