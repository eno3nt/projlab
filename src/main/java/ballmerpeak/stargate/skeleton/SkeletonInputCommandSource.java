package ballmerpeak.stargate.skeleton;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.gui.InputCommandSource;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class SkeletonInputCommandSource implements InputCommandSource {

	@Override
	public void setInputCommandHandler(InputCommandHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputCommand getNextCommand() {
		// TODO Auto-generated method stub
		enter();
		log("SkeletonInputCommandSource#getNextCommand");
		leave();
		return null;
	}

}
