import java.awt.*;
import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Optional BoardStyleOne. Instantiates the style of the game in Robin's
 * style.
 * 
 * @author Robin, Gyeongheun, Rabia
 *
 */
public class BoardStyleTwo implements BoardStyle {
	private MancalaBoard board;
	private Pit[] pits;
	private int x, y, width, height;

	/**
	 * Constructor
	 * 
	 * @param x      int
	 * @param y      int
	 * @param width  int
	 * @param height int
	 * @param data   CircularLinkedList
	 */
	public BoardStyleTwo(int x, int y, int width, int height, CircularLinkedList data) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		board = new MancalaBoard(x, y, width, height);

		pits = new Pit[14];

		// create playerB's Mancala pit.
		pits[0] = new Pit(x + width / 45, y + height / 6, width / 10, 2 * height / 3);

		// create playerA's Mancala pit
		pits[7] = new Pit(x + 7 * width / 45 + width / 45 + 7 * width / 10, y + height / 6, width / 10, 2 * height / 3);

		// create playerA's play pits
		for (int i = 1; i < 7; i++)
			pits[i] = new Pit(x + (i + 1) * width / 45 + (i) * width / 10, y + height / 6 + height / 3 + height / 30,
					width / 10, height / 3 - height / 30);

		// create playerB's play pits
		for (int i = 8; i < 14; i++)
			pits[i] = new Pit(x + (15 - i) * width / 45 + (14 - i) * width / 10, y + height / 6, width / 10,
					height / 3 - height / 30);

		// create number of stones from data model
		for (int i = 0; i < pits.length; i++) {
			pits[i].setNumStones(data.getNumStones(i));
		}

	}

	@Override
	public void draw(Graphics2D g2) {
		// 0E0B16 void 14, 11, 22 need to set background color of frame
		// A239CA fuschia 162, 57, 202
		g2.setStroke(new BasicStroke(4));
		g2.setPaint(new Color(162, 57, 202));
		board.draw(g2);
		// 4717F6 blue purple 71, 23, 246
		g2.setStroke(new BasicStroke(4));
		g2.setPaint(new Color(71, 23, 246));
		// E7DFDD Stark/white 231, 223, 221
		for (int i = 0; i < pits.length; i++) {
			g2.setStroke(new BasicStroke(1));
			g2.setPaint(new Color(71, 23, 246));
			pits[i].draw(g2);
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pit[] getPits() {
		return pits;
	}

	@Override
	public void updatePits(CircularLinkedList data) {
		for (int i = 0; i < pits.length; i++) {
			pits[i].setNumStones(data.getNumStones(i));
		}
	}
}
