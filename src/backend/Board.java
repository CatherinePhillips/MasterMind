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
	public Board(int[] sequence, int totalGuesses, int rows, int colors) {
		secretGuess = sequence;
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
		currentPegs = returnPegs();
	}
	
	/**
	 * returns the black and white pegs 
	 * white peg - 1 - means there is one of the right color but in the wrong place
	 * black peg - 2 - means there is one with the right placement and color
	 * @return an array of 4 ints that represent the small black and white pegs for the current guess
	 */
	public int[] returnPegs() {
		return returnPegs(secretGuess, currentGuess);
	}
	
	/**
	 * returns the black and white pegs 
	 * white peg - 1 - means there is one of the right color but in the wrong place
	 * black peg - 2 - means there is one with the right placement and color
	 * @param guessOne the guess that the second guess is compared to 
	 * @param guessTwo the guess by the player that we want the pegs from 
	 * @return an array of 4 ints that represent the small black and white pegs for the current guess
	 */
	public int[] returnPegs(int[] guessOne, int[] guessTwo) {

		int numBlack = 0;
		int numWhite = 0;
		int[] tempPegs = new int[numRows];

		//go through each color and find matches
		for(int i = 1; i < numColors; i++) {
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
			if(numInSecret <= numInGuess) {
				numWhite += numInSecret - numBlackInGuess;
			} else {
				numWhite += numInGuess - numBlackInGuess;
			}
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
	
	public ArrayList<int[]> createCombos(int numRows, int numColors) {
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

	public void printCombo(int [] c)
	{
		for (int i = 0; i < c.length; i++)
			System.out.print(c[i]+" ");
		System.out.println();
	}
	
	public void calculateRemainingSolutions() {
		int[] targetPegs = returnPegs(secretGuess, currentGuess);
		for(int i = possibleSolutions.size() - 1; i >= 0; i--) {
			int[] solutionPegs = returnPegs(possibleSolutions.get(i), currentGuess);
//			printCombo(targetPegs);
//			printCombo(solutionPegs);
//			System.out.println();
			if(!Arrays.equals(targetPegs, solutionPegs)) {
				possibleSolutions.remove(i);
			}
		}
	}
	
	public ArrayList<int[]> returnPossibleSolutions() {
		return possibleSolutions;
	}
	
	public int returnNumPossibleSolutions() {
		calculateRemainingSolutions();
		return possibleSolutions.size();
	}
}
