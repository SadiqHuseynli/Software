package BattleshipAI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Sets the layout and adds buttons and labels to the layout
 */
public class BattleshipPanel extends JPanel {

	private JButton btnPlay;
	private JButton btnPlaceShips = new JButton("Place Ships");
	private JLabel lblP1;
	private JLabel lblInfo = new JLabel();
	private JLabel lblAI;
	private JLabel lblTitle;
	private JLabel lblPlayer1;
	private JLabel lblComp;
	private Grid grid;
	private Ships ship;
	private AI comp;
	private Update update;

	/**
	 * sets the layout, creates buttons and labels and adds them into the panel
	 */
	public BattleshipPanel() {

		// Setting Layout,color,font, and size of the Grid
		setLayout(new GridLayout(1, 3, 5, 5));
		setBackground(new Color(20, 50, 60));
		setPreferredSize(new Dimension(2000, 500));
		setFont(new Font("Arial", Font.BOLD, 12));

		// Creating Buttons
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ButtonListener());

		btnPlaceShips.addActionListener(new ButtonListener());

		// Creating Labels
		lblTitle = new JLabel("Battleship");
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 80));
		lblTitle.setBackground(Color.darkGray);
		lblTitle.setForeground(Color.green);
		lblTitle.setOpaque(true);

		// setting the font and color for a label
		lblPlayer1 = new JLabel();
		lblPlayer1.setFont(new Font("Magneto", Font.BOLD, 60));
		lblPlayer1.setForeground(Color.white);

		// setting the font and color for a label
		lblComp = new JLabel();
		lblComp.setFont(new Font("Magneto", Font.BOLD, 60));
		lblComp.setForeground(Color.white);

		// setting the details of the label
		lblP1 = new JLabel();
		lblP1.setLayout(new GridLayout(10, 10, 1, 1)); // Creating a grid layout
														// inside a label
		lblP1.setVisible(false);

		// adding components to the label
		lblInfo.setLayout(new GridLayout(4, 1, 5, 5)); // Creating a grid layout
														// inside a label
		lblInfo.add(lblTitle);
		lblInfo.add(lblPlayer1);
		lblInfo.add(lblComp);
		lblInfo.add(btnPlay);

		// setting the details of the label
		lblAI = new JLabel();
		lblAI.setLayout(new GridLayout(10, 10, 1, 1)); // Creating a grid layout
														// inside a label
		lblAI.setVisible(false);

		// Adding the components to the Panel
		add(lblP1);
		add(lblInfo);
		add(lblAI);

		// Initializing objects
		grid = new Grid(btnPlaceShips, lblInfo);
		update = new Update(grid);
		ship = new Ships(grid);
		comp = new AI(grid);

	}

	/**
	 * Removes the "Place Ships" button
	 */
	public void removeShipBtn() {
		lblInfo.remove(btnPlaceShips);
	}

	/**
	 * decides what happens when each button is clicked
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * creates the player and AI board AI places its ships
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == btnPlay) {
				lblPlayer1.setText("<--You");
				lblComp.setText("Computer -->");

				update.createBoard(lblP1, lblAI);

				lblP1.setVisible(true);
				lblAI.setVisible(true);

				comp.placeShips();

				lblInfo.remove(btnPlay);
				lblInfo.add(btnPlaceShips);

			}

			// Player places the ships on the player board
			if (event.getSource() == btnPlaceShips) {
				ship.selectShips(1, 0);
			}

		}

	}

}
