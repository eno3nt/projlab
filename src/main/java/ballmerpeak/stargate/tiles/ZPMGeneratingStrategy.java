/**
 * 
 */
package ballmerpeak.stargate.tiles;

/**
 * @author ballmerpeak
 *
 * used by the Floor for generating new ZPMs
 */
public interface ZPMGeneratingStrategy {
    /**
     * must return a Floor where a new zpm can be placed
     */
	Floor getFloorForNewZPM();
}
