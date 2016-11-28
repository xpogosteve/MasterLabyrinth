package code.tile;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import code.tokens.Token;

/**
 * Class is the parent class for all the Tile classes in the game. File is fully defined with the exception of
 * the rotation method, which is unique to each tile and must be written by each tile. Class is
 * abstract so that it cannot be called.
 * @author Wiechec
 *
 */
/* (non-Javadoc)
 * Renamed from Tiles to Tile because Tiles is an awful name for this class
 * Changed variable and method return types from Boolean to boolean because the wrapper class is unnecessary here
 * Reduced field visibility from public to private or protected.
 * - Blake
 */
public abstract class ATile {

	/* (non-Javadoc)
	 * Extracted Strings as constants to improve flexibility.
	 * - Blake
	 */
	/**
	 * Representation of the west orientation of the tile
	 */
	public static final String WEST = "West";
	/**
	 * Representation of the east orientation of the tile
	 */
	public static final String EAST = "East";
	/**
	 * Representation of the south orientation of the tile
	 */
	public static final String SOUTH = "South";
	/**
	 * Representation of the north orientation of the tile
	 */
	public static final String NORTH = "North";
	/**
	 * Represents the current rotation of the tile
	 */
	protected String _rotation;
	/**
	 * @author Wiechec
	 * _north: Field storing whether or not the tile allows movement in the north direction.
	 */

	protected boolean _north = false;
	/**
	 * @author Wiechec
	 *  _east: Field storing whether or not the tile allows movement in the east direction.
	 */

	protected boolean _east = false;
	/**
	 * @author Wiechec
	 * _south: Field storing whether or not the tile allows movement in the south direction.
	 */
	protected boolean _south = false;
	/**
	 * @author Wiechec
	 * _west: Field storing whether or not the tile allows movement in the west direction.
	 */
	protected boolean _west = false;
	private Token token;

	/**
	 * Constructor for the tile, defaults to north orientation
	 */
	public ATile() {
		this(NORTH);
	}
	
	/**
	 * Constructor for the tile which takes in the orientation
	 * @param rotation
	 */
	public ATile(String rotation) {
		rotate(rotation);
	}
	
	/**
	 * Constructor for the tile which takes in an integer representation of the orientation
	 * @param rotation
	 */
	public ATile(int rotation) {
		this(rotateIntToString(rotation));
	}
	
	/**
	 * Converts the integer representation of the rotation to a string
	 * @param rotation the integer representation
	 * @return string representation
	 * @author Blake
	 */
	public static String rotateIntToString(int rotation) {
		switch (rotation) {
		case 0:  return NORTH;
		case 1:  return EAST;
		case 2:  return SOUTH;
		case 3:  return WEST;
		default: throw new IllegalArgumentException();
		}
	}
	/**
	 * @author Wiechec
	 * method getRotation();
	 * returns the current rotation of the object.
	 * @return: Current rotation of the object.
	 */
	public String getRotation(){
		return _rotation;
	}

	/**
	 * @author Wiechec
	 * method Rotate();
	 * abstract method stub. Method is used to rotate the directional fields on the tiles.
	 * code is the responsibility of the subclasses.
	 * @return: no return.
	 */
	/* (non-Javadoc)
	 * renamed from Rotate to rotate to follow naming convention
	 * - Blake
	 */
	public abstract void rotate(String direction);
	
	/**
	 * Calls rotate method after converting integer to string
	 * @param direction
	 */
	public void rotate(int direction) {
		rotate(rotateIntToString(direction));
	}
	/**
	 * @author Wiechec
	 * Method returns the boolean values of the tile for the indicated direction of the parameter.
	 * Method returns false as a default if an incorrect value is entered into parameter.
	 * @param dir is the direction being checked
	 * @return the value of the direction being checked.
	 */
	public boolean getDirection(String dir){
		switch(dir){
		case NORTH:
			return _north;
		case SOUTH:
			return _south;
		case EAST:
			return _east;
		case WEST:
			return _west;
		}
		return false;
	}
	
	/**
	 * Sets the token on this tile
	 * @param _token
	 */
	public void setToken(Token _token){
		token = _token;
	}
	
	/**
	 * Returns the token which is on this tile
	 * @return
	 */
	public Token getToken(){
		return token;
	}
	
	/**
	 * Returns whether this tile has a token
	 * @return
	 */
	public boolean hasToken(){
		return token != null;
	}
	
	/**
	 * Removes and returns this token
	 * @return Token that was on this tile
	 */
	public Token pickUpToken(){
		Token temp=token;
		token=null;
		return temp;
	}
	
	/**
	 * returns a string representation of this tile
	 * @author Blake, Matt
	 */
	@Override public String toString() {
		String result = '[' + String.valueOf(getClass().getSimpleName().charAt(0));
		switch (_rotation) {
		case ATile.NORTH:
			result += '0';
			break;
		case ATile.EAST:
			result += '1';
			break;
		case ATile.SOUTH:
			result += '2';
			break;
		case ATile.WEST:
			result += '3';
			break;
		default:
			throw new RuntimeException("Malformed Tile.");
		}
		result += ',';
		result += (hasToken()) ? token.getValue() : 0;
		result += ',';
		return result;
	}
}


