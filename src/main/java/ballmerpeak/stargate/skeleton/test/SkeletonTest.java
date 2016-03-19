package ballmerpeak.stargate.skeleton.test;

import java.io.FileNotFoundException;
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
	String description;
	MapLoader loader;
	
	List<InputCommand> commands;
	
	public String getDescription() {
		return description;
	}

	public SkeletonTest(String mapFilename, List<InputCommand> commands, String desc) {
		try {
			loader = new MapLoader(mapFilename);
			this.commands = commands;
			description = desc;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		try {
			game = loader.getGame();
			factory = new SkeletonInputCommandFactory(commands);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputCommand cmd;
		while ((cmd = factory.nextCommand()) != null) {
			game.receiveInput(cmd);
		}
	}
}
