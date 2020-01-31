//import Project.CircularLinkedList.Node;

/**
 * Circular linked list to hold data for Mancala. Took some implementation from
 * https://algorithms.tutorialhorizon.com/circular-linked-list-complete-implementation/
 * 
 * @author Robin, Gyeongheun, Rabia
 *
 */
public class CircularLinkedList {
	public int size = 0;
	public Node head = null;
	public Node tail = null;

	/**
	 * Copy constructor.
	 * 
	 * @param newData CircularLinkedList
	 */
	public CircularLinkedList(CircularLinkedList newData) {
		for (int i = 0; i < newData.getSize(); i++) {
			Node temp = newData.get(i);
			addNode(temp.player, temp.data, temp.isMancala);
		}
		setAdjacent();
	}

	/**
	 * Constructor
	 * 
	 * @param player1        String
	 * @param player2        String
	 * @param numberOfStones int
	 */
	public CircularLinkedList(String player1, String player2, int numberOfStones) {
		// HEAD NODE, add mancala for player2
		addNode(player2, 0, true);

		// add player pits for player1
		for (int i = 0; i < 6; i++)
			addNode(player1, numberOfStones, false);

		// add mancala for player1
		addNode(player1, 0, true);

		// add player pits for player2
		for (int i = 0; i < 6; i++)
			addNode(player2, numberOfStones, false);

		setAdjacent();
	}

	/**
	 * Checks if all nodes are empty, i.e. if all the players' pits have no stones
	 * in them.
	 * 
	 * @return 3 if empty and scores are same, 1 if playerOne's score is higher, 2
	 *         if playerTwo's score is higher. 0 if they are not empty.
	 */
	public int checkPlayerPitsEmpty() {
		if ((get(1).data == 0 && get(2).data == 0 && get(3).data == 0 && get(4).data == 0 && get(5).data == 0
				&& get(6).data == 0)
				|| (get(8).data == 0 && get(9).data == 0 && get(10).data == 0 && get(11).data == 0 && get(12).data == 0
						&& get(13).data == 0)) {
			if (getPlayerOneScore() == getPlayerTwoScore())
				return 3;
			else if (getPlayerOneScore() > getPlayerTwoScore())
				return 1;
			else
				return 2;
		}

		return 0;
	}

	/**
	 * Sum up playerOne's score and return.
	 * 
	 * @return playerOneScore
	 */
	public int getPlayerOneScore() {
		return get(7).data + get(1).data + get(2).data + get(3).data + get(4).data + get(5).data + get(6).data;
	}

	/**
	 * Sum up playerTwo's score and return.
	 * 
	 * @return playerTwoScore
	 */
	public int getPlayerTwoScore() {
		return get(0).data + get(8).data + get(9).data + get(10).data + get(11).data + get(12).data + get(13).data;
	}

	/**
	 * Returns head node.
	 * 
	 * @return head Node
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Returns tail node.
	 * 
	 * @return tail Node
	 */
	public Node getTail() {
		return tail;
	}

	/**
	 * Gets size of circular linked list.
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds a node to the end of the list.
	 * 
	 * @param player    String
	 * @param data      int
	 * @param isMancala boolean
	 */
	public void addNode(String player, int data, boolean isMancala) {
		Node n = new Node(player, data, isMancala);
		if (size == 0) {
			head = n;
			tail = n;
			n.next = head;
		} else {
			tail.next = n;
			tail = n;
			tail.next = head;
		}
		size++;
	}

	/**
	 * Set adjacents for mancala data model.
	 */
	public void setAdjacent() {
		get(1).adjacent = get(13);
		get(13).adjacent = get(1);

		get(2).adjacent = get(12);
		get(12).adjacent = get(2);

		get(3).adjacent = get(11);
		get(11).adjacent = get(3);

		get(4).adjacent = get(10);
		get(10).adjacent = get(4);

		get(5).adjacent = get(9);
		get(9).adjacent = get(5);

		get(6).adjacent = get(8);
		get(8).adjacent = get(6);
	}

	/**
	 * Gets node at index n;
	 * 
	 * @param index int
	 * @return node Node
	 */
	public Node get(int index) {
		if (index > size) {
			return null;
		}
		Node n = head;
		while (index != 0) {
			n = n.next;
			index--;
		}
		return n;
	}

	/**
	 * Checks if node at index is a mancala.
	 * @param index int
	 * @return true of node.isMancala
	 */
	public boolean isMancala(int index) {
		if (index > size) {
			return false;
		}
		Node n = head;
		while (index != 0) {
			n = n.next;
			index--;
		}
		return n.isMancala;
	}

	/**
	 * Gets the number of stones at the current node.
	 * 
	 * @param index of CircularLinkedList
	 * @return node.data
	 */
	public int getNumStones(int index) {
		if (index > size) {
			return 0;
		}
		Node n = head;
		while (index != 0) {
			n = n.next;
			index--;
		}
		return n.data;
	}

	/**
	 * Returns the player's name at index n.
	 * 
	 * @param index int
	 * @return node.player String
	 */
	public String getPlayerName(int index) {
		if (index > size) {
			return null;
		}
		Node n = head;
		while (index != 0) {
			n = n.next;
			index--;
		}
		return n.player;
	}

	/**
	 * Prints the circular linked list. Used for debugging.
	 */
	public void print() {
		System.out.println("Circular Linked List:");
		Node temp = head;
		if (size <= 0) {
			System.out.print("List is empty");
		} else {
			do {
				System.out.println(temp + " " + temp.player + " " + temp.data + " " + temp.isMancala + " "
						+ temp.adjacent + " " + temp.next);
				temp = temp.next;
			} while (temp != head);
		}
		System.out.println();
	}

	/**
	 * Move the integer data from the node at index n into next nodes until data = 0
	 * based on mancala rules. Returns true if it is next player's turn or false if it still current turn.
	 * 
	 * @param index of CircularLinkedList
	 * @return next player's turn
	 */
	public boolean moveStones(int index) {
		// It will always be the next player's turn unless last stone is put in your
		// mancala.
		boolean nextPlayerTurn = true;

		// Get number of stones to move, get name of player to make sure you don't put
		// stones into opposite player's mancala pit, then set current stones at
		// selected pit index to 0
		Node currentNode = get(index);
		int stoneCount = currentNode.data;
		String currentPlayer = currentNode.player;
		currentNode.data = 0;

		// Create a loop here to add a stone to correct pits until stoneCount is 0.
		while (stoneCount > 0) {
			Node nextNode = currentNode.next;
			// If on the last stone to be distributed, the next pit is the currentPlayer's
			// pit and has no stones, take nextNode's adjacent node's stones + the 1 stone
			// and add to currentPlayer's mancala
			if (stoneCount == 1 && !nextNode.isMancala && nextNode.data == 0 && currentPlayer.equals(nextNode.player)) {
				if (currentPlayer.equals(get(0).player)) {
					stoneCount--;
					get(0).data++;
					get(0).data += nextNode.adjacent.data;
					nextNode.adjacent.data = 0;
				}
				if (currentPlayer.equals(get(7).player)) {
					stoneCount--;
					get(7).data++;
					get(7).data += nextNode.adjacent.data;
					nextNode.adjacent.data = 0;
				}
				// Need a break here, otherwise loop will go once more, making stoneCount = -1.
				// and making nextNode still += 1
				break;
			}

			// If nextNode is a pit and not a mancala, drop a stone
			if (!nextNode.isMancala) {
				stoneCount--;
				nextNode.data++;
			}
			// If nextNode is a mancala and the current player's mancala, drop a stone.
			if (nextNode.isMancala && currentPlayer.equals(nextNode.player)) {
				if (stoneCount == 1)
					nextPlayerTurn = false;
				stoneCount--;
				nextNode.data++;
			}

			currentNode = nextNode;
		}
		System.out.println(nextPlayerTurn);
		return nextPlayerTurn;

	}
	

	private boolean areIdenticalRecur(Node a, Node b, int recurCount) {
		// If both lists are empty
		if (a == null && b == null)
			return true;

		// If both lists are not empty, then data of
		// current nodes must match, and same should
		// be recursively true for rest of the nodes.
		if (a != null && b != null && recurCount < 14) {
			recurCount++;
			return (a.compareTo(b) == 0) && areIdenticalRecur(a.next, b.next, recurCount);

		}
		// If we reach here, then one of ths lists
		// is empty and other is not
		return false;
	}

	/*
	 * Returns true if linked lists a and b are identical, otherwise false.
	 */
	public boolean areIdentical(CircularLinkedList listb) {
		return areIdenticalRecur(this.head, listb.head, 0);
	}

	/**
	 * Node class for game.
	 * @author Robin, Gyeongheun, Rabbia
	 *
	 */
	class Node implements Comparable<Node> {
		private String player;
		private int data;
		private boolean isMancala;
		private Node next;
		private Node adjacent;

		/**
		 * Constructor for Node.
		 * @param player String
		 * @param data int
		 * @param isMancala boolean
		 */
		public Node(String player, int data, boolean isMancala) {
			this.player = player;
			this.data = data;
			this.isMancala = isMancala;
		}

		/**
		 * Accessor for player's name of node.
		 * @return player String
		 */
		public String getPlayer() {
			return player;
		}

		/**
		 * Mutator for player's name of node.
		 * @param player String
		 */
		public void setPlayer(String player) {
			this.player = player;
		}

		/**
		 * Accessor for number of stones/data in node.
		 * @return data int
		 */
		public int getData() {
			return data;
		}

		/**
		 * Mutator for number of stones/data in Node.
		 * @param data int
		 */
		public void setData(int data) {
			this.data = data;
		}

		/**
		 * Accessor for node isMancala.
		 * @return isMancala boolean
		 */
		public boolean isMancala() {
			return isMancala;
		}

		/**
		 * Mutator for whether node isMancala.
		 * @param isMancala boolean
		 */
		public void setMancala(boolean isMancala) {
			this.isMancala = isMancala;
		}

		@Override
		public int compareTo(Node o) {
			if (player == o.getPlayer() && data == o.getData() && isMancala == o.isMancala())
				return 0;
			return -1;
		}

	}

}
