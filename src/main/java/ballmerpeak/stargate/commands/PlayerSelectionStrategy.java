package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;

/**
 * selecting the right player object for the next command
 * (jaffa or oneil)
 */
public interface PlayerSelectionStrategy {

    /**
     * must return the player the next command should be executed on
     */
	public Player getPlayer(Game game);
}
