import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
public class MasterMindGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMindGUI window = new MasterMindGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}

	/**
	 * Create the application.
	 */
	public MasterMindGUI(){
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private boolean debug = true;
	private int counter = 0;
	
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 18));
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 1474, 718);
		frame.setTitle("MasterMind");
		
		ImageIcon BlackPeg = new ImageIcon("BLACK.gif");
		MyIcon WhitePeg = new MyIcon();
		ImageIcon NoPeg = new ImageIcon("NOCOLOR.gif");

		String numRows = JOptionPane.showInputDialog("How many rows? (up to 6)");
		String numColors = JOptionPane.showInputDialog("How many colors? (up to 6)");
		int rows = Integer.parseInt(numRows);
		int colors = Integer.parseInt(numColors);

		Random rand = new Random();
		int[] sequence = new int[rows];
		if (debug){
			System.out.print("sequence: ");
		}
		for (int i = 0; i < rows; i ++){
			int num = rand.nextInt(colors);
			sequence[i] = num;
			if (debug){
				System.out.print(num);
			}
		}

		Board board = new Board(sequence, 10, rows, colors); 
		MyComboBox[][] comboBoxes = new MyComboBox[10][rows];
		MyComboBox[] winBoxes = new MyComboBox[rows];
		JLabel[][] pegs = new JLabel[10][rows];

		JLabel numGuesses = new JLabel("Number of possible guesses remaining: " + board.returnNumPossibleSolutions());
		numGuesses.setBounds(906, 520, 513, 80);
		numGuesses.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		frame.getContentPane().add(numGuesses);

		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(938, 212, 132, 127);
		guessButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		frame.getContentPane().add(guessButton);
		guessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] currentGuess = new int[rows];
				if (debug){
					System.out.println("");
					System.out.print("guess: ");
				}
				for (int i = 0; i < comboBoxes[counter].length; i ++){
					int num = comboBoxes[counter][i].getSelectedIndex() - 1; // fix for colors 0-numColors
					currentGuess[i] = num;
					comboBoxes[counter][i].setEnabled(false);
					if (counter < 9){
						comboBoxes[counter + 1][i].setEnabled(true);
					}
					if (debug){
						System.out.print(num + " ");
					}
				}
				if (debug){
					System.out.println("");
				}
				board.addGuess(currentGuess);
				int[] currentPegs = board.returnPegs();
				boolean win = true;
				if (debug){
					System.out.print("pegs: ");
				}
				for (int i = 0; i < currentPegs.length; i ++){ // if all 2, then win 
					if (currentPegs[i] == 2){
						pegs[counter][i].setIcon(BlackPeg);
					} else {
						win = false;
						if (currentPegs[i] == 1){
							pegs[counter][i].setIcon(WhitePeg);
						}
					}
					if (debug){
						System.out.print(currentPegs[i] + " ");
					}
				}
				if (debug){
					System.out.println("");
				}
				if (win){
					for (int i = 0; i < comboBoxes.length; i ++){ // disable all boxes
						for (int j = 0; j < rows; j ++){
							comboBoxes[i][j].setEnabled(false);
						}
					}
					guessButton.setEnabled(false);
					for (int i = 0; i < rows; i ++){ // display the sequence
						winBoxes[i].setVisible(true);
						winBoxes[i].setEnabled(false);
						winBoxes[i].setSelectedIndex(sequence[i] + 1);
					}

					Icon youWin = new ImageIcon("youwin.gif");
					Icon fireworks = new ImageIcon("Fireworks.gif");
					Icon confetti = new ImageIcon("confetti.gif");
					
					JLabel lblNewLabel_50 = new JLabel(youWin);
					lblNewLabel_50.setBounds(938, 42, 300, 154);
					frame.getContentPane().add(lblNewLabel_50);
					
					JLabel lblNewLabel_51 = new JLabel(fireworks);
					lblNewLabel_51.setBounds(938, 355, 314, 119);
					frame.getContentPane().add(lblNewLabel_51);

					JLabel lblNewLabel_52 = new JLabel(confetti);
					lblNewLabel_52.setBounds(1085, 201, 167, 158);
					frame.getContentPane().add(lblNewLabel_52);

					numGuesses.setText("Number of possible guesses remaining: 0");
					JOptionPane.showMessageDialog(frame, "You won!");
				} else {
				numGuesses.setText("Number of possible guesses remaining: " + board.returnNumPossibleSolutions());
				counter ++;
				}
			}
		});
		
		ImageIcon[] icons = 
		{
			 new ImageIcon("NOCOLOR.gif"), // Fully transparent GIF
	         new ImageIcon("RED.gif"),
	         new ImageIcon("GREEN.gif"),
	         new ImageIcon("BLUE.gif"),
	         new ImageIcon("YELLOW.gif"),
	         new ImageIcon("BLACK.gif"),
	         new ImageIcon("WHITE.gif")
		};
		
		ImageIcon[] iconColors = new ImageIcon[colors + 1]; // colors + 1 to include the default transparent one
		for (int i = 0; i < iconColors.length; i ++){
			iconColors[i] = icons[i];
		}
		
		for (int i = 0; i < 10; i ++){ // make the boxes
			for (int j = 0; j < rows; j ++){
				MyComboBox combobox = new MyComboBox();
				DefaultComboBoxModel model = new DefaultComboBoxModel(iconColors);
				combobox.setModel(model);
				comboBoxes[i][j] = combobox;
				combobox.setBounds(48 + 85 * i, 114 + 85 * j, 71, 71);
				combobox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				frame.getContentPane().add(combobox);
				
				JLabel peg = new JLabel(NoPeg);
				pegs[i][j] = peg;
				peg.setBounds(50 + 85 * i, 200 + 85 * (rows - 1) + 24 * j, 69, 20);
				frame.getContentPane().add(peg);
			}
		}
		
		for (int i = 1; i < comboBoxes.length; i ++){ // disable all boxes except the first row
			for (int j = 0; j < rows; j ++){
				comboBoxes[i][j].setEnabled(false);
			}
		}

		
		for (int i = 0; i < rows; i ++){ // make win boxes
			MyComboBox winBox = new MyComboBox();
			winBoxes[i] = winBox;
			winBox.setBounds(1267, 114 + 85 * i, 71, 71);
			winBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			frame.getContentPane().add(winBox);
			winBox.setVisible(false);
		}

	}
}
