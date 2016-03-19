package ballmerpeak.stargate.skeleton.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.MoveCommand;
import ballmerpeak.stargate.commands.PickupCommand;
import ballmerpeak.stargate.commands.ShootCommand;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

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
	
	static Game game;
	static Player player;
	static Tile playerTile;
	
	static Door door;
	static Scale scale;
	
	static Pit pit;
	
	static Gate gate;
	
	static SpecialWall wall1, wall2;
	
	static {
		gate = new Gate();
		wall1 = new SpecialWall(Direction.UP, gate);		
		wall2 = new SpecialWall(Direction.UP, gate);		
		
		door = new Door();
		scale = new Scale();
		scale.setDoor(door);
		
		pit = new Pit();
		
		player = new Player();
		playerTile = new Floor();
		player.setTile(playerTile);
		
		game = new Game(player, 0);
	}
	
	static void floorTest() {
		setNextTile(new Floor());
		if (yesNo("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}
	
	static void shootTest() {
		setNextTile(wall1);
		if (yesNo("Should the player shoot?")) {
			game.receiveInput(SHOOT_BLUE);
		}
	}
	
	static void scaleMoveTest() {
		setNextTile(scale);
		if (yesNo("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}
	
	static void doorMoveTest() {
		setNextTile(door);
		if (yesNo("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}
	
	static void scalePickupTest() {
		setNextTile(scale);
		if (yesNo("Should the player attempt to drop or pickup crate?")) {
			game.receiveInput(PICKUP);
		}
	}
	
	static void specialWallMoveTest() {
		setNextTile(wall1);
		if (yesNo("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}
	
	static void pitMoveTest() {
		setNextTile(pit);
		if (yesNo("Should the player move up?")) {
			game.receiveInput(MOVE_UP);
		}
	}
	
	static void setNextTile(Tile tile) {
		playerTile.setNeightborForDirection(Direction.UP, tile);
	}
	
	static void quit() {
		game.didPlayerWin();
		game.isPlayerAlive();
		System.exit(0);
	}
	
	static void menu() {
		System.out.println("which test to run?");
		System.out.println("0: floor move test");
		System.out.println("1: shoot special wall test");
		System.out.println("2: scale move test");
		System.out.println("3: door move test");
		System.out.println("4: scale pickup test");
		System.out.println("5: special wall move test");
		System.out.println("6: pit move test");
		System.out.println("q: quit");
		String ans = question("");
		if (ans.toLowerCase().charAt(0) == 'q') {
			quit();
		}
		int i = Integer.parseInt(ans);
		switch (i) {
		case 0: floorTest(); break;
		case 1: shootTest(); break;
		case 2: scaleMoveTest(); break;
		case 3: doorMoveTest(); break;
		case 4: scalePickupTest(); break;
		case 5: specialWallMoveTest(); break;
		case 6: pitMoveTest(); break;
		}
	}
	
	public static void main(String... args) throws FileNotFoundException, IOException {
		while (true) {
			menu();
		}
	}
}
