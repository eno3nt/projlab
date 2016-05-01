/**
 * 
 */
package ballmerpeak.stargate;

/**
 * @author ballmerpeak
 *
 */
public class RandomReplicatorMovement implements ReplicatorMovementStrategy {

    /**
     * returns a random direction for the replicator to move towards
     */
	@Override
	public Direction getDirection() {
		return Direction.randomDirection();
	}

}
