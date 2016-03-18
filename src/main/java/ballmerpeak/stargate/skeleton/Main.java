package ballmerpeak.stargate.skeleton;

import java.io.FileNotFoundException;
import java.io.IOException;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandFactory;
import ballmerpeak.stargate.utils.MapLoader;
import noop.NoopInputCommandFactory;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class Main {
	
	private InputCommandFactory ifc;
	private Game game;
	
	public Main(MapLoader loader, InputCommandFactory ifc) throws FileNotFoundException, IOException {
		game = loader.getGame();
		this.ifc = ifc;
	}
	
	public void run() {
		while (!game.didPlayerWin() && game.isPlayerAlive()) {
			InputCommand cmd = ifc.nextCommand();
			game.receiveInput(cmd);
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		enter();
		log("Main#main");
		Main main = new Main(new MapLoader("map4.txt"), new NoopInputCommandFactory());
		main.run();
		leave();
	}
}
