package code.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import code.FileIO;
import code.model.Model;
import code.pawn.Pawn;
import code.tile.ATile;

/* (non-Javadoc)
 * renamed from keyMovement to KeyMovement to follow naming conventions
 * - Blake
 */
public class KeyMovement implements KeyListener {

	/**
	 * The model which this listener is acting upon
	 */
	private Model _model;
	/**
	 * The button which will be pressed when enter is typed
	 */
	private JButton _endTurn;

	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Creates the key listener for the whole game that is used for pawn movement.
	 * @param m Associates the Model class with this one in order to access the board.
	 * @param p Associates the Pawn class with this one to call on the move methods.
	 * @param endTurn 
	 */	
	public KeyMovement(Model m, JButton endTurn) {
		_model = m;
		_endTurn = endTurn;
	}

	/**
	 * Uses a switch statement to determine the action to be taken based upon the key input
	 * @author Blake, Matt
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown())
			switch (e.getKeyCode()) {
			case 'S':
				if (_model.getTurnPhase() != Model.SHIFT_BOARD_PHASE) {
					System.err.println("Sorry, you must save at the beginning of your turn.");
					break;
				}
				boolean success;
				String s;
				if (e.isShiftDown())
					success = doSave(s = requestFilename());
				else
					success = doSave(s = FileIO.DEFAULT_FILENAME);

				if (success)
				{
					System.out.println("Game saved successfully as "+s);
					/**
					 * @author Joe
					 * Added System.exit to exit upon game's successful save.
					 */
					
					System.exit(0);
				}
				else
					System.err.println("Failed to save game.");
				break;
			case 'P':
				if (_model.getTurnPhase() != Model.SHIFT_BOARD_PHASE || !doSave(FileIO.TEMP_FILENAME)) {
					System.err.println("Sorry, unable to execute this command right now.");
					break;
				}
				_endTurn.setEnabled(false);
				new Minigame((JFrame) e.getSource(), _model) {
					@Override public void cleanup() { _endTurn.setEnabled(true); }
				};
			}
	}

	/**
	 * Performs the saving of this game
	 * @param filename filename to save the game under
	 * @return whether the save was successful
	 */
	private boolean doSave(String filename) {
		return FileIO.save(filename, _model.toString());
	}

	/**
	 * Allows the user to specify the filename to be saved with
	 * @return
	 */
	private String requestFilename() {
		Scanner sc = new Scanner(System.in);
		System.out.flush();
		System.out.println("Enter filename:");
		String result = sc.nextLine();
		sc.close();
		return result;
	}

	/**
	 * @author <jtmirfie>
	 * Runs after one of the arrow keys are pressed and then released.
	 * Checks to first make sure that a tile is shifted. 
	 * After the code checks if the movement is possible and also checks if there is token to pick up.
	 */	
	@Override
	public void keyReleased(KeyEvent e) {

		if(_model.getTurnPhase())
			return;

		Pawn p = _model.getCurrentPlayer();

		switch (e.getKeyCode()) { 
		case KeyEvent.VK_UP:    case KeyEvent.VK_KP_UP:
			p.move(ATile.NORTH);
			break;
		case 'S': if (e.isControlDown()) break;
		case KeyEvent.VK_DOWN:  case KeyEvent.VK_KP_DOWN:
			p.move(ATile.SOUTH);
			break;
		case KeyEvent.VK_LEFT:  case KeyEvent.VK_KP_LEFT:
			p.move(ATile.WEST);
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			p.move(ATile.EAST);
			break;
		case KeyEvent.VK_ENTER:
			_endTurn.doClick();
		default:
			return;
		}
		
		_model.gameChanged();
	}

	@Override public void keyTyped(KeyEvent e) {}


}
