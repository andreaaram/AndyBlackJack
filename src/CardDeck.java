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
    private static final int BLACKJACK = 1;
    private static final int FIVECARDCHARLIE = 2;
    private static final int USERWINS = 3;
    private static final int PUSH = 4;
    private static final int DEALERWINS = 5;
    private static boolean continueRound = true;
    private static Scanner userInput = new Scanner(System.in);
    private static String input = "";
    private static String YES = "y";
    private static int[][] deckOfCards = new int[CARD_NUMBERS][4];
    private static int[] cardValueArray = new int[CARD_NUMBERS];
    private static int[] userCards = new int[5];
    private static int[] dealerCards = new int[10];
    private static int userCardCounter = 0;
    private static int dealerCardCounter = 0;
    private static int cardValue = 0;
    private static int userCardSum = 0;
    private static int dealerCardSum = 0;
    private static boolean doublePlay = false;
    private static boolean userHasBlackJack = false;
    private static boolean userHasFiveCardCharlie = false;

    private static void userCardsSimulate() {
        int i = 0;
        userCards[i++] = 9;
        userCards[i++] = 3;
        userCards[i++] = 2;
        userCards[i++] = 2;
        userCards[i++] = 2;
        userCardSum = getCardsSum(userCards);
        userCardCounter = i;
        System.out.println("User sum " + userCardSum);
    }

    private static void dealerCardsSimulate() {
        int i = 0;
        dealerCards[i++] = 2;
        dealerCards[i++] = 2;
        dealerCards[i++] = 1;
        dealerCards[i++] = 2;
        dealerCards[i++] = 2;
        dealerCards[i++] = 1;
        dealerCards[i++] = 1;
        dealerCards[i++] = 8;
        dealerCardCounter = i;
        getCardsSum(dealerCards);
    }

    public static void main(String[] args) {

        //userCardsSimulate();
        //dealerCardsSimulate();

        initCards(4);
        userDrawsCard();
        dealerDrawsCard();
        userDrawsCard();
        checkBlackJack();
        // printCardDeckStatus();
       // userCardsSimulate();

        playDoubleDown();

        userBusted();
        if (!doublePlay && !userHasBlackJack) {

            hitFunction();
            standMode();
        }
    }

    private static boolean correctPlayingConditions() {
        if (userCardSum < 21 && userCardCounter < 5) {
            return true;
        }
        return false;
    }

    private static void playDoubleDown() {
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
        userCards[userCardCounter++] = drawOneRandomCard();
        userCardSum = getCardsSum(userCards);
        System.out.println("  User card sum is <" + userCardSum);


    }

    private static void dealerDrawsCard() {
        dealerCards[dealerCardCounter++] = drawOneRandomCard();
        dealerCardSum = getCardsSum(dealerCards);
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

    private static int randomCardNumberGenerator() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * CARD_NUMBERS);
        return randomNumber;
    }

    private static int randomSuitNumberGenerator() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * 4);
        return randomNumber;
    }

    private static int drawOneRandomCard() {
        boolean cardIsValid = false;
        int cardNumber = 0;
        int cardSuit = 0;

        while (!cardIsValid) {
            cardNumber = randomCardNumberGenerator();
            cardSuit = randomSuitNumberGenerator();
            if (deckOfCards[cardNumber][cardSuit] > 0) {
                //System.out.println((cardNumber + 1) + " " + getSuit(cardSuit) + " "  + deckOfCards[cardNumber][cardSuit] );
                deckOfCards[cardNumber][cardSuit] = deckOfCards[cardNumber][cardSuit] - 1;
                cardIsValid = !cardIsValid;
            }
        }
        cardValue = cardValueArray[cardNumber];
        System.out.println("The drawn card is <" + (cardNumber + 1) + "> < " + getSuit(cardSuit) + "> ");

        return cardValue;
    }

    private static void standMode() {

        checkFiveCardCharlie();

        if (userHasFiveCardCharlie) {
            typeOfWin(2);
        } else {
            System.out.println("User Total <" + userCardSum + ">");
            System.out.println("User is now in stand, waiting for dealer moves.");
            System.out.println("");

            boolean dealerPlaying = true;

            while (dealerPlaying && continueRound) {
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

        if (userHasFiveCardCharlie) {
            typeOfWin(2);
        } else {

            if (userCardSum < dealerCardSum) {
                typeOfWin(5);
            } else {

                if (userCardSum == dealerCardSum) {
                    typeOfWin(4);
                } else {

                    if (userHasBlackJack) {
                        typeOfWin(1);

                    } else {
                        typeOfWin(3);

                    }
                }
            }

        }
    }

    private static void hitFunction() {
        boolean stand = false;

        while (correctPlayingConditions() && continueRound && !stand) {
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
            continueRound = true;
            throw new IllegalArgumentException("User busted " + userCardSum + " (" + dealerCardSum + ")");
        }
    }

    private static void dealerBusted() {
        if (dealerCardSum > 21) {
            System.out.println("Dealer lost! User wins.");
            throw new IllegalArgumentException("Dealer busted " + dealerCardSum + " (" + userCardSum + ")");
        }
    }

    private static void checkBlackJack() {
        if (userCardSum == 21 && userCardCounter == 2) {
            System.out.println("BLACKJACK!");
            standMode();
            userHasBlackJack = true;
        }
    }

    private static int getCardsSum(int[] cardsArray) {
        boolean essExists = false;
        int sum = 0;
        System.out.print(" # cards are <");
        for (int i = 0; i < cardsArray.length && cardsArray[i] > 0; i++) {
            int cardValue = cardsArray[i];
            System.out.print(cardValue + ", ");
            sum += cardValue;
            if (cardValue == 1) {
                essExists = true;
            }
        }
        System.out.println(">");
        if (sum < 21 && essExists && sum < 12) {
            //kolla om ev ess gör sum bättre
            sum = sum + 10;
        }
        return sum;
    }

    private static void typeOfWin(int winType) {
        switch (winType){
            case BLACKJACK:
                System.out.println("User wins by BLACKJACK.  \n User:"  + userCardSum + " > Dealer: " + dealerCardSum);
                // bet payed 2:1 (wallet += 3*bet)
                break;
            case FIVECARDCHARLIE:
                System.out.println("User wins by FIVECARDCHARLIE \n User:"  + userCardSum + " > Dealer: " + dealerCardSum);
                // bet payed 1:1 (wallet += 2*bet)
                break;
            case USERWINS:
                System.out.println("User wins. \nUser:"  + userCardSum + " > Dealer: " + dealerCardSum);
                // bet payed 1:1 (wallet += 2*bet)
                break;
            case PUSH:
                System.out.println("\n PUSH - nobody wins. \n User: " + userCardSum + "> Dealer: " + dealerCardSum);
                // bet payed: returned (wallet += bet)
                break;
            case DEALERWINS:
                System.out.println("\n DEALER WINS! \n User: <" + userCardSum + "> Dealer: " + dealerCardSum);
                // bet is already lost, (wallet = wallet)
                break;
            //      default:
          //      throw new IllegalArgumentException("Invalid win ");
        }
    }

    private static void checkFiveCardCharlie() {
        if (userCardSum < 22 && userCardCounter == 5) {
            System.out.println("FIVECARDCHARLIE!");
            continueRound = false;
            userHasFiveCardCharlie = true;
        }
    }

}

