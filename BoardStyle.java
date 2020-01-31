import java.awt.Graphics2D;

/**
 * BoardStyle Interface
 * @author Robin, Gyeongheun, Rabia
 *
 */
public interface BoardStyle {

	/**
	 * Draws the style.
	 * @param g2
	 */
	void draw(Graphics2D g2);	
	
	/**
	 * Returns X value of the style being drawn.
	 * @return x
	 */
	int getX();
	
	/**
	 * Returns the Y value of the style being drawn.
	 * @return y
	 */
	int getY();
	
	/**
	 * Returns the width value of the style being drawn.
	 * @return width
	 */
	int getWidth();
	
	/**
	 * Returns the height value of the style being drawn.
	 * @return height
	 */
	int getHeight();
	
	/**
	 * Returns the pits of the style being drawn.
	 * @return pits[] 
	 */
	Pit[] getPits();
	
	/**
	 * Updates the number of stones in the pits.
	 * @param data CircularLinkedList
	 */
	void updatePits(CircularLinkedList data);
}
