package ballmerpeak.stargate.gui;

import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandFactory;

public class SwingInputCommandFactory extends InputCommandFactory {

	private KeyEvent event;

	public void setKeyEvent(KeyEvent e) {
		event = e;
	}
	
	private List<Integer> jaffaEvents = Arrays.asList(
			VK_W, VK_S, VK_A, VK_D,
			VK_Q, VK_E, VK_F
	);
	
	private List<Integer> oneilEvents = Arrays.asList(
			VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT,
			VK_J, VK_K, VK_L, VK_ESCAPE
	);

	@Override
	public InputCommand nextCommand() {
		
		if (oneilEvents.contains(event.getKeyCode())) {
			pss = oneilChooser;
		} else {
			pss = jaffaChooser; 
		}
		
		switch (event.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			return moveUp;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			return moveDown;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			return moveLeft;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			return moveRight;
		case KeyEvent.VK_J:
		case KeyEvent.VK_Q:
			return shootYellow;
		case KeyEvent.VK_K:
		case KeyEvent.VK_E:
			return shootBlue;
		case KeyEvent.VK_F:
		case KeyEvent.VK_L:
			return pickup;
		case KeyEvent.VK_ESCAPE:
			return quit;

		default:
			return unknown;
		}
	}
}
