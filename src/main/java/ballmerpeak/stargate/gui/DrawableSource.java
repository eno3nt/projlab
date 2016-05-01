package ballmerpeak.stargate.gui;

public interface DrawableSource {
	public int getWidth();
	public int getHeight();
	public Drawable tileAt(int y, int x);
}
