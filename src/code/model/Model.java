package code.model;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import code.FileIO;
import code.FormulaFactory;
import code.pawn.Pawn;
import code.tile.ATile;
import code.tile.ITile;
import code.tile.LTile;
import code.tile.TTile;
import code.tokens.Token;

public class Model {

	/**
	 * Sets up the Observer pattern for User Interfaces.
	 */
	private Observer _observer;
	/**
	 * Represents the state of the game.
	 * @value true  - waiting for a player to shift the board
	 * @value false - waiting for a player to move their pawn
	 */
	public static final boolean SHIFT_BOARD_PHASE = true;
	public static final boolean MOVE_PAWN_PHASE = false;
	
	/**
	 * represents the current turn phase, game starts with shifting board
	 */
	private boolean turnPhase = SHIFT_BOARD_PHASE;
	/**
	 * The 2-dimensional array of tiles on which the game is played
	 */
	ATile[][] board = new ATile[7][7];
	/**
	 * The extra tile in the game
	 */
	ATile hold;
	/**
	 * The pawns in the game, stored in order of play
	 */
	public Pawn[] pawns;
	/**
	 * The value of the next token to be picked up
	 */
	public int tokenCounter = 1;
	/**
	 * The index of the player whose turn it is
	 */
	private int playerUp = 0;
	/**
	 * The tile which was the most recent to be pushed into the board
	 */
	private ATile lastPlacedTile;
	/**
	 * used for deciding shape of unused tile when game is restored from file
	 */
	private int[] tilesLeft = {19,13,18}; 

	/**
	 * @author Wiechec - Constructor
	 * Calls method for board creation and setting the permanent tiles via the set static method.
	 * Previous version did not have a constructor and relied on tests calling the methods separately.
	 * This version of the constructor would automate board creation for the player. An additional 
	 * constructor can be added in the future for players to set the board layout by adding individual
	 * pieces.
	 */

	//error in code caused by pawn needing fourth parameter from command line input. New parameter sets the player's name into the pawn
	/* (non-Javadoc)
	 * Corrected the error mentioned above by passing in the player names from the Driver
	 * - Blake
	 */ 
	public Model(String[] args){
		this.setFixedTiles();
		this.setBoard();
		pawns = new Pawn[args.length];
		final FormulaFactory formulas = new FormulaFactory();
		for (int i=0; i<args.length; i++) {
			pawns[i] = new Pawn(2 + 2*(i/2), 2 + (2*(i%2)), this, args[i], formulas.getFormula());
			pawns[i].setColor(i);
		}
	}

	/**
	 * Constructs a game from the string representation contained within the file passed in
	 * @param filename the file to restore this game from
	 * @author Matt, Steven, Robert
	 */
	public Model(String filename)
	{
		String contents = FileIO.load(filename); //loads file
		String[] lines = contents.split(System.lineSeparator()); //splits contents by line
		setPawns(lines[0]); //creates players
		setBoard(lines[1]); //creates board
		
		switch (Integer.parseInt(lines[2])) { //determines the location of the lastPlacedTile
		case 0:
			break;
		case 1:
			lastPlacedTile = board[1][0];
			break;
		case 2:
			lastPlacedTile = board[3][0];
			break;
		case 3:
			lastPlacedTile = board[5][0];
			break;
		case 4:
			lastPlacedTile = board[6][1];
			break;
		case 5:
			lastPlacedTile = board[6][3];
			break;
		case 6:
			lastPlacedTile = board[6][5];
			break;
		case 7:
			lastPlacedTile = board[5][6];
			break;
		case 8:
			lastPlacedTile = board[3][6];
			break;
		case 9:
			lastPlacedTile = board[1][6];
			break;
		case 10:
			lastPlacedTile = board[0][5];
			break;
		case 11:
			lastPlacedTile = board[0][3];
			break;
		case 12:
			lastPlacedTile = board[0][1];
			break;
		default:
			throw new IllegalArgumentException();
		}

		gameChanged();
	}

	/**
	 * Creates the pawns for this game based upon the passed in string representation
	 * @param allPawns
	 * @author Matt, Steven, Blake
	 */
	private void setPawns(String allPawns) {
		String[] eachPawn = allPawns.split("\\]\\]");  //separates string of all pawns into string array of individual pawns
		pawns = new Pawn[eachPawn.length]; //creates pawn array

		for (int i=0; i<eachPawn.length; i++)
		{
			if (i != 0)
				eachPawn[i] = eachPawn[i].substring(2); //removes ,[
			else 
				eachPawn[i] = eachPawn[i].substring(1); //removes [

			pawns[i] = new Pawn(0, 0, this, eachPawn[i].substring(0, eachPawn[i].indexOf(',')), 
					FormulaFactory.getFormula(eachPawn[i].substring(eachPawn[i].indexOf('[')+1, eachPawn[i].indexOf(']'))));
			eachPawn[i] = eachPawn[i].substring(eachPawn[i].indexOf(',') + 1); //cuts out player name and following comma

			pawns[i].setColor(eachPawn[i].substring(0, eachPawn[i].indexOf(','))); //sets color of pawn
			eachPawn[i] = eachPawn[i].substring(eachPawn[i].indexOf(',') + 1); //cuts out color name and following comma

			pawns[i].setNumWands(eachPawn[i].charAt(0) - '0'); //starting number of wands
			eachPawn[i] = eachPawn[i].substring(2);

			eachPawn[i] = eachPawn[i].substring(eachPawn[i].indexOf(']')+3); 

			if (eachPawn[i].isEmpty())
				continue; //there is no list of tokens for this pawn

			String[] tokens = eachPawn[i].split(",");
			for (String s: tokens)
			{
				Token t = new Token(Integer.parseInt(s)); //creates token from string representation
				pawns[i].addTokenToPickedUp(t); //adds to token list 
			}
			
		}
	}

	/**
	 * Author: Wiechec
	 * Method returns the boolean value on the tile associated with the array x and y,
	 * Board[x][y] values. Tile calls get direction method in the tile class which indicates
	 * if that direction is free for movement.
	 * @param x horizontal movement in 2d array
	 * @param y vertical movement in 2d array
	 * @param direction: Direction pawn wishes to move to.
	 * @return :True or false depending on the field set in the Tile for that direction.
	 */
	public boolean getTileBool(int x, int y, String direction){
		return board[x][y].getDirection(direction);
	}

	/**
	 * @author <jtmirfie>
	 * Sets an observer on the board to check when the board has been updated.
	 * @param o Observer to check if board has changed
	 */	
	public void setObserver(Observer o){
		_observer = o;
	}

	/**
	 * @author <jtmirfie>
	 * Update called on observer to send an update from Model to GUI.
	 */	
	public void gameChanged() {
		if (_observer != null) 
			_observer.update();
	}
	/**
	 * Sets the phase of the game.
	 * Choose from Model.SHIFT_BOARD_PHASE and Model.MOVE_PAWN_PHASE.
	 * @param turnPhase
	 */
	public void setTurnPhase(boolean turnPhase) {
		this.turnPhase = turnPhase;
	}

	/** @author bdlipp
	 * Method sets the tiles that are static on the board.
	 * These 'static tiles' should not be movable by the methods in this class.
	 * 
	 */
	public void setFixedTiles() {
		board[0][0] = new LTile(ATile.NORTH);
		board[0][2] = new TTile(ATile.WEST);
		board[0][4] = new TTile(ATile.WEST);
		board[0][6] = new LTile(ATile.WEST);

		board[2][0] = new TTile(ATile.NORTH);
		board[2][2] = new TTile(ATile.WEST);
		board[2][4] = new TTile(ATile.SOUTH);
		board[2][6] = new TTile(ATile.SOUTH);

		board[4][0] = new TTile(ATile.NORTH);
		board[4][2] = new TTile(ATile.NORTH);
		board[4][4] = new TTile(ATile.EAST);
		board[4][6] = new TTile(ATile.SOUTH);

		board[6][0] = new LTile(ATile.EAST);
		board[6][2] = new TTile(ATile.EAST);
		board[6][4] = new TTile(ATile.EAST);
		board[6][6] = new LTile(ATile.SOUTH);
	}
	/**@author bdlipp
	 * Method sets the board with tiles.
	 * These tiles are randomly chosen from Straight, Elbow and T groups and
	 * placed in the needed spots. The board should be completely full after 
	 * this method with one remaining tile which is our 'hold' tile.
	 * 
	 */
	/* (non-Javadoc)
	 * Updated this method so it generates a random board instead of the same one every time.
	 * - Blake
	 */
	public void setBoard(){
		List<ATile> remainingTiles = new ArrayList<ATile>(34);
		Random r = new Random();
		for (int i=0; i<6; i++)
			remainingTiles.add(new TTile(r.nextInt(4)));
		for (int i=0; i<15; i++)
			remainingTiles.add(new LTile(r.nextInt(4)));
		for (int i=0; i<13; i++)
			remainingTiles.add(new ITile(r.nextInt(2)));
		Collections.shuffle(remainingTiles);

		ArrayList<Token> tokens = setupTokens();

		//CHANGED*********
		for(int i=0; i<7; i++)
			for(int j =0; j<7; j++) {
				if(board[i][j]==null)
					board[i][j] = remainingTiles.remove(remainingTiles.size()-1);
				if (i!=0 && j!=0 && i!=6 && j!=6 && (i%2!=0 || j%2!=0))
					board[i][j].setToken(tokens.remove(tokens.size()-1));
			}

		setHoldTile(remainingTiles.remove(0));
	}

	/**
	 * Creates the board from the string representation of each of its tiles
	 * @param allTiles string representation
	 * @author Steven, Matt
	 */
	private void setBoard(String allTiles) 
	{
		allTiles = allTiles.substring(1, allTiles.length()-1); //remove front and end braces
		String[] tiles = allTiles.split("\\],\\["); //splits single string into array of tile strings

		for (int i=0; i<7; i++)
			for (int j=0; j<7; j++)
				board[i][j] = createTile(tiles[i+j*7], i, j); //constructs a tile object to put into the board

		if (tilesLeft[0] != 0)  //this if statement determines the shape of the hold tile based upon how
			setHoldTile(new LTile()); //many of each tile shape have already been added to the board
		else if (tilesLeft[1] != 0)
			setHoldTile(new ITile());
		else 
			setHoldTile(new TTile());
	}
	 
	/**
	 * Creates a tile object from the passed in string representation
	 * @param tileString string representation
	 * @param i x coordinate of the tile's location
	 * @param j y coordinate of the tile's location
	 * @return tile object
	 * @author Matt, Steven
	 */
	private ATile createTile(String tileString, int i, int j) 
	{
		ATile t;

		switch (tileString.charAt(0)) {  //determines which shape of tile to create
		case 'L': t = new LTile(tileString.charAt(1) - '0'); tilesLeft[0]--; break;
		case 'I': t = new ITile(tileString.charAt(1) - '0'); tilesLeft[1]--; break;
		case 'T': t = new TTile(tileString.charAt(1) - '0'); tilesLeft[2]--; break;
		default: throw new IllegalArgumentException();
		}
		tileString = tileString.substring(3);	

		int tokenValue = Integer.parseInt(tileString.substring(0, tileString.indexOf(',')));
		if (tokenValue != 0) //no token added if value is zero
		{
			t.setToken(new Token(tokenValue)); //create token and adds it to tile
		}
		tileString = tileString.substring(tileString.indexOf('[')+1, tileString.length()-1);

		if (tileString.isEmpty()) //no players on the tile
			return t;

		String[] players = tileString.split(",");
		for (String s: players)
		{
			for (int k=0; k<pawns.length; k++)
			{
				if (Pawn.stringAsColor(s).equals(pawns[k].getColor())) //adds the correct pawn based on its color string representation
					pawns[k].setCurrentLoc(j, i);
			}

		}

		return t;
	}

	/**@author bdlipp
	 * Method pushes the 'hold' tile across a row or down a column by passing reference from 
	 * one tile to the next. The last tile on the board will be pushed off and will become
	 * the 'hold' tile.
	 * 
	 * @param col
	 * @param row
	 * @return the 'hold' tile whether that be the last one pushed out or the original
	 * 		   if the move fails
	 */
	public ATile moveTiles(int col, int row){//takes in two ints for points to move and one Tile which is the one tile not on the board

		if (!turnPhase || (((col == 0 || col == 6) && board[6-col][row] == lastPlacedTile) || ((row == 0 || row == 6) && board[col][6-row] == lastPlacedTile)))
			return hold;

		lastPlacedTile = hold;

		ATile[][] tempBoard = new ATile[7][];


		for(int i=0;i<7;i++)
			tempBoard[i] = Arrays.copyOf(board[i], board[i].length);

		if((col==0 || col == 6) && (row % 2 == 1)){//right/left
			hold=tempBoard[6 - col][row];
			if(hold.hasToken())
				lastPlacedTile.setToken(hold.pickUpToken());

			board[col][row]=lastPlacedTile;
			for (int i=1; i<=6; i++)
				board[Math.abs(col - i)][row] = tempBoard[Math.abs(col - (i-1))][row];

			for (Pawn p : pawns) 
				if(p.getY()==row)
					p.setX(p.getX()+1-col/3);




			gameChanged();
			setTurnPhase(MOVE_PAWN_PHASE);
		}
		else if(((row == 0 || row == 6) && (col % 2 == 1))){//top/bottom
			hold=tempBoard[col][6 - row];
			if(hold.hasToken()){
				lastPlacedTile.setToken(hold.pickUpToken());
			}	
			board[col][row]=lastPlacedTile;
			for (int i=1; i<=6; i++)
				board[col][Math.abs(row-i)] = tempBoard[col][Math.abs(row-(i-1))];

			for (Pawn p : pawns) 
				if(p.getX()==col)
					p.setY(p.getY()+1-row/3);

			gameChanged();
			setTurnPhase(MOVE_PAWN_PHASE);
		}

		return hold;

	}
	/**@author bdlipp, Blake
	 * Method returns the tile at a specific spot in the graph.
	 * 
	 * @param col
	 * @param row
	 * @return Tiles object that is in a specific column and row.
	 */
	public ATile getTile(int col, int row){
		return board[col][row];
	}
	public ATile getTile(Point loc) {
		return board[loc.x][loc.y];
	}
	/**@author bdlipp
	 * Method sets the 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 * @param aTile
	 */
	public void setHoldTile(ATile aTile){
		hold = aTile;
	}
	/**@author bdlipp
	 * Method returns the 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 * 
	 * @return 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 */
	public ATile getHoldTile(){
		return hold;
	}

	/**
	 * @author <jtmirfie>
	 * Returns the 'board' tiles. Used to associate between other classes.
	 * @return 'board' tiles in order to access it from other classes.
	 */	
	public ATile[][] getBoard(){
		return board;
	}

	/**
	 * @author <jtmirfie>
	 * Compiles an array of the tokens class and then shuffles them using a collection method.
	 * @return 
	 */	
	public ArrayList<Token> setupTokens(){
		ArrayList<Token> tokens = new ArrayList<Token>(21);
		for(int i=1;i<21;i++){
			tokens.add(new Token(i));
		}
		tokens.add(new Token(25));
		Collections.shuffle(tokens);
		return tokens;
	}
	public int getNextTokenValue() {
		return tokenCounter;
	}
	/**
	 * @author <jtmirfie>
	 * Runs after the last token is picked up. Counts up all the tokens each player has picked up then exits the game.
	 */	
	/* NON-Javadoc
	 * Changed to be much simpler using methods for score in the pawn class
	 */
	public void endGame(){
		for (Pawn e: pawns)
			System.out.println(e.getName() + ":" + e.getFinalScore());

		System.exit(0);
	}
	/**
	 * Returns the location of a Tile on the Board.
	 * @author Blake, Matt
	 */
	public Point loc(ATile aTile) {
		for (int i=0; i<7; i++)
			for (int j=0; j<7; j++)
				if (aTile == board[i][j])
					return new Point(i,j);
		return null;
	}
	/**
	 * Returns a list of Pawns on a Tile.
	 * @author Blake, Matt
	 */
	public List<Pawn> getPlayersOnTile(ATile aTile) {
		List<Pawn> result = new ArrayList<Pawn>(pawns.length);
		for (Pawn p : pawns) 
			if (p.getPos().equals(loc(aTile)))
				result.add(p);
		return result;
	}
	/**
	 * Transforms a list of Players into a list of their colors
	 * @author Blake
	 */
	public List<String> playerNames(List<Pawn> players) {
		List<String> result = new ArrayList<String>(players.size());
		for (Pawn p : players)
		{
			Color c = p.getColor();
			for (int i=0; i<4; i++)
				if (Pawn.PLAYER_COLORS[i].equals(c))
					result.add(Pawn.PLAYER_COLORS_AS_STRINGS[i]);
		}
		return result;
	}
	/**
	 * Returns the index of the pawn in the underlying pawn array.
	 * @author Blake
	 */
	public int playerIndex(Pawn p) {
		for (int i=0; i<pawns.length; i++)
			if (p == pawns[i])
				return i;
		return -1;
	}
	/**
	 * Returns a string representation of all the Tiles on the Board.
	 * @author Blake, Matt
	 */
	@Override public String toString()
	{
		String result = new String();
		//Line 1
		for (int i=0; i<pawns.length; i++)
			result += pawns[(i+playerUp)%pawns.length] + ",";
		result = result.substring(0, result.length()-1);
		result += System.lineSeparator(); //newline character

		//Line 2
		for (int i=0; i<7; i++)
			for (int j=0; j<7; j++)
				result += board[j][i] + playerNames(getPlayersOnTile(board[j][i])).toString() + "],";

		result = result.substring(0,result.length()-1); //removes comma at the end of the string
		result += System.lineSeparator(); //newline character

		//Line 3
		int i;
		if (lastPlacedTile == null)
			i = 0;
		else {
			Point p = loc(lastPlacedTile);
			i = 1 + (p.x + p.y)/2;
			if (p.y == 6 || p.x == 0)
				i = 13 - i;
		}
		result += i;

		return result;		
	}
	
	/**
	 * Called to update which is the next token to be picked up
	 */
	public void tokenPickedUp() {
		tokenCounter++;
		if(tokenCounter == 21)
			tokenCounter=25;

		if(tokenCounter==26)
			endGame();
	}
	
	/**
	 * Updates the reference to playerUp to be the next player in turn order
	 */
	public void nextPlayer() {
		playerUp = (playerUp + 1) % pawns.length;	
	}
	
	/**
	 * Returns a reference to the player whose turn it currently is
	 * @return
	 */
	public Pawn getCurrentPlayer() {
		return pawns[playerUp];
	}
	
	/**
	 * Returns the color of the current player, for player panel in GUI
	 * @return
	 */
	public Color getCurrentPlayerColor() {
		return pawns[playerUp].getColor();
	}
	
	/**
	 * Returns the boolean value which represents the current phase of the turn
	 * @return
	 */
	public boolean getTurnPhase() {
		return turnPhase;
	}
	
	/**
	 * Returns a reference to the last placed tile
	 * @return lastPlacedTile
	 * @author Matt
	 */
	public ATile getLastPlaced()
	{
		return lastPlacedTile;
	}

}