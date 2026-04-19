package atm.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import atm.database.Conn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class WithdrawlScreen extends JPanel implements ActionListener {
    JButton backButton, WithdrawButton;
    JTextField amountField;
    private MainFrame mainFrame;

    private String pinnumber; // pinumber for the respective session

    public void setpinnumber(String pinnumber) {
        this.pinnumber = pinnumber;
    }

    WithdrawlScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        JLabel text = new JLabel("Enter the amount to Withdraw :-");
        text.setBounds(170, 200, 400, 25);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 20));
        background.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 18));
        amountField.setBounds(170, 250, 400, 30);
        background.add(amountField);

        WithdrawButton = new JButton("Withdraw");
        WithdrawButton.setBounds(460, 350, 150, 30);
        WithdrawButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(WithdrawButton);
        WithdrawButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(460, 400, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == WithdrawButton) {
            String number = amountField.getText();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a certain amount to withdraw");
            } else {
                try {
                    Conn conn = new Conn();
                    ResultSet rs = conn.s
                            .executeQuery("select balance from login where pinnumber = '" + pinnumber + "'");
                    int balance = 0;
                    if (rs.next()) {
                        balance = rs.getInt("balance");
                        if (rs.wasNull()) {
                            JOptionPane.showMessageDialog(null, "Insufficient balance");
                            return; // the logic does not move further if this block is executed
                        }

                        else if (ae.getSource() != backButton && balance < Integer.parseInt(number)) {
                            JOptionPane.showMessageDialog(null, "Insufficient balance");
                            return;
                        }

                        // assign value by geting balance from login table later
                        String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdraw','"
                                + number
                                + "')"; // to insert action into table

                        // Calculate new balance after withdrawal
                        int newBalance = balance - Integer.parseInt(number);

                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrawn Successfully");
                        String inbal = "update login set balance =" + newBalance + " where pinnumber = '" + pinnumber
                                + "'";
                        conn.s.executeUpdate(inbal);
                        mainFrame.showScreen(("MENU"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == backButton) {
            mainFrame.showScreen("MENU");
        }
    }
}