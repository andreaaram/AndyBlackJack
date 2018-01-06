public class CardDeck {
    private static int[][] cardsArray = new int[13][4];
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

    public static void main(String[] args) {
        initCards(4);
        drawCardUser();
        drawCardDealer();
        drawCardUser();
// loop - waits for userInput to see if it should drawCard or not.
   //     else - drawCardDealer(); as long as dealerCardSum < 17
    }

    private static void drawCardUser() {
        userCardCounter++;
        System.out.println("Card has been drawn from user. User now has <" + userCardCounter + "> cards");
    }

    private static void drawCardDealer() {
        dealerCardCounter++;
        System.out.println("Card has been drawn from dealer. Dealer now has <" + dealerCardCounter + "> cards");
    }

    private static void initCards(int countDecks) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cardsArray[i][j] = countDecks;
            }
            cardValueArray[i] = i + 1;
            if (i > 9) {
                cardValueArray[i] = 10;
            }
        }
        printCardDeckStatus();

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

    private static void drawCard (int[][] cardsArray, int [] cardValueArray) {

        }

    private static void printCardDeckStatus() {
        System.out.println("    cardsArray values");
        for (int i = 0; i <= cardsArray.length - 1; i++) {
            for (int j = 0; j <= cardsArray[i].length - 1; j++) {

                System.out.println("Card " + (i + 1) + " " + getSuit(j + 1) + " > " + cardsArray[i][j] + "<" + cardValueArray[i] + ">") ;
            }

        }
    }
}
