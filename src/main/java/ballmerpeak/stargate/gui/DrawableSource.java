package ballmerpeak.stargate.gui;

/**
 * 
 * @author ballmerpeak
 *
 * Used by the GameRenderer to get the tiles to draw
 * Created by a MapLoaderHelper implementation after reading all the tiles
 */
public interface DrawableSource {
	public int getWidth();
	public int getHeight();
	public Drawable tileAt(int y, int x);
}
