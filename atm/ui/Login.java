package atm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener{
    JButton submit, clear;
    JTextField cardField;
    JPasswordField pinField;

    Login(){
        setTitle("ATM-system");
        setLayout(null);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/logo.png"));
        Image i1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel label = new JLabel(i2);
        label.setBounds(70,30,100,100);
        add(label);

        JLabel greet = new JLabel("Welcome to DHAN Bank");
        greet.setFont(new Font("Osward" , Font.BOLD, 38));
        greet.setBounds(220,70,450,40);
        add(greet);

        JLabel cardno = new JLabel("Enter Card Number:");
        cardno.setFont(new Font("Raleway" , Font.BOLD, 25));
        cardno.setBounds(70,200,250,40);
        add(cardno);

        cardField = new JTextField();
        cardField.setBounds(350, 200, 300, 40);
        cardField.setFont(new Font("Arial", Font.PLAIN, 22));
        add(cardField);

        JLabel pin = new JLabel("Enter your PIN: ");
        pin.setFont(new Font("Raleway" , Font.BOLD, 25));
        pin.setBounds(70,270,250,40);
        add(pin);

        pinField = new JPasswordField();
        pinField.setBounds(350, 270, 300, 40);
        pinField.setFont(new Font("Arial", Font.PLAIN, 22));
        add(pinField);

        submit = new JButton("SUBMIT");
        submit.setBounds(240, 350, 120, 35);
        submit.setFont(new Font("Raleway" , Font.BOLD, 15));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);
        
        clear = new JButton("CLEAR");
        clear.setBounds(390, 350, 120, 35);
        clear.setFont(new Font("Raleway" , Font.BOLD, 15));
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        setSize(800, 550);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit){
            setVisible(false);
            new Menu().setVisible(true);
        } else if (e.getSource() == clear){
            cardField.setText("");
            pinField.setText("");
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
