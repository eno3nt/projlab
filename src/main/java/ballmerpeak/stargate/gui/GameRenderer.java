package ballmerpeak.stargate.gui;

/**
 * 
 * @author ballmerpeak
 * 
 * Used by the GameWindow to draw the game
 * Uses the DrawableSource interface to get the tiles
 */
public interface GameRenderer {
	public void setDrawableSource(DrawableSource src);
	public void drawGame();
}
