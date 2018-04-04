package backend;

public class Board {

	public int[] secretPegs;
	public int[] currentGuess;
	public int[][] pastGuesses;
	public int numGuesses;
	
	public Board(int[] sequence, int totalGuesses) {
		secretPegs = sequence;
		pastGuesses = new int[3][totalGuesses];
		numGuesses = totalGuesses;
		
	}
	
	public void addGuess(int[] guess) {
		for(int i=0; i<4; i++) {
			pastGuesses[i][(pastGuesses[i].length - numGuesses)] = currentGuess[i];
		}
		currentGuess = guess;
		numGuesses--;
	}
	
	public int[][] returnPastGuesses() {
		return pastGuesses;
	}
	
}
