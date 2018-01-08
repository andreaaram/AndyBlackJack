import java.util.Scanner;

public class CardDeck {
    private static final int CARD_NUMBERS = 13;
    private static final int HEARTS = 1;
    private static final int DIAMONDS = 2;
    private static final int SPADES = 3;
    private static final int CLUBS = 4;
    private static final String HEART_STR = "Heart";
    private static final String DIAMONDS_STR = "Diamond";
    private static final String SPADES_STR = "Spade";
    private static final String CLUBS_STR = "Clubs";
    private static boolean ENDGAME = false;
    public static boolean CONTINUE = true;          // is this important?
    private static Scanner userInput = new Scanner(System.in);
    private static String input = "";
    private static int[][] deckOfCards = new int[CARD_NUMBERS][4];
    private static int[] cardValueArray = new int[CARD_NUMBERS];
    private static int[] userCardsArray = new int[5];
    private static int[] dealerCardsArray = new int[19];
    private static int userCardCounter = 0;
    private static int dealerCardCounter = 0;       // is this important?
    private static int getUserCardSum = 0;
    private static int getDealerCardSum = 0;
    private static int cardValue = 0;
    private static int userCardSum = 0;
    private static int dealerCardSum = 0;
    private static String USER = "user";            // is this important?
    private static String DEALER = "dealer";        // is this important?
    private static boolean doublePlay = false;
    private static String YES = "y";
    private static String NO = "n";                 // is this important?

    public static void main(String[] args) {


        continuePlaying();

        initCards(4);
        userDrawsCard();
        dealerDrawsCard();
        userDrawsCard();
        checkBlackJack();
        //  printCardDeckStatus();

        playDouble();

        userBusted();
        if (!doublePlay) {

            hitFunction();
            standMode();
        }
    }

    private static boolean continuePlaying() {
        if (getUserCardSum < 21 && userCardCounter < 5) {
            return true;
        }
        return false;
    }


    private static void playDouble() {
        if (userCardSum == 9 || userCardSum == 10 || userCardSum == 11) {

            System.out.println("\nWould you like to play a double down?");
            input = userInput.nextLine();
            if (input.compareToIgnoreCase(YES) == 0) {
                doublePlay = true;
                userDrawsCard();
                System.out.println("DOUBLE PLAYED");
                standMode();
            }

        }
    }

    private static void userDrawsCard() {
        drawOneRandomCard();
        System.out.println("  User card sum is <" + getUserCardSum() + ">");
        userCardsArray[userCardCounter++] = cardValue;
        int sum = 0;
        for (int i : userCardsArray) {
             sum += i;
        }
        System.out.println(sum);


    }

    private static void dealerDrawsCard() {
        drawOneRandomCard();
        System.out.println("Dealer card sum is <" + getDealerCardSum() + ">");
        dealerCardsArray[dealerCardCounter++] = cardValue;


    }

    private static int getUserCardSum() {
        boolean userHasAce = false;

        if (cardValue == 1) {
            userHasAce = true;
        }

        if (userHasAce) {
            int tempUserCardSum = userCardSum + 11;
            userCardSum += cardValue;

            while (tempUserCardSum > userCardSum && tempUserCardSum <= 21 && userCardSum <= 21) {
                userCardSum = tempUserCardSum;
                return userCardSum;
            }
        } else {
            userCardSum += cardValue;
        }
        System.out.println("    userCARDSUM GOTTEN");
        return userCardSum;
    }

    private static int getDealerCardSum() {
        boolean dealerHasAce = false;

        if (cardValue == 1) {
            dealerHasAce = true;
        }

        if (dealerHasAce) {
            int tempDealerCardSum = dealerCardSum + 11;
            dealerCardSum += cardValue;

            while (tempDealerCardSum > dealerCardSum && tempDealerCardSum <= 21 && dealerCardSum <= 21) {
                dealerCardSum = tempDealerCardSum;
                return dealerCardSum;
            }
        } else {
            dealerCardSum += cardValue;
        }
        System.out.println("    dealerCARDSUM GOTTEN");
        return dealerCardSum;


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

        if (userCardSum <= 21 && userCardCounter == 5) {
            compareTotals();
        } else {
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
    }

    private static void compareTotals() {
        int gUCS = userCardSum;
        int gDCS = dealerCardSum;

        if (userCardCounter == 5 && gUCS <= 21) {
            System.out.println("\n FIVE CARD CHARLIE");
            System.out.println("User wins");
            ENDGAME = !ENDGAME;
        } else {

            if (gDCS > gUCS) {
                System.out.println("\nUser <" + gUCS + "> Dealer <" + gDCS + ">");
                System.out.println("    Dealer wins.");
                ENDGAME = !ENDGAME;
            }

            if (gUCS > gDCS) {
                System.out.println("\nUser <" + gUCS + "> Dealer <" + gDCS + ">");
                System.out.println("User wins");
                ENDGAME = !ENDGAME;

            }

            if (gUCS == gDCS) {
                System.out.println("\nUser <" + gUCS + "> Dealer <" + gDCS + ">");
                System.out.println("Push - nobody wins");
                ENDGAME = !ENDGAME;
            }


            if (gUCS == 21 && userCardCounter == 2 && gDCS != 21) {
                System.out.println("\n BLACKJACK");
                System.out.println("    User wins.");
                ENDGAME = !ENDGAME;

            }
        }

     //  printCardDeckStatus();
    }

    private static void hitFunction() {
        boolean stand = false;

        while (continuePlaying() && !ENDGAME && !stand) {
            System.out.println("\nWould you like to draw a new card?");
            input = userInput.nextLine();
            if (input.compareToIgnoreCase(YES) == 0) {
                userDrawsCard();
                userBusted();
            } else {
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

    private static void dealerBusted() {
        if (dealerCardSum > 21) {
            System.out.println("Dealer lost! User wins.");
            ENDGAME = true;
            throw new IllegalArgumentException("Dealer busted " + dealerCardSum);
        }
    }

    private static void checkBlackJack () {
        if (userCardSum == 21 && userCardCounter == 2) {
            System.out.println("BLACKJACK!");
            standMode();
        }
    }
}