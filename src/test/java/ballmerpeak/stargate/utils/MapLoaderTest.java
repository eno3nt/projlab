package ballmerpeak.stargate.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Game;

public class MapLoaderTest {

	Game game;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		SwingMapLoader loader = new SwingMapLoader(new File(Paths.get("src/main/resources/maps/map1.txt").toUri()));
		game = loader.getGame();
	}

	@Test
	public void testLabyrinthAttribs() {
		
	}
	

}
