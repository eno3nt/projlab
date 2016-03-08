package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;
import ballmerpeak.stargate.utils.SwingMapLoader;

public class GameWindow extends JFrame implements KeyListener, InputCommandSource {
	private GameRenderer canvas;
	private InputCommandHandler inputHandler;
	private Game game;
	private DrawableSource gfxModel;
	private SwingInputCommandFactory ifc;

	public GameWindow() throws FileNotFoundException, IOException {
		String mapDirectory = "/maps/";
		MapLoader loader = new SwingMapLoader(mapDirectory + "map4.txt");

		game = loader.getGame();
		gfxModel = loader.getGraphicsModel();
		GameCanvas canvas = new GameCanvas(gfxModel.getHeight(), gfxModel.getWidth());
		canvas.loadAssets("/images/");
		canvas.setDrawableSource(gfxModel);
		this.canvas = canvas;

		add((JPanel) canvas);

		setInputCommandHandler(game);
		ifc = new SwingInputCommandFactory();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setSize(760, 760);
		setVisible(true);
		canvas.drawGame();
	}

	public static void main(String... args) throws IOException {
		new GameWindow();
	}
	

	public void setInputCommandHandler(InputCommandHandler handler) {
		this.inputHandler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ifc.setKeyEvent(e);
		InputCommand cmd = ifc.nextCommand();
		inputHandler.receiveInput(cmd);
		if (!game.isPlayerAlive()) {
			System.exit(0);
		}
		if (game.didPlayerWin()) {
			System.exit(0);
		}
		canvas.drawGame();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputCommand getNextCommand() {
		return null;
	}

}
