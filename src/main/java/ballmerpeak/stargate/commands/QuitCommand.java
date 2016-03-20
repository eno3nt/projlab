package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import ballmerpeak.stargate.Game;

public class QuitCommand implements InputCommand {
	
	private Game game;

	public QuitCommand(Game game) {
		this.game = game;
	}

	@Override
	public void execute(Player player) {
		enter();
		log("QuitCommand#execute");
		if (!game.isPlayerAlive()) {
			log("the player died");
		} else if(game.didPlayerWin()) {
			log("the player won");
		}
		System.exit(0);
	}

}
