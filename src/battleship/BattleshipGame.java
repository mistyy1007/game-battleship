package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rui Yang
 *
 */

public class BattleshipGame {

	private int askForInput(Scanner input, String rowCol, int limit) {
		int coordinate;
		do {
			try {
				do {
					System.out.print(rowCol);
					coordinate = input.nextInt();
					if (coordinate < 0 || coordinate > limit - 1)
						System.err.println("Beyond the scope - please enter a number between 0 - " + limit + ".");
				} while (coordinate < 0 || coordinate > limit - 1);
				return coordinate;
			} catch (Exception ex) {
				System.err.println("Invalid answer - please enter a number between 0 - " + limit + ".");
				input.nextLine();
			}
		} while (true);
	}

	private Position getValidInput(Scanner input, int limit) {
		int row;
		int column;
		System.out.println("Where do you want to fire (x,y)?");
		row = askForInput(input, "x/row = ", limit);
		column = askForInput(input, "y/column = ", limit);
		System.out.println();
		return new Position(row, column);
	}

	void run() {
		List<String> replies = new ArrayList<>();
		replies.add("Yes");
		replies.add("yes");
		replies.add("y");

		int limit;
		String newGame;
		Scanner input = new Scanner(System.in);

		do {
			Ocean o = new Ocean();
			limit = o.getShipArray().length;

			o.placeAllShipsRandomly();

			System.out.println();
			o.print();

			do {
				Position p = getValidInput(input, limit);
				System.out.println(displayOutcome(o, p));
				o.print();
			} while (!o.isGameOver());

			System.out.println(displayEndGame(o));

			newGame = input.next();
		} while (replies.contains(newGame));
	}

	private String displayOutcome(Ocean o, Position p) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		sb.append("\n");
		if (o.shootAt(p.getRow(), p.getColumn())) {
			if (o.getShipArray()[p.getRow()][p.getColumn()].isSunk()) {
				sb.append("You just sunk a " + o.getShipArray()[p.getRow()][p.getColumn()].getShipType()
						+ "! Well done.");
			} else {
				sb.append("A hit at x = " + p.getRow() + ", y = " + p.getColumn() + ". Well done");
			}
		} else {
			sb.append("A miss at x = " + p.getRow() + ", y = " + p.getColumn() + ". Try again.");
		}
		sb.append("\n");
		sb.append("You have shot for " + o.shotsFired + " times");
		sb.append("\n");
		sb.append("You have hit for " + o.hitCount + " times");
		sb.append("\n");
		sb.append("You have sunk " + o.getShipsSunk() + " ship" + "s");
		sb.append("\n");
		sb.append("\n");

		sb.append("_ is empty sea\n. is a miss\nS is a hit\nx is a sunken ship\n");

		return sb.toString();
	}

	private static String displayEndGame(Ocean o) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nCongratulations, the game is over\n");
		sb.append("You sunk the fleet in ").append(o.getShotsFired()).append(" shots.\n");
		sb.append("\nDo you want to play again (Yes or No)?");

		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BattleshipGame bsg = new BattleshipGame();
		bsg.run();
	}
}
