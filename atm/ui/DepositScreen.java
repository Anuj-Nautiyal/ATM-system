package atm.ui;

import atm.database.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositScreen extends JPanel implements ActionListener {
    JButton backButton, depositButton;
    JTextField amountField;
    private MainFrame mainFrame;

    private String pinnumber; // pinumber for the respective session

    public void setpinnumber(String pinnumber) {
        this.pinnumber = pinnumber;
    }

    DepositScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        background.setLayout(null);
        add(background);

        JLabel text = new JLabel("Enter the amount to be deposited :-");
        text.setBounds(170, 200, 400, 25);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 20));
        background.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 18));
        amountField.setBounds(170, 250, 400, 30);
        background.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(460, 350, 150, 30);
        depositButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(depositButton);
        depositButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(460, 400, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depositButton) {
           
            String number = amountField.getText();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a certain amount to deposit.");
            } else {
                try {
                    Conn conn = new Conn();

                    // update the bank values table
                    String query = "insert into bank(pinnumber, date, type, amount) values('" + pinnumber + "','" + date + "','Deposit','" + number + "')";

                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully!");

                    ResultSet rs = conn.s.executeQuery("select balance from login where pinnumber = '" + pinnumber + "'");

                    int balance=0;
                    if(rs.next()){
                        if(rs.wasNull()){
                            balance =0;
                        }
                        else{
                             balance = rs.getInt("balance");
                        }
                    }
                    int newBalance = balance + Integer.parseInt(number);
                   

                    String inbal = "update login set balance =" + newBalance + " where pinnumber = '" + pinnumber + "'";//update the balnce in the login table respective to the pinnumber
                    conn.s.executeUpdate(inbal);

                    amountField.setText("");
                    mainFrame.showScreen(("MENU"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == backButton) {
            mainFrame.showScreen("MENU");
        }
    }
}