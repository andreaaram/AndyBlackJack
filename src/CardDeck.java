import java.util.Scanner;

public class CardDeck {
    private static int[][] deckOfCards = new int[13][4];
    private static int[] cardValueArray = new int[13];
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
    private static int cardNumber = 0;
    private static int cardSuit = 0;

    public static void main(String[] args) {
        initCards(4);
        drawCardUser();
        drawCardDealer();
        drawCardUser();
       /* if (userCardSum == 9 || userCardSum == 10 || userCardSum == 11)  {
            ask user if they want double
        userInput = yes -> playDouble;
        userInput = no - > go on
        }      */
        // loop - waits for userInput to see if it should drawCard or not.
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to draw a new card?");
        //  String answer = userInput.nextLine();
        //     else - drawCardDealer(); as long as dealerCardSum < 17
    }

    private static void drawCardUser() {
        userCardCounter++;
        cardNumber = GetRandom(13);
        cardSuit = GetRandom(4) + 1;
        cardValue = cardValueArray[cardNumber];
        userCardSum += cardValue;
        System.out.println("    Card <" + (cardNumber + 1) + "> <" + getSuit(cardSuit) + "> of the value <" + cardValue + " >has been drawn from user. User now has <" + userCardCounter + "> cards");
        System.out.println("    User card sum is <" + userCardSum + ">");

    }

    private static void drawCardDealer() {
        dealerCardCounter++;
        cardNumber = GetRandom(13);
        cardSuit = GetRandom(4) + 1;
        cardValue = cardValueArray[cardNumber];
        dealerCardSum += cardValue;
        System.out.println("    Card <" + (cardNumber + 1) + "> <" + getSuit(cardSuit) + "> of the value <" +  cardValue +  "> has been drawn from dealer. Dealer now has <" + dealerCardCounter + "> cards");
        System.out.println("Dealer card sum is <" + dealerCardSum + ">");

    }


    private static void initCards(int countDecks) {
        for (int i = 0; i < 13; i++) {
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

    private static void drawCard(int[][] deckOfCards, int[] cardValueArray) {

    }

    private static void printCardDeckStatus() {
        System.out.println("    deckOfCards values");
        for (int i = 0; i <= deckOfCards.length - 1; i++) {
            for (int j = 0; j <= deckOfCards[i].length - 1; j++) {

                System.out.println("Card " + (i + 1) + " " + getSuit(j + 1) + " > " + deckOfCards[i][j] + "<" + cardValueArray[i] + ">");
            }

        }
    }

    private static int GetRandom(int range) {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * range);
        return randomNumber;
    }

}
