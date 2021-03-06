package backend;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	private ArrayList<int[]> possibleSolutions;
	//whether you've won or not 
	private boolean hasWon = false;
	/**
	 * creates a new board of mastermind
	 * @param sequence the secret sequence to start with 
	 * @param totalGuesses the total number of guesses before you lose the game 
	 */
	public Board(int[] secret, int totalGuesses, int rows, int colors) {
		secretGuess = secret;
		numRows = rows;
		numColors = colors;
		currentGuess = new int[numRows];
		numGuesses = totalGuesses;
		currentPegs = new int[numRows];
		possibleSolutions = createCombos(rows, colors);
		
	}
	
	public int[] getSecretGuess() {
		return secretGuess;
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
		currentPegs = getPegs(secretGuess, currentGuess);
	}
	
	/**
	 * returns the black and white pegs 
	 * white peg - 1 - means there is one of the right color but in the wrong place
	 * black peg - 2 - means there is one with the right placement and color
	 * @return an array of 4 ints that represent the small black and white pegs for the current guess
	 */
	public int[] getPegs() {
		return getPegs(secretGuess, currentGuess);
	}
	
	/**
	 * returns the black and white pegs 
	 * white peg - 1 - means there is one of the right color but in the wrong place
	 * black peg - 2 - means there is one with the right placement and color
	 * @param guessOne the guess that the second guess is compared to 
	 * @param guessTwo the guess by the player that we want the pegs from 
	 * @return an array of 4 ints that represent the small black and white pegs for the current guess
	 */
	public int[] getPegs(int[] guessOne, int[] guessTwo) {

		int numBlack = 0;
		int numWhite = 0;
		int[] tempPegs = new int[numRows];

		//go through each color and find matches
		for(int i = 0; i < numColors; i++) {
			int numInSecret = 0;
			int numInGuess = 0;
			int numBlackInGuess = 0;
			//for each position check if there is one of the chosen color in guess or in secret
			for(int j = 0; j < numRows; j++) {
				if(guessOne[j] == i) {
					numInSecret++;
				}
				if(guessTwo[j] == i) {
					numInGuess++;
				}
				if(guessOne[j] == i && guessTwo[j] == i) {
					numBlackInGuess++;
					numBlack++;
				}
			}
			//if there is the same number of this color in both the secret and the guess,
			//that number will be the sum of the black and white pegs for that color
			numWhite += Math.min(numInSecret, numInGuess) - numBlackInGuess;
		}
		
		Arrays.fill(tempPegs, 0);
		//the first numBlack entries will be 2 
		for(int i = 0; i < numBlack; i++) {
			tempPegs[i] = 2;
		}
		
		//afer those first entries the next numWhite entries will be 1 
		for(int i = numBlack; i < numBlack + numWhite; i++) {
			tempPegs[i] = 1;
		}
		
		return tempPegs;
	}
	
	/**
	 * creates an arraylist of all possible combination of pegs for a given number of rows and columns
	 * recursive because why not 
	 * @param numRows number of pegs in a row 
	 * @param numColors number of possible colors
	 * @return the arraylist of all possible combos
	 */
	private ArrayList<int[]> createCombos(int numRows, int numColors) {
		ArrayList<int[]> tempList = new ArrayList<int[]>();
		if (numRows == 0) {  // Just one combination, of size 0
			tempList.add(new int[0]); // Weird, but I need an empty combo
			return tempList;
		}
		else {  // length is greater than 1; use recursion
			ArrayList<int[]> smallCombos = createCombos(numRows - 1,numColors);
			for (int small = 0; small < smallCombos.size(); small++) {
				for (int color = 0; color < numColors; color++) {
					// Build new, larger combo
					int[] tempCombo = new int[numRows];
					
					// copy small combo to new combo, and add color
					for (int k = 0; k < numRows - 1; k++) {
						tempCombo[k] = smallCombos.get(small)[k];
					}
					tempCombo[numRows - 1] = color;
					
					// Add to list of combos
					tempList.add(tempCombo);
				}

			}
			return tempList;	
		}
	}

	/**
	 * eliminates all solutions from possibleSolutions that don't match with the current guess and pegs
	 */
	private void calculateRemainingSolutions() {
		int[] targetPegs = getPegs(secretGuess, currentGuess);
		for(int i = possibleSolutions.size() - 1; i >= 0; i--) {
			int[] solutionPegs = getPegs(possibleSolutions.get(i), currentGuess);
			if(!Arrays.equals(targetPegs, solutionPegs)) {
				possibleSolutions.remove(i);
			}
		}
	}
	
	/**
	 * returns the arraylist of possible solutions that have not been eliminated
	 * @return the arraylist of possible solutions 
	 */
	public ArrayList<int[]> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	/**
	 * returns the number of possible solutions that have not been eliminated by calculateRemainingSolutions
	 * @return the number of possible solutions
	 */
	public int getNumPossibleSolutions() {
		calculateRemainingSolutions();
		return possibleSolutions.size();
	}
	
	/**
	 * method to print out an int[] nicely
	 * @param array the array to print 
	 */
	public void printArray(int [] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
}
