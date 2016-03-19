package ballmerpeak.stargate.skeleton;

import ballmerpeak.stargate.commands.InputCommand;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import ballmerpeak.stargate.InputCommandHandler;
import ballmerpeak.stargate.InputCommandSource;

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
