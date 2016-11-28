package tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import code.tile.ATile;
import code.tile.ITile;
import code.tile.LTile;
import code.tile.TTile;
import code.tokens.Token;

public class TileTests {
	/**
	 * @author bdlipp, Matt
	 * all tests
	 */
	@Test public void testNorthHasDirectionNorth()
	{
		String expected="North";
		LTile ebt = new LTile("North");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testEastHasDirectionEast()
	{
		String expected="East";
		LTile ebt = new LTile("East");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testSouthHasDirectionSouth()
	{
		String expected="South";
		LTile ebt = new LTile("South");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testWestHasDirectionWest()
	{
		String expected="West";
		LTile ebt = new LTile("West");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testNorthNumHasDirectionNorth()
	{
		String expected="North";
		LTile ebt = new LTile(0);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testEastNumHasDirectionEast()
	{
		String expected="East";
		LTile ebt = new LTile(1);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testSouthNumHasDirectionSouth()
	{
		String expected="South";
		LTile ebt = new LTile(2);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testWestNumHasDirectionWest()
	{
		String expected="West";
		LTile ebt = new LTile(3);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateNorth()
	{
		String expected="North";
		LTile ebt = new LTile("East");
		ebt.rotate("North");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateEast()
	{
		String expected="East";
		LTile ebt = new LTile("North");
		ebt.rotate("East");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateSouth()
	{
		String expected="South";
		LTile ebt = new LTile("East");
		ebt.rotate("South");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateWest()
	{
		String expected="West";
		LTile ebt = new LTile("East");
		ebt.rotate("West");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateNorthWithNumber()
	{
		String expected="North";
		LTile ebt = new LTile("East");
		ebt.rotate(0);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateEastWithNumber()
	{
		String expected="East";
		LTile ebt = new LTile("North");
		ebt.rotate(1);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateSouthWithNumber()
	{
		String expected="South";
		LTile ebt = new LTile("East");
		ebt.rotate(2);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	@Test public void testRotateWestWithNumber()
	{
		String expected="West";
		LTile ebt = new LTile("East");
		ebt.rotate(3);
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	
	
	/**
	 * Tests whether a non-rotated TTile is as expected
	 */
	@Test
	public void testTTile01() //1
	{
		boolean[] expected = {false, true, true, true};
		ATile tile = new TTile(0);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the T tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether one rotation of a T tile results in the correct connections array
	 */
	@Test
	public void testOneRotationT() //2
	{
		boolean[] expected = {true, false, true, true};
		ATile tile = new TTile(1);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the T tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether two rotations of a T tile results in the correct connections array
	 */
	@Test
	public void testTwoRotationsT() //3
	{
		boolean[] expected = {true, true, false, true};
		ATile tile = new TTile(2);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the T tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether three rotations of a T tile results in the correct connections array
	 */
	@Test
	public void testThreeRotationsT() //4
	{
		boolean[] expected = {true, true, true, false};
		ATile tile = new TTile(3);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the T tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether a non-rotated ITile is as expected
	 */
	@Test
	public void testITile01() //6
	{
		boolean[] expected = {true, false, true, false};
		ATile tile = new ITile(0);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the I tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether one rotation of an I tile results in the correct connections array
	 */
	@Test
	public void testOneRotationI() //7
	{
		boolean[] expected = {false, true, false, true};
		ATile tile = new ITile(1);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the I tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether two rotations of an I tile results in the correct connections array
	 */
	@Test
	public void testTwoRotationsI() //8
	{
		boolean[] expected = {true, false, true, false};
		ATile tile = new ITile(2);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the I tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether three rotations of an I tile results in the correct connections array
	 */
	@Test
	public void testThreeRotationsI() //9
	{
		boolean[] expected = {false, true, false, true};
		ATile tile = new ITile(3);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the I tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether a non-rotated LTile is as expected
	 */
	@Test
	public void testLTile01() //12
	{
		boolean[] expected = {false, true, true, false};
		ATile tile = new LTile(0);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the L tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether one rotation of a L tile results in the correct connections array
	 */
	@Test
	public void testOneRotationL() //13
	{
		boolean[] expected = {false, false, true, true};
		ATile tile = new LTile(1);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the L tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether two rotations of a L tile results in the correct connections array
	 */
	@Test
	public void testTwoRotationsL() //14
	{
		boolean[] expected = {true, false, false, true};
		ATile tile = new LTile(2);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the L tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests whether three rotations of a L tile results in the correct connections array
	 */
	@Test
	public void testThreeRotationsL() //15
	{
		boolean[] expected = {true, true, false, false};
		ATile tile = new LTile(3);
		boolean[] actual = new boolean[]{tile.getDirection("North"), tile.getDirection("East"), tile.getDirection("South"), tile.getDirection("West")};
		assertTrue("I expected the L tile to have a connection array of " + Arrays.toString(expected) + ", but it was " + Arrays.toString(actual), Arrays.equals(expected, actual));
	}
	/**
	 * Tests that a tile has a token associated with it once it has been added
	 */
	@Test
	public void testTokenAdd() //17
	{
		int tokenValue = (int)(Math.random() * 21);
		boolean expected = true; 
		ATile t = new TTile(0);
		t.setToken(new Token(tokenValue));
		boolean actual = t.hasToken();
		assertTrue("I expected that the tile having a token was" + expected + ", but it was " + actual, expected == actual);
	}
	/**
	 * Tests that a tile has a token associated with it once it has been added
	 */
	@Test
	public void testTokenGet() //17
	{
		int tokenValue = (int)(Math.random() * 21);
		ATile t = new TTile(0);
		t.setToken(new Token(tokenValue));
		int actual = t.getToken().getValue();
		assertTrue("I expected that the tile had a token with value" + tokenValue + ", but it was " + actual, tokenValue == actual);
	}
	/**
	 * Tests that a tile is initially created with no token
	 */
	@Test
	public void testTokenNotInitiallyInTile() //18
	{
		boolean expected = false;
		ATile t = new ITile(0);
		boolean actual = t.hasToken();
		assertTrue("I expected that the tile having a token was" + expected + ", but it was " + actual, expected == actual);
	}
	/**
	 * Tests that the removal of a token will result in no token associated with the tile
	 */
	@Test
	public void testTokenGoneAfterRemoval() //19
	{
		boolean expected = false;
		ATile t = new LTile(0);
		t.setToken(new Token(1));
		t.pickUpToken();
		boolean actual = t.hasToken();	
		assertTrue("I expected that the tile having a token was" + expected + ", but it was " + actual, expected == actual);
	}
	/**
	 * Tests that a token has the same value before addition and after removal
	 */
	@Test
	public void testTokenValueMaintainance() //20
	{
		int tokenValue = (int)(Math.random() * 21);
		int expected = tokenValue;
		ATile t = new TTile(0);
		t.setToken(new Token(tokenValue));
		int actual = t.pickUpToken().getValue();
		assertTrue("I expected the added token to have value " + expected + ", but it was " + actual, expected == actual);
	}
}

