package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;

public class SelectJaffaStrategy implements PlayerSelectionStrategy {

    /**
     * returns the jaffa object
     */
	@Override
	public Player getPlayer(Game game) {
		return game.getJaffa();
	}

}
