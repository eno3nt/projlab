package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;

public interface InputCommandHandler {
	public void receiveInput(InputCommand command);
}
