package backend;

public class Main {

	static Board board;
	static int[] secretPegs = new int[]{1, 1, 1, 1};
	
	public static void main(String[] args) {
		board = new Board(secretPegs, 5);
		board.addGuess(secretPegs);
		board.addGuess(secretPegs);
		System.out.println(board.currentGuess[1]);
		System.out.println(board.returnPastGuesses()[1][1]);
	}

}
