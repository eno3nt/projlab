package commands;
import common.Player;
import tiles.ShotColor;

import static skeleton.SkeletonIO.*;
// TODO: Auto-generated Javadoc
/**
 * The Class ShootCommand.
 */
public class ShootCommand implements InputCommand {
	
	/** The color. */
	private ShotColor color;

	/**
	 * Instantiates a new shoot command.
	 *
	 * @param color the color
	 */
	public ShootCommand(ShotColor color) {
		enter();
		log("ShootCommand#ShootCommand");
		this.color = color;
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.commands.InputCommand#execute(ballmerpeak.stargate.Player)
	 */
	@Override
	public void execute(Player player) {
		enter();
		log("ShootCommand#execute");
		player.shoot(color);
		leave();
	}

}
