import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RandomGame implements ActionListener {

    JFrame f;
    JTextField guessField;
    JButton guessButton, resetButton;
    JLabel promptLabel, feedbackLabel, scoreLabel;
    Random random;
    int targetNumber, attempts;
    Font font = new Font("Monospaced", Font.BOLD, 30); 

    RandomGame() {
        random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;

        f = new JFrame("Number Guessing Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 550);
        f.getContentPane().setBackground(Color.decode("#2C3E50")); 
        f.setLayout(new GridLayout(0, 1, 10, 10));  
        f.setResizable(false);

        promptLabel = new JLabel("Pick Number _ 1 to 100");
        promptLabel.setFont(font);
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        promptLabel.setForeground(Color.WHITE);
        
        guessField = new JTextField();
        guessField.setFont(font);
        guessField.setHorizontalAlignment(SwingConstants.CENTER);
        
        guessButton = new JButton("Guess");
        guessButton.setFont(font);
        guessButton.setBackground(Color.decode("#E74C3C")); 
        guessButton.setForeground(Color.WHITE); 
        guessButton.addActionListener(this);
        guessButton.setFocusable(false);
        
        feedbackLabel = new JLabel("");
        feedbackLabel.setFont(font);
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.GREEN); 
        
        scoreLabel = new JLabel("Attempts: 0");
        scoreLabel.setFont(font);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE); 
        
        resetButton = new JButton("Reset");
        resetButton.setFont(font);
        resetButton.setBackground(Color.decode("#E74C3C")); 
        resetButton.addActionListener(this);
        resetButton.setForeground(Color.WHITE); 
        
        f.add(promptLabel);
        f.add(guessField);
        f.add(guessButton);
        f.add(feedbackLabel);
        f.add(scoreLabel);
        f.add(resetButton);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                scoreLabel.setText("Attempts: " + attempts);
                if (guess < targetNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                    feedbackLabel.setForeground(Color.WHITE);
                } else if (guess > targetNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                    feedbackLabel.setForeground(Color.WHITE);
                } else {
                    feedbackLabel.setText("Congratulations :)");
                    feedbackLabel.setForeground(Color.GREEN);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
                feedbackLabel.setForeground(Color.WHITE);
            }
        } else if (e.getSource() == resetButton) {
            targetNumber = random.nextInt(100) + 1;
            attempts = 0;
            guessField.setText("");
            feedbackLabel.setText("");
            feedbackLabel.setForeground(Color.WHITE);
            scoreLabel.setText("Attempts: 0");
        }
    }
}
