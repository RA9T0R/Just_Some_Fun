import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RockPaperScissors implements ActionListener {

    JFrame f;
    JButton[] choiceButtons = new JButton[3];
    JButton resetButton;
    JPanel p;
    JTextField t, resultField;
    JLabel playerLabel, computerLabel, playerScoreLabel, computerScoreLabel;
    Font font = new Font("Monospaced", Font.BOLD, 30);
    Font font2 = new Font("Monospaced", Font.BOLD, 57);
    Random random = new Random();
    int playerScoreValue = 0, computerScoreValue = 0;

    RockPaperScissors() {
        f = new JFrame("Rock Paper Scissors Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 700);
        f.getContentPane().setBackground(Color.decode("#2C3E50"));
        f.setLayout(null);
        f.setResizable(false);

        t = new JTextField("Rock Paper Scissors GAME");
        t.setBounds(40, 25, 330, 60);
        t.setFont(new Font("Monospaced", Font.BOLD, 20));
        t.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        t.setEditable(false);
        t.setHorizontalAlignment(JTextField.CENTER);

        resultField = new JTextField("");
        resultField.setBounds(30, 380, 350, 60);
        resultField.setFont(font);
        resultField.setBorder(BorderFactory.createLineBorder(Color.magenta, 3));
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);

        playerLabel = new JLabel("PLAYER : ");
        playerLabel.setFont(font);
        playerLabel.setHorizontalAlignment(JLabel.CENTER);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setBounds(50, 250, 330, 60);

        computerLabel = new JLabel("COMPUTER : ");
        computerLabel.setFont(font);
        computerLabel.setHorizontalAlignment(JLabel.CENTER);
        computerLabel.setForeground(Color.WHITE);
        computerLabel.setBounds(50, 300, 330, 60);

        playerScoreLabel = new JLabel("Player Score : 0");
        playerScoreLabel.setFont(font);
        playerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        playerScoreLabel.setForeground(Color.WHITE);
        playerScoreLabel.setBounds(50, 440, 340, 60);

        computerScoreLabel = new JLabel("Computer Score : 0");
        computerScoreLabel.setFont(font);
        computerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        computerScoreLabel.setForeground(Color.WHITE);
        computerScoreLabel.setBounds(40, 490, 350, 60);

        p = new JPanel();
        p.setBounds(50, 125, 300, 100);
        p.setLayout(new GridLayout(1, 3, 10, 10));
        p.setBackground(Color.decode("#2C3E50"));

        String[] choices = {"👊", "✋", "✌"};
        for (int i = 0; i < 3; i++) {
            choiceButtons[i] = new JButton(choices[i]);
            choiceButtons[i].setFocusable(false);
            choiceButtons[i].setFont(font2);
            choiceButtons[i].setBackground(Color.decode("#e3e3e3"));
            choiceButtons[i].setForeground(Color.BLACK);
            choiceButtons[i].addActionListener(this);
            p.add(choiceButtons[i]);
        }

        resetButton = new JButton("Reset");
        resetButton.setBounds(120, 570, 180, 50);
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.decode("#3498db"));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);

        f.add(computerScoreLabel);
        f.add(playerScoreLabel);
        f.add(resultField);
        f.add(p);
        f.add(t);
        f.add(playerLabel);
        f.add(computerLabel);
        f.add(resetButton);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
        } else {
            int computerChoice = random.nextInt(3);
            String computerMove = getMove(computerChoice);
            String playerMove = "";

            for (int i = 0; i < 3; i++) {
                if (e.getSource() == choiceButtons[i]) {
                    playerMove = getMove(i);
                    break;
                }
            }

            String result = getResult(playerMove, computerMove);
            updateScores(result);

            playerLabel.setText("PLAYER : " + playerMove);
            computerLabel.setText("COMPUTER : " + computerMove);
            resultField.setText(result);
        }
    }

    private String getMove(int index) {
        switch (index) {
            case 0: return "Rock";
            case 1: return "Paper";
            case 2: return "Scissor";
            default: return "";
        }
    }

    private String getResult(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "- IT'S A TIE -";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissor")) ||
                   (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                   (playerMove.equals("Scissor") && computerMove.equals("Paper"))) {
            return "YOU WIN :)";
        } else {
            return "YOU LOSE :(";
        }
    }

    private void updateScores(String result) {
        if (result.equals("YOU WIN :)")) {
            playerScoreValue++;
        } else if (result.equals("YOU LOSE :(")) {
            computerScoreValue++;
        }
        playerScoreLabel.setText("Player Score : " + playerScoreValue);
        computerScoreLabel.setText("Computer Score : " + computerScoreValue);
    }

    private void resetGame() {
        playerScoreValue = 0;
        computerScoreValue = 0;
        playerScoreLabel.setText("Player Score : 0");
        computerScoreLabel.setText("Computer Score : 0");
        playerLabel.setText("PLAYER : ");
        computerLabel.setText("COMPUTER : ");
        resultField.setText("");
    }
    public static void main(String[] args) {
        new RockPaperScissors();
    }
}
