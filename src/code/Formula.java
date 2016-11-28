package code;

/**
 * @author Matt, Steven
 */
public class Formula
{
	/**
	 * values of the tokens represented by this formula
	 */
	private int i1, i2, i3;
	
	/**
	 * Constructor for a formula based upon the passed in array of ingredients
	 * @param ingredients
	 */
	public Formula(int[] ingredients) 
	{
		i1 = ingredients[0];
		i2 = ingredients[1];
		i3 = ingredients[2];
	}
	
	/**
	 * Returns whether the formula contains the passed-in value
	 * @param value
	 * @return 
	 */
	public boolean contains(int value)
	{
		return value == i1 || value == i2 || value == i3;
	}
	
	/**
	 * Returns a string representation of this formula
	 */
	@Override public String toString()
	{
		return "[" + i1 + "," + i2 + "," + i3 + "]";
	}
	
	/**
	 * Returns a string to be used in representing the formula in the GUI
	 * @return
	 */
	public String getRepresentationOnCard()
	{
		return i1 + "<br>" + i2 + "<br>" + i3;
	}
}
