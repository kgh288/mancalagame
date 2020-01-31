import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Stone class depicts the stones for the Mancala Game.
 * 
 * @author Robin, Gyeongheun, Rabbia
 *
 */
public class Stone {
	private Ellipse2D stone;

	/**
	 * Constructor
	 * 
	 * @param x     int
	 * @param y     int
	 * @param width int
	 */
	public Stone(int x, int y, int width) {
		stone = new Ellipse2D.Double(x, y, width, width);
	}

	/**
	 * Draws the stone.
	 * 
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2) {
		g2.fill(stone);
	}

}
