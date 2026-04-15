package atm.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel implements ActionListener {
    JButton deposit, withdraw, balance, exit, fastcash, statement, pinchange;
    private MainFrame mainFrame;

    MenuScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        JLabel title = new JLabel("DHAN BANK");
        title.setBounds(300, 120, 200, 100);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("System", Font.BOLD, 30));
        background.add(title);

        JLabel text = new JLabel("Select your transaction");
        text.setBounds(320, 150, 250, 100);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.PLAIN, 14));
        background.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(180, 250, 200, 30);
        deposit.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(deposit);
        deposit.addActionListener(this);

        withdraw = new JButton("Cash Withdrawl");
        withdraw.setBounds(410, 250, 200, 30);
        withdraw.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(withdraw);
        withdraw.addActionListener(this);

        fastcash = new JButton("FastCash");
        fastcash.setBounds(180, 300, 200, 30);
        fastcash.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(fastcash);
        fastcash.addActionListener(this);

        statement = new JButton("Mini Statement");
        statement.setBounds(410, 300, 200, 30);
        statement.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(statement);
        statement.addActionListener(this);

        pinchange = new JButton("Change Pin");
        pinchange.setBounds(180, 350, 200, 30);
        pinchange.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(pinchange);
        pinchange.addActionListener(this);

        balance = new JButton("Check Balance");
        balance.setBounds(410, 350, 200, 30);
        balance.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(balance);
        balance.addActionListener(this);

        exit = new JButton("EXIT");
        exit.setBounds(410, 400, 200, 30);
        exit.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(exit);
        exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            mainFrame.showScreen("DEPOSIT");
        } else if (ae.getSource() == withdraw) {
            mainFrame.showScreen("WITHDRAW");
        } else if (ae.getSource() == fastcash) {
            mainFrame.showScreen("FASTCASH");
        } else if (ae.getSource() == pinchange) {
            mainFrame.showScreen("PINCHANGE");
        } else if (ae.getSource() == balance) {
            mainFrame.showScreen("BALANCE");
        } else if (ae.getSource() == statement) {
            mainFrame.showScreen("MINISTATEMENT");
        }
    }
}