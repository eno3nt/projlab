package ballmerpeak.stargate.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Game;

public class MapLoaderTest {

	Game game;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		URL map1Path = this.getClass().getResource("/maps/map1.txt");
		// TODO: refactor SwingMapLoader to allow the use of both resources paths (included in JARs)
		// and file paths to load map files for automated tests.
		//SwingMapLoader loader = new SwingMapLoader(map1Path.getPath());
		//game = loader.getGame();
	}

	@Test
	public void testLabyrinthAttribs() {
		
	}
	

}
