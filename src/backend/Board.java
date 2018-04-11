package backend;

public class Board {

	//initial sequence to set up the game
	private int[] secretGuess;
	private int numGuesses;
	
	//current guess and array of past guesses
	private int[] currentGuess;
	private int[][] pastGuesses;
	
	//accuracy pegs for current guess and past guesses
	private int[] currentPegs;
	private int[][] pastPegs;
	
	/**
	 * creates a new board of mastermind
	 * @param sequence the secret sequence to start with 
	 * @param totalGuesses the total number of guesses before you lose the game 
	 */
	public Board(int[] sequence, int totalGuesses) {
		secretGuess = sequence;
		currentGuess = new int[4];
		pastGuesses = new int[4][totalGuesses];
		numGuesses = totalGuesses;
		
		currentPegs = new int[4];
		pastPegs = new int[4][totalGuesses];
		
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
		for(int i = 1; i < 7; i++) {
			int numInSecret = 0;
			int numInGuess = 0;
			int numBlackInGuess = 0;
			//for each position check if there is one of the chosen color in guess or in secret
			for(int j = 0; j < 4; j++) {
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
		return currentPegs;
	}
	
	/**
	 * archives the current guess and pegs into the pastGuess/pastPegs arrays 
	 */
	public void archiveCurrentGuess() {
		
			pastGuesses[(pastGuesses[0].length - numGuesses)] = currentGuess;
			pastPegs[(pastPegs[0].length - numGuesses)] = currentPegs;
		
	}

	/**
	 * returns the past guesses 
	 * @return an int array of the past guesses so far 
	 */
	public int[][] returnPastGuesses() {
		return pastGuesses;
	}
	
	/**
	 * returns the past pegs
	 * @return an int array of the past peg values so far 
	 */
	public int[][] returnPastPegs() {
		return pastPegs;
	}
	
}
