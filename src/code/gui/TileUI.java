package code.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import code.model.Model;
import code.tile.ATile;

public class TileUI extends JPanel {

	private static final long serialVersionUID = -451455850722088112L;
	/* (non-Javadoc)
	 * Extracted Colors from drawTile method and made them constants.
	 * Wrapped collors yellow, red, blue, and green into the Color array PLAYER_COLORS.
	 * - Blake
	 */
	
	/**
	 * The color of the paths on the tiles
	 */
	public static final Color PATH_COLOR = new Color(155,255,235);
	/**
	 * The color of the area of the tile which is not a path
	 */
	public static final Color BACKGROUND_COLOR = new Color(100, 0, 150);
	/**
	 * The color of the tokens on the board
	 */
	public static final Color TOKEN_COLOR = Color.YELLOW;
	/**
	 * The tile in the model which this UI element is representing
	 */
	private ATile _tile;
	/**
	 * The underlying model which is being acted on by this listener
	 */
	private Model _model;
	/**
	 * The x coordinate of this tile
	 */
	private int x;
	/**
	 * The y coordinate of this tile
	 */
	private int y;

	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Creates the tiles panel used to display the tiles from the model.
	 * A 3x3 JPanel is used that is filled with buttons to display the paths from the model.
	 * @param t associates the Tiles class with this one in order to figure out what type of tile is being used.
	 * @param m associates the Model class to retrieve the tile from the board.
	 * @param x value that is used for the location of the horizontal spot of the tile
	 * @param y value that is used for the location of the vertical spot of the tile
	 */	
	public TileUI(ATile t, Model m, int x, int y){
		_model = m;
		refresh(t,x,y);
	}

	/**
	 * @author <jtmirfie>
	 * Draws the tiles on a panel using JButtons.
	 * Each button is given colors to show whether its a valid path or a wall.
	 * It also finds the location of each pawn and draws a unique color for each one individually.
	 */	
	public void drawTile(){

		this.setFocusable(true);
		this.setLayout(new GridLayout(3,3));
		this.setPreferredSize(new Dimension(125,125));
		this.setBackground(Color.black);

		for(int i=0;i<3;i++){

			for(int z=0;z<3;z++){

				/* (non-Javadoc)
				 * Changed _j from a JButton to a JPanel
				 * Renamed _j to panel to follow naming conventions
				 * Replaced panel.setText(temp) with panel.add(new JLabel(temp))
				 * - Blake
				 */
				JPanel panel = new JPanel();
				panel.setOpaque(true);
				panel.setFocusable(false);
				panel.setPreferredSize(new Dimension(125,125));
				panel.setBackground(BACKGROUND_COLOR);

				if(i==1 && z==1){
					panel.setBackground(PATH_COLOR);
				}

				if(x!=10 || y!=10){
					if(i==1 && z==1){

						//						panel.setBackground(BG);

						/* (non-Javadoc)
						 * Removed redundant hasToken check.
						 * - Blake
						 */
						if (x!=10 && y!=10 && _model.getBoard()[x][y].hasToken()){

							panel.setBackground(TOKEN_COLOR);
							panel.add(new JLabel(String.valueOf(_model.getBoard()[x][y].getToken().getValue())));

						}

						/* (non-Javadoc)
						 * Rolled this into a loop.
						 * - Blake
						 */
						for (int index=0; index<_model.pawns.length; index++)
							if (_model.pawns[index].getX()==x && _model.pawns[index].getY()==y) 
								panel.setBackground(_model.pawns[index].getColor());
					}

				}



				if (_tile.getDirection(ATile.NORTH) && i==0 && z==1 ||
						_tile.getDirection(ATile.EAST)  && i==1 && z==2 ||
						_tile.getDirection(ATile.SOUTH) && i==2 && z==1 ||
						_tile.getDirection(ATile.WEST)  && i==1 && z==0)
					panel.setBackground(PATH_COLOR);

				this.add(panel);

			}
		}



	}
	/**
	 * Returns the JPanel associated with this UI element
	 * @return JPanel
	 */
	public JPanel returnPanel(){
		return this;
	}
	/**
	 * Updates the graphical representation of this tile after a change in the model
	 * @param t the tile element this is representing
	 * @param i the x coordinate of the tile
	 * @param j the y coordinate of the tile
	 * @return the JPanel which contains a representation of the tile after redraw
	 * @author Blake, Matt
	 */
	public JPanel refresh(ATile t, int i, int j) {
		_tile = t;
		x = i;
		y = j;
		removeAll();
		drawTile();
		return this;
	}

}
