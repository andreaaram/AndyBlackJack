import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Window extends JFrame implements ActionListener {

    public static final String HIT_PNG = "hit.png";
    public static final String STAND_PNG = "stand.png";
    public static final String DOUBLE_PNG = "double.png";
    public static final String DEAL_PNG = "deal.png";
    JLabel introductionLabel = new JLabel("Welcome to the Aram Casino! Begin by choosing how much you want to bet, then click on the Deal button!", JLabel.CENTER);
    JLabel walletLabel = new JLabel("Your wallet contains this much:", JLabel.CENTER);
    JLabel betLabel = new JLabel("Enter your bet below:", JLabel.CENTER);
    JLabel betTotal = new JLabel("This is your current bet:", JLabel.CENTER);
    JLabel userBust = new JLabel("user BUST");
    JLabel dealerBust = new JLabel("dealer bUST");
    JLabel userWins = new JLabel("wohoo, user won");
    JLabel userLoses = new JLabel("uh oh, user lost");
    JLabel BlackJack = new JLabel("Blackjack!");
    JLabel fiveCardCharlie = new JLabel("Five Card Charlie!");
    JLabel emptySpace = new JLabel("  ");
    JTextField enterBet = new JTextField("10", 10);
    JTextField showTotalBet = new JTextField("10", 10);
    JTextField showWallet = new JTextField("100", 2);
    JLabel cardLabel[] = new JLabel[52];
    ImageIcon cardImage[] = new ImageIcon[52];
    ImageIcon standIMAGE = new ImageIcon(STAND_PNG);
    ImageIcon hitIMAGE = new ImageIcon(HIT_PNG);
    ImageIcon doubleIMAGE = new ImageIcon(DOUBLE_PNG);
    ImageIcon dealImage = new ImageIcon(DEAL_PNG);
    ImageIcon testcardIMAGE = new ImageIcon("2C.png");
    JLabel testCard = new JLabel(testcardIMAGE);


    JButton hitButton = new JButton(hitIMAGE);
    JButton standButton = new JButton(standIMAGE);
    JButton doubleButton = new JButton(doubleIMAGE);
    JButton dealButton = new JButton(dealImage);

    Color greenBackground = new Color(40, 81, 36);
    Color blueBackground = new Color(40, 81, 36);

    public Window() {
        super("Coolest BlackJack program ever");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container contentArea = getContentPane();
        getContentPane().setBackground(greenBackground);  //Whatever color
        setContentPane(contentArea);

        JPanel top = setTop();
        JPanel leftSide = setLeftSide();
        JPanel rightSide = setRightSide();
        contentArea.add("North", top);
        contentArea.add("East", rightSide);
        contentArea.add("West", leftSide);

        CardDeck.startRound();
        loadUserCards();
        loadDealerCards();


    }

    private void loadDealerCards(){
        for (int i = 0; i < CardDeck.dealerCardsSuit.length && CardDeck.dealerCardsSuit[i] != null; i++) {
            String image = CardDeck.dealerCardsSuit[i] + ".png";
            System.out.print(image + ", ");

        }
    }
    private void loadUserCards(){
        for (int i = 0; i < CardDeck.userCardsSuit.length && CardDeck.userCardsSuit[i] != null; i++) {
            String image = CardDeck.userCardsSuit[i] + ".png";
            System.out.print(image + ", ");

        }
    }


    public JPanel setTop() {
        JPanel panel = new JPanel(new GridLayout(1, 1, 0, 0));
        panel.setBackground(Color.red);
        panel.add(introductionLabel);
        return panel;
    }

    public JPanel setLeftSide() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBackground(Color.yellow);


        JPanel userCards = new JPanel(new GridLayout(1, 5, 5, 15));
        JPanel dealerCards = new JPanel(new GridLayout(1, 5, 0, 0));
        testCard.setSize(50, 104);
        testCard.setLocation(10,10);
        panel.add(testCard);
       /* dealerCards.add(label1);
        dealerCards.add(label1);
        dealerCards.add(label1);
        dealerCards.add(label1); */
      //  panel.add("North", dealerCards);
        // panel.add(userCards);

        panel.setLayout(null);
        return panel;
    }

    public JPanel setRightSide() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
        panel.setBackground(greenBackground);

        showTotalBet.setEditable(false);
        showWallet.setEditable(false);

        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        doubleButton.addActionListener(this);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        doubleButton.setEnabled(false);
        hitButton.setSize(80,80);

        JPanel moneySite = new JPanel(new GridLayout(6, 1, 0, 0)); //row, column, horizontal gap,  vertical gap
        JPanel buttonSite = new JPanel(new GridLayout(1, 3, 0, 0));

        moneySite.add(betLabel);
        moneySite.add(enterBet);
        moneySite.add(betTotal);
        moneySite.add(showTotalBet);
        moneySite.add(walletLabel);
        moneySite.add(showWallet);
        buttonSite.add(hitButton);
        buttonSite.add(standButton);
        buttonSite.add(doubleButton);
        panel.add("North", moneySite);
        panel.add("South", buttonSite);

        return panel;
    }

    public void Windows() {
        //Create your window.
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Create your container. This holds all interface components.
        Container contentArea = getContentPane();
        Color greenBackground = new Color(40, 81, 36);
        getContentPane().setBackground(greenBackground);  //Whatever color

        // image icons as an array (second row)

        JPanel moneySite = new JPanel(new GridLayout(6, 3, 0, 0)); //row, column, horizontal gap,  vertical gap
        JPanel top = new JPanel(new GridLayout(1, 3, 0, 0));
        JPanel buttonSite = new JPanel(new GridLayout(1, 3, 0, 0));
        JPanel cardSite = new JPanel(new GridLayout(2, 1, 0, 0));
        JPanel userCards = new JPanel(new GridLayout(5, 1, 5, 15));
        JPanel dealerCards = new JPanel(new GridLayout(5, 1, 5, 15));
        JPanel rightSideofWindow = new JPanel(new GridLayout(2, 1, 2, 0));
        JPanel dealButton = new JPanel(new GridLayout(1, 1));
        //Add a Layout-manager. This decides positioning of interface components. we're gonna use a grid layout


        //Create Interface components
        //	contentArea.add(buttons);

        //Apply Interface components to the container.

        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        doubleButton.addActionListener(this);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        doubleButton.setEnabled(false);

        Color redBackground = new Color(196, 66, 65);
        buttonSite.setBackground(redBackground);
        moneySite.setBackground(redBackground);


        moneySite.add(betLabel);
        moneySite.add(enterBet);
        moneySite.add(betTotal);
        moneySite.add(showTotalBet);
        moneySite.add(walletLabel);
        moneySite.add(showWallet);
        buttonSite.add(hitButton);
        buttonSite.add(standButton);
        buttonSite.add(doubleButton);
        rightSideofWindow.add(moneySite);
        rightSideofWindow.add(buttonSite);
        cardSite.add(dealerCards);
        cardSite.add(userCards);
        // dealButton.add(dealButton);
        // showWallet.setText(String(BlackJack.userWallet));
        showTotalBet.setEditable(false);
        showWallet.setEditable(false);

        contentArea.add("North", top);
        contentArea.add("East", rightSideofWindow);
        contentArea.add("West", cardSite);
        //contentArea.add("Center", dealButton);


        //setContentPane(contentArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hitButton) {
            System.out.println("Hit");
        }
        if (e.getSource() == standButton) {
            System.out.println("Stand");
        }
        if (e.getSource() == doubleButton) {
            System.out.println("Double");
        }


    }


    public void hitButtonPressed(ActionEvent e) {
        System.out.println("This occurs 1");
    }
}

public class BlackJackWindow {

    public static void main(String[] args) {
        Window BlackJackWindow = new Window();

    }
}