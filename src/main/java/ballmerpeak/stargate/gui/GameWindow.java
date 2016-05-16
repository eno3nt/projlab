package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.RandomReplicatorMovement;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;
import ballmerpeak.stargate.utils.MapLoaderHelper;

/**
 * the Swing window containing the game
 * entry point for the application
 * initializes the game with the MapLoader
 * handles events from the windowing system, transmits them to the game object
 * uses the GameCanvas to draw the game
 * 
 * @author ballmerpeak
 *
 */
public class GameWindow extends JFrame implements KeyListener {
	
	// the renderer
	private GameRenderer canvas;
	
	// the game
	private Game game;
	
	// translates swing events to InputCommands
	private SwingInputCommandFactory ifc;
	
	/**
	 * constructor
	 * 
	 * @throws Exception
	 */
	public GameWindow() throws Exception {
		
		// setup directories
		String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		String mapDirectory = dataDirectory + "/maps/";
		
		// the map file
		String mapFile = mapDirectory + "map5.txt";
		
		// create the maploader and the helper
		MapLoaderHelper mlh = new SwingMapLoaderHelper();
		MapLoader loader = new MapLoader(mapFile);
		loader.setHelper(mlh);

		// setup the game
		game = loader.getGame();

		// set the random movement for the replicator
		game.setReplicatorMovementStrategy(new RandomReplicatorMovement());
		
		// load the images
		dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		GameCanvas.loadAssets(dataDirectory + "/images/");

		// create the canvas and set its graphics model
		DrawableSource gfxModel = mlh.getGraphicsModel();
		canvas = new GameCanvas(gfxModel.getHeight(), gfxModel.getWidth());
		canvas.setDrawableSource(gfxModel);

		add((JPanel) canvas);

		// setup the inputCommandFactory
		ifc = new SwingInputCommandFactory();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// register ourselves as the keylistener
		addKeyListener(this);
		
		// show the window
		setSize(760, 760);
		setVisible(true);
		canvas.drawGame();
	}
	
	/**
	 * main entry point
	 * @param args
	 * @throws Exception
	 */
	public static void main(String... args) throws Exception {
		new GameWindow();
	}

	/**
	 * called by swing when a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		// set the key event for the factory
		ifc.setKeyEvent(e);
		
		// get the next command 
		InputCommand cmd = ifc.nextCommand();
		
		// set the games playerSelectionStrategy
		game.setPlayerSelectionStrategy(ifc.getPlayerSelectionStrategy());
		
		// send the input to the game
		game.receiveInput(cmd);
		
		// check for dead players
		if (!game.isOneilAlive() || !game.isJaffaAlive()) {
			// quit the game if one of them is dead
			System.exit(0);
		}
		
		// check for victory condition
		if (game.didPlayersWin()) {
			// quit if somebody won
			System.exit(0);
		}
		
		// redraw the game
		canvas.drawGame();
	}

	/**
	 * needed empty implementation
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * needed empty implementation
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
