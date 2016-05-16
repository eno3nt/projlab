package ballmerpeak.stargate.utils;

import ballmerpeak.stargate.gui.DrawableSource;
import ballmerpeak.stargate.tiles.Tile;

/**
 * 
 * @author ballmerpeak
 *
 * used by the MapLoader
 * creates the DrawableSource for the GameRenderer
 * 
 * methods called by the MapLoader when reading in the mapfile
 * 
 */
public interface MapLoaderHelper {

	public void dimensionsRead(int height, int width);
	public void tileGenerated(Tile tile, int y, int x);
	public DrawableSource getGraphicsModel();
}
