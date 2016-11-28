package code.gui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

import code.FileIO;
import code.model.Model;
import code.pawn.Pawn;
import code.tile.ATile;
import code.tile.ITile;
import code.tile.LTile;
import code.tile.TTile;

/**
 * @author Matt, Blake
 */
public abstract class Minigame {

	private static final boolean LOST = false, WON = true;

	protected static final int SQUARE_SIZE = 30;

	private final int[] L_CONNECTIONS = {1,1,1,0, 0,2,2,1, 1,1,0,0, 2,2,3,3, 3,0,0,2, 2,3,3,3},
						I_CONNECTIONS = {1,1,1,0, 0,1,0,0, 1,1,0,0, 0,0,1,1, 0,0,1,0, 0,1,1,1},
						T_CONNECTIONS = {0,0,0,3, 3,2,1,1, 0,0,3,3, 1,1,2,2, 3,3,0,1, 1,2,2,2};

	private int playerDirection = -1;

	private JFrame frame;

	private Model model;
	
	private KeyListener playerDirectionListener;

	private Pawn player;

	private List<Ghost> ghosts = new ArrayList<Ghost>(3);

	private Timer playerTimer = new Timer(250, null),
				   ghostTimer = new Timer(600, null);

	public Minigame(JFrame frame, Model model) {
		this.model = model;
		this.frame = frame;
		player = model.getCurrentPlayer();
		makeConnections();
		model.gameChanged();
		startGame();
	}

	private void makeConnections() {
		ATile t;
		int index=0;
		for (int i=0; i<7; i++)
			for (int j=-(i%2-1); j<7; j+=2) {
				t = model.getTile(j, i);
				if (t instanceof LTile)
					t.rotate(L_CONNECTIONS[index++]);
				if (t instanceof ITile)
					t.rotate(I_CONNECTIONS[index++]);
				if (t instanceof TTile)
					t.rotate(T_CONNECTIONS[index++]);
			}
		for (int i=1; i<7; i+=2)
			for (int j=1; j<7; j+=2) {
				model.getTile(j,i).rotate(optimalRotation(j,i));
			}

	}

	private int optimalRotation(int i, int j) {
		ATile t = model.getTile(j,i);
		int maxConnections = 0;
		int maxConnectionsIndex = 0;
		for (int k=0; k<4; k++) {
			t.rotate(k);
			int numConnections = 0;
			if (t.getDirection(ATile.NORTH) && model.getTile(j, i-1).getDirection(ATile.SOUTH))
				numConnections++;
			if (t.getDirection(ATile.EAST)  && model.getTile(j+1, i).getDirection(ATile.WEST))
				numConnections++;
			if (t.getDirection(ATile.SOUTH) && model.getTile(j, i+1).getDirection(ATile.NORTH))
				numConnections++;
			if (t.getDirection(ATile.WEST)  && model.getTile(j-1, i).getDirection(ATile.EAST))
				numConnections++;
			if (numConnections > maxConnections) {
				maxConnections = numConnections;
				maxConnectionsIndex = k;
			}
		}
		return maxConnectionsIndex;			
	}

	private void startGame() {

		playerTimer.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				model.setTurnPhase(Model.MOVE_PAWN_PHASE);
				switch (playerDirection) {
				case 0:
					player.move(ATile.NORTH);
					break;
				case 1:
					player.move(ATile.EAST);
					break;
				case 2:
					player.move(ATile.SOUTH);
					break;
				case 3:
					player.move(ATile.WEST);
					break;
				}
				if (model.getTile(player.getPos()).hasToken()) {
					/* 
					 * Everything the light touches....but what about that shadowy place?
					 * That's java.lang.reflect.*. You must never go there, Goran.
					 */
					try {
						Method getToken = Pawn.class.getDeclaredMethod("getToken");
						getToken.setAccessible(true);
						getToken.invoke(player);
						System.out.println(player.getPos());
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

				afterMove();
			}
		});
		
		playerTimer.start();

		ArrayList<Pawn> players = new ArrayList<Pawn>(Arrays.asList(model.pawns));
		players.remove(player);

		ghosts.add(new Ghost(players.get(0)) {
			@Override
			public Point target() {
				return player.getPos();
			}
		});

		if (players.size() > 1)
			ghosts.add(new Ghost(players.get(1)) {
				@Override public Point target() {
					Point p = player.getPos();
					switch (playerDirection) {
					case 0:
						return new Point(p.x, p.y-3);
					case 1:
						return new Point(p.x+3, p.y);
					case 2:
						return new Point(p.x, p.y+3);
					case 3:
						return new Point(p.x-3, p.y);
					default:
						return (Point) p.clone();
					}
				}

			});
		if (players.size() > 2)
			ghosts.add(new Ghost(players.get(2)) {
				@Override public Point target() {
					Point p = player.getPos(),
						  g = ghosts.get(0).getPos();
					return new Point(2*p.x-g.x, 2*p.y-g.y);
				}

			});

		ghostTimer.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				for (Ghost g : ghosts) {

					switch (g.calculateDirection()) {
					case 0:
						g.move(ATile.NORTH);
						g.last = 0;
						break;
					case 1:
						g.move(ATile.EAST);
						g.last = 1;
						break;
					case 2:
						g.move(ATile.SOUTH);
						g.last = 2;
						break;
					case 3:
						g.move(ATile.WEST);
						g.last = 3;
						break;
					}
				}

				afterMove();
			}
		});
		ghostTimer.start();

		frame.addKeyListener(playerDirectionListener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("old player direction = "+playerDirection);
				switch (e.getKeyCode()) { 
				case KeyEvent.VK_UP:    case KeyEvent.VK_KP_UP:    case 'W':
					playerDirection = 0;
					break;
				case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT: case 'D':
					playerDirection = 1;
					break;
				case KeyEvent.VK_DOWN:  case KeyEvent.VK_KP_DOWN:  case 'S':
					playerDirection = 2;
					break;
				case KeyEvent.VK_LEFT:  case KeyEvent.VK_KP_LEFT:  case 'A':
					playerDirection = 3;
					break;
				}
				System.out.println("new player direction = "+playerDirection);
			}

			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
			
		});

	}

	public void afterMove() {
		model.gameChanged(); //updates GUI
		model.setTurnPhase(Model.SHIFT_BOARD_PHASE); //allows player to shift board between movements
		if (noTokens())
			endGame(WON);
		if (collisionOccurred())
			endGame(LOST);
	}

	private boolean collisionOccurred() {
		for (Ghost g : ghosts)
			if (player.getPos().equals(g.getPos()))
				return true;
		return false;
	}

	private boolean noTokens() {
		for (int i=0; i<7; i++)
			for (int j=0; j<7; j++)
				if (model.getTile(i,j).hasToken())
					return false;
		return true;
	}

	private void endGame(boolean victory) {	//TODO
		
		playerTimer.stop();
		ghostTimer.stop();
		frame.removeKeyListener(playerDirectionListener);
		
		try {
			Field modelRef = GUI.class.getDeclaredField("_model");
			modelRef.setAccessible(true);
			Field guiRef = Model.class.getDeclaredField("_observer");
			guiRef.setAccessible(true);
			GUI gui = (GUI) guiRef.get(model);
			modelRef.set(gui, new Model(FileIO.TEMP_FILENAME));
//			Method setupFrame = GUI.class.getDeclaredMethod("setupFrame");
//			setupFrame.setAccessible(true);
//			setupFrame.invoke(gui);
			gui.setupFrame();
			gui.update();
		} catch (Throwable e) { e.printStackTrace(); }
		
		cleanup();
	}
	
	public abstract void cleanup();

	private abstract class Ghost {

		private Pawn p;
		private int last;

		private Ghost(Pawn p) {
			this.p = p;
		}

		public abstract Point target();

		public int calculateDirection() {
			Point target = target();
			Point loc = p.getPos();
			
			Point diff = new Point(target.x - loc.x, target.y - loc.y);
			Point absdiff = new Point(Math.abs(diff.x), Math.abs(diff.y));
			if (absdiff.x > absdiff.y)
				if (diff.x > 0 && loc.x != 6 && p.canMove(ATile.EAST) && last != 3)
					return 1;
				else if (diff.x < 0 && loc.x != 0 && p.canMove(ATile.SOUTH) && last != 0)
					return 3;
				else
					if (loc.y != 0)
						return 0;
					else 
						return 2;
			else 
				if (diff.y > 0 && loc.y != 6 && p.canMove(ATile.SOUTH) && last != 0)
					return 2;
				else if (diff.y < 0 && loc.y != 0 && p.canMove(ATile.NORTH) && last != 2)
					return 0;
				else
					if (loc.x != 0 && p.canMove(ATile.WEST) && last != 1)
						return 3;
					else if (p.canMove(ATile.EAST) && last != 3)
						return 1;

			return 0;
		}

		public void move(String direction) {
			p.move(direction);
		}

		public Point getPos() {
			return p.getPos();
		}
	}
}