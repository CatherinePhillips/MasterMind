package backend;

public class Main {

	static Board board;
	static int[] secretPegs = {1, 2, 1, 1};
	static int[] guessOne = {2, 1, 1, 1};
	
	public static void main(String[] args) {
		board = new Board(secretPegs, 5);
		
		board.addGuess(guessOne);
		for(int i = 0; i<4; i++) {
			System.out.println(board.returnPegs()[i]);
			}
		System.out.println("/n");
		board.archiveCurrentGuess();
		
		board.addGuess(secretPegs);
		for(int i = 0; i<4; i++) {
			System.out.println(board.returnPegs()[i]);
			}
		System.out.println("/n");
		board.archiveCurrentGuess();
		
		for(int i = 0; i<4; i++) {
		System.out.println(board.returnPastGuesses()[1][i]);
		}
		for(int i = 0; i<4; i++) {
			System.out.println(board.returnPastGuesses()[2][i]);
			}
	}

}
