package commands;
import common.Player;


import static skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
/**
 * The Class PickupCommand.
 */
public class PickupCommand implements InputCommand {

	/* (non-Javadoc)
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