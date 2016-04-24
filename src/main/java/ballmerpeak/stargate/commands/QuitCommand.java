package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

public class QuitCommand implements InputCommand {
	
    /**
     * quits the game
     */
	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
