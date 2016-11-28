package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.FileIO;
import code.model.Model;
import code.tile.ATile;
import code.tile.ITile;
import code.tile.LTile;
import code.tile.TTile;
/**
 * Gives tests for the Board class
 * @author Matt, Steven, Blake
 *
 */
public class BoardTests 
{
	private ATile[][] board;
	private Model m;
	
	@Before
	public void setUp()
	{
		m = new Model(new String[]{"River", "Mal"});
		board = m.getBoard();
	}
	
	@After
	public void tearDown()
	{
		m = null;
		board = null;
	}

	/**
	 * Checks that the correct number of T tiles are placed in the board
	 * @author Matt
	 */
	@Test
	public void testProperNumberOfTs()
	{
		int expected = 18;
		
		int actual = 0;
		for (ATile[] e: board)
			for (ATile f: e)
			if (f.toString().charAt(1) == 'T')
				actual++;
		if (m.getHoldTile().toString().charAt(1) == 'T')
			actual++;
		assertEquals(expected, actual);
	}
	
	/**
	 * Checks that the correct number of I tiles are placed in the board
	 * @author Matt
	 */
	@Test
	public void testProperNumberOfIs()
	{
		int expected = 13;
		
		ATile[][] board = m.getBoard();
		int actual = 0;
		for (ATile[] e: board)
			for (ATile f: e)
			if (f.toString().charAt(1) == 'I')
				actual++;
		if (m.getHoldTile().toString().charAt(1) == 'I')
			actual++;
		assertEquals(expected, actual);
	}
	
	/**
	 * Checks that the correct number of L tiles are placed in the board
	 * @author Matt
	 */
	@Test
	public void testProperNumberOfLs()
	{
		int expected = 19;
		
		ATile[][] board = m.getBoard();
		int actual = 0;
		for (ATile[] e: board)
			for (ATile f: e)
			if (f.toString().charAt(1) == 'L')
				actual++;
		if (m.getHoldTile().toString().charAt(1) == 'L')
			actual++;
		assertEquals(expected, actual);
	}
	
	/**
	 * Used in testing the shifting of rows
	 * @param board the board 
	 * @param row the row to creates string from
	 * @return string representation of the tiles in the row
	 */
	private String getRowString(ATile[][] board, int row)
	{
		String s = "";
		for (int i=0; i<7; i++)
		{
			s += board[row][i].toString().substring(1, 3);
		}
		return s;
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the first row to the right
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR1()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getRowString(board, 1).substring(0, 12);
		m.moveTiles(1, 0);
		String actual = getRowString(board, 1);
		
		assertEquals(expected, actual);
	}

	
	/**
	 * 	Ensures that the unused tile is the former rightmost tile in row 1 after 
	 *  the rightward rotation of the first row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR1Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[1][6];
		m.moveTiles(1, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the rightward rotation of the first row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR1LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(1, 0);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the right, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testRightShiftR0Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the right, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testRightShiftR2Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(2, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the right, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testRightShiftR4Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(4, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the right, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testRightShiftR6Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the third row to the right
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR3()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getRowString(board, 3).substring(0, 12);
		m.moveTiles(3, 0);
		String actual = getRowString(board, 3);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former rightmost tile in row 1 after 
	 *  the rightward rotation of the third row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR3Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[3][6];
		m.moveTiles(3, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the rightward rotation of the third row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR3LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(3, 0);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the fifth row to the right
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR5()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getRowString(board, 5).substring(0, 12);
		m.moveTiles(5, 0);
		String actual = getRowString(board, 5);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former rightmost tile in row 1 after 
	 *  the rightward rotation of the fifth row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR5Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[5][6];
		m.moveTiles(5, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the rightward rotation of the fifth row
	 *  @author Matt, Blake
	 */
	@Test
	public void testRightShiftR5LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(5, 0);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 *  Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the first row to the left
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR1()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getRowString(board, 1).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(1, 6);
		String actual = getRowString(board, 1);
		
		assertEquals(expected, actual);
	}
	
	/**
	 *  Ensures that the unused tile is the former leftmost tile in row 1 after 
	 *  the leftward rotation of the first row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR1Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[1][0];
		m.moveTiles(1, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the leftward rotation of the first row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR1LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(1, 6);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the left, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testLeftShiftR0Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the left, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testLeftShiftR2Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(2, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the left, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testLeftShiftR4Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(4, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second row cannot be rotated to the left, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testLeftShiftR6Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the third row to the left
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR3()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getRowString(board, 3).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(3, 6);
		String actual = getRowString(board, 3);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former leftmost tile in row 1 after 
	 *  the leftward rotation of the third row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR3Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[3][0];
		m.moveTiles(3, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the leftward rotation of the third row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR3LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(3, 6);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the fifth row to the left
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR5()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getRowString(board, 5).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(5, 6);
		String actual = getRowString(board, 5);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former leftmost tile in row 1 after 
	 *  the leftward rotation of the fifth row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR5Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[5][0];
		m.moveTiles(5, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the leftward rotation of the fifth row
	 *  @author Matt, Blake
	 */
	@Test
	public void testLeftShiftR5LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(5, 6);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for right then left rotation of the row 1
	 *  @author Matt
	 */
	@Test
	public void testR1RotateRLBacktrackingFalse()
	{
		
		m.moveTiles(0, 1);
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 1);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for right then left rotation of the row 3
	 *  @author Matt
	 */
	@Test
	public void testR3RotateRLBacktrackingFalse()
	{
		
		m.moveTiles(0, 3);
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 3);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for right then left rotation of the row 5
	 *  @author Matt
	 */
	@Test
	public void testR5RotateRLBacktrackingFalse()
	{
		
		m.moveTiles(0, 5);
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 5);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for left then right rotation of the row 1
	 *  @author Matt
	 */
	@Test
	public void testR1RotateLRBacktrackingFalse()
	{
		
		m.moveTiles(6, 1);
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 1);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for left then right rotation of the row 3
	 *  @author Matt
	 */
	@Test
	public void testR3RotateLRBacktrackingFalse()
	{
		
		m.moveTiles(6, 3);
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 3);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for left then right rotation of the row 5
	 *  @author Matt
	 */
	@Test
	public void testR5RotateLRBacktrackingFalse()
	{
		
		m.moveTiles(6, 5);
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 5);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	private String getColString(ATile[][] board, int col)
	{
		String s = "";
		for (int i=0; i<7; i++)
		{
			s += board[i][col].toString().substring(1, 3);
		}
		return s;
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the first column downward
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC1()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getColString(board, 1).substring(0, 12);
		m.moveTiles(0, 1);
		String actual = getColString(board, 1);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former bottom most tile in column 1 after 
	 *  the downward rotation of the first column
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC1Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[6][1];
		m.moveTiles(0, 1);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the downward rotation of the first column
	 *  @author Matt
	 */
	@Test
	public void testDownShiftC1LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 1);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Ensures that the second column cannot be rotated downward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testDownShiftC0Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated downward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testDownShiftC2Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 2);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated downward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testDownShiftC4Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 4);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated downward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testDownShiftC6Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}

	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the third column downward
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC3()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getColString(board, 3).substring(0, 12);
		m.moveTiles(0, 3);
		String actual = getColString(board, 3);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former bottom most tile in column 1 after 
	 *  the downward rotation of the third column
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC3Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[6][3];
		m.moveTiles(0, 3);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the downward rotation of the third column
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC3LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 3);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the fifth column downward
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC5()
	{
		
		ATile[][] board = m.getBoard();
		String expected = m.getHoldTile().toString().substring(1, 3) + getColString(board, 5).substring(0, 12);
		m.moveTiles(0, 5);
		String actual = getColString(board, 5);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former bottom most tile in column 1 after 
	 *  the downward rotation of the fifth column
	 *  @author Matt, Blake
	 */
	@Test
	public void testDownShiftC5Unused()
	{ 
		
		ATile[][] board = m.getBoard();
		ATile expected = board[6][5];
		m.moveTiles(0, 5);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the downward rotation of the fifth column
	 *  @author Matt
	 */
	@Test
	public void testDownShiftC5LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(0, 5);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 *  Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the first column upward
	 *  @author Matt, Blake
	 */
	@Test
	public void testUpShiftC1()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getColString(board, 1).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(6, 1);
		String actual = getColString(board, 1);
		
		assertEquals(expected, actual);
	}
	
	/**
	 *  Ensures that the unused tile is the former topmost tile in column 1 after 
	 *  the upward rotation of the first column
	 *  @author Matt, Blake
	 */
	@Test
	public void testUpShiftC1Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[0][1];
		m.moveTiles(6, 1);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the upward rotation of the first column
	 *  @author Matt, Blake
	 */
	@Test
	public void testUpShiftC1LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 1);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Ensures that the second column cannot be rotated upward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testUpShiftC0Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated upward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testUpShiftC2Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 2);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated upward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testUpShiftC4Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 4);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Ensures that the second column cannot be rotated upward, as there are fixed tiles
	 * @author Matt
	 */
	@Test
	public void testUpShiftC6Illegal()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the third column upward
	 *  @author Matt, Blake
	 */
	@Test
	public void testUpShiftC3()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getColString(board, 3).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(6, 3);
		String actual = getColString(board, 3);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former topmost tile in column 1 after 
	 *  the upward rotation of the third column
	 *  @author Matt
	 */
	@Test
	public void testUpShiftC3Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[0][3];
		m.moveTiles(6, 3);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the upward rotation of the third column
	 *  @author Matt
	 */
	@Test
	public void testUpShiftC3LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 3);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * 	Ensures that the tiles on the board are in the proper locations after the unused
	 *  tile pushed the tiles in the fifth column upward
	 *  @author Matt, Blake
	 */
	@Test
	public void testUpShiftC5()
	{
		
		ATile[][] board = m.getBoard();
		String expected = getColString(board, 5).substring(2, 14) + m.getHoldTile().toString().substring(1, 3);
		m.moveTiles(6, 5);
		String actual = getColString(board, 5);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the unused tile is the former most tile in column 1 after 
	 *  the upward rotation of the fifth column
	 *  @author Matt
	 */
	@Test
	public void testUpShiftC5Unused()
	{
		
		ATile[][] board = m.getBoard();
		ATile expected = board[0][5];
		m.moveTiles(6, 5);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);
	}
	
	/**
	 * 	Ensures that the last placed tile is the former unused after 
	 *  the upward rotation of the fifth column
	 *  @author Matt
	 */
	@Test
	public void testUpShiftC5LastPlaced()
	{
		
		ATile expected = m.getHoldTile();
		m.moveTiles(6, 5);
		ATile actual = m.getLastPlaced();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for downward then upward rotation of the column 1
	 *  @author Matt
	 */
	@Test
	public void testC1RotateDUBacktrackingFalse()
	{
		
		m.moveTiles(1, 0);
		ATile expected = m.getHoldTile();
		m.moveTiles(1, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for downward then upward rotation of the column 3
	 *  @author Matt
	 */
	@Test
	public void testC3RotateDUBacktrackingFalse()
	{
		
		m.moveTiles(3, 0);
		ATile expected = m.getHoldTile();
		m.moveTiles(3, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for downward then upward rotation of the column 5
	 *  @author Matt
	 */
	@Test
	public void testC5RotateDUBacktrackingFalse()
	{
		
		m.moveTiles(5, 0);
		ATile expected = m.getHoldTile();
		m.moveTiles(5, 6);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for upward then downward rotation of the column 1
	 *  @author Matt
	 */
	@Test
	public void testC1RotateUDBacktrackingFalse()
	{
		
		m.moveTiles(1, 6);
		ATile expected = m.getHoldTile();
		m.moveTiles(1, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for upward then downward rotation of the column 3
	 *  @author Matt
	 */
	@Test
	public void testC3RotateUDBacktrackingFalse()
	{
		
		m.moveTiles(3, 6);
		ATile expected = m.getHoldTile();
		m.moveTiles(3, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	
	/** 
	 *  Ensures that move backtracking is not allowed for upward then downward rotation of the column 5
	 *  @author Matt
	 */
	@Test
	public void testC5RotateUDBacktrackingFalse()
	{
		
		m.moveTiles(5, 6);
		ATile expected = m.getHoldTile();
		m.moveTiles(5, 0);
		ATile actual = m.getHoldTile();
		
		assertEquals(expected, actual);	
	}
	/**@author Jmosca
	 * Test 25-36 :Checks to see if tiles are moved to correct location
	 * 
	 */
	@Test
	public void test25() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new LTile("North");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	
	@Test
	public void test26() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new LTile("South");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test27() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new LTile("East");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test28() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new LTile("West");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test29() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new ITile("North");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test30() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new ITile("South");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test31() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new ITile("East");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test32() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new ITile("West");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test33() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new TTile("North");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test34() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new TTile("South");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test35() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new TTile("East");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test36() {
		Model board = new Model(new String[]{"River", "Mal"});
		ATile new_place = new TTile("West");
		board.setHoldTile(new_place);
		ATile old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
/**@author Jmosca
 * Test 37-41: Checks if the held tile stays the same 
 */

	@Test
	public void test37() {
		Model board = new Model(new String[]{"River", "Mal"});

		ATile tile = new ITile("East");
		board.setHoldTile(tile);
		ATile actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test38() {
		Model board = new Model(new String[]{"River", "Mal"});

		ATile tile = new LTile("North");
		board.setHoldTile(tile);
		ATile actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test39() {
		Model board = new Model(new String[]{"River", "Mal"});

		ATile tile = new LTile("South");
		board.setHoldTile(tile);
		ATile actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test40() {
		Model board = new Model(new String[]{"River", "Mal"});

		ATile tile = new LTile("East");
		board.setHoldTile(tile);
		ATile actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test41() {
		Model board = new Model(new String[]{"River", "Mal"});

		ATile tile = new LTile("West");
		board.setHoldTile(tile);
		ATile actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
}
