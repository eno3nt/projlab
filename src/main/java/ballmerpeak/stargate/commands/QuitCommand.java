package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import ballmerpeak.stargate.Game;

public class QuitCommand implements InputCommand {
	
	private Game game;

	public QuitCommand(Game game) {
		enter();
		log("QuitCommand#QuitCommand");
		this.game = game;
		leave();
	}

	@Override
	public void execute(Player player) {
		enter();
		log("QuitCommand#execute");
		if (game.isPlayerAlive()) {
			if (game.didPlayerWin()) {
				log("the player won");
			} else {
				log("the player quit");
			}
		} else {
			log("the player died");
		}
		leave();
	}

}
