/**
 * 
 */
package ballmerpeak.stargate.proto;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandFactory;

/**
 * @author ballmerpeak
 *
 * interprets some of the commands read by the protorunner
 */
public class ProtoInputCommandFactory extends InputCommandFactory {

    /**
     * set by the protorunner
     * used to figure out the right command object to return
     */
	private String string;

    /**
     * is oneil the target of the next command?
     */
	public boolean oneil = true;

    /**
     * called by the protorunner
     */
	public void setInputString(String string) {
		this.string = string;
	}

    /**
     * return the next command based on the string set by the protorunner
     */
	@Override
	public InputCommand nextCommand() {

        /**
         * quit
         */
		if (string.startsWith("q")) {
			return quit;
		}
		
        /**
         * split it up
         */
		String words[] = string.split(" ");

        /**
         * unknown command for the empty string
         */
		if (words.length == 0)
			return unknown;

        /**
         * set up the playerselection strategy
         */
		if (words[0].startsWith("j")) {
			oneil = false;
			pss = jaffaChooser;
			return unknown;
		} else if (words[0].startsWith("o")) {
			oneil = true;
			pss = oneilChooser;
			return unknown;
		}

        /**
         * handle the actual command
         * pickup, move, shoot
         */
		switch (words[0]) {
		case "pickup":
		case "p":
			return pickup;
		case "up":
		case "u":
			return moveUp;
		case "down":
		case "d":
			return moveDown;
		case "left":
		case "l":
			return moveLeft;
		case "right":
		case "r":
			return moveRight;
		case "shoot":
			if (words.length != 2)
				return unknown;
			switch (words[1]) {
			case "yellow":
			case "y":
				return shootYellow;
			case "blue":
			case "b":
				return shootBlue;
			case "green":
			case "g":
				return shootBlue;
			case "red":
			case "r":
				return shootYellow;
			default:
				return unknown;
			}
		default:
			return unknown;
		}
	}

}
