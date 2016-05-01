package ballmerpeak.stargate.utils;

import ballmerpeak.stargate.gui.DrawableSource;
import ballmerpeak.stargate.tiles.Tile;

public interface MapLoaderHelper {

	public void dimensionsRead(int height, int width);
	public void tileGenerated(Tile tile, int y, int x);
	public DrawableSource getGraphicsModel();
}
