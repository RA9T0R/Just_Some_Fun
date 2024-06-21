import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ChooseGame implements ActionListener {

    JFrame f = new JFrame();
    JLabel l1 = new JLabel("========= Welcome To Dinosaur Arcade =========");
    JLabel l2 = new JLabel("FROM HERE YOU ARE FREE TO CHOOSE YOUR GAME");
    JButton RPS = new JButton("ROCK-PAPER-SCISSORS _ GAME");
    JButton CG = new JButton("CALCULATOR _ GAME");
    JButton QG = new JButton("QUIZ _ GAME");
    JButton RG = new JButton("RANDOM _ GAME");
    JButton XO = new JButton("XO _ GAME");
    JButton EXIT = new JButton("EXIT");

    // Sidebar buttons
    JButton helpButton = new JButton("HELP");
    JButton settingsButton = new JButton("SETTINGS");

    ChooseGame() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1380, 820);
        f.setLayout(new BorderLayout(5, 5));
        f.setResizable(false);
        f.setVisible(true);

        l1.setFont(new Font("Monospaced", Font.BOLD, 40));
        l1.setForeground(Color.white);
        l1.setVerticalAlignment(JLabel.CENTER);
        l1.setHorizontalAlignment(JLabel.CENTER);

        l2.setFont(new Font("Monospaced", Font.BOLD, 40));
        l2.setForeground(Color.white);
        l2.setVerticalAlignment(JLabel.CENTER);
        l2.setHorizontalAlignment(JLabel.CENTER);

        // Adjust button margins to make them smaller while keeping the font size
        Insets buttonMargin = new Insets(5, 10, 5, 10);
        Font buttonFont = new Font("Monospaced", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(500, 100);

        setupButton(RPS, buttonFont, buttonMargin, buttonSize);
        setupButton(CG, buttonFont, buttonMargin, buttonSize);
        setupButton(QG, buttonFont, buttonMargin, buttonSize);
        setupButton(RG, buttonFont, buttonMargin, buttonSize);
        setupButton(XO, buttonFont, buttonMargin, buttonSize);
        setupButton(EXIT, buttonFont, buttonMargin, buttonSize);    
        RPS.setBackground(Color.decode("#2980B9"));  // Bel Air Blue
        CG.setBackground(Color.decode("#1ABC9C"));   // Coastal Green
        QG.setBackground(Color.decode("#8E44AD"));   // Wisteria
        RG.setBackground(Color.decode("#F39C12"));   // Saffron
        XO.setBackground(Color.decode("#D35400"));   // Pumpkin
        EXIT.setBackground(Color.decode("#C0392B")); // Monza Red

        // Sidebar buttons
        Insets sidebarMargin = new Insets(2, 5, 2, 5);
        Font sidebarFont = new Font("Monospaced", Font.BOLD, 25);
        Dimension sidebarSize = new Dimension(100, 100);
        setupButton(settingsButton, sidebarFont, sidebarMargin, sidebarSize);
        setupButton(helpButton, sidebarFont, sidebarMargin, sidebarSize);
        settingsButton.setBackground(Color.decode("#0A6847"));
        helpButton.setBackground(Color.decode("#E74C3C"));

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(l1, BorderLayout.CENTER);

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());    
        p2.add(helpButton, BorderLayout.CENTER);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());     
        p3.add(settingsButton, BorderLayout.CENTER);

        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        p4.add(l2, BorderLayout.CENTER);

        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 65));
        p5.add(RPS);
        p5.add(CG);
        p5.add(QG);
        p5.add(RG);
        p5.add(XO);
        p5.add(EXIT);

        p1.setBackground(Color.decode("#2C3E50")); // Dark Blue Grey
        p2.setBackground(Color.decode("#34495E")); // Pickled Bluewood
        p3.setBackground(Color.decode("#34495E")); // Pickled Bluewood
        p4.setBackground(Color.decode("#2C3E50")); // Dark Blue Grey
        p5.setBackground(Color.decode("#ECF0F1")); // Whitesmoke

        p1.setPreferredSize(new Dimension(100, 100));
        p2.setPreferredSize(new Dimension(150, 100));
        p3.setPreferredSize(new Dimension(150, 100));
        p4.setPreferredSize(new Dimension(100, 100));
        p5.setPreferredSize(new Dimension(100, 100));

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.WEST);
        f.add(p3, BorderLayout.EAST);
        f.add(p4, BorderLayout.SOUTH);
        f.add(p5, BorderLayout.CENTER);
    }

    private void setupButton(JButton button, Font font, Insets margin, Dimension size) {
        button.setFont(font);
        button.setMargin(margin);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setForeground(Color.decode("#f2f2f2"));
        if (size != null) {
            button.setPreferredSize(size);
            button.setMaximumSize(size);
            button.setMinimumSize(size);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == EXIT) {
            System.exit(0);
            f.dispose();
        } else if (e.getSource() == CG) {
            new Calculator();
        } else if (e.getSource() == RPS) {
            new RockPaperScissors();
        } else if (e.getSource() == QG) {
            new QuestionGame();
        } else if (e.getSource() == RG) {
            new RandomGame();
        } else if (e.getSource() == XO) {
            new XOGame();
        } else if (e.getSource() == helpButton) {
            JOptionPane.showMessageDialog(f, "Help: Choose a game and have fun!");
        } else if (e.getSource() == settingsButton) {
            JOptionPane.showMessageDialog(f, "Settings: Adjust your preferences here.");
        }
    }
    public static void main(String[] args) {
        new ChooseGame();
    }
}
