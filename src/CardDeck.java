import java.util.Scanner;

public class CardDeck {
    private static final int CARD_NUMBERS = 13;
    private static int[][] deckOfCards = new int[CARD_NUMBERS][4];
    private static int[] cardValueArray = new int[CARD_NUMBERS];
    private static final int HEARTS = 1;
    private static final int DIAMONDS = 2;
    private static final int SPADES = 3;
    private static final int CLUBS = 4;
    private static final String HEART_STR = "Heart";
    private static final String DIAMONDS_STR = "Diamond";
    private static final String SPADES_STR = "Spade";
    private static final String CLUBS_STR = "Clubs";
    private static int userCardCounter = 0;
    private static int dealerCardCounter = 0;
    private static int userCardSum = 0;
    private static int dealerCardSum = 0;
    private static int cardValue = 0;
    private static String USER = "user";
    private static String DEALER = "dealer";
    private static boolean doublePlay = false;
    private static String YES = "y";
    private static String NO = "n";
    public static boolean ENDGAME = false;
    public static boolean CONTINUE = true;
    public static Scanner userInput = new Scanner(System.in);
    public static String input = "";


    public static void main(String[] args) {




        playMore();

        initCards(4);
            userDrawsCard();
            dealerDrawsCard();
            userDrawsCard();
            //  printCardDeckStatus();

            playDouble();

            userBusted();
            if (!doublePlay) {

                hitFunction();
                standMode();
            }


            // loop - waits for userInput to see if it should drawCard or not.
            //  String answer = userInput.nextLine();
            //     else - drawCardDealer(); as long as dealerCardSum < 17
        }

    private static boolean playMore() {
        if (userCardSum < 21 && userCardCounter < 5) {
            return true;
        }
        return false;
    }


    private static void playDouble() {
         if (userCardSum == 9 || userCardSum == 10 || userCardSum == 11) {

            System.out.println("\nWould you like to play a double down?");
            System.out.println("DOUBLE PLAYED");
            input = userInput.nextLine();
            userDrawsCard();
            if (input.compareToIgnoreCase(YES) == 0) {
                doublePlay = true;
                standMode();
            }

        }
    }


    private static void userDrawsCard() {
        userCardCounter++;
        drawOneRandomCard();
        userCardSum += cardValue;
        System.out.println("    User card sum is <" + userCardSum + ">");

    }

    private static void dealerDrawsCard() {
        dealerCardCounter++;
        drawOneRandomCard();
        dealerCardSum += cardValue;
        System.out.println("Dealer card sum is <" + dealerCardSum + ">");

    }

    private static void initCards(int countDecks) {
        for (int i = 0; i < CARD_NUMBERS; i++) {
            for (int j = 0; j < 4; j++) {
                deckOfCards[i][j] = countDecks;
            }
            cardValueArray[i] = i + 1;
            if (i > 9) {
                cardValueArray[i] = 10;
            }
        }
        //  printCardDeckStatus();

    }

    private static String getSuit(int suit) {
        String returnValue = "";
        suit++;
        switch (suit) {
            case HEARTS:
                returnValue = HEART_STR;
                break;
            case DIAMONDS:
                returnValue = DIAMONDS_STR;
                break;
            case SPADES:
                returnValue = SPADES_STR;
                break;
            case CLUBS:
                returnValue = CLUBS_STR;
                break;
            default:
                throw new IllegalArgumentException("Invalid suit " + suit);
        }
        return returnValue;
    }

    private static void printCardDeckStatus() {
        System.out.println("\ndeckOfCards status");
        for (int i = 0; i <= deckOfCards.length - 1; i++) {
            for (int j = 0; j <= deckOfCards[i].length - 1; j++) {

                System.out.println("Card " + (i + 1) + " " + getSuit(j) + " > " + deckOfCards[i][j] + " REMAINING, value <" + cardValueArray[i] + ">");
            }

        }
    }

    private static int randomCardNumber() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * CARD_NUMBERS);
        return randomNumber;
    }

    private static int randomSuitNumber() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * 4);
        return randomNumber;
    }

    private static void drawOneRandomCard() {
        boolean cardIsValid = false;
        int cardNumber = 0;
        int cardSuit = 0;

        while (!cardIsValid) {
            cardNumber = randomCardNumber();
            cardSuit = randomSuitNumber();
            if (deckOfCards[cardNumber][cardSuit] > 0) {
                //System.out.println((cardNumber + 1) + " " + getSuit(cardSuit) + " "  + deckOfCards[cardNumber][cardSuit] );
                deckOfCards[cardNumber][cardSuit] = deckOfCards[cardNumber][cardSuit] - 1;
                cardIsValid = !cardIsValid;
            }
        }
        cardValue = cardValueArray[cardNumber];
        System.out.println("The drawn card is <" + (cardNumber + 1) + "> < " + getSuit(cardSuit) + "> ");


    }

    private static void standMode() {
        System.out.println("User Total <" + userCardSum + ">");
        System.out.println("User is now in stand, waiting for dealer moves.");
        System.out.println("");

        boolean dealerPlaying = true;

        while (dealerPlaying && !ENDGAME) {
            dealerDrawsCard();
            dealerBusted();

            if (dealerCardSum > 17) {
                System.out.println("Dealer Total <" + dealerCardSum + ">");
                dealerPlaying = false;
            }
        }
        compareTotals();
    }

    private static void compareTotals() {
        if (dealerCardSum > userCardSum) {
            System.out.println("\nUser <" + userCardSum + "> Dealer <" + dealerCardSum + ">");
            System.out.println("    Dealer wins.");
               ENDGAME = !ENDGAME;
        }

        if (userCardSum > dealerCardSum) {
            System.out.println("\nUser <" + userCardSum + "> Dealer <" + dealerCardSum + ">");
            System.out.println("User wins");
            ENDGAME = !ENDGAME ;

        }

        if (userCardSum == dealerCardSum) {
            System.out.println("\nUser <" + userCardSum + "> Dealer <" + dealerCardSum + ">");
            System.out.println("Push - nobody wins");
            ENDGAME = !ENDGAME;
        }


        if (userCardSum == 21 && dealerCardSum != 21) {
            System.out.println("\n BLACKJACK");
            System.out.println("    User wins.");
            ENDGAME = !ENDGAME;

        }
    }

    private static void hitFunction() {
        boolean stand = false;

        while ( playMore() && !ENDGAME && !stand) {
            System.out.println("\nWould you like to draw a new card?");
            input = userInput.nextLine();
            if (input.compareToIgnoreCase(YES) == 0) {
                userDrawsCard();
                userBusted();
            } else{
                stand = !stand;
            }
        }
    }

        private static void userBusted() {
            if (userCardSum > 21) {
                System.out.println("User lost!");
                ENDGAME = true;
                throw new IllegalArgumentException("User busted " + userCardSum);
            }
        }
        private static void dealerBusted () {
            if (dealerCardSum > 21) {
                System.out.println("Dealer lost!");
                ENDGAME = true;
                throw new IllegalArgumentException("Dealer busted " + dealerCardSum);
            }
        }

}