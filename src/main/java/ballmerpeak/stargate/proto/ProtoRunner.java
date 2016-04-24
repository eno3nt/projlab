package ballmerpeak.stargate.proto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.RandomReplicatorMovement;
import ballmerpeak.stargate.ReplicatorMovementStrategy;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;

/**
 * @author ballmerpeak
 *
 * the entry point for the prototype
 */
public class ProtoRunner {

	MapLoader loader;
	Game game;
	ProtoInputCommandFactory icf;
	ProtoIO io;

    /**
     * files for fixing the random elements
     */
	String replicatorFile, zpmFile;
	
    /**
     * directory containing the map, tests etc.
     */
	static String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
	
	ReplicatorMovementStrategy fixedReplicatorMovementStrategy;

	private boolean prompt;

    /**
     * resets the state of the game
     */
	private void reset() throws Exception {
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map5.txt";
		loader = new MapLoader(mapFile);
		game = loader.getGame();
		icf = new ProtoInputCommandFactory();
		io = new ProtoIO(game);
		fixedReplicatorMovementStrategy = new FixedReplicatorMovementStrategy(replicatorFile);
		prompt = true;
	}

    /**
     * enables or disables the random elements
     */
	private void setRandom(boolean b) {
		if (b) {
			game.setReplicatorMovementStrategy(new RandomReplicatorMovement());
		} else {
			game.setReplicatorMovementStrategy(fixedReplicatorMovementStrategy);
		}
	}
	
	private void setPrompt(boolean b) {
		prompt = b;
	}

    /**
     * constructor
     * sets up the dataDirectory and the files
     * calls reset() initializing the game
     */
	public ProtoRunner(String zpmFile, String replicatorFile) throws Exception {
		
		this.zpmFile = dataDirectory + "/random/" + zpmFile;
		this.replicatorFile = dataDirectory + "/random/" + replicatorFile;
		reset();
	}

    /**
     * the main loop
     * reads input from stdin, interprets some commands by itself,
     * delegates the rest to the inputcommandfactory
     */
	public void run() throws Exception {
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		io.printInfo();
		prompt();
		while ((line = reader.readLine()) != null) {
		
			if (line.equals("reset")) {
				reset();
				continue;
			}
			
			else if (line.startsWith("prompt")) {
				String words[] = line.split(" ");
				if (words.length != 2)
					continue;
				setPrompt(words[1].equals("on"));

			}

			else if (line.startsWith("random")) {
				String words[] = line.split(" ");
				if (words.length != 2)
					continue;
				setRandom(words[1].equals("on"));
				continue;
			}
			
			else if (line.startsWith("rep")) {
				io.printReplicatorInfo();
			}
			
			else {
				icf.setInputString(line);
				InputCommand command = icf.nextCommand();
				game.setPlayerSelectionStrategy(icf.getPlayerSelectionStrategy());
				game.receiveInput(command);
				icf.setInputString(line);
			}
			
			io.printInfo();
			if (prompt)
				prompt();
		}
	}

	private void prompt() {
		System.out.format("%s> ", icf.oneil ? "oneil" : "jaffa");
	}

    /**
     * entry point
     * instantiates a ProtoRunner and calls it's run method
     */
	public static void main(String... args) throws Exception {
		new ProtoRunner("zpm", "replicator").run();
	}
}
