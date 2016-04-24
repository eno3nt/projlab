package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandHandler;
import ballmerpeak.stargate.commands.PlayerSelectionStrategy;
import ballmerpeak.stargate.tiles.Floor;

/**
 * the interface between the ProtoRunner and the game objects
 * contains the players and the replicator
 */
public class Game implements InputCommandHandler {

	private Player oneil;
	private Player jaffa;

    /**
     * used for selecting the right player object for the Command
     * to be executed
     */
	private PlayerSelectionStrategy pss;

	private Replicator replicator;

    /**
     * used to get the next tile for the replicator movement
     */
	private ReplicatorMovementStrategy rms;

	public Game(Player player1, Player player2, Replicator replicator) {
		this.oneil = player1;
		this.jaffa = player2;
		this.replicator = replicator;
	}

    /**
     * the game ends when there are no more ZPMs left
     */
	public boolean didPlayersWin() {
		return !anyZPMsLeft();
	}

	public boolean isOnilAlive() {
		return oneil.isAlive();
	}

	public boolean isJaffaAlive() {
		return jaffa.isAlive();
	}

	public boolean bothPlayersAlive() {
		return isOnilAlive() && isJaffaAlive();
	}

	public Player getJaffa() {
		return jaffa;
	}

	public Player getOneil() {
		return oneil;
	}

	public Replicator getReplicator() {
		return replicator;
	}

	public void setPlayerSelectionStrategy(PlayerSelectionStrategy pss) {
		this.pss = pss;
	}

	public void setReplicatorMovementStrategy(ReplicatorMovementStrategy rms) {
		this.rms = rms;
	}

    /**
     * called by the ProtoRunner
     * retreives the target of the command with the PlayerSelectionStrategy object
     * after the command is executed the replicator moves (if it is alive)
     */
	@Override
	public void receiveInput(InputCommand command) {
		Player player = pss.getPlayer(this);
		command.execute(player);
		if (replicator.isAlive())
			replicator.move(rms.getDirection());
	}

	public boolean anyZPMsLeft() {
		return Floor.floors.stream().anyMatch(f -> f.hasZPM());
	}
}
