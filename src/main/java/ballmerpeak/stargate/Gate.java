package ballmerpeak.stargate;

import java.util.List;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;

/**
 * manages the specialwalls and the gates on them
 */
public class Gate {

    /**
     * contains references to all the SpecialWall objects
     * on the map
     */
	List<SpecialWall> walls;

    /**
     * checks whether there is an active gate for the given color
     * called by SpecialWall#canPlayerMoveHere
     */
	public boolean isActiveForColor(ShotColor color) {
		ShotColor destinationColor = getOtherColor(color);
		for (SpecialWall wall : walls) {
			if (wall.getColor() == destinationColor)
				return true;
		}
		return false;
	}

    /**
     * called by the maploader
     */
	public void setSpecialWalls(List<SpecialWall> walls) {
		this.walls = walls;
	}

    /**
     * called by the wall when it gets shot by the given color
     * updates the state of the affected walls
     */
	public void wallShot(SpecialWall wall, ShotColor color) {
		for (SpecialWall w : walls) {
			if (wall == w) {
				w.setColor(color);
			} else if (w.getColor() == color) {
				w.setColor(ShotColor.INACTIVE);
			}
		}
	}

    /**
     * called by SpecialWall#stepOnTile
     * retrieves the other end of the active gate (determined by the originColor)
     * and calls the teleport method on that wall
     */
	public void teleport(Entity entity, ShotColor originColor) {
		ShotColor destinationColor = getOtherColor(originColor);
		for (SpecialWall wall : walls) {
			if (wall.getColor() == destinationColor)
				wall.teleport(entity);
		}
	}

    /**
     * retrieves the "other" color for the given color
     * yellow - blue
     * red - green
     */
	private ShotColor getOtherColor(ShotColor color) {
		return color == ShotColor.BLUE ? ShotColor.YELLOW
				: color == ShotColor.YELLOW ? ShotColor.BLUE
						: color == ShotColor.GREEN ? ShotColor.RED : ShotColor.GREEN;
	}
}
