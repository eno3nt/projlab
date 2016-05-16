package ballmerpeak.stargate.gui;

/**
 * 
 * @author ballmerpeak
 *
 * The possible values indicating the state of a tile
 * The {@link Drawable#getDrawableIndex()} method must return one of these
 * based on the state of the tile.
 * The GameRenderer uses this to decide what to draw
 */
public enum DrawableIndex {
	FLOOR, FLOOR_WITH_ZPM, FLOOR_WITH_ZPM2, FLOOR_WITH_CRATE,
	
	SCALE, SCALE_WITH_CRATE, DOOR_CLOSED, DOOR_OPEN,
	
	WALL, SPECIAL_WALL_INACTIVE, SPECIAL_WALL_YELLOW, SPECIAL_WALL_BLUE,
	SPECIAL_WALL_GREEN, SPECIAL_WALL_RED,
	
	PIT,
	
	ONEILL_FACING_LEFT, ONEILL_FACING_RIGHT, ONEILL_FACING_UP, ONEILL_FACING_DOWN,
	
	JAFFA_FACING_UP, JAFFA_FACING_RIGHT, JAFFA_FACING_LEFT, JAFFA_FACING_DOWN,
	
	REPLICATOR_FACING_LEFT, REPLICATOR_FACING_RIGHT, REPLICATOR_FACING_UP, REPLICATOR_FACING_DOWN
	
}
