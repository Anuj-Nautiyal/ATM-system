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
            ResultSet rs = conn.s
                    .executeQuery("select * from bank where pinnumber ='" + pinnumber + "' order by date desc limit 5");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("amount") + "<br><br><html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select balance from login where pinnumber = '" + pinnumber + "'");
            while (rs.next()) {
                bal.setText("Balance = " + rs.getString("balance"));
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
