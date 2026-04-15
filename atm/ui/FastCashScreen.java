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

public class FastCashScreen extends JPanel implements ActionListener {
    JButton a1, a2, a3, a4, a5, a6, backButton;
    private MainFrame mainFrame;
    private String pinnumber; // pinumber for the respetive session

    public void setpinnumber(String pinnumber) {
        this.pinnumber = pinnumber;
    }

    FastCashScreen(MainFrame mainFrame) {
        setLayout(null);
        this.mainFrame = mainFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        JLabel title = new JLabel("SELECT your Withdrawl amount");
        title.setBounds(250, 150, 350, 100);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("System", Font.BOLD, 20));
        background.add(title);

        a1 = new JButton("Rs 100");
        a1.setBounds(180, 250, 150, 30);
        a1.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a1);
        a1.addActionListener(this);

        a2 = new JButton("Rs 500");
        a2.setBounds(450, 250, 150, 30);
        a2.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a2);
        a2.addActionListener(this);

        a3 = new JButton("Rs 1000");
        a3.setBounds(180, 300, 150, 30);
        a3.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a3);
        a3.addActionListener(this);

        a4 = new JButton("Rs 2000");
        a4.setBounds(450, 300, 150, 30);
        a4.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a4);
        a4.addActionListener(this);

        a5 = new JButton("Rs 5000");
        a5.setBounds(180, 350, 150, 30);
        a5.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a5);
        a5.addActionListener(this);

        a6 = new JButton("Rs 10000");
        a6.setBounds(450, 350, 150, 30);
        a6.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(a6);
        a6.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(450, 400, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            mainFrame.showScreen("MENU");
        }
    }
}