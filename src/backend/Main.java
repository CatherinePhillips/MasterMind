package backend;

public class Main {

	static Board boardOne;
	static int[] secretPegsOne = {1, 2, 2, 2};
	static int[] boardOneGuessOne = {3, 1, 1, 1};
	static int[] boardOneGuessTwo = {2, 1, 3, 1};
	static int[] boardOneGuessThree = {3, 3, 3, 3};
	static int[] boardOneGuessFour = {2, 2, 2, 2};
	

	public static void main(String[] args) {
		boardOne = new Board(secretPegsOne, 5, 4, 5);

		boardOne.addGuess(boardOneGuessOne);
		printPegs(boardOne);
		
		boardOne.addGuess(boardOneGuessTwo);
		printPegs(boardOne);
		
		boardOne.addGuess(boardOneGuessThree);
		printPegs(boardOne);
		
		boardOne.addGuess(boardOneGuessFour);
		printPegs(boardOne);
		
		boardOne.addGuess(secretPegsOne);
		printPegs(boardOne);
	}

	public static void printPegs(Board board) {
		for(int i = 0; i < 4; i++) {
			System.out.print(board.returnPegs()[i]);
		}
		System.out.println();
		
	}
	
}
