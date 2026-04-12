package atm.ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private MenuScreen menuScreen;
    private WithdrawlScreen withdrawScreen;
    private DepositScreen depositScreen;
    private FastCashScreen fastCashScreen;
    private PinChangeScreen pinChangeScreen;

    public MainFrame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuScreen = new MenuScreen(this);
        withdrawScreen = new WithdrawlScreen(this);
        depositScreen = new DepositScreen(this);
        fastCashScreen = new FastCashScreen(this);
        pinChangeScreen = new PinChangeScreen(this);

        mainPanel.add(menuScreen, "MENU");
        mainPanel.add(depositScreen, "DEPOSIT");
        mainPanel.add(withdrawScreen, "WITHDRAW");
        mainPanel.add(fastCashScreen, "FASTCASH");
        mainPanel.add(pinChangeScreen, "PINCHANGE");

        add(mainPanel);

        setSize(800, 800);
        setLocation(350, 30);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        showScreen("MENU");
    }

    public void showScreen(String screen){
        cardLayout.show(mainPanel, screen);
    }
}
