package ballmerpeak.stargate.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * @author ballmerpeak
 *
 * GameRenderer implementation for the Swing GUI
 */
public class GameCanvas extends JPanel implements GameRenderer {
	
	// Tile sizes
	private static final int TILE_WIDTH = 32;
	private static final int TILE_HEIGHT = 32;

	// the images drawn on the screen
	private static final String imageFormat = "png";
	private static final Image tileImages[] = new Image[DrawableIndex.values().length];

	// the object containing the tiles
	private DrawableSource gfxModel;
	
	// double buffering
	private Image backBuffer;

	/**
	 * constructor setting up the backbuffer
	 * 
	 * @param height
	 * @param width
	 */
	public GameCanvas(int height, int width) {
		this.backBuffer = new BufferedImage(width * TILE_WIDTH, height * TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * method called by Swing to draw the component
	 */
	@Override
	protected void paintComponent(Graphics g) {
		synchronized (backBuffer) {
			redraw(g);
		}
	}

	/**
	 * draws the game to the screen from the backbuffer
	 * 
	 * @param g
	 */
	public void redraw(Graphics g) {
		int imgW = this.backBuffer.getWidth(null);
		int imgH = this.backBuffer.getHeight(null);
		int winW = super.getWidth();
		int winH = super.getHeight();
		float imageRatio = (float)imgW/imgH;
		float windowRatio = (float)winW/winH;
		int offX = 0;
		int offY = 0;
		if(windowRatio > 1.0) {
			imgH = winH;
			imgW = (int)(imgH * imageRatio);
			offX = (winW - imgW)/2;
		} else if(windowRatio < 1.0) {
			imgW = winW;
			imgH = (int)(imgW / imageRatio);
			offY = (winH - imgH)/2;
		}
		
		g.drawImage(backBuffer, offX, offY, imgW, imgH, null);
	}

	/**
	 * template method indicating double buffering
	 */
	@Override
	public boolean isDoubleBuffered() {
		return true;
	}

	public static void loadAssets(String path) throws IOException {
		for (DrawableIndex asset : DrawableIndex.values()) {
			String assetFileName = path + asset.name() + "." + imageFormat;
			File assetFile = new File(assetFileName);
			if (!assetFile.exists()) {
				System.err.println("[WARNING] Asset not found: " + assetFileName);
				continue;
			}
			tileImages[asset.ordinal()] = ImageIO.read(assetFile);
		}
	}

	/**
	 * 
	 * @param asset the index to be rendered
	 * @return the Image (indexed by the asset)
	 */
	private static Image getAsset(DrawableIndex asset) {
		return tileImages[asset.ordinal()];
	}

	/**
	 * draws the game into the backbuffer
	 */
	@Override
	public void drawGame() {
		synchronized (this.backBuffer) {
			Graphics g = backBuffer.getGraphics();
			
			// iterate through the tiles in the gfxModel
			for (int y = 0; y < gfxModel.getHeight(); y++) {
				for (int x = 0; x < gfxModel.getWidth(); x++) {
					// get the tile from the model at the coordinates
					Drawable tile = gfxModel.tileAt(y, x);
					
					// check if redrawing is needed
					if (!tile.isDirty())
						continue;
					
					// get the image for the state of the tile
					DrawableIndex drawableIndex = tile.getDrawableIndex();
					Image image = getAsset(drawableIndex);
					
					// draw the tile into the buffer
					int scrX = x * TILE_WIDTH;
					int scrY = y * TILE_HEIGHT;
					g.drawImage(image, scrX, scrY, TILE_WIDTH, TILE_HEIGHT, null);
					
					// clear the dirty flag
					tile.setDirty(false);
				}
			}
			
			// update the screen from the backbuffer
			redraw(getGraphics());
		}
	}

	/**
	 * called by the GameWindow
	 */
	@Override
	public void setDrawableSource(DrawableSource src) {
		gfxModel = src;
	}
}
