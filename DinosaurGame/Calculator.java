import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator implements ActionListener{

    JFrame f;
    JTextField t;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton , negButton;
    JPanel p;

    Font myFont = new Font("Monospaced", Font.BOLD, 30);

    double num1=0,num2=0,result=0;
    char operator;

    Calculator(){
        f = new JFrame("Calculator_Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420,550);
        f.setLayout(null);
        f.setResizable(false);
        
        t = new JTextField();
        t.setBounds(50,25,300,50);
        t.setFont(myFont);
        t.setBorder(BorderFactory.createLineBorder(Color.red,3));
        f.getContentPane().setBackground(Color.decode("#2C3E50"));
        t.setEditable(false);
        
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("⬅");
        clrButton = new JButton("C");
        clrButton.setForeground(Color.red);
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for(int i = 0 ; i < 9 ; i++)  { 
            functionButtons[i].addActionListener(this); 
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            if(i!=7) {functionButtons[i].setBackground(Color.decode("#E74C3C"));
            functionButtons[i].setForeground(Color.decode("#f2f2f2"));}
        }
        for(int i = 0 ; i < 10 ; i++)  {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.decode("#E74C3C"));
            numberButtons[i].setForeground(Color.decode("#f2f2f2"));
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
        
        p = new JPanel();
        p.setBounds(50,100,300,300);
        p.setLayout(new GridLayout(4,4,10,10));
        //p.setBackground(Color.decode("#2C3E50"));

        p.add(numberButtons[1]);
        p.add(numberButtons[2]);
        p.add(numberButtons[3]);
        p.add(addButton);
        p.add(numberButtons[4]);
        p.add(numberButtons[5]);
        p.add(numberButtons[6]);
        p.add(subButton);
        p.add(numberButtons[7]);
        p.add(numberButtons[8]);
        p.add(numberButtons[9]);
        p.add(mulButton);
        p.add(decButton);
        p.add(numberButtons[0]);
        p.add(equButton);
        p.add(divButton);


        f.add(p);
        f.add(negButton);
        f.add(delButton);
        f.add(clrButton);
        f.add(t);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0 ; i < 10 ; i++){
            if(e.getSource() == numberButtons[i]){
                t.setText(t.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decButton){
            t.setText(t.getText().concat("."));
        }
        if(e.getSource() == addButton){
            num1 = Double.parseDouble(t.getText());
            operator = '+';
            t.setText("");
        }
        if(e.getSource() == subButton){
            num1 = Double.parseDouble(t.getText());
            operator = '-';
            t.setText("");
        }
        if(e.getSource() == mulButton){
            num1 = Double.parseDouble(t.getText());
            operator = '*';
            t.setText("");
        }
        if(e.getSource() == divButton){
            num1 = Double.parseDouble(t.getText());
            operator = '/';
            t.setText("");
        }
        if(e.getSource() == equButton){
            num2 = Double.parseDouble(t.getText());
            switch(operator){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            t.setText(String.valueOf(result));
            num1 = result;
        }
        if(e.getSource() == clrButton){
            t.setText("");
        }
        if(e.getSource() == delButton){
            String s = t.getText();
            t.setText("");
            for(int i = 0 ; i < s.length() - 1 ; i++){
                t.setText(t.getText() + s.charAt(i));
            }
        }
        if(e.getSource() == negButton){
            double temp = Double.parseDouble(t.getText());
            temp *= -1;
            t.setText(String.valueOf(temp));
        }
    }

}