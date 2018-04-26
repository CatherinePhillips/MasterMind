package backend;

public class Main {

	static Board board;
	static int[] secretPegs = {1, 2, 1, 1, 1};
	static int[] guessOne = {2, 1, 1, 1, 1};
	static int[] guessTwo = {2, 1, 3, 1, 1};

	public static void main(String[] args) {
		board = new Board(secretPegs, 5, 5, 5);

		board.addGuess(guessOne);
		for(int i = 0; i < 5; i++) {
			System.out.print(board.returnPegs()[i]);
		}
		System.out.println();
		
		board.addGuess(guessTwo);
		for(int i = 0; i < 5; i++) {
			System.out.print(board.returnPegs()[i]);
		}
		System.out.println();
		
		board.addGuess(secretPegs);
		for(int i = 0; i < 5; i++) {
			System.out.print(board.returnPegs()[i]);
		}
		System.out.println();
	}

}
