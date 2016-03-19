package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.skeleton.SkeletonIO;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Game implements InputCommandHandler {

	private Player player;
	private int numberOfZPMs;
	
	public Game(Player player, int numberOfZPMs) {
		SkeletonIO.enter();
		SkeletonIO.log("Game#Game");
		this.player = player;
		this.numberOfZPMs = numberOfZPMs;
		SkeletonIO.leave();
	}

	@Override
	public void receiveInput(InputCommand command) {
		SkeletonIO.enter();
		SkeletonIO.log("Game#receiveInput");
		command.execute(player);
		SkeletonIO.leave();
	}

	public boolean didPlayerWin() {
		enter();
		log("Game#didPlayerWin");
		boolean answer = yesNo("Did the player win?");
		leave();
		return answer;
	}
	
	public boolean isPlayerAlive() {
		enter();
		log("Game#isPlayerAlive");
		leave();
		return player.isAlive();
	}
}
