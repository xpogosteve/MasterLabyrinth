package code.tile;

/**
 * Child class of the Tiles class. ElbowTile has overloaded constructors to create either a base version of
 * tile with directional fields set to NORTH. Also has a constructor with a direction parameter.
 * or a version of the tile
 * @author Wiechec
 *
 */
/* (non-Javadoc)
 * Renamed from ElbowTile to LTile to follow the project conventions
 * - Blake
 */
public class LTile extends ATile {
	
	/**
	 * @author Wiechec
	 * Constructor uses a direction parameter to call Rotate on the tile. The parameter is passed into
	 * the rotate method, setting the tile's directional values for the orientation indicated.
	 * @param orientation is the orientation the tile will be set to. parameter is passed to Rotate 
	 * method call.
	 */
	public LTile(String orientation){
		super(orientation);
	}
	/**
	 * @author Wiechec
	 * Constructor creates a tile, but returns a tile defaulted into the NORTH orientation.
	 */
	public LTile(){
		super();
	}
	public LTile(int rotation) {
		super(rotation);
	}
	/**
	 * @author Wiechec
	 * Method rotates the tile into one of the indicated orientations. The indicated open values
	 * are the direction this tile will allow movements.
	 * NORTH: NORTH AND EAST ARE OPEN
	 * EAST: EAST AND SOUTH ARE OPEN
	 * SOUTH:WEST AND SOUTH ARE OPEN
	 * WEST:NORTH AND WEST ARE OPEN
	 */
	/* (non-Javadoc)
	 * Corrected Tile formations to match Dr. Alphonce's conventions
	 * North --> West
	 * East --> North
	 * South --> East
	 * West --> South
	 * - Blake
	 */
	public void rotate(String direction){
		_rotation = direction;
		switch (direction){
		case ATile.WEST: 
			_north = true;
			_east = true;
			_west = false;
			_south = false;
			break;
		case ATile.EAST:
			_north = false;
			_east = false;
			_west = true;
			_south = true;
			break;
		case ATile.NORTH:
			_north = false;
			_east = true;
			_west = false;
			_south = true;
			break;
		case ATile.SOUTH:
			_north = true;
			_east = false;
			_west = true;
			_south = false;
			break;
		}
	}
	
}
