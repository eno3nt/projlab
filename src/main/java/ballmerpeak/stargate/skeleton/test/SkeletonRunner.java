package ballmerpeak.stargate.skeleton.test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.MoveCommand;
import ballmerpeak.stargate.commands.PickupCommand;
import ballmerpeak.stargate.commands.ShootCommand;
import ballmerpeak.stargate.skeleton.Pair;
import ballmerpeak.stargate.tiles.ShotColor;

public class SkeletonRunner {
	
	static final InputCommand MOVE_UP = new MoveCommand(Direction.UP);
	static final InputCommand MOVE_DOWN = new MoveCommand(Direction.DOWN);
	static final InputCommand MOVE_LEFT = new MoveCommand(Direction.LEFT);
	static final InputCommand MOVE_RIGHT = new MoveCommand(Direction.RIGHT);
	
	static final InputCommand SHOOT_YELLOW = new ShootCommand(ShotColor.YELLOW);
	static final InputCommand SHOOT_BLUE = new ShootCommand(ShotColor.BLUE);
	
	static final InputCommand PICKUP = new PickupCommand();
	
	static final Map<String, InputCommand> commandsMap = new HashMap<String, InputCommand>() {{
		put("up", MOVE_UP); put("down", MOVE_DOWN); put("left", MOVE_LEFT); put("right", MOVE_RIGHT);
		put("yellow", SHOOT_YELLOW); put("blue", SHOOT_BLUE);
		put("pickup", PICKUP);
	}};


	static List<Pair<String, List<InputCommand>>> testPairs = new ArrayList<>();
	
	static void readTestsFile(String filename) throws FileNotFoundException, IOException {
		try (BufferedReader r = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = r.readLine()) != null) {
				// commands must be on one line
				if (line.startsWith("#") || line.isEmpty())
					continue;
				String mapFile = line;
				String commandLine = r.readLine();
				String[] commandStrings = commandLine.split(" ");
				List<InputCommand> commands = new ArrayList<>();
				for (String s : commandStrings) {
					commands.add(commandsMap.get(s));
				}
				testPairs.add(new Pair<>("skeleton/" + mapFile, commands));
			}
		}
	}
	
	public static void main(String... args) throws FileNotFoundException, IOException {
		readTestsFile("skeleton/skeletontests.txt");
		List<SkeletonTest> tests = new ArrayList<>();
		for (Pair<String,List<InputCommand>> pair : testPairs) {
			tests.add(new SkeletonTest(pair.left, pair.right));
		}
		
		for (SkeletonTest test : tests) {
			test.run();
		}
	}
}
