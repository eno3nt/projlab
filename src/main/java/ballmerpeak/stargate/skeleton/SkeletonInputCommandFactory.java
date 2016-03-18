package ballmerpeak.stargate.skeleton;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandFactory;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class SkeletonInputCommandFactory implements InputCommandFactory {

	@Override
	public InputCommand nextCommand() {
		enter();
		log("SkeletonInputCommandFactory#nextCommand");
		leave();
		return null;
	}

}
