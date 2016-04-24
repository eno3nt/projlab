package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.ShotColor;

public class ShootCommand implements InputCommand {
	
    /**
     * the color of the shot
     */
	private ShotColor color;

	public ShootCommand(ShotColor color) {
		this.color = color;
	}

    /**
     * if the player is alive it calls its shoot method
     * with the color of the shot
     */
	@Override
	public void execute(Player player) {
		if (player.isAlive())
			player.shoot(color);
	}

}
