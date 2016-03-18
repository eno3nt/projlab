package ballmerpeak.stargate.skeleton.test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.skeleton.SkeletonInputCommandFactory;
import ballmerpeak.stargate.utils.MapLoader;

public class SkeletonTest {

	Game game;
	SkeletonInputCommandFactory factory;
	
	public SkeletonTest(String filename, List<InputCommand> commands) {
		try {
			MapLoader loader = new MapLoader(filename);
			game = loader.getGame();
			factory = new SkeletonInputCommandFactory(commands);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		InputCommand cmd;
		while ((cmd = factory.nextCommand()) != null) {
			game.receiveInput(cmd);
		}
	}
}
