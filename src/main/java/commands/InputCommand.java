package commands;
import common.Player;

/**
 * The Interface InputCommand.
 */
public interface InputCommand {
	
	/**
	 * Execute.
	 *
	 * @param player the player
	 */
	public void execute(Player player);
}
