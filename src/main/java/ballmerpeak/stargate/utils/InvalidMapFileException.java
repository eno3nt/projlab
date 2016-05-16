package ballmerpeak.stargate.utils;

/**
 * 
 * @author ballmerpeak
 *
 * thrown by the MapLoader if it reads an invalid mapfile
 * 
 */
public class InvalidMapFileException extends RuntimeException {

	public InvalidMapFileException(String msg) {
		super(msg);
	}
}
