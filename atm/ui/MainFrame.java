package atm.ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LoginScreen loginScreen;
    private MenuScreen menuScreen; 
    private WithdrawlScreen withdrawScreen;
    private DepositScreen depositScreen;
    private FastCashScreen fastCashScreen;
    private PinChangeScreen pinChangeScreen;
    private BalanceCheckScreen balanceCheckScreen;
    private MiniStatementScreen miniStatementScreen;

    public MainFrame(){
        setLocation(350, 150);
        setSize(800,500);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loginScreen = new LoginScreen(this); //pass this so login can call setSessionpin
        setContentPane(loginScreen); //separates the login screen from the mainFrame

        setVisible(true);
    }

    public void onLoginSuccess(String pin){
        setSize(800, 800);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        menuScreen = new MenuScreen(this);
        withdrawScreen = new WithdrawlScreen(this);
        depositScreen = new DepositScreen(this);
        fastCashScreen = new FastCashScreen(this);
        pinChangeScreen = new PinChangeScreen(this);
        balanceCheckScreen = new BalanceCheckScreen(this);
        miniStatementScreen = new MiniStatementScreen(this);

        setSessionPin(pin);

        mainPanel.add(menuScreen, "MENU");
        mainPanel.add(depositScreen, "DEPOSIT");
        mainPanel.add(withdrawScreen, "WITHDRAW");
        mainPanel.add(fastCashScreen, "FASTCASH");
        mainPanel.add(pinChangeScreen, "PINCHANGE");
        mainPanel.add(balanceCheckScreen, "BALANCE");
        mainPanel.add(miniStatementScreen, "MINISTATEMENT");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        revalidate(); //recalculates all sizes and positions after changing the screen size
        showScreen("MENU");
    }
    
    public void setSessionPin(String pin){
        depositScreen.setpinnumber(pin);
        withdrawScreen.setpinnumber(pin);
        fastCashScreen.setpinnumber(pin);
        balanceCheckScreen.setpinnumber(pin);
        pinChangeScreen.setpinnumber(pin);
        miniStatementScreen.setpinnumber(pin);
    }

    public void showScreen(String screen){
        if(screen.equals("MINISTATEMENT")){
            miniStatementScreen.updateMini();
        }
        if (screen.equals("BALANCE")) {
        balanceCheckScreen.updateBalance();
    }
        cardLayout.show(mainPanel, screen);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        new MainFrame();
    }
}