package atm.ui;

import atm.database.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniStatementScreen extends JPanel implements ActionListener {
    JButton backButton;
    JLabel card, mini, bank, bal;
    private MainFrame mainFrame;

    private String pinnumber; // pinumber for the respective session

    public void setpinnumber(String pinnumber) {
        this.pinnumber = pinnumber;
    }

    MiniStatementScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        mini = new JLabel();
        mini.setBounds(200, 180, 400, 250);
        mini.setForeground(Color.WHITE);
        background.add(mini);

        bank = new JLabel("Dhan Bank");
        bank.setBounds(300, 145, 400, 25);
        bank.setForeground(Color.WHITE);
        bank.setFont(new Font("Osward", Font.BOLD, 34));
        background.add(bank);

        card = new JLabel();
        card.setBounds(200, 185, 400, 35);
        card.setForeground(Color.WHITE);
        background.add(card);

        bal = new JLabel();
        bal.setBounds(200, 400, 400, 20);
        bal.setForeground(Color.WHITE);
        background.add(bal);

        backButton = new JButton("Back");
        backButton.setBounds(460, 400, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void updateMini() {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pinnumber = '" + pinnumber + "'");
            while (rs.next()) {
                card.setText("Card Number = " + rs.getString("cardnumber").substring(0, 2) + "XXXX"
                        + rs.getString("cardnumber").substring(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Conn conn = new Conn();

            // ORDER BY id DESC gives latest first, LIMIT 5 gives only last 5
            ResultSet rs = conn.s.executeQuery(
                    "SELECT * FROM bank WHERE pinnumber = '" + pinnumber +
                            "' ORDER BY id DESC LIMIT 5");

            StringBuilder sb = new StringBuilder();
            sb.append("<html><pre>");

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                // %-10s means left-align in a 10 character wide column
                // this gives equal spacing regardless of "Deposit" or "Withdraw"
                sb.append(String.format("%-15s %-12s %-10s", date, type, amount));
                sb.append("<br>");
            }
            sb.append("</pre></html>");
            mini.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select balance from login where pinnumber = '" + pinnumber + "'");
            while (rs.next()) {
                bal.setText("Balance:- Rs " + rs.getString("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            mainFrame.showScreen("MENU");
        }
    }
}
