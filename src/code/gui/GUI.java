package code.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

import code.model.Model;
import code.model.Observer;
import code.pawn.Pawn;
import code.tile.ATile;

public class  GUI implements Runnable, Observer {
	/**
	 * main panels of the game
	 */
	private JPanel _boardPanel;
	private JPanel _gamePanel;
	private JPanel _offGamePanel;

	/**
	 * edge panels around board
	 */
	private JPanel _westBPanel;
	private JPanel _northBPanel;
	private JPanel _eastBPanel;
	private JPanel _southBPanel;

	/**
	 * components of the off game panel
	 */
	
	private JPanel _rotatePanel;
	private TileUI _holdPanel;
	private JButton _rotateButton;
	private JButton _endTurn;
	private JButton _useWand;
	private JLabel _player;
	private JLabel _formula;
	private JLabel _instructions;
	
	/**
	 * parent GUI component
	 */
	private JFrame _gameFrame;
	
	/**
	 * underlying model
	 */
	private Model _model;

	/**
	 * object for taking in keyboard input
	 */
	private KeyMovement key;
	
	

	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Associates the Model class with this one and sets up the observer that to notify the model when to update.
	 * @param m Associated between this class and the model to get the information needed to display. 
	 */	
	public GUI(Model m){
		_model = m;
		_model.setObserver(this);
	}

	/**
	 * @author <jtmirfie>
	 * Runs at the beginning of the code. JFrame is compiled and the setupBoard() method is called onto it.
	 * Various panels are placed on the frame which hold the tiles and the buttons around the board.
	 * A key listener is also implemented which is used for pawn movement respective to each players turn.
	 */	
	@Override
	public void run() {

		_gameFrame = new JFrame();
		_gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_gameFrame.getContentPane().setLayout(new FlowLayout());
		_gameFrame.setFocusable(true);
		_gameFrame.setResizable(false);
		_gameFrame.setLayout(new BorderLayout());
		setupFrame();
	}

	/**
	 * @author <jtmirfie>
	 * Draws onto the JFrame of the board setting up all the tiles and other panels that are placed on the board.
	 * The buttons to shift the board are also implemented with their actionlisteners.
	 */	
	public void setupFrame(){
		_boardPanel = new JPanel();
		_boardPanel.setFocusable(true);
		_boardPanel.setLayout(new GridLayout(7,7));
		for (int a = 0; a<7;a++){			
			for (int b = 0; b<7;b++){

				_boardPanel.add(new TileUI(_model.getTile(b, a),_model,b,a).returnPanel());
			}
		}


		JButton westB1 = new JButton(">");
		westB1.addActionListener(new ShiftListener(_model,0,1));
		westB1.setFocusable(false);
		JButton westB2 = new JButton(">");
		westB2.addActionListener(new ShiftListener(_model,0,3));
		westB2.setFocusable(false);
		JButton westB3 = new JButton(">");
		westB3.addActionListener(new ShiftListener(_model,0,5));
		westB3.setFocusable(false);

		JButton northB1 = new JButton("V");
		northB1.addActionListener(new ShiftListener(_model,1,0));
		northB1.setFocusable(false);
		JButton northB2 = new JButton("V");
		northB2.addActionListener(new ShiftListener(_model,3,0));
		northB2.setFocusable(false);
		JButton northB3 = new JButton("V");
		northB3.addActionListener(new ShiftListener(_model,5,0));
		northB3.setFocusable(false);

		JButton eastB1 = new JButton("<");
		eastB1.addActionListener(new ShiftListener(_model,6,1));
		eastB1.setFocusable(false);
		JButton eastB2 = new JButton("<");
		eastB2.addActionListener(new ShiftListener(_model,6,3));
		eastB2.setFocusable(false);
		JButton eastB3 = new JButton("<");
		eastB3.addActionListener(new ShiftListener(_model,6,5));
		eastB3.setFocusable(false);

		/* (non-Javadoc)
		 * Changed JButton text to "^" rather than "Λ".
		 * - Blake
		 */
		JButton southB1 = new JButton("^");
		southB1.addActionListener(new ShiftListener(_model,1,6));
		southB1.setFocusable(false);
		JButton southB2 = new JButton("^");
		southB2.addActionListener(new ShiftListener(_model,3,6));
		southB2.setFocusable(false);
		JButton southB3 = new JButton("^");
		southB3.addActionListener(new ShiftListener(_model,5,6));
		southB3.setFocusable(false);

		_westBPanel = new JPanel();
		_westBPanel.setFocusable(true);
		_westBPanel.setLayout(new GridLayout(7,1));

		_westBPanel.add(new JPanel());
		_westBPanel.add(westB1);
		_westBPanel.add(new JPanel());
		_westBPanel.add(westB2);
		_westBPanel.add(new JPanel());
		_westBPanel.add(westB3);
		_westBPanel.add(new JPanel());

		_northBPanel = new JPanel();
		_northBPanel.setFocusable(true);
		_northBPanel.setLayout(new GridLayout(1,7));

		_northBPanel.add(new JPanel());
		_northBPanel.add(northB1);
		_northBPanel.add(new JPanel());
		_northBPanel.add(northB2);
		_northBPanel.add(new JPanel());
		_northBPanel.add(northB3);
		_northBPanel.add(new JPanel());

		_eastBPanel = new JPanel();
		_eastBPanel.setFocusable(true);
		_eastBPanel.setLayout(new GridLayout(7,1));

		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB1);
		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB2);
		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB3);
		_eastBPanel.add(new JPanel());

		_southBPanel = new JPanel();
		_southBPanel.setFocusable(true);
		_southBPanel.setLayout(new GridLayout(1,7));

		_southBPanel.add(new JPanel());
		_southBPanel.add(southB1);
		_southBPanel.add(new JPanel());
		_southBPanel.add(southB2);
		_southBPanel.add(new JPanel());
		_southBPanel.add(southB3);
		_southBPanel.add(new JPanel());

		_gamePanel = new JPanel();
		_gamePanel.setFocusable(true);
		_gamePanel.setLayout(new BorderLayout());

		_gamePanel.add(_westBPanel,BorderLayout.WEST);
		_gamePanel.add(_northBPanel,BorderLayout.NORTH);
		_gamePanel.add(_eastBPanel,BorderLayout.EAST);
		_gamePanel.add(_southBPanel,BorderLayout.SOUTH);

		_gamePanel.add(_boardPanel,BorderLayout.CENTER);

		_offGamePanel = new JPanel();
		_offGamePanel.setFocusable(true);
		_offGamePanel.setLayout(new GridLayout(6,1));

		_holdPanel = new TileUI(_model.getHoldTile(),_model,10,10);
//		_holdPanel.setFocusable(true);
		_offGamePanel.add(_holdPanel.returnPanel());

		_rotatePanel= new JPanel();
		_rotatePanel.setFocusable(true);
		_rotatePanel.setLayout(new GridLayout(3,1));

		_rotateButton = new JButton("Rotate");
		_rotateButton.addActionListener(new ActionListener() {
			/**
			 * Rotate listener moved within the GUI as an anonymous inner class
			 * @author Blake, Matt
			 */
			@Override public void actionPerformed(ActionEvent e) {
				ATile hold = _model.getHoldTile();
				switch (hold.getRotation()) {
				case ATile.NORTH:
					hold.rotate(ATile.EAST);
					break;
				case ATile.EAST:
					hold.rotate(ATile.SOUTH);
					break;
				case ATile.SOUTH:
					hold.rotate(ATile.WEST);
					break;
				case ATile.WEST:
					hold.rotate(ATile.NORTH);
					break;
				}
				_model.gameChanged();
			}
		});
		
		_rotateButton.setFocusable(false);

		_endTurn = new JButton("End Turn");
		_endTurn.setFocusable(false);
		_endTurn.addActionListener(new ActionListener() {
			/**
			 * Event handler for the end turn button, moved here
			 * @author Matt, Blake
			 */
			
			@Override public void actionPerformed(ActionEvent e) {
				if(_model.getTurnPhase() == Model.SHIFT_BOARD_PHASE)
					return;

				Pawn p = _model.getCurrentPlayer();
				if(p.checkIfValidMove()){
					p.pickupToken();
					p.resetStartLoc();
				}

				_useWand.setEnabled(true);
				_model.nextPlayer();
				_model.gameChanged();
				_model.setTurnPhase(Model.SHIFT_BOARD_PHASE);
				
			}});

		_useWand = new JButton("Use Wand");
		_useWand.setFocusable(false);
		_useWand.addActionListener(new ActionListener() {
			/**
			 * Event handler for the use wand button
			 * @author Matt, Blake
			 */
			
			@Override public void actionPerformed(ActionEvent e) {
				if(!(_model.getTurnPhase())){
					_model.getCurrentPlayer().wand();
					_useWand.setEnabled(false);
				}
			}});


		_player = new JLabel();
		_player.setHorizontalAlignment(JLabel.CENTER);
		_player.setBorder(new StrokeBorder(new BasicStroke(2)));
		_player.setText("<html><div style='text-align: center;'><font size=6>  " + _model.pawns[0].getName() + "</font><br>" +
				"<font size=4>  Score: " + _model.pawns[0].getScore() + "<br>" +
				"  Wands Left: " + _model.pawns[0].getNumWands() + "</font></html>");
		_player.setOpaque(true);
		_player.setBackground(_model.getCurrentPlayerColor());
		
		_formula = new JLabel();
		_formula.setHorizontalAlignment(JLabel.CENTER);
		_formula.setBorder(new StrokeBorder(new BasicStroke(2)));
		/**
		 * @author Steven, Joe
		 * Adds functionality to the Formula JLabel to only display the current 
		 * players formula upon hovering over the JLabel with the cursor.
		 */
		
		_formula.addMouseListener(new MouseListener(){
			@Override
			public void mouseEntered(MouseEvent arg0) {
				_formula.setText("<html><div style='text-align: center;'><font size=6>Formula:</font><br>" 
						+ "<font size=4>" + _model.getCurrentPlayer().getFormula().getRepresentationOnCard() + "</div></html>");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				_formula.setText("<html><div style='text-align: center;'><font size=6>Formula:</font><br><font size = 10>?</font></div></html>");	
			}
			@Override
			public void mouseClicked(MouseEvent arg0){}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			
		});
		_formula.setText("<html><div style='text-align: center;'><font size=6>Formula:</font><br><font size = 10>?</font></div></html>");	
		_formula.setOpaque(true);
		_formula.setBackground(Color.WHITE);
		_instructions = new JLabel();
		_instructions.setHorizontalAlignment(JLabel.CENTER);
		_instructions.setBorder(new StrokeBorder(new BasicStroke(2)));
		_instructions.setText("<html><div style='text-align: center;'><font size=5>Instructions:</font><br>"
				+ "<font size=3>Use Buttons</font><br><font size=3>to slide rows!</font><br><br>"
				+ "<font size=3>Use Arrow Keys</font><br><font size=3>to move your Pawn!</font><br><br>"
				+ "<font size=3>Press Ctrl + S</font><br><font size=3>to save the game!</font><br>"
				+ "</div></html>");
		_instructions.setOpaque(true);;
		_instructions.setBackground(Color.WHITE);
		
		_rotatePanel.add(_rotateButton);
		_rotatePanel.add(_endTurn);
		_rotatePanel.add(_useWand);
		_offGamePanel.add(_rotatePanel);
		_offGamePanel.add(_player);
		_offGamePanel.add(_formula);
		_offGamePanel.add(_instructions);

		key = new KeyMovement(_model, _endTurn);
		_gameFrame.addKeyListener(key);
		_gameFrame.getContentPane().add(_gamePanel,BorderLayout.WEST);
		_gameFrame.getContentPane().add(_offGamePanel,BorderLayout.EAST);
		_gameFrame.pack();
		_gameFrame.setVisible(true);
	}

	/**
	 * @author <jtmirfie>
	 * Runs whenever a change is made to the model and redraws all of the modifications.
	 * All of the panels are removed and it redraws the entire board according to the new model.
	 */	
	@Override public void update() {


		_gamePanel.remove(_boardPanel);

		_player.setText("<html><div style='text-align: center;'><font size=6>" + _model.getCurrentPlayer().getName() + "</font><br>" +
				"<font size=4>Score: " + _model.getCurrentPlayer().getScore() + "<br>" +
				"Wands Left: " + _model.getCurrentPlayer().getNumWands() + "</font></html>");
		_player.setBackground(_model.getCurrentPlayerColor());
		
		/**
		 * @author Joe
		 * Removed _formula.setText due to the new hover functionality over the formula JLabel
		 * 
		 */
		//_formula.setText("<html><div style='text-align: center;'><font size=6>Formula:</font><br>" 
		//	+ "<font size=4>" + _model.getCurrentPlayer().getFormula().getRepresentationOnCard() + "</div></html>"); 

		_holdPanel.refresh(_model.getHoldTile(),10,10);
		
		_boardPanel = new JPanel();
		_boardPanel.setFocusable(true);
		_boardPanel.setLayout(new GridLayout(7,7));
		for (int a = 0; a<7;a++){			
			for (int b = 0; b<7;b++){

				_boardPanel.add(new TileUI(_model.getTile(b, a),_model,b,a).returnPanel());
			}
		}
		_gamePanel.add(_boardPanel,BorderLayout.CENTER);

		_gameFrame.pack();
		_gameFrame.repaint();
	}

}