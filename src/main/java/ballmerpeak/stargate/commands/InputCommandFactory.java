package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.tiles.ShotColor;

/**
 * base class that contains commands that can be used to control the game
 * subclasses should return these commands from there nextCommand methods
 */
public abstract class InputCommandFactory {
	
    /**
     * strategies for selecting the players
     */
	protected PlayerSelectionStrategy jaffaChooser = new SelectJaffaStrategy();
	protected PlayerSelectionStrategy oneilChooser = new SelectOneilStrategy();
	
    /**
     * the active player selection strategy
     */
	protected PlayerSelectionStrategy pss = oneilChooser;
	
    /**
     * movement commands
     */
	protected InputCommand moveUp = new MoveCommand(Direction.UP);
	protected InputCommand moveDown = new MoveCommand(Direction.DOWN);
	protected InputCommand moveLeft = new MoveCommand(Direction.LEFT);
	protected InputCommand moveRight = new MoveCommand(Direction.RIGHT);

    /**
     * shooting commands
     */
	protected InputCommand shootBlue = new ShootCommand(ShotColor.BLUE);
	protected InputCommand shootYellow = new ShootCommand(ShotColor.YELLOW);

    /**
     * pickup command
     */
	protected InputCommand pickup = new PickupCommand();

    /**
     * quit command
     */
	protected InputCommand quit = new QuitCommand();

    /**
     * unknown command, should be returned if an unrecognized input is received
     */
	protected InputCommand unknown = new UnknownCommand();
	
    /**
     * must return the next command to be executed
     * also the pss object needs to be set accordingly
     */
	public abstract InputCommand nextCommand();
	
	public PlayerSelectionStrategy getPlayerSelectionStrategy() {
		return pss;
	}
}
