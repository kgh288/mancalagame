import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * MancalaBoard frame of the game.
 * @author Robin, Gyeongheun, Rabia
 *
 */
public class MancalaBoard {

	private RoundRectangle2D board;

	/**
	 * Constructor
	 * @param x int
	 * @param y int
	 * @param width int
	 * @param height int
	 */
	public MancalaBoard(int x, int y, int width, int height) {
		board = new RoundRectangle2D.Double(x, y, width, height, 50, 50);
	}

	/**
	 * Alternate Constructor to accept round value.
	 * @param x int
	 * @param y int
	 * @param width int
	 * @param height int
	 * @param round int
	 */
	public MancalaBoard(int x, int y, int width, int height, int round) {
		board = new RoundRectangle2D.Double(x, y, width, height, round, round);
	}

	/**
	 * Draws the MancalaBoard (Rectangle2D)
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2) {
		g2.draw(board);
	}

}
