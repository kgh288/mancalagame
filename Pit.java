import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Pit of the Mancala Game.
 * 
 * @author Robin
 *
 */
public class Pit {
	private int numStones;
	private Ellipse2D pit;

	/**
	 * Constructor
	 * 
	 * @param x      int
	 * @param y      int
	 * @param width  int
	 * @param height int
	 */
	public Pit(int x, int y, int width, int height) {
		pit = new Ellipse2D.Double(x, y, width, height);
		this.numStones = 0;
	}

	/**
	 * Draws the pit and randomized stones within the pit based on how numStones it
	 * has.
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		g2.draw(pit);
		for (int i = 0; i < numStones; i++) {
			Random rand = new Random();
			int stoneWidth = (int) pit.getWidth() / 5;
			// Make random angle
			double randomAngle = rand.nextDouble() * 2 * Math.PI;
			// Set radius slightly smaller than actual width and height to encompass
			// stoneWidth
			double randomWidthRadius = rand.nextDouble() * pit.getWidth() / 2.5;
			double randomHeightRadius = rand.nextDouble() * pit.getHeight() / 2.5;
			int randomX = (int) (pit.getCenterX() + Math.cos(randomAngle) * randomWidthRadius - stoneWidth / 2);
			int randomY = (int) (pit.getCenterY() + Math.sin(randomAngle) * randomHeightRadius - stoneWidth / 2);
			Stone stone = new Stone(randomX, randomY, stoneWidth);
			stone.draw(g2);
		}
	}

	/**
	 * Mutator for number of stones in pit.
	 * @param numStones int
	 */
	public void setNumStones(int numStones) {
		this.numStones = numStones;
	}

	/**
	 * Accessor for number of stones in pit.
	 * @return numStones int
	 */
	public int getNumStones() {
		return numStones;
	}

	/**
	 * Checks if input x and y values are within the pit.
	 * @param x int
	 * @param y int
	 * @return whether or not the x and y are contained in pit.
	 */
	public boolean contains(double x, double y) {
		return pit.contains(x, y);
	}

	/**
	 * Accessor for Ellispe2D of pit. 
	 * @return pit Ellipse2D
	 */
	public Ellipse2D getPit() {
		return pit;
	}
}
