package ballmerpeak.stargate.skeleton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.MoveCommand;
import ballmerpeak.stargate.commands.PickupCommand;
import ballmerpeak.stargate.commands.QuitCommand;
import ballmerpeak.stargate.commands.ShootCommand;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;
import ballmerpeak.stargate.utils.MapLoader;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class SkeletonRunner {
	
	static MapLoader loader = new MapLoader();
	static Game game = loader.getGame();

	static final InputCommand MOVE_UP = new MoveCommand(Direction.UP);

	static final InputCommand SHOOT_YELLOW = new ShootCommand(ShotColor.YELLOW);

	static final InputCommand PICKUP = new PickupCommand();

	static final InputCommand QUIT = new QuitCommand(game);

	static void floorTest() {
		setNextTile(new Floor());
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void shootWallTest() {
		setNextTile(loader.wall);
		if (ask("Should the player shoot?")) {
			game.receiveInput(SHOOT_YELLOW);
		}
	}

	static void shootSpecialWallTest() {
		setNextTile(loader.wall1);
		if (ask("Should the player shoot?")) {
			game.receiveInput(SHOOT_YELLOW);
		}
	}

	static void stepOnScaleTest() {
		setNextTile(loader.scale);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void leaveScaleTest() {
		loader.player.setTile(loader.scale);
		loader.playerTile = loader.scale;
		setNextTile(new Floor());
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void doorMoveTest() {
		setNextTile(loader.door);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void scalePickupTest() {
		setNextTile(loader.scale);
		if (ask("Should the player attempt to drop or pickup crate?")) {
			game.receiveInput(PICKUP);
		}
	}

	static void specialWallMoveTest() {
		setNextTile(loader.wall1);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void pitMoveTest() {
		setNextTile(loader.pit);
		if (ask("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}

	static void floorPickupTest() {
		setNextTile(new Floor());
		if (ask("Should the player attempt to drop or pickup crate?")) {
			game.receiveInput(PICKUP);
		}
	}
	static void quit() {
		game.receiveInput(QUIT);
		System.exit(0);
	}

	static void setNextTile(Tile tile) {
		loader.getPlayerTile().setNeightborForDirection(Direction.UP, tile);
	}

	static void menu() {
		System.out.println("which test to run?");
		System.out.println("0: floor move test");
		System.out.println("1: shoot wall test");
		System.out.println("2: step on scale test");
		System.out.println("3: leave scale test");
		System.out.println("4: door move test");
		System.out.println("5: floor pickup test");
		System.out.println("6: scale pickup test");
		System.out.println("7: special wall move test");
		System.out.println("8: pit move test");
		System.out.println("9: shoot special wall test");
		System.out.println("q: quit");
		String ans = question("");
		if (ans.toLowerCase().charAt(0) == 'q') {
			quit();
		}
		int i = Integer.parseInt(ans);
		switch (i) {
		case 0:
			floorTest();
			break;
		case 1:
			shootWallTest();
			break;
		case 2:
			stepOnScaleTest();
			break;
		case 3:
			leaveScaleTest();
			break;
		case 4:
			doorMoveTest();
			break;
		case 5:
			floorPickupTest();
			break;
		case 6:
			scalePickupTest();
			break;
		case 7:
			specialWallMoveTest();
			break;
		case 8:
			pitMoveTest();
			break;
		case 9:
			shootSpecialWallTest();
			break;
		}
	}

	public static void main(String... args) throws FileNotFoundException, IOException {
		while (true) {
			menu();
		}
	}
}
