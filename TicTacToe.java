
import java.util.Scanner;

public class TicTacToe {
	private char[][] board; // 3x3 game board
	private char currentPlayer; // current player, either 'X' or 'O'

	// constructor initializes the game
	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		initializeBoard();
	}

	// initialize the game board with empty spaces
	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// print the game board
	public void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// switch the current player
	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// check if the game is over
	public boolean isGameOver() {
		if (checkRows() || checkColumns() || checkDiagonals()) {
			return true;
		} else if (isBoardFull()) {
			System.out.println("It's a tie!");
			return true;
		} else {
			return false;
		}
	}

	// check if any row contains three of the same symbol
	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				System.out.println("Player " + board[i][0] + " wins!");
				return true;
			}
		}
		return false;
	}

	// check if any column contains three of the same symbol
	private boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				System.out.println("Player " + board[0][i] + " wins!");
				return true;
			}
		}
		return false;
	}

	// check if any diagonal contains three of the same symbol
	private boolean checkDiagonals() {
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			System.out.println("Player " + board[0][0] + " wins!");
			return true;
		} else if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			System.out.println("Player " + board[0][2] + " wins!");
			return true;
		}
		return false;
	}

	// check if the game board is full
	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public void nextMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;

		do {
			System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
			row = scanner.nextInt() - 1;
			System.out.print("Player " + currentPlayer + ", enter column (1-3): ");
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));

		board[row][col] = currentPlayer;
		printBoard();
		switchPlayer();
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			System.out.println("Invalid row or column, please try again.");
			return false;
		} else if (board[row][col] != '-') {
			System.out.println("That cell is already occupied, please try again.");
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.printBoard();

		while (!game.isGameOver()) {
			game.nextMove();
		}
	}
}
