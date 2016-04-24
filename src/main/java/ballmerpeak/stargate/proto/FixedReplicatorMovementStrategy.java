package ballmerpeak.stargate.proto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.ReplicatorMovementStrategy;

/**
 * @author ballmerpeak
 *
 * Moves the replicator according to a pattern defined in a text file
 */
public class FixedReplicatorMovementStrategy implements ReplicatorMovementStrategy {

    /**
     * the movement directions the replicator must follow
     */
	private List<Direction> directions;

    /**
     * index into the directions list for the next movement direction
     */
	private int index;

    /**
     * reads the file given to it and constructs the list of directions
     * all directions must be on a single line separated by a single whitespace
     */
	public FixedReplicatorMovementStrategy(String filename) throws Exception {
		directions = new ArrayList<>();
		index = 0;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String line = br.readLine();
			String[] words = line.split(" ");
			for (String word : words) {
				switch (word) {
				case "up":
					directions.add(Direction.UP);
					break;
				case "down":
					directions.add(Direction.DOWN);
					break;
				case "left":
					directions.add(Direction.LEFT);
					break;
				case "right":
					directions.add(Direction.RIGHT);
					break;
				default:
					throw new Exception("invalid direction read from file " + filename);
				}
			}
		}
	}

    /**
     * returns the next direction from the list
     * when the last direction is returned it starts from the beginning
     */
	@Override
	public Direction getDirection() {
		Direction dir = directions.get(index);
		index++;
		index %= directions.size();
		return dir;
	}

}
