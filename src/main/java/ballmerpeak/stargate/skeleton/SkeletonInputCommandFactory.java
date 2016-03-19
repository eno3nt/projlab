package ballmerpeak.stargate.skeleton;

import ballmerpeak.stargate.InputCommandFactory;
import ballmerpeak.stargate.commands.InputCommand;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

import java.util.List;

public class SkeletonInputCommandFactory implements InputCommandFactory {
	
	private List<InputCommand> commands;
	private int index;

	public SkeletonInputCommandFactory(List<InputCommand> commands) {
		this.commands = commands;
		this.index = 0;
	}

	@Override
	public InputCommand nextCommand() {
		enter();
		log("SkeletonInputCommandFactory#nextCommand");
		if (index == commands.size())
			return null;
		leave();
		return commands.get(index++);
	}

}
