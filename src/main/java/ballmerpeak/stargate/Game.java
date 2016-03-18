package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.skeleton.SkeletonLogger;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Game implements InputCommandHandler {

	private Player player;
	private int numberOfZPMs;
	
	public Game(Player player, int numberOfZPMs) {
		SkeletonLogger.enter();
		SkeletonLogger.log("Game#Game");
		this.player = player;
		this.numberOfZPMs = numberOfZPMs;
		SkeletonLogger.leave();
	}

	@Override
	public void receiveInput(InputCommand command) {
		SkeletonLogger.enter();
		SkeletonLogger.log("Game#receiveInput");
		command.execute(player);
		SkeletonLogger.leave();
	}

	public boolean didPlayerWin() {
		enter();
		log("Game#didPlayerWin");
		leave();
		return player.getZPMsCarried() == numberOfZPMs;
	}
	
	public boolean isPlayerAlive() {
		enter();
		log("Game#isPlayerAlive");
		leave();
		return player.isAlive();
	}
}
