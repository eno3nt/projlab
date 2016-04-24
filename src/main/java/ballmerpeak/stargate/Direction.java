package ballmerpeak.stargate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * enum representing directions in game
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();

    /**
     * used by randomDirection()
     */
	private static final Random RANDOM = new Random();

    /**
     * random direction generation for the replicator movement
     */
	public static Direction randomDirection() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
