package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.model.Model;

public class Playermovement {
	
	/**
	 * @author Wiechec
	 * Test checks for player movement and correct functionality of player movement
	 * Boolean check for canMove method. Will return true for both statements
	 * if player is able to make the legal move. Also will return true
	 * if player is unable to make the move and does not make the move. 
	 * 
	 * Version is giving unstable results. Further testing is necessary to determine
	 * where the issue is in the code. 
	 */
	
	/**@author Wiechec
	 * commonAbleMoveBoolTest for pawn positioned at grid space 2,2
	 * @param dir direction pawn will travel
	 * @param expected expected result.
	 */
	public void commonAbleMoveBoolTest(String dir, Boolean expected){
		Model b = new Model("boardForPlayerTests.mls");
		Boolean actual = b.getCurrentPlayer().canMove(dir);
		
		assertTrue("Expected: "+expected+" Result: "+actual,expected==actual);
		
	}
	/**@author Wiechec
	 * commonAbleMoveBoolTest for pawn positioned at grid space 4,4
	 * @param dir direction pawn will travel
	 * @param expected expected result.
	 */
	public void commonAbleMoveBoolTestD(String dir, Boolean expected){
		Model b = new Model("boardForPlayerTests.mls");
		boolean actual = b.getCurrentPlayer().canMove(dir);
	//	System.out.println("e: "+expected);
	//	System.out.println("a: "+actual);
		assertTrue("Expected: "+expected+" Result: "+actual,expected==actual);
		
	}
	/**
	 * @author Wiechec
	 * All tests
	 */
	
	/**
	 * Test for pawn at 2,2 traveling west.
	 */
	@Test
	public final void test00() {
		commonAbleMoveBoolTest ("East", true);
	}
	/**
	 * Test for pawn at 2,2 traveling north
	 */
	@Test
	public final void test01() {
		commonAbleMoveBoolTest ("North", false);
	}
	/**
	 * Test for pawn at 2,2 traveling west
	 */
	@Test
	public final void test02() {
		commonAbleMoveBoolTest ("West", false);
	}
	/**
	 * Test for pawn at 2,2 traveling UP, which is not a valid move
	 */
	@Test
	public final void test03() {
		commonAbleMoveBoolTest ("UP", false);
	}
	/**
	 * Test for pawn at 2,2 traveling FLOPPY, which is invalid.
	 */
	@Test
	public final void test04() {
		commonAbleMoveBoolTest ("Floppy", false);
	}
	/**
	 * Test for pawn at 2,2 traveling south
	 */
	@Test
	public final void test05() {
		commonAbleMoveBoolTest ("South", false);
	}
	/**
	 * Test for pawn at 2,2 traveling Middle, which is invalid
	 */
	@Test
	public final void test06() {
		commonAbleMoveBoolTest ("Middle", false);
	}
	/**
	 * Test for pawn at 4,4 traveling north
	 */
	@Test
	public final void test07() {
		commonAbleMoveBoolTestD ("North", true);
	}
	/**
	 * Test for pawn at 4,4 traveling west
	 */
	@Test
	public final void test08() {
		commonAbleMoveBoolTestD ("West", true);
	}
	/**
	 * Test for pawn at 4,4 traveling south
	 */
	@Test
	public final void test09() {
		commonAbleMoveBoolTestD ("South", false);
	}
	/**
	 * Test for pawn at 4,4 traveling east
	 */
	@Test
	public final void test10() {
		commonAbleMoveBoolTestD ("East", true);
	}
	
	

}
