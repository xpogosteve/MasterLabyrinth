package code.tile;


/**
 * Class creates a tile of the T type. It is a child class of the Tile class. T type is a tile laid out
 * similar to a T in structure.
 * @author Wiechec
 *
 */
public class TTile  extends ATile{
	
	/**
	 * @author Wiechec
	 * @param orientation allows the tile to be constructed with a specific orientation layout
	 * at the time of creation.
	 */
	public TTile(String orientation){
		super(orientation);
	}
	/**
	 * @author Wiechec
	 * Constructor creates a tile, but returns a tile defaulted into the NORTH orientation.
	 */
	public TTile(){
		super();
	}
	public TTile(int rotation) {
		super(rotation);
	}
	/**
	 * @author Wiechec
	 * Method rotates the tile into one of the indicated orientations. The indicated open values
	 * are the direction this tile will allow movements.
	 * NORTH: NORTH AND WEST AND EAST ARE OPEN
	 * EAST:NORTH AND SOUTH AND EAST ARE OPEN
	 * SOUTH:EAST AND WEST AND SOUTH ARE OPEN
	 * WEST:WEST AND NORTH AND SOUTH ARE OPEN
	 */
	/* (non-Javadoc)
	 * Corrected Tile formations to match Dr. Alphonce's conventions
	 * North --> South
	 * South --> North
	 * East --> West
	 * West --> East
	 * - Blake
	 */
	public void rotate(String direction){
		_rotation = direction;
		switch (direction){
		case ATile.SOUTH: 
			_north = true;
			_east = true;
			_west = true;
			_south = false;
			break;
		case ATile.NORTH:
			_north = false;
			_east = true;
			_west = true;
			_south = true;
			break;
		case ATile.WEST:
			_north = true;
			_east = true;
			_west = false;
			_south = true;
			break;
		case ATile.EAST:
			_north = true;
			_east = false;
			_west = true;
			_south = true;
			break;
		}
	}
}
