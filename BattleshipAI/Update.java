package BattleshipAI;

import javax.swing.JLabel;

/**
 * updates the grid with the ships placed by the player and the AI
 *
 */
public class Update {

	private Grid grid;
	
	/**
	 * initializes the instance Object by the object given in the parameters
	 */
	public Update(Grid grid){
		this.grid = grid;
	}
	
	/**
	 * creates the grid layout of the player and AI by calling on the Grid class
	 */
	public void createBoard(JLabel lblP1, JLabel lblAI) {
		grid.createBoard(lblP1, lblAI);
	}
	
	/**
	 * updates the board with the ships
	 * position, length and rotation are given in by the Player
	 * AI selects the position length and rotation randomly
	 */
	public boolean updateBoard(int position, String rotation, int length, int player) {
		// Setting the Ships According to the Position given by the User.
		if (position > 0 && rotation != null) {

			// Removing the Ship type from the list of Ships

			if (rotation.equals("RIGHT")) {
				for (int i = 0; i < length; i++) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else{
						//grid.setBtnColor(position - 1 + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
			if (rotation.equals("DOWN")) {
				for (int i = 0; i < length * 10; i += 10) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else {
						//grid.setBtnColor(position  + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
			if (rotation.equals("LEFT")) {
				for (int i = 0; i < length; i++) {
					if (player == 1) {
						grid.setLblColor(position - 1 - i);
						grid.addP1Location(position - 1 - i);
					} else {
						//grid.setBtnColor(position - 1  - i);
						grid.addAILocation(position - 1 - i);
					}
				}
				return true;
			}
			if (rotation.equals("UP")) {
				for (int i = 0; i > (length * 10) * -1; i -= 10) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else {
					//	grid.setBtnColor(position - 1 + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
		}
		return false;
	}
}
