public class BlackJack {
    private static int userBet;
    private static int userBetTotal;
    private static int userWallet;
    private static int minimumBet;
    private static int roundCounter = 0;
    private static int gameCounter = 0;

    public static void main(String[] args) {
        boolean continueGame = true;
        boolean startGame = true;
        initGame();
        // loop:
        // if enoughChips:
       // yes -> startBet
        //no -> endGame. Ask: do you want to play again? Yes -> restart, No -> closeWindow
        if (userWallet < minimumBet) {
            System.out.println("There isn't enough money in your wallet to play.");
            startGame = false;
        }
        if (minimumBet < 1) {
            System.out.println("minimumBet can't be lower than 1.");
            startGame = false;
        }
        if (userBet < minimumBet) {
            System.out.println("Your bet <" + userBet + "> has to be bigger than the minimumBet <" + minimumBet + ">");
            startGame = false;
        }
        while (startGame) {
            if (userWallet >= minimumBet && userWallet > userBet) {
                userWallet = startBet(userWallet, userBet);
            } else {
                continueGame = endGame();
            }
            if (continueGame == false) {
                break;
            }
        }
    }

    private static int startBet (int userWallet, int betSize) {
        roundCounter++;
        System.out.println("#Game " + gameCounter + " Round " + roundCounter + " is starting");
        System.out.println("    UserWallet contains " + userWallet);
        System.out.println("    User lost " + betSize);
        userWallet -= betSize;
        return userWallet;

    }

    private static boolean endGame() {
        boolean userInput = true;
        if (gameCounter == 2) {
            userInput = false;
        }

        System.out.println("#You don't have enough money to bet. Would you like to restart the game?");
        if (userInput) {
            initGame();
            return true;
        } else {
            return false;
        }
    }

    private static void initGame() {
        gameCounter++;
        userBet = 30;
        userBetTotal = userBet;
        userWallet = 100;
        minimumBet = 10;
        roundCounter = 0;
        System.out.println("#Game " + gameCounter + " initialized");
        printUserStatus();

    }

    private static void printUserStatus() {
        System.out.println("    These are your current values");
        System.out.println("    Wallet              = " + userWallet);
        System.out.println("    Bet                 = " + userBetTotal);
        System.out.println("    Minimum bet allowed = " + minimumBet);

    }
}
