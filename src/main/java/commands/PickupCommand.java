package commands;
import common.Player;


import static skeleton.SkeletonIO.*;

/**
 * The Class PickupCommand.
 */
public class PickupCommand implements InputCommand {

	/**
	 * @see ballmerpeak.stargate.commands.InputCommand#execute(ballmerpeak.stargate.Player)
	 */
	@Override
	public void execute(Player player) {
		enter();
		log("PickupCommand#execute()");
		player.pickupCrate();
		leave();
	}

}