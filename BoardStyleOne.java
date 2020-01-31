import java.awt.*;
import java.awt.geom.*;

/**
 * Optional BoardStyleOne. Instantiates the style of the game in Gyeongheun's
 * style.
 * 
 * @author Robin, Gyeongheun, Rabia
 *
 */
public class BoardStyleOne implements BoardStyle {

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
	public BoardStyleOne(int x, int y, int width, int height, CircularLinkedList data) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		board = new MancalaBoard(x, y, width, height, width / 20);
		pits = new Pit[14];

		// Mancala Pits, playerB then playerA
		pits[0] = new Pit(x + 5, y + height * 3 / 25, width * 6 / 50, height * 19 / 25);
		pits[7] = new Pit(x + width * 44 / 50 - 5, y + height * 3 / 25, width * 6 / 50, height * 19 / 25);

		// Player A's pits
		pits[13] = new Pit(x + width * 7 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);
		pits[12] = new Pit(x + width * 13 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);
		pits[11] = new Pit(x + width * 19 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);
		pits[10] = new Pit(x + width * 26 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);
		pits[9] = new Pit(x + width * 32 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);
		pits[8] = new Pit(x + width * 38 / 50, y + height / 25 * 5, width * 5 / 50, width * 5 / 50);

		// Player B's pits
		pits[6] = new Pit(x + width * 38 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);
		pits[5] = new Pit(x + width * 32 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);
		pits[4] = new Pit(x + width * 26 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);
		pits[3] = new Pit(x + width * 19 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);
		pits[2] = new Pit(x + width * 13 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);
		pits[1] = new Pit(x + width * 7 / 50, y + height / 25 * 15, width * 5 / 50, width * 5 / 50);

		// create number of stones from data model
		for (int i = 0; i < pits.length; i++) {
			pits[i].setNumStones(data.getNumStones(i));
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < pits.length; i++)
			pits[i].draw(g2);
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(7));
		board.draw(g2);

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
