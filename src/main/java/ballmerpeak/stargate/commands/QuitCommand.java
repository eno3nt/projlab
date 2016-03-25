package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import ballmerpeak.stargate.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class QuitCommand.
 */
public class QuitCommand implements InputCommand {
	
	/** The game. */
	private Game game;

	/**
	 * Instantiates a new quit command.
	 *
	 * @param game the game
	 */
	public QuitCommand(Game game) {
		enter();
		log("QuitCommand#QuitCommand");
		this.game = game;
		leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.commands.InputCommand#execute(ballmerpeak.stargate.Player)
	 */
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
