package ballmerpeak.stargate.tiles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class DoorAndScaleTest {
	
	Door door;
	Scale scale;
	Player player;
	
	@Before
	public void setup() {
		Position doorPos = new Position(20, 30);
		Position scalePos = new Position(13, 23);
		door = new Door(doorPos);
		scale = new Scale(scalePos);
		scale.setDoor(door);
		
		player = new Player();
		player.setPosition(new Position(0, 0));
	}

	@Test
	public void testStepOn() {
		scale.stepOnTile(player);
		assertTrue(door.isOpen());
		scale.leaveTile();
		assertFalse(door.isOpen());
	}
	
	@Test
	public void dropCrateTest() {
		scale.dropCrateHere(player);
		assertTrue(door.isOpen());
		assertTrue(scale.isOccupied());
	}
	
	@Test
	public void testDoorCanKillPlayer() {
		door = new Door(new Position(20, 31));
		scale = new Scale (new Position(20, 30));
		scale.setDoor(door);
		player.setPosition(new Position(20, 29));
		player.setDirection(Direction.RIGHT);
		player.stepForward();
		scale.stepOnTile(player);
		assertEquals(scale.getPosition(), player.getPosition());
		assertTrue(door.isOpen());
		scale.leaveTile();
		player.stepForward();
		door.stepOnTile(player);
		assertEquals(door.getPosition(), player.getPosition());
		assertFalse(player.isAlive());
		assertFalse(door.isOpen());
	}

}
