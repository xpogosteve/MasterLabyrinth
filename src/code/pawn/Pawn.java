package code.pawn;


import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import code.Formula;
import code.gui.DialogBox;
import code.model.Model;
import code.tile.ATile;
import code.tokens.Token;

/**
 * Class will create a Pawn object. Class contains methods for movement of the pawn, which
	  calls to the tile/board to verify movement is possible. There are method stubs
	  and some additional code beyond the scope of the project, but any additional coding can be
	  taken as ideas that were noted for possible future implementation.
 * @author Wiechec
 *
 */
/* (non-Javadoc)
 * Changed variable types from Boolean to boolean as the wrapper class is unnecessary here
 * - Blake
 */
public class Pawn {

	/**
	 * @author Wiechec
	 * numWands: Field containing the number of wands the player has. Future implementation.
	 */
	private int numWands = 3;

	/**
	 * @author Blake
	 * tokens: the tokens that the player has collected.
	 */
	private List<Token> tokens = new ArrayList<Token>();
	/**
	 * @author Blake
	 * the location of the pawn on the board
	 */
	private Point loc;
	/**
	 * @author Wiechec
	 * board: associates the board to the pawn for calling methods back to the board.
	 */
	private Model board;
	/** 
	 * @author Wiechec
	 * next: Storage field for testing the canMove method.
	 */
	private boolean next = false;

	/**
	 * @author Matt
	 * The location of the pawn at the start of the next turn
	 */
	private Point startLoc;
	
	/**
	 * The name associated with this player
	 */
	private String name;

	/**
	 * The Formula card of this player
	 */
	private final Formula formula;

	/**
	 * The color used to represent this player on the GUI
	 */
	private Color color;
	
	/**
	 * Array of the color names used in the game
	 */
	public static final String[] PLAYER_COLORS_AS_STRINGS =
		{"RED", "GREEN", "BLUE", "BLACK"};

	/**
	 * Array of the player colors used in the game
	 */
	public static final Color[] PLAYER_COLORS = {
		new Color(205,51,51),  //red
		new Color(34,139,34), //green
		new Color(65,105,225), //blue
		new Color(0,0,0)}; //black


	/**
	 * @author Wiechec
	 * Method can create a pawn at a specified position for game creation.
	 * @param loc.y coordinate
	 * @param loc.x coordinate
	 * @param board links to board.
	 * @param formula 
	 */
	public Pawn(int y, int x, Model board, String name, Formula formula){

		this.board = board;
		this.name = name;
		this.formula = formula;

		setCurrentLoc(x,y);
		resetStartLoc();
	}

	/**
	 * @author <jtmirfie>
	 * @return checks 'validMove' if the pawn has made a movement that is not the same spot
	 * it has started from
	 */	
	/* (non-Javadoc)
	 * Changed from !...equals method to != operator
	 */
	public boolean checkIfValidMove(){
		return board.getTile(loc) != (board.getTile(startLoc));

	}

	/**
	 * Sets the color of the pawn based upon the string representation which is passed in
	 * @param colorName
	 */
	public void setColor(String colorName)
	{
		for (int i=0; i<4; i++)
		{
			if (PLAYER_COLORS_AS_STRINGS[i].equals(colorName))
			{
				color = PLAYER_COLORS[i];
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Returns a color corresponding to the string representation which is passed in
	 * @param s string representation	
	 * @return color
	 */
	public static Color stringAsColor(String s) {
		for (int i=0; i<4; i++)
			if (s.equals(PLAYER_COLORS_AS_STRINGS[i]))
				return PLAYER_COLORS[i];
		return null;
	}
	
	/**
	 * Sets the color of the pawn based upon its turn number in the game
	 * @param index
	 */
	public void setColor(int index)
	{
		color = PLAYER_COLORS[index];
	}
	
	/**
	 * @author <jtmirfie>
	 * @return checks 'tokens' to see how many tokens each pawn has collected.
	 */	
	public List<Token> tokenCount(){
		return tokens;
	}

	/**
	 * @author Wiechec
	 * This method sets the current location of the pawn's instance variable at a specific position
	 * on the board using loc.x and loc.y coordinates. This method may be used for game creation as well as
	 * moving the player piece during the game play.
	 * @param loc.x sets the pawns's positionX field
	 * @param loc.y sets the pawn's positionY field
	 */
	public void setCurrentLoc(int y, int x){
		loc = new Point(x, y);
	}
	
	/**
	 * Sets the x coordinate of the player position
	 * @param x
	 */
	public void setX(int x) {
		loc.x = x;
	}
	
	/**
	 * Sets the y coordinate of the player position
	 * @param y
	 */
	public void setY(int y) {
		loc.y = y;
	}	
	
	/**
	 * Sets the number of wands that a player has, used in game restoration
	 * @param num
	 * @author Matt
	 */
	public void setNumWands(int num)
	{
		numWands = num;
	}
	
	/**
	 * Returns the number of wands that this player has
	 * @return
	 */
	public int getNumWands()
	{
		return numWands;
	}
	
	/**
	 * @author Wiechec
	 * Returns pawn's positionX value, which is where it is horizontally on the board.
	 * @return
	 */
	public int getX(){
		return loc.x;
	}
	/**
	 * @author Wiechec
	 * Returns pawn's positionY value, which is where it is vertically on the board.
	 * @return
	 */
	public int getY(){
		return loc.y;
	}
	/**
	 * Returns the pawn's position as a Point.
	 * @author Blake, Matt
	 */
	public Point getPos() {
		return (Point) loc.clone();
	}
	/**
	 * @author Wiechec
	 *This method takes in, as a string, the direction of movement called on the pawn. The direction,
	 *taken in as a String, is set to lowercase and only the zeroth index is checked for the direction.
	 *Once the input is taken into the parameter, it is passed into the first if statement, which
	 *calls on the canMove method to verify the movement is possible prior to any further action
	 *taking place. If the canMove method returns true, the pawn's instance variables for
	 *it's position are then incremented, which is determined by the direction in which the pawn
	 * moves.
	 */
	public void move(String direction){
		if (canMove(direction)){
			switch (direction){
			case ATile.NORTH: loc.y--;
			break;
			case ATile.WEST: loc.x--;
			break;
			case ATile.SOUTH: loc.y++;
			break;
			case ATile.EAST: loc.x++;
			}
		}
	}
	/**
	 * @author Wiechec
	 * This method takes in the direction the pawn is attempting to move, sends the information to the
	 *board, which determines the location the pawn is on. It then checks the tile at that location,
	 *verifies the boolean value set for the direction it wishes to go, then checks the target tile
	 *to determine if it is accepting movement from that direction. If this method returns true, then 
	 *and only then can the pawn move.
	 */
	/* (non-Javadoc)
	 * Added boundary position checking to fix problem with ArrayIndexOutOfBounds.
	 * - Blake
	 */
	public boolean canMove(String direction){
		boolean current = board.getTileBool(loc.x, loc.y, direction);
		switch (direction){

		case ATile.NORTH:
			if (loc.y == 0)
				return false;
			next = board.getTileBool((loc.x), loc.y-1, ATile.SOUTH);
			return (current && next);

		case ATile.SOUTH:
			if (loc.y == 6)
				return false;
			next = board.getTileBool((loc.x), loc.y+1, ATile.NORTH);
			return (current && next);

		case ATile.WEST:
			if (loc.x == 0)
				return false;
			next=board.getTileBool(loc.x-1, (loc.y), ATile.EAST);
			return (current && next);

		case ATile.EAST:
			if (loc.x == 6)
				return false;
			next = board.getTileBool(loc.x+1, (loc.y), ATile.WEST);
			return (current && next);
		}
		return false;

	}
	/**
	 * @author Wiechec
	 * Method returns the boolean value of the next field.
	 * @return
	 */
	public boolean getNext(){
		return next;
	}


	/**
	 * @author Blake, Matt, Steven
	 * 
	 */
	public boolean wand() {
		if (board.getTurnPhase() != Model.MOVE_PAWN_PHASE || numWands == 0)
			return false;
		if (checkIfValidMove())
			pickupToken();
		board.setTurnPhase(Model.SHIFT_BOARD_PHASE);
		numWands--;
		board.gameChanged();
		return true;
	}
		
	/**
	 * @author Wiechec
	 * Method stub. Will, once completed, act as the boolean check to ensure that the token
	 * will be obtainable via the getToken method in combination with a data structure
	 * such as a hashmap for the token values and a method, such as currentAvailableToken, implemented
	 * in either the Board class or the Tile structures.
	 * @param token unused at this time.
	 */
	public boolean canPickUpToken(){
		return board.getTile(loc).hasToken() && board.getTile(loc).getToken().getValue() == board.getNextTokenValue();
				
	}
	/**
	 * @author Wiechec
	 * Method for future implementation, which will enable the player to see the current inventory 
	 * for his pawn object. Code currently contained is unfinished and can only be considered as
	 * noted ideas for the implementation of the code.
	 */
	public void inventory(){
		System.out.println("Number of wands: "+numWands);
		//		System.out.println("Number of tokens: " +numOfTokens);
		System.out.println("Tokens in possession: " ); 

	}

	/**
	 * Creates the dialog which is used to determine whether token is picked up at the end of the turn
	 */
	public void pickupToken() {
		if (canPickUpToken()) 
			new DialogBox() {
				private static final long serialVersionUID = 1L;
				@Override public void button1Pressed(ActionEvent arg0) { getToken(); }
				@Override public void button2Pressed(ActionEvent arg0) {}
				@Override public void afterAction() {
					dispose();
					board.gameChanged();
				}
		};
	}
	
	/**
	 * Carries out procedure of picking up the token
	 */
	private void getToken() {
		tokens.add(board.getTile(loc).pickUpToken());
		board.tokenPickedUp();
		board.gameChanged();
	}

	/**
	 * @author <jtmirfie>
	 * @return accesses 'player' in order to know the name of the player using the pawn.
	 */	
	public String getName(){
		return name;
	}
	
	/**
	 * Returns a string representation of the color of this player
	 * @return
	 */
	private String getColorString()
	{
		for (int i=0; i<4; i++)
		{
			if (PLAYER_COLORS[i].equals(color))
			{
				return PLAYER_COLORS_AS_STRINGS[i];
			}
		}
		throw new IllegalStateException();
	}
	
	/**
	 * Returns a string representation of this pawn
	 * @author Matt, Blake
	 */
	@Override public String toString() {
		String result = '[' + name + ',';
		result += getColorString();
		result += "," + numWands + ",";
		result += formula + "," + tokens + ']';
		return result;
	}
	
	/**
	 * Sets the start location of the pawn for the next turn equal to the current location this turn
	 */
	public void resetStartLoc() {
		startLoc = new Point(loc);
	}

	/**
	 * Adds the passed in token to the collection of those that have been picked up
	 * @param t the token to be added
	 */
	public void addTokenToPickedUp(Token t)
	{
		tokens.add(t);
	}
	
	/**
	 * Returns the color of this pawn, used in drawing the GUI
	 * @return color
	 * @author Matt
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Determines the score for the player based on the tokens which have been picked up
	 * @return score
	 * @author Robert
	 */
	public int getScore(){
		int score = 0;
		for(Token x: tokens){
			score = x.getValue() + score;
			if(formula.contains(x.getValue())){
				score += 20;
			}
		}
		return score;
	}
	
	/**
	 * Determines the final score, which also takes into consideration the wands which the player has left
	 * @return final score
	 * @author Robert
	 */
	public int getFinalScore(){
		int finalscore = getScore();
		return finalscore + (numWands * 3);
	}
	
	/**
	 * Returns the formula card associated with this player
	 * @return Formula card
	 */
	public Formula getFormula()
	{
		return formula;
	}
}
