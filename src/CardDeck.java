import java.util.Scanner;

public class CardDeck {
    private static final int MAX_CARDS = 13;
    private static int[][] deckOfCards = new int[MAX_CARDS][4];
    private static int[] cardValueArray = new int[MAX_CARDS];
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


    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String yes = "yes";
        String no = "no";
        String input = "";

        initCards(4);
        drawCardUser();
        drawCardDealer();
        drawCardUser();
        //  printCardDeckStatus();
        while (userCardSum == 9 || userCardSum == 10 || userCardSum == 11) {
            System.out.println("Would you like to play a double down?");
            input = userInput.nextLine();
            if (input.compareToIgnoreCase(yes) == 0) {
                playDouble();
                //  stop further pathway -> go to Stand
            }
            break;
        }
        // loop - waits for userInput to see if it should drawCard or not.
        System.out.println("\nWould you like to draw a new card?");
        //  String answer = userInput.nextLine();
        //     else - drawCardDealer(); as long as dealerCardSum < 17
    }

    private static void playDouble() {         //this should not be in CardDeck?
        System.out.println("DOUBLE PLAYED");
        drawCardUser();

    }

    private static void removeUsedCard(int cardNumber, int cardSuit) {
        (deckOfCards[cardNumber][cardSuit]) = deckOfCards[cardNumber][cardSuit] - 1;
    }

    private static void drawCardUser() {
        userCardCounter++;
        int cardNumber = randomCardNumber();
        int cardSuit = randomSuitNumber();
        cardValue = cardValueArray[cardNumber];
        userCardSum += cardValue;
        removeUsedCard(cardNumber, (cardSuit));
        System.out.println("    Card <" + (cardNumber + 1) + "> <" + getSuit(cardSuit) + "> of the value <" + cardValue + " >has been drawn from user. User now has <" + userCardCounter + "> cards");
        System.out.println("    User card sum is <" + userCardSum + ">");

    }

    private static void drawCardDealer() {
        dealerCardCounter++;
        int cardNumber = randomCardNumber();
        int cardSuit = randomSuitNumber();
        cardValue = cardValueArray[cardNumber];
        dealerCardSum += cardValue;
        removeUsedCard(cardNumber, cardSuit);
        System.out.println("Card <" + (cardNumber + 1) + "> <" + getSuit(cardSuit) + "> of the value <" + cardValue + "> has been drawn from dealer. Dealer now has <" + dealerCardCounter + "> cards");
        System.out.println("Dealer card sum is <" + dealerCardSum + ">");

    }

    private static void initCards(int countDecks) {
        for (int i = 0; i < MAX_CARDS; i++) {
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
        randomNumber = (int) Math.floor(Math.random() * 13);
        return randomNumber;
    }

    private static int randomSuitNumber() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * 4);
        return randomNumber;
    }

}
