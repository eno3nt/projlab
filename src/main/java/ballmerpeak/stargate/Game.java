package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.skeleton.SkeletonIO;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game implements InputCommandHandler {

	/** The player. */
	private Player player;
	
	/** The number of zp ms. */
	private int numberOfZPMs;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param player the player
	 * @param numberOfZPMs the number of zp ms
	 */
	public Game(Player player, int numberOfZPMs) {
		SkeletonIO.enter();
		SkeletonIO.log("Game#Game");
		this.player = player;
		this.numberOfZPMs = numberOfZPMs;
		SkeletonIO.leave();
	}

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.InputCommandHandler#receiveInput(ballmerpeak.stargate.commands.InputCommand)
	 */
	@Override
	public void receiveInput(InputCommand command) {
		SkeletonIO.enter();
		SkeletonIO.log("Game#receiveInput");
		command.execute(player);
		SkeletonIO.leave();
	}

	/**
	 * Did player win.
	 *
	 * @return true, if successful
	 */
	public boolean didPlayerWin() {
		enter();
		log("Game#didPlayerWin");
		boolean answer = ask("Did the player win?");
		leave();
		return answer;
	}
	
	/**
	 * Checks if is player alive.
	 *
	 * @return true, if is player alive
	 */
	public boolean isPlayerAlive() {
		enter();
		log("Game#isPlayerAlive");
		boolean alive =  player.isAlive();
		leave();
		return alive;
	}
}
