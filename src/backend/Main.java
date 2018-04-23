package backend;

public class Main {

	static Board board;
	static int[] secretPegs = {1, 2, 1, 1};
	static int[] guessOne = {2, 1, 1, 1};
	static int[] guessTwo = {2, 1, 3, 1};

	public static void main(String[] args) {
		board = new Board(secretPegs, 5);

		board.addGuess(guessOne);
		System.out.println(board.returnPegs());

		board.addGuess(guessTwo);
		System.out.println(board.returnPegs());
	}

}
