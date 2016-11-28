package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import code.Formula;
import code.model.Model;
import code.pawn.Pawn;
import code.tokens.Token;

public class PawnTests 
{
	private Pawn p;
	private Model m;
	
	@Before
	public void before()
	{
		m = new Model(new String[]{"River", "Mal"});
		p = new Pawn(0,0, m, "Inara", new Formula(new int[]{1,2,3}));
	}
	
	@Test
	public void testCorrectNumberOfWandsAtStart()
	{
		int expected = 3;
		int actual = p.getNumWands();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCorrectNumberOfWandsAfterUse()
	{
		int expected = 2;
		m.setTurnPhase(Model.MOVE_PAWN_PHASE);
		p.wand();
		int actual = p.getNumWands();
		assertEquals(expected, actual);
		
		expected = 1;
		m.setTurnPhase(Model.MOVE_PAWN_PHASE);
		p.wand();
		actual = p.getNumWands();
		assertEquals(expected, actual);
		
		expected = 1; //not correct phase
		p.wand();
		actual = p.getNumWands();
		assertEquals(expected, actual);
		
		expected = 0;
		m.setTurnPhase(Model.MOVE_PAWN_PHASE);
		p.wand();
		actual = p.getNumWands();
		assertEquals(expected, actual);
		
		expected = 0;
		m.setTurnPhase(Model.MOVE_PAWN_PHASE); //can't take another
		p.wand();
		actual = p.getNumWands();
		assertEquals(expected, actual);
	}
	
	@Test
	public void correctTurnPhasingWithWand()
	{
		boolean expected = Model.SHIFT_BOARD_PHASE;
		boolean actual = m.getTurnPhase();
		assertEquals(expected, actual);
		
		expected = Model.SHIFT_BOARD_PHASE;
		p.wand();
		actual = m.getTurnPhase();
		assertEquals(expected, actual);
		
		m.setTurnPhase(Model.MOVE_PAWN_PHASE);
		p.wand();
		actual = m.getTurnPhase();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTokenPickUpWithScore()
	{
		p.addTokenToPickedUp(new Token(10));
		p.addTokenToPickedUp(new Token(11));
		p.addTokenToPickedUp(new Token(12));
		p.addTokenToPickedUp(new Token(13));
		
		int expected = 46;
		int actual = p.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTokenPickUpWithScore2()
	{
		p.addTokenToPickedUp(new Token(1));
		p.addTokenToPickedUp(new Token(11));
		p.addTokenToPickedUp(new Token(12));
		p.addTokenToPickedUp(new Token(13));
		
		int expected = 57;
		int actual = p.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTokenPickUpWithScore3()
	{
		p.addTokenToPickedUp(new Token(1));
		p.addTokenToPickedUp(new Token(2));
		p.addTokenToPickedUp(new Token(3));
		
		int expected = 66;
		int actual = p.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTokenPickUpWithFinalScore()
	{
		p.addTokenToPickedUp(new Token(1));
		p.addTokenToPickedUp(new Token(2));
		p.addTokenToPickedUp(new Token(3));
		
		int expected = 75;
		int actual = p.getFinalScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTokenPickUpWithFinalScore2()
	{
		p.addTokenToPickedUp(new Token(10));
		p.addTokenToPickedUp(new Token(2));
		p.addTokenToPickedUp(new Token(3));
		
		m.setTurnPhase(Model.MOVE_PAWN_PHASE);
		p.wand();
		
		int expected = 61;
		int actual = p.getFinalScore();
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testTokensWithList()
	{
		int[] expected = new int[]{1,2,3,10,14};
		for (Integer e: expected){
			p.addTokenToPickedUp(new Token(e));
		}
		ArrayList<Token> list = (ArrayList<Token>)p.tokenCount();
		
		int[] actual = new int[expected.length];
		for (int i=0; i<list.size(); i++)
		{
			actual[i] = list.get(i).getValue();
		}
		
		assertTrue("", Arrays.equals(actual, expected));
	}	
	
	@Test
	public void testName()
	{
		String expected = "Inara";
		String actual = p.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLoc()
	{
		Point expected = new Point(0,0);
		Point actual = p.getPos();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLocAfterSet()
	{
		p.setCurrentLoc(5, 2);
		Point expected = new Point(2,5); //location is transformed based on how the code was initially set up
		Point actual = p.getPos();
		assertEquals(expected, actual);
	}
	
}
