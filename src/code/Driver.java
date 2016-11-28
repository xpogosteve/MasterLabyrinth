package code;

import javax.swing.SwingUtilities;

import code.gui.GUI;
import code.model.Model;

/* (non-Javadoc)
 * Rewrote constructor to correctly check for correct number of players.
 * - Blake
 */

public class Driver {

	/**
	 * Entry point of the program
	 * Determines the number of arguments passed in and chooses action accordingly
	 * One argument- restores game from contents in given file
	 * 2-4 arguments- creates new game with these player names
	 * @param args
	 * @author Matt, Robert, Blake
	 */
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 4)
			throw new RuntimeException("You must have 2-4 players.");
		
		Model m;
		if (args.length == 1)
			m = new Model(args[0]);
		else if (contains(args, '[', ']', ',')) //checking for illegal player names
			throw new RuntimeException("You can't name a player that!");
		else
			m = new Model(args);

		SwingUtilities.invokeLater(new GUI(m));
	}
	
	/**
	 * Determines whether the given string array contains any of the passed in characters
	 * @param toCheck String array which is checked
	 * @param chars characters to check for
	 * @return whether any of the characters are present
	 * @author Blake
	 */
	public static boolean contains(String[] toCheck, char... chars) {
		for (String s : toCheck)
				for (char c : chars)
					if (s.indexOf(c) >= 0)
						return true;
		return false;
	}

}
