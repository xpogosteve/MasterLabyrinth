package code.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Blake
 */
public abstract class DialogBox extends JComponent {

	private static final long serialVersionUID = 1298993012739449414L;

	/**
	 * The variable which was used in the previous code, added here for convenience
	 */
	private static final int SQUARE_SIZE = 35;

	/**
	 * The frame which houses this dialog box
	 */
	private JDialog _frame;
	
	/**
	 * The label which gives instructions for the dialog box
	 */
	private JLabel _label;
	
	/**
	 * The buttons representing the yes and no options presented by this dialog box
	 */
	private JButton _button1, _button2;
	
	/**
	 * The constructor for this dialog box, which handles the construction of the frame components and the creation
	 * of its associated listeners 
	 */
	public DialogBox() {
		_frame = new JDialog((JFrame) null, "You landed on the next token!", true);
		_frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		_frame.setLayout(new GridLayout(0,1));
		
		JPanel panel1 = new JPanel();
		_frame.add(panel1);
		_label = new JLabel("<html><p><center>Would you like to pick up the token?</center></p><br /></html>");
		increaseFont(_label, 2);
		_label.setPreferredSize(new Dimension(SQUARE_SIZE*10, SQUARE_SIZE*3));
		panel1.add(_label);
		
		JPanel panel2 = new JPanel();
		_frame.add(panel2);
		_button1 = new JButton("YES");
		increaseFont(_button1, 2);
		_button1.setPreferredSize(new Dimension(SQUARE_SIZE*5, SQUARE_SIZE*3));
		_button2 = new JButton("NO");
		increaseFont(_button2, 2);
		_button2.setPreferredSize(new Dimension(SQUARE_SIZE*5, SQUARE_SIZE*3));
		panel2.add(_button1);
		panel2.add(_button2);
		
//		_frame.setPreferredSize(new Dimension(SQUARE_SIZE*10+10, SQUARE_SIZE*5+10));
		_frame.pack();
		
		_button1.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				button1Pressed(arg0);
				afterAction();
			}
		});
		
		_button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button2Pressed(arg0);
				afterAction();				
			}
		});
		
		_frame.setVisible(true);
		
	}
	
	/**
	 * Method representing the action taken by button one
	 * @param arg0
	 */
	public abstract void button1Pressed(ActionEvent arg0);
	/**
	 * Method representing the action taken by button two
	 * @param arg0
	 */
	public abstract void button2Pressed(ActionEvent arg0);
	/**
	 * Method representing the behavior of the dialog box after a button has been pressed
	 * @param arg0
	 */
	public abstract void afterAction();
	
	/**
	 * Sets the text of the first button to YES
	 * @param text
	 */
	public void addTextToButton1(String text) {
		_button1.setText(text);
	}

	/**
	 * Sets the text of the second button to NO
	 * @param text
	 */
	public void addTextToButton2(String text) {
		_button2.setText(text);
	}
	
	/**
	 * Adds the instructions text
	 * @param text
	 */
	public void addTextToLabel(String text) {
		_label.setText(text);
	}
	
	/**
	 * removed the dialog box from the screen after the choice has been made
	 */
	public void dispose() {
		_frame.dispose();
	}
	
	/**
	 * Used to increase the size of the font in the associated components
	 * @param c
	 * @param factor
	 */
	private void increaseFont(JComponent c, double factor)
	{
		c.setFont(c.getFont().deriveFont((float) (c.getFont().getSize()*factor)));
	}
}
