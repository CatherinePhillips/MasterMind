package backend;

public class Board {

	//initial sequence to set up the game
	private int[] secretGuess;
	private int numGuesses;
	
	//current guess and the pegs from that guess
	private int[] currentGuess;
	private int[] currentPegs;
	
	//number of rows and colors are adjustable 
	private int numRows;
	private int numColors;
	
	//whether you've won or not 
	private boolean hasWon = false;
	/**
	 * creates a new board of mastermind
	 * @param sequence the secret sequence to start with 
	 * @param totalGuesses the total number of guesses before you lose the game 
	 */
	public Board(int[] sequence, int totalGuesses, int rows, int colors) {
		secretGuess = sequence;
		numRows = rows;
		numColors = colors;
		currentGuess = new int[numRows];
		numGuesses = totalGuesses;
		
		currentPegs = new int[numRows];
		
	}
	
	/**
	 * adds a new guess to the board from the player character 
	 * @param guess an array of 4 ints that represent the colors guessed
	 */
	public void addGuess(int[] guess) {
		if(numGuesses > 0) {
			currentGuess = guess;
			numGuesses--;
		} else {
			System.out.println("No more guesses!");
		}
	}
	
	/**
	 * returns the black and white pegs 
	 * white peg - 1 - means there is one of the right color but in the wrong place
	 * black peg - 2 - means there is one with the right placement and color
	 * @return an array of 4 ints that represent the small black and white pegs for the current guess
	 */
	public int[] returnPegs() {

		int numBlack = 0;
		int numWhite = 0;

		//go through each color and find matches
		for(int i = 1; i < numColors; i++) {
			int numInSecret = 0;
			int numInGuess = 0;
			int numBlackInGuess = 0;
			//for each position check if there is one of the chosen color in guess or in secret
			for(int j = 0; j < numRows; j++) {
				if(secretGuess[j] == i) {
					numInSecret++;
				}
				if(currentGuess[j] == i) {
					numInGuess++;
				}
				if(secretGuess[j] == i && currentGuess[j] == i) {
					numBlackInGuess++;
					numBlack++;
				}
			}
			//if there is the same number of this color in both the secret and the guess,
			//that number will be the sum of the black and white pegs for that color
			if(numInSecret == numInGuess) {
				numWhite += numInSecret - numBlackInGuess;
			}
		}
		//the first numBlack entries will be 2 
		for(int i = 0; i < numBlack; i++) {
			currentPegs[i] = 2;
		}
		
		//afer those first entries the next numWhite entries will be 1 
		for(int i = numBlack; i < numBlack + numWhite; i++) {
			currentPegs[i] = 1;
		}
		
		//tells you if you win
		if(isWinner() && !hasWon) {
			System.out.println("You won!");
			hasWon = true;
		}
		
		return currentPegs;
	}
	
	/**
	 * tells you whether you're a winner or not 
	 * (you win if you still have turns left and your pegs are all twos)
	 * @return true if you win, false otherwise 
	 */
	public boolean isWinner() {
		boolean allTwos = true;
		for(int i = 0; i < numRows; i++) {
			if(currentPegs[i] != 2) {
				allTwos = false;
			}
		}
		return (numGuesses > 0 && allTwos);
	}
}
