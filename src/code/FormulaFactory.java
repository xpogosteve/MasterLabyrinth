package code;

import java.util.Arrays;

/**
 * An enumeration of an object representing the formula card which is held by each of the player. This holds
 * all of the possible cards in the game, as well as the methods to access the contents of the formula, as
 * well as to pass a reference to the formula card to a player. 
 * 
 * @author Matthew, Robert
 */
public class FormulaFactory {
	/**
	 * Represents all possible formula card possibilities
	 */
	private int[][] possibleFormulas = {{8,19,5},{18,11,19},{3,18,1},{1,10,13},{10,12,16},{6,14,8},{20,17,3},
										{5,25,18},{9, 20, 11},{4,15,20},{12,1,9},{14,4,10},{19,7,15},{25,16,2},
										{13,15,12},{11,3,14},{2,8,17},{16,9,7},{7,6,25},{17,5,6},{15,2,4}};	
	/**
	 * Returns a random, unused Formula object constructed from the list of possibilities
	 * @return
	 */
	public Formula getFormula()
	{
		boolean unchosen = true;
		Formula f = null;
		
		while(unchosen)
		{
			int i = (int)(Math.random() * 20 + 1);
			if (!Arrays.equals(possibleFormulas[i], new int[]{0,0,0}))
			{
				f = new Formula(possibleFormulas[i]);
				possibleFormulas[i] = new int[]{0,0,0};
				unchosen = false;
			}	
		}
		return f;
	}
	
	/**
	 * Returns a Formula object defined by the passed in string, used in game restoration
	 * @param formula string representation of formula
	 * @return Formula object
	 */
	public static Formula getFormula(String formula)
	{
		String[] ingredients = formula.split(",");
		int[] array = {Integer.parseInt(ingredients[0]), Integer.parseInt(ingredients[1]), Integer.parseInt(ingredients[2])};
		return new Formula(array);
	}
}
