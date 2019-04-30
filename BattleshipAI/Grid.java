package BattleshipAI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Creates the grid for the player and the AI Allows the Player and AI to place
 * the ships checks for winner
 */
public class Grid {

	private ArrayList<JButton> btns = new ArrayList<>();
	private ArrayList<JLabel> lbls = new ArrayList<>();
	private ArrayList<Integer> shipsAI = new ArrayList<>();
	private ArrayList<Integer> shipsP1 = new ArrayList<>();
	private AI comp;
	private JButton btnPlaceShips;
	private JLabel lblInfo;

	/**
	 * creates an object for the AI class sets instance variables
	 * label that holds the button
	 */
	public Grid(JButton btn, JLabel lbl) {
		comp = new AI(this);
		this.btnPlaceShips = btn;
		this.lblInfo = lbl;
	}

	/**
	 * creates 100 labels for the player board and 100 buttons for the AI board
	 * sets the color to the labels and buttons adds the labels in one arraylist
	 * and buttons in another
	 */
	public void createBoard(JLabel lblP1, JLabel lblAI) {
		// Creating a grid of buttons and labels
		for (int i = 0; i < 100; i++) {
			lbls.add(new JLabel(String.valueOf(i + 1)));
			lbls.get(i).setBackground(Color.cyan);
			lbls.get(i).setOpaque(true);
			lblP1.add(lbls.get(i));

			btns.add(new JButton());
			btns.get(i).setBackground(Color.cyan);
			lblAI.add(btns.get(i));
			btns.get(i).setEnabled(false);
			btns.get(i).addActionListener(new HitListener(i));
		}

	}

	/**
	 * returns a specific label from the arraylist
	 */
	public JLabel getLbl(int i) {

		return lbls.get(i);
	}

	/**
	 * changes the color of a specified label to gray
	 */
	public void setLblColor(int i) {
		lbls.get(i).setBackground(Color.gray);
	}

	/**
	 * changes the color of a specified button to gray
	 */
	public void setBtnColor(int a) {
		btns.get(a).setBackground(Color.gray);
	}

	/**
	 * adds the location of where the AI has placed it's ships
	 */
	public void addAILocation(int a) {
		shipsAI.add(a);
	}

	/**
	 * adds the location of where the player has placed their ships
	 */
	public void addP1Location(int a) {
		shipsP1.add(a);
	}

	/**
	 * returns the size of the arraylist that stored the location of the
	 * player's ships
	 */
	public int getP1ShipSize() {
		return shipsP1.size();
	}

	/**
	 * returns a specified button from the arraylist
	 */
	public JButton getBtn(int i) {

		return btns.get(i);
	}

	/**
	 * hides the "Place Ships" button and tells the player that the game has
	 * begun
	 */
	public void removePlaceShipsBtn() {
		btnPlaceShips.setVisible(false);
		lblInfo.remove(btnPlaceShips);
		JOptionPane.showMessageDialog(null,
				"The Game has begun!\nClick on the Boxes on the Right to Fire at the Computer!");
	}

	/**
	 * disables all buttons from the AI grid
	 */
	public void disableBtns() {
		for (int i = 0; i < btns.size(); i++) {
			btns.get(i).setEnabled(false);
		}
	}

	/**
	 * enables all buttons from the AI grid
	 */
	public void enableBtns() {
		for (int i = 0; i < btns.size(); i++) {
			btns.get(i).setEnabled(true);
		}
	}

	/**
	 * checks if there is a ship in a specific location
	 */
	public boolean getShipLocation(int location) {

		for (int a : shipsAI) {
			if (a == location) {
				return true;
			}
		}

		return false;
	}

	/**
	 * what happenes when a button from the AI grid is clicked
	 */
	private class HitListener implements ActionListener {

		private int i;

		/**
		 * sets an instance variable which is the location of a button in the arraylist
		 */
		public HitListener(int i) {
			this.i = i;
		}

		/**
		 * checks if there is a ship in the specified location given in the
		 * parameter
		 */
		public boolean ship(int location) {

			for (int element : shipsAI) {
				if (element == location) {
					return true;
				}
			}

			return false;
		}

		/**
		 * if there is a ship where the button was clicked the color changes to
		 * red if there isnt a ship, it changes to blue AI makes its move
		 * Decides what happens if player has won
		 */
		public void actionPerformed(ActionEvent event) {

			if (ship(i)) {
				btns.get(i).setBackground(Color.red);
				btns.get(i).setEnabled(false);
			} else {
				btns.get(i).setBackground(Color.blue);
				btns.get(i).setEnabled(false);
			}

			try {
				comp.missile();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (playerWin()) {
				JOptionPane.showMessageDialog(null, "Player 1 Wins!\nPress OK to Quit");
				System.exit(0);
			}
		}
	}

	/**
	 * checks if the player has won
	 */
	public boolean playerWin() {
		int count = 0;
		for (JButton element : btns) {
			if (element.getBackground() == Color.red) {
				count++;
			}
		}
		if (count == 16) {
			return true;
		}
		return false;
	}
}
