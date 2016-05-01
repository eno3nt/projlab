/**
 * 
 */
package ballmerpeak.stargate;

/**
 * @author ballmerpeak
 *
 * interface for the replicator movement
 */
public interface ReplicatorMovementStrategy {

    /**
     * must return the direction the replictor should move towards
     */
	Direction getDirection();
}
