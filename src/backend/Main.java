package backend;

public class Main {

	static Board boardOne;
	static int[] secretGuessOne = {2, 2, 2, 2};
	static int[] boardOneGuessOne = {3, 1, 1, 1};
	static int[] boardOneGuessTwo = {2, 1, 3, 1};
	static int[] boardOneGuessThree = {3, 3, 3, 3};
	static int[] boardOneGuessFour = {1, 2, 2, 2};
	

	public static void main(String[] args) {
		boardOne = new Board(secretGuessOne, 5, 4, 5);

		System.out.println(boardOne.getNumPossibleSolutions());
		boardOne.addGuess(boardOneGuessOne);
		System.out.println(boardOne.getNumPossibleSolutions());
		boardOne.addGuess(boardOneGuessTwo);
		System.out.println(boardOne.getNumPossibleSolutions());
/*		testPegs(boardOne, boardOneGuessOne);
		testPegs(boardOne, boardOneGuessTwo);
		testPegs(boardOne, boardOneGuessThree);
		testPegs(boardOne, boardOneGuessFour);
		testPegs(boardOne, secretGuessOne);
*/	}

	public static void testPegs(Board board, int[] guess) {
		board.addGuess(guess);
		board.printArray(board.getSecretGuess());
		board.printArray(board.getPegs());
		board.printArray(board.getPossibleSolutions());
		System.out.println();
		
	}
	
}
