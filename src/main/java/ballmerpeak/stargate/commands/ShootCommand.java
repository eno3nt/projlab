package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.ShotColor;

import static ballmerpeak.stargate.skeleton.SkeletonLogger.*;

public class ShootCommand implements InputCommand {
	
	private ShotColor color;

	public ShootCommand(ShotColor color) {
		enter();
		log("ShootCommand#ShootCommand");
		this.color = color;
		leave();
	}

	@Override
	public void execute(Player player) {
		enter();
		log("ShootCommand#execute");
		player.shoot(color);
		leave();
	}

}
