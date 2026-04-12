package atm.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceCheckScreen extends JPanel implements ActionListener{
    JButton backButton;
    private MainFrame mainFrame;

    BalanceCheckScreen(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/ui/icons/atm-screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        JLabel text = new JLabel("Your current balance is Rs ");
        text.setBounds(170, 200, 400, 25);
        text.setForeground(Color.white);
        text.setFont(new Font("System" , Font.BOLD, 20));
        background.add(text);

        backButton = new JButton("Back");
        backButton.setBounds(460, 400, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        background.add(backButton);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == backButton){
            mainFrame.showScreen("MENU");
        }
    }
}
