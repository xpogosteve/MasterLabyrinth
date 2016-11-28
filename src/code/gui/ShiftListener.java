package code.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.model.Model;

/* (non-Javadoc)
 * Renamed from shiftListener to ShiftListener to follow naming conventions
 * - Blake
 */
public class ShiftListener implements ActionListener {

	/**
	 * The underlying Model on which this listener is acting
	 */
	private Model _model;
	/**
	 * The column of the shift
	 */
	private int col;
	/**
	 * The row of the shift
	 */
	private int row;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Runs the moveTiles() method whenever the respective button is clicked.
	 * @param m associates the model class to run the moveTiles() method.
	 * @param x value associated with the column of the button to be shifted.
	 * @param y value associated with the row of the button to be shifted.
	 */	
	public ShiftListener(Model m,int x, int y){
		_model=m;
		col = x;
		row = y;
	}
	
	/**
	 * @author <jtmirfie>
	 * Runs the moveTiles() method whenever a button is clicked.
	 * @param e
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.moveTiles(col,row);
		
	}

}
