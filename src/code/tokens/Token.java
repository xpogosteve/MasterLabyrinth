package code.tokens;

public class Token {

	/**@author bdlipp cwiechec
	 * @field tokenName = name of token
	 * @field tokenValue = sets the value of the token.
	 * @field setCount = used in token creation. Ensures everytime a new token in instantiated that the next token is created.
	 * @field pickUpCount = variable used to control which token can be picked up. 
	 * 
	 */
	private String tokenName=null;
	private int value=0;

	/**
	 * Constructor for the Token object
	 * @param value the value of this token
	 */
	public Token(int value) {
		this.value = (value > 20) ? 25 : value;
		tokenName();
	}

	/**
	 * @author bdlipp cwiechec
	 * @param tokenNum sets the tokenName of the token that's instantiated. Also sets that token's tokenValue to be equal to the
	 * tokenValue of when it was called. cwiechec contributed to Mistletoe token value and final  statement only.
	 */

	public void tokenName(){

		switch (value) {
		case 1:
			tokenName="Crab Apples";
			break;
		case 2:
			tokenName="Pine Cone";
			break;
		case 3:
			tokenName="Oak Leaves";
			break;
		case 4:
			tokenName="Slug Oil";
			break;
		case 5:
			tokenName="Four Leaf Clover";
			break;
		case 6:
			tokenName="Garlic";
			break;
		case 7:
			tokenName="Ravens Feather";
			break;
		case 8:
			tokenName="Henbane";
			break;
		case 9:
			tokenName="Spiders";
			break;
		case 10:
			tokenName="Skull Moss";
			break;
		case 11:
			tokenName="Blindworm";
			break;
		case 12:
			tokenName="Quartz Crystal";
			break;
		case 13:
			tokenName="Toads";
			break;
		case 14:
			tokenName="Fire Salamandars";
			break;
		case 15:
			tokenName="Weasle Spit";
			break;
		case 16:
			tokenName="Silver Thistle";
			break;
		case 17:
			tokenName="Snake";
			break;
		case 18:
			tokenName="Emerarlds";
			break;
		case 19:
			tokenName="Mandrake Root";
			break;
		case 20:
			tokenName="Basilisk";
			break;
		default:
			tokenName="Mistletoe";
			break;
		}
	}



	/**
	 * @author bdlipp
	 * @returns token name
	 */
	public String getName(){
		return tokenName;
	}
	/**
	 * @author bdlipp
	 * @return value of the token.
	 */
	public int getValue(){
		return value;
	}

	/**
	 * Returns a string representation of this tile, which is simply its value
	 * @author Matt
	 */
	@Override
	public String toString()
	{
		return Integer.toString(value);
	}

}
