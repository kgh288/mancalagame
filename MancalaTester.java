import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MancalaTester {

	public static void main(String[] args) {
		final int BOARD_WIDTH = 600;
		final int BOARD_HEIGHT = 300;
		int x = 25, y = 25;

		JComboBox combo = new JComboBox();
		combo.addItem(3);
		combo.addItem(4);
		int numStones = (int) combo.getSelectedItem();

		String playerOne = "Gyeongheun";
		String playerTwo = "Rabia";

		// create mancaladatamodel with initial number of stones.
		MancalaModel model = new MancalaModel(playerOne, playerTwo, numStones);
		
		BoardStyle styleOne = new BoardStyleOne(x, y, BOARD_WIDTH, BOARD_HEIGHT, model.getData());
		BoardStyle styleTwo = new BoardStyleTwo(x, y, BOARD_WIDTH, BOARD_HEIGHT, model.getData());
		model.addStyle(styleOne);
		model.addStyle(styleTwo);

		JFrame frame = new JFrame();
		frame.setSize(BOARD_WIDTH + 100, BOARD_HEIGHT + 200);
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		GamePanel centerPanel = new GamePanel(model);
		JPanel bottomPanel = new JPanel();

		model.attach(centerPanel);

		// bottons for bottomPanel (maybe should make a BottomPanel class and add all
		// the bottons there)
		JButton undo = new JButton("Undo");
		JButton nextTurn = new JButton("Next Turn");

		undo.addActionListener((ActionEvent e) -> {
			System.out.println("Undo clicked (not finished)");
			if (model.getUndoCount() > 0) {
				model.undoPressed();
				model.getStyle().updatePits(model.getData());
				centerPanel.repaint();
			} else 
				JOptionPane.showMessageDialog(frame, "No more undos left!");
			
		});

		nextTurn.addActionListener((ActionEvent e) -> {
			System.out.println("Next turn clicked (not finished)");

			if (model.isNextMove()) {
				model.nextPlayer();
				centerPanel.repaint();
			}
			else
				JOptionPane.showMessageDialog(frame, "Bonus move for " + model.getCurrentPlayer() + "!");

		});

		bottomPanel.add(undo);
		bottomPanel.add(nextTurn);

		JButton style1 = new JButton("Style1");
		JButton style2 = new JButton("Style2");

		style1.addActionListener((ActionEvent e) -> {
			System.out.println("style 1 is clicked");
			if (!model.getGameStarted()) {
				model.setNumStones((int) combo.getSelectedItem());
				model.setGameStarted(true);
			}
			styleOne.updatePits(model.getData());
			model.setStyle(0);
			centerPanel.repaint();
		});

		style2.addActionListener((ActionEvent e) -> {
			System.out.println("style 2 is clicked");
			if (!model.getGameStarted()) {
				model.setNumStones((int) combo.getSelectedItem());
				model.setGameStarted(true);
			}
			styleTwo.updatePits(model.getData());
			model.setStyle(1);
			centerPanel.repaint();
		});
		
		
		

		topPanel.add(combo);
		topPanel.add(style1);
		topPanel.add(style2);

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}


}
