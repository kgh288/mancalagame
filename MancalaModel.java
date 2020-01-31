import java.util.ArrayList;
import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Mancala Data Model. Holds relevant data required for game to operate.
 * @author Robin, Gyeongheun, Rabia
 *
 */
public class MancalaModel {
	private ArrayList<BoardStyle> styles;
	private CircularLinkedList data;
	private CircularLinkedList previousData;
	private BoardStyle currentStyle;
	private boolean nextMove;
	private String playerOne;
	private String playerTwo;
	private String currentPlayer;
	private int undoCount;
	private boolean gameStarted;
	private ArrayList<ChangeListener> view;

	/**
	 * Constructor
	 * 
	 * @param playerOne String
	 * @param playerTwo String
	 * @param numStones int
	 */
	public MancalaModel(String playerOne, String playerTwo, int numStones) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		styles = new ArrayList<BoardStyle>();
		data = new CircularLinkedList(playerOne, playerTwo, numStones);
		previousData = new CircularLinkedList(data);
		view = new ArrayList<ChangeListener>();
		nextMove = false;
		undoCount = 3;
		Random rand = new Random();

		if (rand.nextBoolean())
			currentPlayer = playerOne;
		else
			currentPlayer = playerTwo;
	}

	/**
	 * Adds a style to list of styles for game.
	 * 
	 * @param toAdd BoardStyle
	 */
	public void addStyle(BoardStyle toAdd) {
		styles.add(toAdd);
	}

	/**
	 * Sets currentStyle of game to requested BoardStyle at index.
	 * 
	 * @param index int
	 */
	public void setStyle(int index) {
		currentStyle = styles.get(index);
	}

	/**
	 * Returns the currentStyle of game.
	 * 
	 * @return currentStyle BoardStyle
	 */
	public BoardStyle getStyle() {
		return currentStyle;
	}

	/**
	 * Gets current state/data of game.
	 * 
	 * @return data CircularLinkedList
	 */
	public CircularLinkedList getData() {
		return data;
	}

	/**
	 * Sets the previous state of the game to current state of game.
	 */
	public void setPreviousData() {
		previousData = new CircularLinkedList(data);
	}

	/**
	 * This sets the current state of the game to the previous state of the game.
	 * Meant for undo button.
	 */
	public void undoData() {
		if (!data.areIdentical(previousData)) {
			data = previousData;
		}
	}

	/**
	 * Listener of model. Adds another ChangeListener to ArrayList<ChangeListener>
	 * in model.
	 * 
	 * @param toAdd ChangeListener
	 */
	public void attach(ChangeListener toAdd) {
		view.add(toAdd);
	}

	/**
	 * Updates the data model to move the stones at index n of mancala. Returns
	 * whether or not it is the next player's turn.
	 * 
	 * @param index int
	 * @return isNextTurn of game
	 */
	public boolean update(int index) {
		boolean isNextTurn = data.moveStones(index); 

		for (ChangeListener listener : view) {
			listener.stateChanged(new ChangeEvent(this));
		}
		return isNextTurn;
	}

	/**
	 * Accessor for whether or not it's next player's turn.
	 * 
	 * @return nextMove.
	 */
	public boolean isNextMove() {
		return nextMove;
	}

	/**
	 * Mutator for setMove.
	 * 
	 * @param bool
	 */
	public void setNextMove(boolean bool) {
		nextMove = bool;
	}

	/**
	 * Decrements undo count.
	 */
	public void decrementUndo() {
		if (undoCount > 0)
			undoCount--;

	}

	/**
	 * Mutator for undo count, resets back to 3.
	 */
	public void resetUndoCount() {
		undoCount = 3;
	}

	/**
	 * Accessor for undoCount;
	 * 
	 * @return undoCount int
	 */
	public int getUndoCount() {
		return undoCount;
	}

	/**
	 * Undo Action for button pressed. Resets data to previousData, decrements
	 * undoCount, resets nextMove to false;
	 */
	public void undoPressed() {
		undoData();
		decrementUndo();
		nextMove = false;
	}

	/**
	 * Accessor for currentPlayer's name.
	 * 
	 * @return currentPlayer.
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the currentPlayer to the next player. Resets undo count and nextMove.
	 */
	public void nextPlayer() {
		setNextMove(false);
		if (currentPlayer.equals(playerOne))
			currentPlayer = playerTwo;
		else
			currentPlayer = playerOne;
		previousData = data;
		resetUndoCount();
	}

	/**
	 * Creates a new data model with an initial number of stones.
	 * 
	 * @param num int
	 */
	public void setNumStones(int num) {
		data = new CircularLinkedList(playerOne, playerTwo, num);
	}

	/**
	 * Mutator for gameStarted. Sets it to true once game has started.
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	/**
	 * Accessor for gameStarted.
	 * 
	 * @return gameStarted boolean
	 */
	public boolean getGameStarted() {
		return gameStarted;
	}
	
	/**
	 * Checks whether or not the game is over when the players' pits are empty.
	 * @return true if players' pits are empty.
	 */
	public int gameOver() {
		
		return data.checkPlayerPitsEmpty();
	}

	/**
	 * Accessor for player one's name.
	 * @return playerOne String
	 */
	public String getPlayerOne() {
		return playerOne;
	}
	
	/**
	 * Accessor for player two's name.
	 * @return playerTwo String
	 */
	public String getPlayerTwo() {
		return playerTwo;
	}
}
