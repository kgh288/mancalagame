import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GamePanel is the GUI that displays the mancala game.
 * 
 * @author Robin, Gyeongheun, Rabbia
 *
 */
public class GamePanel extends JPanel implements ChangeListener {
	private MancalaModel model;
	private BoardStyle style;
	private JLabel currentPlayerLabel;
	private JLabel undoCountLabel;
	private int restartGame;

	/**
	 * Constructor
	 * 
	 * @param model MancalaModel
	 */
	public GamePanel(MancalaModel model) {
		this.model = model;
		style = model.getStyle();
		restartGame = -1;
		setLayout(null);
		setVisible(true);
		this.addMouseListener(new MancalaMouseListener());

	}

	/**
	 * Paints the mancala game.
	 * 
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g) {
		this.removeAll();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		style = model.getStyle();
		if (style != null) {
			style.draw(g2);

			// need to find better place to draw/add labels. paintComponent isn't the best
			// place for this.
			// Creating them here since on initial construction of GamePanel, BoardStyle is
			// null.
			// Repainting it after style1 or style2 button is pressed instantiates
			// BoardStyle style and draw pits + labels
			Pit[] pits = style.getPits();
			JLabel[] mancalaLabels = new JLabel[pits.length];
			for (int i = 0; i < pits.length; i++) {
				String playerName = model.getData().getPlayerName(i);
				int numStones = model.getData().getNumStones(i);
				int labelX = (int) pits[i].getPit().getX();
				int labelY = (int) (pits[i].getPit().getY() + pits[i].getPit().getHeight());

				if (model.getData().isMancala(i)) {
					mancalaLabels[i] = new JLabel(playerName.substring(0, 1) + "'s M: " + numStones);
				} else
					mancalaLabels[i] = new JLabel(playerName.substring(0, 1) + " : " + numStones);

				mancalaLabels[i].setLocation(labelX, labelY);
				mancalaLabels[i].setSize((int) pits[i].getPit().getWidth(), 20);
				add(mancalaLabels[i]);
			}
			currentPlayerLabel = new JLabel(model.getCurrentPlayer() + "'s Turn");
			currentPlayerLabel.setSize(200, 10);
			currentPlayerLabel.setLocation(mancalaLabels[0].getX(), 0);
			undoCountLabel = new JLabel("Undo Count: " + model.getUndoCount());
			undoCountLabel.setSize(200, 10);
			undoCountLabel.setLocation(mancalaLabels[0].getX(), 0 + currentPlayerLabel.getHeight());
			add(currentPlayerLabel);
			add(undoCountLabel);
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}

	/**
	 * Takes an integer from JOptionPane button press and relays message for user to
	 * restart game.
	 * 
	 * @param confirmDialog int
	 */
	public void userRestartGame(int confirmDialog) {
		if (confirmDialog == 0) {
			JOptionPane.showMessageDialog(GamePanel.this, "Please pick the number of stones and a style.");
			model.setGameStarted(false);
		} else if (confirmDialog == 1)
			JOptionPane.showMessageDialog(GamePanel.this, "Later!");

	}

	/**
	 * MancalaMouseListener is a concrete MouseAdapter class.
	 * 
	 * @author Robin, Gyeongheun, Rabbia
	 *
	 */
	class MancalaMouseListener extends MouseAdapter {
		// need to limit based on turn of player

		@Override
		public void mousePressed(MouseEvent e) {
			if (!model.isNextMove() && style != null) {
				model.setPreviousData();
				int x = e.getX();
				int y = e.getY();
				Pit[] pits = style.getPits();
				int index = 0;
				String currentPlayer = model.getCurrentPlayer();
				// Find the index of clicked pit.
				for (int i = 0; i < pits.length; i++) {
					// If it is current player's turn, mousePressed inside pit, is not mancala, and
					// current pit has stones, index is i
					if (currentPlayer.equals(model.getData().get(i).getPlayer()) && pits[i].contains(x, y) && i != 0
							&& i != 7 && pits[i].getNumStones() != 0) {
						System.out.println("pit " + i + " is clicked");
						index = i;
						model.setNextMove(true);
					}
				}
				if (index != 0 && index != 7) {
					model.setNextMove(model.update(index));
					for (int i = 0; i < pits.length; i++)
						pits[i].setNumStones(model.getData().getNumStones(i));
				}
			}
			int gameFinished = model.gameOver();
			if (gameFinished == 1) {
				System.out.println(model.getData().getPlayerOneScore() + " " + model.getData().getPlayerTwoScore());
				restartGame = JOptionPane.showConfirmDialog(GamePanel.this,
						"Total Score\n    Gyeongheun: " + model.getData().getPlayerOneScore() + "\n    Rabia: "
								+ model.getData().getPlayerTwoScore()
								+ "\nGyeongheun wins!\nWould you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION);
				userRestartGame(restartGame);
			} else if (gameFinished == 2) {
				System.out.println(model.getData().getPlayerOneScore() + " " + model.getData().getPlayerTwoScore());
				restartGame = JOptionPane.showConfirmDialog(GamePanel.this,
						"Total Score\n    Gyeongheun: " + model.getData().getPlayerOneScore() + "\n    Rabia: "
								+ model.getData().getPlayerTwoScore() + "\nRabia wins!\nWould you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION);
				userRestartGame(restartGame);

			} else if (gameFinished == 3) {
				restartGame = JOptionPane.showConfirmDialog(GamePanel.this, "Draw! Would you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION);
				userRestartGame(restartGame);

			}
		}
	}

	class MouseAdapter implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
