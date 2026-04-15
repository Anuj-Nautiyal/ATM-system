package atm.ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    

    private LoginScreen LoginScreen;
    private MenuScreen menuScreen; 
    private WithdrawlScreen withdrawScreen;
    private DepositScreen depositScreen;
    private FastCashScreen fastCashScreen;
    private PinChangeScreen pinChangeScreen;
    private BalanceCheckScreen balanceCheckScreen;
    private MiniStatementScreen miniStatementScreen;
   
    private String currentpin;

    public MainFrame(){
        setTitle("Dhan Bank ATM");
        setSize(800, 800);
        setLocation(350,30);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginScreen = new LoginScreen(this); //pass this so login can call setSessionpin
        menuScreen = new MenuScreen(this);
        withdrawScreen = new WithdrawlScreen(this);
        depositScreen = new DepositScreen(this);
        fastCashScreen = new FastCashScreen(this);
        pinChangeScreen = new PinChangeScreen(this);
        balanceCheckScreen = new BalanceCheckScreen(this);
        miniStatementScreen = new MiniStatementScreen(this);

        mainPanel.add(LoginScreen, "LOGIN");
        mainPanel.add(menuScreen, "MENU");
        mainPanel.add(depositScreen, "DEPOSIT");
        mainPanel.add(withdrawScreen, "WITHDRAW");
        mainPanel.add(fastCashScreen, "FASTCASH");
        mainPanel.add(pinChangeScreen, "PINCHANGE");
        mainPanel.add(balanceCheckScreen, "BALANCE");
        mainPanel.add(miniStatementScreen, "MINISTATEMENT");

        add(mainPanel);

        setSize(800, 800);
        setLocation(350, 30);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        showScreen("LOGIN");
    }
    
    public void setSessionPin(String pin){
        this.currentpin =pin;
        menuScreen.setpinnumber(pin);
        depositScreen.setpinnumber(pin);
        withdrawScreen.setpinnumber(pin);
        fastCashScreen.setpinnumber(pin);
        balanceCheckScreen.setpinnumber(pin);
        pinChangeScreen.setpinnumber(pin);
        miniStatementScreen.setpinnumber(pin);
    }

    public void showScreen(String screen){
        cardLayout.show(mainPanel, screen);
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
}

