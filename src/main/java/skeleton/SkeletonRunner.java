package skeleton;


import static skeleton.SkeletonIO.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import commands.InputCommand;
import commands.MoveCommand;
import commands.PickupCommand;
import commands.QuitCommand;
import commands.ShootCommand;
import common.Direction;
import common.Game;
import tiles.Floor;
import tiles.ShotColor;
import tiles.Tile;
import utils.MapLoader;

/**
 * The Class SkeletonRunner.
 */
public class SkeletonRunner {
	
	/** The loader. */
	static MapLoader loader = new MapLoader();
	
	/** The game. */
	static Game game = loader.getGame();

	/** The Constant MOVE_UP. */
	static final InputCommand MOVE_UP = new MoveCommand(Direction.UP);

	/** The Constant SHOOT_YELLOW. */
	static final InputCommand SHOOT_YELLOW = new ShootCommand(ShotColor.YELLOW);

	/** The Constant PICKUP. */
	static final InputCommand PICKUP = new PickupCommand();

	/** The Constant QUIT. */
	static final InputCommand QUIT = new QuitCommand(game);

	/**
	 * Step on floor test.
	 */
	static void stepOnFloorTest() {
		setNextTile(new Floor());
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Shoot wall test.
	 */
	static void shootWallTest() {
		setNextTile(loader.wall);
		if (ask("Should the player shoot?")) {
			game.receiveInput(SHOOT_YELLOW);
		}
	}

	/**
	 * Shoot special wall test.
	 */
	static void shootSpecialWallTest() {
		setNextTile(loader.wall1);
		if (ask("Should the player shoot?")) {
			game.receiveInput(SHOOT_YELLOW);
		}
	}

	/**
	 * Step on scale test.
	 */
	static void stepOnScaleTest() {
		setNextTile(loader.scale);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Leave scale test.
	 */
	static void leaveScaleTest() {
		loader.player.setTile(loader.scale);
		loader.playerTile = loader.scale;
		setNextTile(new Floor());
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Step on door test.
	 */
	static void stepOnDoorTest() {
		setNextTile(loader.door);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Scale pickup test.
	 */
	static void scalePickupTest() {
		setNextTile(loader.scale);
		if (ask("Should the player attempt to drop or pickup crate?")) {
			game.receiveInput(PICKUP);
		}
	}

	/**
	 * Step on special wall.
	 */
	static void stepOnSpecialWall() {
		setNextTile(loader.wall1);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Step on pit test.
	 */
	static void stepOnPitTest() {
		setNextTile(loader.pit);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	/**
	 * Floor pickup test.
	 */
	static void floorPickupTest() {
		setNextTile(new Floor());
		if (ask("Should the player attempt to drop or pickup crate?")) {
			game.receiveInput(PICKUP);
		}
	}
	
	/**
	 * Quit.
	 */
	static void quit() {
		game.receiveInput(QUIT);
		System.exit(0);
	}

	/**
	 * Sets the next tile.
	 *
	 * @param tile the new next tile
	 */
	static void setNextTile(Tile tile) {
		loader.getPlayerTile().setNeightborForDirection(Direction.UP, tile);
	}

	/**
	 * Menu.
	 */
	static void menu() {
		System.out.println("which test to run?");
		System.out.println("0: scale pickup");
		System.out.println("1: step on floor");
		System.out.println("2: shoot wall");
		System.out.println("3: shoot special wall");
		System.out.println("4: step on scale");
		System.out.println("5: leave scale");
		System.out.println("6: step on door");
		System.out.println("7: step on special wall");
		System.out.println("8: step on pit");
		System.out.println("9: floor pickup");
		System.out.println("q: quit");
		String ans = question("");
		if (ans.toLowerCase().charAt(0) == 'q') {
			quit();
		}
		int i = Integer.parseInt(ans);
		switch (i) {
		case 0:
			scalePickupTest();
			break;
		case 1:
			stepOnFloorTest();
			break;
		case 2:
			shootWallTest();
			break;
		case 3:
			shootSpecialWallTest();
			break;
		case 4:
			stepOnScaleTest();
			break;
		case 5:
			leaveScaleTest();
			break;
		case 6:
			stepOnDoorTest();
			break;
		case 7:
			stepOnSpecialWall();
			break;
		case 8:
			stepOnPitTest();
			break;
		case 9:
			floorPickupTest();
			break;
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String... args) throws FileNotFoundException, IOException {
		while (true) {
			menu();
		}
	}
}
