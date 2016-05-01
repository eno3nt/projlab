package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

/**
 * represents areas where the players can move to
 * also a base class for several other Tiles
 */
public class Floor extends Tile {

    /**
     * the strategy for generating new zpms
     */
	private static ZPMGeneratingStrategy zpmgs;
    /**
     * list containing all the other floors
     * needed for the zpm generation
     */
	public static List<Floor> floors = new ArrayList<>();

    /**
     * list containing the entities that are currently standing on it
     */
	protected List<Entity> entities;

    /**
     * true if it has a ZPM on it
     */
	private boolean ZPM;

    /**
     * the number of crates on the floor
     */
	private int numCrates;

	public static void setZPMGeneratingStrategy(ZPMGeneratingStrategy zpmgs_) {
		zpmgs = zpmgs_;
	}

	public Floor() {
		entities = new ArrayList<Entity>();
		ZPM = false;
		numCrates = 0;
	}

    /**
     * factory method used by the maploader
     */
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}

    /**
     * factory method used by the maploader
     */
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.numCrates = 1;
		return floor;
	}

    /**
     * return true if the number of crates on the floor is not zero
     */
	public boolean hasCrate() {
		return numCrates != 0;
	}

    /**
     * increments crate count and calls setCarrying(false) on the player
     */
	@Override
	public void dropCrateHere(Player player) {
		numCrates++;
		player.setCarrying(false);
	}

    /**
     * if there is a crate decrement the count and all setCarrying(true)
     * on the player
     */
	@Override
	public void pickupCrate(Player player) {
		if (hasCrate()) {
			numCrates--;
			player.setCarrying(true);
		}
	}

    /**
     * adds the entity to the list
     * if it has a zpm, calls the entity's steppedOnZPM method
     */
	@Override
	public void stepOnTile(Entity entity) {
		entities.add(entity);
		if (ZPM) {
			entity.steppedOnZPM(this);
		}
		entity.setTile(this);
		super.stepOnTile(entity);
	}

    /**
     * remove the entity from the list
     */
	@Override
	public void leaveTile(Entity entity) {
		super.leaveTile(entity);
		entities.remove(entity);
	}

    /**
     * if there are no entities transmits the shot to the next tile (in the right direction)
     * else calls shootIt on all the entities on the floor
     */
	@Override
	public void shootIt(ShotColor color, Direction dir) {
		if (!hasEntity() || allEntitiesOnFloorAreDead())
			super.shootIt(color, dir);
		else {
			for (Entity entity : entities) {
				entity.shootIt();
			}
			cleanupDeadEntities();
		}
	}

    /**
     * if it has an entity on the floor it returns its DrawableIndex
     * else returns an index based on its state (crate, zpm)
     */
	public DrawableIndex getDrawableIndex() {
		Random random = new Random();
		DrawableIndex d = (random.nextInt() % 2 == 0) ? DrawableIndex.FLOOR_WITH_ZPM : DrawableIndex.FLOOR_WITH_ZPM2;
		return hasEntity() ? entities.get(0).getDrawableIndex()
				: hasZPM() ? d : hasCrate() ? DrawableIndex.FLOOR_WITH_CRATE : DrawableIndex.FLOOR;
	}

	public boolean hasEntity() {
		return !entities.isEmpty();
	}

	protected boolean allEntitiesOnFloorAreDead() {
		return entities.stream().allMatch(e -> !e.isAlive());
	}

	protected void cleanupDeadEntities() {
		entities.removeIf(e -> !e.isAlive());
	}

	public void setZPM(boolean b) {
		this.ZPM = b;
		setDirty(true);
	}

	public boolean hasZPM() {
		return ZPM;
	}

    /**
     * generate a new zpm on a floor returned by the zpmgs
     */
	public static void generateNewZPM() {
		Floor floor = zpmgs.getFloorForNewZPM();
		floor.setZPM(true);
	}

	public static void addFloor(Floor floor) {
		floors.add(floor);
	}
}
