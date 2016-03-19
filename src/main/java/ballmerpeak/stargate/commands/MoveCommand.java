package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class MoveCommand implements InputCommand {
	
	private Direction dir;
	
	public MoveCommand(Direction dir) {
		enter();
		log("MoveCommand#MoveCommand");
		this.dir = dir;
		leave();
	}
	
	@Override
	public void execute(Player player) {
		enter();
		log("MoveCommand#execute");
		player.move(dir);
		leave();
	}

}
