package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.utils.MapLoaderHelper;

/**
 * 
 * @author ballmerpeak
 *
 * MapLoaderHelper implementation for the Swing GUI
 * 
 */
public class SwingMapLoaderHelper implements MapLoaderHelper {
	
	private Tile tiles[][];
	
	@Override
	public void tileGenerated(Tile tile, int y, int x) {
		tiles[y][x] = tile;
	}

	@Override
	public void dimensionsRead(int height, int width) {
		tiles = new Tile[height][width];
	}
	
	@Override
	public DrawableSource getGraphicsModel() {
		return new SwingGraphicsModel(tiles);
	}
}
