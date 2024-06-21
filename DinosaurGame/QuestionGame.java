import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class QuestionGame implements ActionListener {
    
    JFrame f;
    JTextField questionNum, question, showScore, showPercent;
    JButton[] Number = new JButton[4];
    JButton reset;
    JLabel[] option = new JLabel[4];
    JTextField TIMER;
    Font font = new Font("Monospaced", Font.BOLD, 30); 
    Font font2 = new Font("Monospaced", Font.BOLD, 60); 

    int score = 0;
    int currentQuestionIndex = 0;
    int timeLeft = 10;
    Timer timer;
    
    String[] questions = {   
        "1 + 1 = ?",
        "2 ^ 2 = ?",
        "3 * 1 = ?",
        "4 - 3 = ?",
        "5 * 5 = ?",
        "6 / 2 = ?",
        "7 - 7 = ?",
        "8 + 8 - 8 = ?",
        "9 * 9 = ?",
        "3*PI-0.12*sin(65) = ?",
    };

    String[][] options = {  
        {"2", "3", "4", "5"},
        {"4", "6", "8", "10"},
        {"1", "2", "3", "4"},
        {"0", "1", "2", "3"},
        {"25", "30", "35", "40"},
        {"1", "2", "3", "4"},
        {"0", "1", "2", "3"},
        {"6", "7", "8", "9"},
        {"81", "82", "83", "84"},
        {"9.42555","9.32556","9.22557","9.12558"},
    };
    char[] answerKey = {'A', 'A', 'C', 'B', 'A', 'C', 'A', 'C', 'A', 'B'};

    QuestionGame() {
        f = new JFrame("Question Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 700);
        f.getContentPane().setBackground(Color.decode("#2C3E50"));
        f.setLayout(null);
        f.setResizable(false);

        questionNum = new JTextField("Question 1 / "+questions.length);
        questionNum.setBounds(40, 25, 330, 60);
        questionNum.setFont(font);
        questionNum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        questionNum.setEditable(false);
        questionNum.setHorizontalAlignment(JTextField.CENTER);

        question = new JTextField(questions[0]);
        question.setBounds(20, 100, 370, 60);
        question.setFont(new Font("Monospaced", Font.BOLD, 25));
        question.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        question.setEditable(false);
        question.setHorizontalAlignment(JTextField.CENTER);

        showScore = new JTextField();
        showScore.setBounds(100, 300, 200, 60);
        showScore.setFont(font);
        showScore.setForeground(Color.BLACK);
        showScore.setBackground(Color.WHITE);
        showScore.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        showScore.setEditable(false);
        showScore.setHorizontalAlignment(JLabel.CENTER);

        showPercent = new JTextField();
        showPercent.setBounds(100, 360, 200, 60);
        showPercent.setFont(font);
        showPercent.setForeground(Color.BLACK);
        showPercent.setBackground(Color.WHITE);
        showPercent.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        showPercent.setEditable(false);
        showPercent.setHorizontalAlignment(JLabel.CENTER);

        TIMER = new JTextField("10");
        TIMER.setBounds(300, 600, 100, 60);
        TIMER.setFont(font2);
        TIMER.setForeground(Color.BLACK);
        TIMER.setBackground(Color.RED);
        TIMER.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        TIMER.setEnabled(false);
        TIMER.setHorizontalAlignment(JLabel.CENTER);

        String[] choices = {"A", "B", "C", "D"};
        for (int i = 0; i < 4; i++) {
            Number[i] = new JButton(choices[i]);
            Number[i].setBounds(20, 180 + (i * 100), 80, 80);
            Number[i].setFont(font2);
            Number[i].setBackground(Color.decode("#e3e3e3"));
            Number[i].setFocusable(false);
            Number[i].addActionListener(this);
            f.add(Number[i]);
        }
        reset = new JButton("RESET");
        reset.setBounds(75, 500, 250, 60);
        reset.setFont(font);
        reset.setForeground(Color.BLACK);
        reset.setBackground(Color.WHITE);
        reset.setFocusable(false);
        reset.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        reset.setHorizontalAlignment(JLabel.CENTER);
        reset.addActionListener(this);

        for (int i = 0; i < 4; i++) {
            option[i] = new JLabel(options[currentQuestionIndex][i]);
            option[i].setBounds(120, 180 + (i * 100), 500, 80);
            option[i].setFont(new Font("Monospaced", Font.BOLD, 50));
            option[i].setForeground(Color.WHITE);
            f.add(option[i]);
        }

        f.add(reset);
        reset.setVisible(false);
        f.add(showPercent);
        showPercent.setVisible(false);
        f.add(showScore);
        showScore.setVisible(false);
        f.add(question);
        f.add(questionNum);
        f.add(TIMER);
        f.setVisible(true);
        
        startTimer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            resetGame();
        } else {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == Number[i]) {
                    for (JButton button : Number) {
                        button.setEnabled(false);
                    }
                    checkAnswer(i);
                    break;
                }
            }
        }
    }

    private void startTimer() {
        timeLeft = 10;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                TIMER.setText("" + timeLeft);
                TIMER.setForeground(Color.RED);
                if (timeLeft <= 0) {
                    timer.stop();
                    checkAnswer(-1);
                }
            }
        });
        timer.start();
    }

    private void checkAnswer(int choice) {
        timer.stop();
        char answer = answerKey[currentQuestionIndex];
        char selectedOption = ' ';
        switch (choice) {
            case 0: selectedOption = 'A'; break;
            case 1: selectedOption = 'B'; break;
            case 2: selectedOption = 'C'; break;
            case 3: selectedOption = 'D'; break;
        }
        
        if (selectedOption == answer) {
            score++;
            if (choice >= 0) {
                Number[choice].setBackground(Color.GREEN);
            }
        } else {
            if (choice >= 0) {
                Number[choice].setBackground(Color.RED);
            }
        }
        
        Timer delayTimer = new Timer(1000, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    updateQuestion();
                } else {
                    showResult();
                }
            }
        });
        
        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    private void updateQuestion() {
        questionNum.setText("Question " + (currentQuestionIndex + 1) + " / " + questions.length);
        question.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            option[i].setText(options[currentQuestionIndex][i]);
            Number[i].setBackground(Color.decode("#e3e3e3"));
        }
        for (JButton button : Number) {
            button.setEnabled(true);
        }
        startTimer();
    }

    private void showResult() {
        for (JButton button : Number) {
            button.setVisible(false);
        }
        questionNum.setText("Game Over");
        question.setText("RESULT");
        TIMER.setVisible(false);
        for (JLabel label : option) {
            label.setVisible(false);
        }
        showScore.setText("(" + score + " / " + questions.length + ")");
        showPercent.setText((int)((score / (double)questions.length) * 100) + "%");

        showPercent.setVisible(true);
        showScore.setVisible(true);
        showScore.setBackground(Color.decode("#0A6847"));
        reset.setVisible(true);
        reset.setBackground(Color.decode("#E74C3C"));

        TIMER.setText("");
    }
    
    public void resetGame() {
        score = 0;
        currentQuestionIndex = 0;
        questionNum.setText("Question 1 / "+questions.length);
        question.setText(questions[0]);
        for (int i = 0; i < 4; i++) {
            Number[i].setBackground(Color.decode("#e3e3e3"));
            Number[i].setVisible(true);
            option[i].setText(options[0][i]);
            option[i].setVisible(true);
        }
        for (JButton button : Number) {
            button.setEnabled(true);
        }
        TIMER.setText("10");
        TIMER.setVisible(true);
        showScore.setVisible(false);
        showPercent.setVisible(false);
        reset.setVisible(false);
        startTimer();
        
    }
}
