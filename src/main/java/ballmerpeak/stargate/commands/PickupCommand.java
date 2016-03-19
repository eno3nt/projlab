package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class PickupCommand implements InputCommand {

	@Override
	public void execute(Player player) {
		enter();
		log("PickupCommand#execute()");
		player.pickupCrate();
		leave();
	}

}
