import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class XOGame implements ActionListener {

    JFrame f;
    JButton[] numberButtons = new JButton[9];
    JButton resetButton;
    JPanel p;
    JTextField t;
    JLabel l;
    Font font = new Font("Monospaced", Font.BOLD, 30); 
    Font font2 = new Font("Monospaced", Font.BOLD, 80); 

    XOGame() {
        f = new JFrame("XO - Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 600);
        f.getContentPane().setBackground(Color.decode("#2C3E50"));
        f.setLayout(null);
        f.setResizable(false);

        t = new JTextField("TIC_TAC_TOE_GAME");
        t.setBounds(40, 25, 330, 60);
        t.setFont(font);
        t.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        t.setEditable(false);
        t.setHorizontalAlignment(JTextField.CENTER);

        l = new JLabel("O - TURN");
        l.setFont(font);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setForeground(Color.GREEN); 
        l.setBounds(40, 425, 330, 60);

        p = new JPanel();
        p.setBounds(50, 100, 300, 300);
        p.setLayout(new GridLayout(3, 3, 10, 10));
        p.setBackground(Color.decode("#2C3E50"));

        for (int i = 0; i < 9; i++) {
            numberButtons[i] = new JButton();
            numberButtons[i].setFocusable(false);
            numberButtons[i].setFont(font2);
            numberButtons[i].setBackground(Color.decode("#e3e3e3"));
            numberButtons[i].addActionListener(this);
            p.add(numberButtons[i]);
        }

        resetButton = new JButton("Reset");
        resetButton.setBounds(130, 500, 150, 50);
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        f.add(t);
        f.add(p);
        f.add(l);
        f.add(resetButton);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
        } else {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == numberButtons[i]) {
                    if (numberButtons[i].getText().equals("")) {
                        if (l.getText().equals("O - TURN")) {
                            numberButtons[i].setText("O");
                            numberButtons[i].setForeground(Color.red);
                            l.setText("X - TURN");
                            checkWin();
                        } else if (l.getText().equals("X - TURN")) {
                            numberButtons[i].setText("X");
                            numberButtons[i].setForeground(Color.decode("#42b883"));
                            l.setText("O - TURN");
                            checkWin();
                        }
                        break;
                    }
                }
            }
        }
    }

    public void checkWin() {
        // Check rows, columns, and diagonals
        if (checkThree(numberButtons[0], numberButtons[1], numberButtons[2]) || // Row 1
            checkThree(numberButtons[3], numberButtons[4], numberButtons[5]) || // Row 2
            checkThree(numberButtons[6], numberButtons[7], numberButtons[8]) || // Row 3
            checkThree(numberButtons[0], numberButtons[3], numberButtons[6]) || // Column 1
            checkThree(numberButtons[1], numberButtons[4], numberButtons[7]) || // Column 2
            checkThree(numberButtons[2], numberButtons[5], numberButtons[8]) || // Column 3
            checkThree(numberButtons[0], numberButtons[4], numberButtons[8]) || // Diagonal 1
            checkThree(numberButtons[2], numberButtons[4], numberButtons[6])) { // Diagonal 2
            // We have a winner
            t.setText("Player " + (l.getText().equals("X - TURN") ? "O" : "X") + " Wins!");
            disableButtons();
        } else if (isBoardFull()) {
            t.setText("It's a Tie!");
            l.setText("--- END GAME ---");
        }
    }

    public boolean checkThree(JButton b1, JButton b2, JButton b3) {
        if (!b1.getText().equals("") && b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText())) {
            b1.setBackground(Color.green);
            b2.setBackground(Color.green);
            b3.setBackground(Color.green);
            l.setText("--- END GAME ---");
            
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (JButton button : numberButtons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void disableButtons() {
        for (JButton button : numberButtons) {
            button.setEnabled(false);
        }
    }

    public void resetGame() {
        for (JButton button : numberButtons) {
            button.setText("");
            button.setBackground(Color.decode("#e3e3e3"));
            button.setEnabled(true);
        }
        l.setText("O - TURN");
        t.setText("TIC_TAC_TOE_GAME");
    }
    public static void main(String[] args) {
        new XOGame();
    }
}
