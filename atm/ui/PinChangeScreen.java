package atm.ui;

import atm.database.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class PinChangeScreen extends JPanel implements ActionListener {
    JButton backButton, confirmButton;
    JPasswordField pinField, confirmField;
    private MainFrame mainFrame;

    private String pinnumber; // pinumber for the respective session

    public void setpinnumber(String pinnumber) {
        this.pinnumber = pinnumber;
    }

    PinChangeScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        JLabel title = new JLabel("Enter your new PIN");
        title.setBounds(300, 150, 350, 100);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("System", Font.BOLD, 20));
        background.add(title);

        JLabel pintext = new JLabel("New PIN :");
        pintext.setBounds(180, 250, 350, 30);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        background.add(pintext);

        pinField = new JPasswordField();
        pinField.setBounds(300, 250, 230, 30);
        pinField.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(pinField);

        JLabel confirmtext = new JLabel("Confirm PIN :");
        confirmtext.setBounds(180, 300, 350, 30);
        confirmtext.setForeground(Color.WHITE);
        confirmtext.setFont(new Font("System", Font.BOLD, 16));
        background.add(confirmtext);

        confirmField = new JPasswordField();
        confirmField.setBounds(300, 300, 230, 30);
        confirmField.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(confirmField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(500, 370, 120, 30);
        confirmButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(confirmButton);
        confirmButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(500, 410, 120, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmButton) {
            try {
                String npin = new String(pinField.getPassword());
                String rpin = new String(confirmField.getPassword());

                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your Pin.");
                    return;
                }

                if (npin.length() != 4) {
                    JOptionPane.showMessageDialog(null, "Pin must be exactly 4 digits.");
                    pinField.setText("");
                    confirmField.setText("");
                    return;
                }

                if (!npin.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Pin must contain numbers only.");
                    pinField.setText("");
                    confirmField.setText("");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please confirm your Pin.");
                    return;
                }

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered Pin does not match.");
                    pinField.setText("");
                    confirmField.setText("");
                    return;
                }

                Conn conn = new Conn();
                String query = "update login set pinnumber = '" + rpin + "' where pinnumber ='" + pinnumber + "'";
                String query1 = "update bank set pinnumber = '" + rpin + "' where pinnumber ='" + pinnumber + "'";
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query);

                // pushes the new pin to all the screens for the same session
                mainFrame.setSessionPin(rpin);
                JOptionPane.showMessageDialog(null, "Pin changed Successfully!");

                pinField.setText("");
                confirmField.setText("");
                mainFrame.showScreen("MENU");
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == backButton) {
            mainFrame.showScreen("MENU");
        }
    }
}