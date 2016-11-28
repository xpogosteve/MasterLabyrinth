package code.tile;

/**
 * Child class of the Tiles class. Tile has overloaded constructors to create either a base version of
 * tile with directional fields set to NORTH. Also has a constructor with a direction parameter.
 * or a version of the tile
 * @author Wiechec
 *
 */
/* (non-Javadoc)
 * renamed from StraightTiles to ITile to follow project conventions
 * - Blake
 */
public class ITile extends ATile{
	
	/**
	 * @author Wiechec
	 * Constructor uses a direction parameter to call Rotate on the tile. The parameter is passed into
	 * the rotate method, setting the tile's directional values for the orientation indicated.
	 * @param orientation is the orientation the tile will be set to. parameter is passed to Rotate 
	 * method call.
	 */
	public ITile(String orientation){
		super(orientation);
	}
	/**
	 * @author Wiechec
	 * Constructor creates a tile, but returns a tile defaulted into the NORTH orientation.
	 */
	public ITile(){
		super();
	}
	public ITile(int rotation) {
		super(rotation);
	}
	/**
	 * @author Wiechec
	 * Method rotates the tile into one of the indicated orientations. The indicated open values
	 * are the direction this tile will allow movements.
	 * NORTH: UP AND DOWN ARE OPEN
	 * EAST:LEFT AND RIGHT ARE OPEN
	 * SOUTH:UP AND DOWN ARE OPEN
	 * WEST:LEFT AND RIGHT ARE OPEN
	 */
	public void rotate(String orientation){
		_rotation = orientation;
		switch (orientation){
		case ATile.NORTH: case ATile.SOUTH:
			_north = true;
			_east = false;
			_west = false;
			_south = true;
			break;
		case ATile.EAST: case ATile.WEST:
			_north = false;
			_east = true;
			_west = true;
			_south = false;
			break;
		}
	}
}
