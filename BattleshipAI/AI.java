package BattleshipAI;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

/**
 * Code for AI
 * Decides how the AI should make It's move
 * checks if the AI has won
 */
public class AI {

	private Grid grid;
	private Random rand = new Random();
	private ArrayList<Integer> lblHit = new ArrayList<>();
	private int randNum = 0;
	private int totalHits = 0;
	private boolean hit;

	private boolean goDown;
	private boolean goUp;
	private boolean goRight;
	private boolean goLeft;

	private int countUp = 0;
	private int countDown = 0;

	/**
	 * initializes the Grid object by the given object in the parameteres
	 */
	public AI(Grid grid) {
		this.grid = grid;
	}

	/**
	 * AI placed all 5 ships one by one
	 */
	public void placeShips() {
		Ships ship = new Ships(grid);
		for (int i = 0; i < 5; i++) {
			ship.selectShips(2, i);
		}
	}

	/**
	 * decides where the AI should shoot
	 * decides what to do if the AI wins
	 */
	public void missile(){

		if (!hit && !goDown && !goUp && !goRight && !goLeft) {
			getRand();
		} else if (hit && !goDown && !goUp && !goRight && !goLeft) {
			goDown();
		} else if (goDown && hit) {
			goDown();
		} else if (goDown && !hit) {
			goUp();
		} else if (goUp && hit) {
			goUp();
		} else if (goUp && !hit) {
			while (grid.getLbl(randNum).getBackground() != Color.red) {
				randNum += 10;
			}
			goRight();
		} else if (goRight && hit) {
			goRight();
		} else if (goRight && !hit) {
			goLeft();
		} else if (goLeft && hit) {
			goLeft();
		} else if (goLeft && !hit) {
			getRand();
		} else {
			getRand();
		}

		if (randNum >= 100 || randNum < 0) {
			getRand();
		}
		lblHit.add(randNum);
		checkHit();

		if(AIWin()){
			JOptionPane.showMessageDialog(null, "Aww The Computer Won!\nPress OK to Quit");
			System.exit(0);
		}
	}
	
	/**
	 * checks if the AI won
	 */
	public boolean AIWin() {
		if(totalHits == 16){
			return true;
		}
		return false;
	}
	
	/**
	 * decides what happens when the player hits a ship and what happens when the player misses
	 */
	public void checkHit() {
		if (randNum >= 100) {
			goLeft();
		}
		if(randNum<0){
			goRight();
		}
		if (grid.getLbl(randNum).getBackground() == Color.gray) {
			grid.getLbl(randNum).setBackground(Color.red);
			totalHits++;
			hit = true;
		} else if (grid.getLbl(randNum).getBackground() == Color.cyan) {
			grid.getLbl(randNum).setBackground(Color.blue);
			hit = false;
		}
	}

	/**
	 * gets a random position for the AI to hit
	 */
	public void getRand() {
		goLeft = false;
		goUp = false;
		goRight = false;
		goDown = false;
		while (!validNum(randNum)) {
			randNum = rand.nextInt(100);
		}
	}

	/**
	 * if the AI hits a position where there is a part of a ship, it will go down
	 */
	public void goDown() {
		goDown = true;
		goUp = false;
		goRight = false;
		goLeft = false;

		while (!validNum(randNum)) {

			countDown++;
			if (countDown > 100) {
				goLeft();
				break;
			}
			if (randNum >= 100) {
				randNum-=10;
				goUp();
				break;
			}
			if (randNum >= 0 && randNum < 100) {
				randNum += 10;
			}

		}

	}

	/**
	 * AI will hit above the previous hit location
	 * if the AI missed while it was going down, it will go upwards
	 */
	public void goUp() {
		goUp = true;
		goDown = false;
		goRight = false;
		goLeft = false;

		while (!validNum(randNum)) {
			countUp++;
			if (countUp > 100) {
				goRight();
				break;
			}
			if (randNum >= 0 && randNum < 100) {
				randNum -= 10;
			}
			if (randNum < 0) {
				randNum += 10;
				goRight();
				break;
			}
		}
	}

	/**
	 * the AI will hit on the right of the previously hit position
	 * if it missed while going up, it will hit to the right
	 */
	public void goRight() {
		goDown = false;
		goLeft = false;
		goRight = true;
		goUp = false;

		while (!validNum(randNum)) {
			if (randNum >= 0 && randNum < 100) {
				randNum += 1;
			}
			if (randNum >= 100) {
				goLeft();
			}
		}
	}

	/**
	 * the AI will hit left of the previously hit target
	 * if the AI missed while going right, it will hit left
	 */
	public void goLeft() {
		goLeft = true;
		goRight = false;
		goUp = false;
		goDown = false;

		while (!validNum(randNum)) {
			if (randNum >= 0) {
				randNum -= 1;
			}
			if (randNum < 0) {
				getRand();
			}
		}

	}

	/**
	 * checks if the position selected by the AI is valid
	 */
	public boolean validNum(int randNum) {

		for (int i : lblHit) {
			if (i == randNum) {
				return false;
			}
		}
		if (randNum > 100 || randNum < 0) {
			return false;
		}

		return true;
	}

}
