package ballmerpeak.stargate.gui;

/*
 * Tiles have to implement Drawable.
 * GameRenderer calls getDrawableIndex on each tile,
 * the tiles respond with the appropriate enum constant.
 * GameRenderer then can decide what to draw.
 */
public interface Drawable {

	public DrawableIndex getDrawableIndex();
	public boolean isDirty();
	public void setDirty(boolean b);
}
