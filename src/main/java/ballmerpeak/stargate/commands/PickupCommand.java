package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

/**
 * handles both the drop and pickup commands
 */
public class PickupCommand implements InputCommand {

    /**
     * if the player is alive it calls its pickupCrate method
     * it will call the appropriate method on the tile in front of it
     * (based on whether its carrying a crate or not)
     */
	@Override
	public void execute(Player player) {
		if (player.isAlive())
			player.pickupCrate();
	}

}
