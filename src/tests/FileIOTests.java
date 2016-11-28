package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import code.FileIO;
import code.model.Model;

public class FileIOTests {

	private static final String[][] PLAYERS = {{"P1", "P2"}, //two players
			{"mal", "river", "kaylee"}, //three players
			{"Goran1", "Goran2", "Goran3", "Goran4"}}; //four players

	private static final String[] FILENAMES = {"test", FileIO.DEFAULT_FILENAME, FileIO.TEMP_FILENAME};

	/**
	 * Tests to make sure the FileIO.save method returns true.
	 * The method returns false only if it catches a FileNotFoundException.
	 * @author Blake
	 */
	public boolean testWrite(String filename, String[] playerNames) {
		return FileIO.save(filename, new Model(playerNames));
	}
	@Test(timeout = 1000) public void testWrite00() {
		testWrite("test4players", PLAYERS[2]);
	}
	@Test(timeout = 1000) public void testWrite01() {
		for (String[] s : PLAYERS)
			for (String filename : FILENAMES)
				assertTrue(testWrite(filename, s));

	}
	@Test(timeout = 1000) public void testWrite04() {
		for (String[] s : PLAYERS)
			assertFalse(testWrite(".mls", s));
	}

	/**
	 * Tests to ensure files are formed as expected.
	 * @author Blake
	 */
	@Test(timeout = 1000) public void testFileFormed() {
		for (String[] s : PLAYERS)
			for (String filename : FILENAMES) {
				Model m = new Model(s);
				FileIO.save(filename, m);
				assertEquals(m.toString(), FileIO.load(filename));
			}
	}
	
	/**
	 * Tests to ensure generated Model is equivalent when it is saved, then loaded.
	 * @author Blake
	 */
	public void testLoad(String filename, String[] players) {
		Model orig = new Model(players);
		FileIO.save(filename, orig);
		assertEquals(orig.toString(), new Model(filename).toString());
	}
	@Test(timeout = 1000) public void testLoad01() {
		for (String[] p : PLAYERS)
			for (String file : FILENAMES) 
				testLoad(file, p);
	}
}
