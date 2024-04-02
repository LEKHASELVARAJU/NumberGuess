package numbergame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessNumber extends JFrame {
	 private static final long serialVersionUID = 1L;
	    private JTextField guessField;
	    private JButton guessButton;
	    private JTextArea outputArea;
	    private Random random;
	    private int randomNumber;
	    private int attemptsLimit = 5; // Set the limit to 5 attempts
	    private int attempts = 0;

	    public GuessNumber() {
	        setTitle("Guess the Number Game");
	        setSize(500, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        guessField = new JTextField(10);
	        guessButton = new JButton("Check");
	        outputArea = new JTextArea();
	        outputArea.setEditable(false);

	        JPanel inputPanel = new JPanel();
	        inputPanel.add(new JLabel("Enter your guess: "));
	        inputPanel.add(guessField);
	        inputPanel.add(guessButton);

	        add(inputPanel, BorderLayout.NORTH);
	        add(new JScrollPane(outputArea), BorderLayout.CENTER);
	        
	        random = new Random();
	        randomNumber = random.nextInt(100) + 1;
	        
	        outputArea.append("Enter your guess between 1 to 100\n");
	        guessButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int userGuess = Integer.parseInt(guessField.getText());
	                guessField.setText("");

	                attempts++;
	                if (userGuess == randomNumber) {
	                    outputArea.append("Congratulations! You guessed the number correctly in " + attempts + " attempts!\n");
	                    int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Congratulations!", JOptionPane.YES_NO_OPTION);
	                    if (option == JOptionPane.YES_OPTION) {
	                        resetGame();
	                        outputArea.setText("");
	                        outputArea.append("Enter your guess between 1 to 100\n");
	                    } else {
	                        System.exit(0);
	                    }
	                } else if (attempts == attemptsLimit) {
	                    outputArea.append("Sorry, you've used all your attempts. The correct number was: " + randomNumber + "\n");
	                    int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Out of Attempts", JOptionPane.YES_NO_OPTION);
	                    if (option == JOptionPane.YES_OPTION) {
	                        resetGame();
	                        outputArea.setText("");
	                        outputArea.append("Enter your guess between 1 to 100\n");
	                    } else {
	                        System.exit(0);
	                    }
	                } else if (userGuess < randomNumber) {
	                    if (randomNumber - userGuess <= 10) {
	                        outputArea.append(userGuess +" -Too low! But you're very close. Try aiming a bit higher!\n");
	                    } else {
	                        outputArea.append(userGuess +" -Too low! You're quite far off. Aim higher!\n");
	                    }
	                } else {
	                    if (userGuess - randomNumber <= 10) {
	                        outputArea.append(userGuess + " -Too high! But you're very close. Try aiming a bit lower!\n");
	                    } else {
	                        outputArea.append(userGuess +" -Too high! You're quite far off. Aim lower!\n");
	                    }
	                }
	            }
	        });

	        
	    }

	    private void resetGame() {
	        attempts = 0;
	        randomNumber = random.nextInt(100) + 1;
	    }
	    
	    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessNumber guessNumber = new GuessNumber();
                guessNumber.setVisible(true);
            }
        });
    }
}
