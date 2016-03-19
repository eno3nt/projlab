package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

import static ballmerpeak.stargate.skeleton.SkeletonIO.*;

public class Scale extends Floor {

	private Door door;
	
	@Override
	public boolean pickupCrate(Player player) {
		enter();
		log("Scale#pickupCrate");
		boolean didPickUpCrate = yesNo("Could the player pick up the crate?");
		
		if (didPickUpCrate) {
			door.close();
			boolean answer = yesNo("Is the player standing on the door?");
			if (answer) {
				player.kill();
			}
		}
		leave();
		return didPickUpCrate;
	}

	@Override
	public boolean dropCrateHere(Player player) {
		enter();
		log("Scale#dropCrateHere");
		boolean didDropCrate = yesNo("Could the player drop the crate?");
		if (didDropCrate) {
			door.open();
		}
		leave();
		return didDropCrate;
	}

	@Override
	public void stepOnTile(Player player) {
		enter();
		log("Scale#stepOnTile");
		door.open();
		leave();;
	}

	@Override
	public void leaveTile(Player player) {
		enter();
		log("Scae#leaveTile");
		boolean hasCrate = yesNo("Is there a crate on the scale?");
		if (!hasCrate)
			door.close();
		boolean standing = yesNo("Is the player standing on the door?");
		if (!hasCrate && standing) {
			player.kill();
		}
		leave();
	}

	public void setDoor(Door door) {
		enter();
		log("Scale#setDoor");
		this.door = door;
		leave();
	}
}
