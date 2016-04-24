package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;

public class MoveCommand implements InputCommand {
	
    /**
     * the direction for the movement
     */
	private Direction dir;
	
	public MoveCommand(Direction dir) {
		this.dir = dir;
	}
	
    /**
     * if the player is alive it moves it in the direction
     */
	@Override
	public void execute(Player player) {
		if (player.isAlive())
			player.move(dir);
	}

}
