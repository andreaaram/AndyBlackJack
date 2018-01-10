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
    private static final int USERBUSTED = 6;
    private static final int DEALERBUSTED = 7;
    private static boolean continueRound = true;
    private static Scanner userInput = new Scanner(System.in);
    private static String input = "";
    private static String YES = "y";
    private static int[][] deckOfCards = new int[CARD_NUMBERS][4];
    private static int[] cardValueArray = new int[CARD_NUMBERS];
    public static int[] userCards = new int[5];
    public static String[] userCardsSuit = new String[5];
    public static int[] dealerCards = new int[10];
    public static String[] dealerCardsSuit = new String[10];
    private static int userCardCounter = 0;
    private static int dealerCardCounter = 0;
    private static int cardValue = 0;
    private static int userCardSum = 0;
    public static int dealerCardSum = 0;
    private static boolean doublePlay = false;
    private static boolean userHasBlackJack = false;
    private static boolean userHasFiveCardCharlie = false;
    public static boolean userBusted = false;
    public static boolean dealerBusted = false;

    private static void userCardsSimulate() {
        int i = 0;
        userCards[i] = 7;
        userCardsSuit[i] = userCards[i++]  + "H";
        userCards[i] = 3;
        userCardsSuit[i] = userCards[i++]  + "D";
        //userCards[i++] = 2;
        //userCardsSuit[i] = userCards[i++]  + "H";
        //userCards[i++] = 2;
        //userCardsSuit[i] = DIAMONDS_STR;
        //userCards[i++] = 2;
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
        startRound();
        loadUserCards();
        playDoubleDown(false);

        checkIfUserIsBusted();
        if (!userBusted && !doublePlay && !userHasBlackJack) {
            hitFunction();
            standMode();
        }
    }

    public static void startRound() {
        //userCardsSimulate();
        //dealerCardsSimulate();

        initCards(4);
        userDrawsCard();
        dealerDrawsCard();
        userDrawsCard();
        checkBlackJack();
        // printCardDeckStatus();
         userCardsSimulate();

    }

    private static boolean correctPlayingConditions() {
        if (userCardSum < 21 && userCardCounter < 5) {
            return true;
        }
        return false;
    }

    public static boolean playDoubleDown(boolean windowRunning) {
        if (userCardSum == 9 || userCardSum == 10 || userCardSum == 11) {
            if(!windowRunning) {
                System.out.println("\nWould you like to play a double down?");
                input = userInput.nextLine();
                if (input.compareToIgnoreCase(YES) == 0) {
                    doublePlay = true;
                    userDrawsCard();
                    System.out.println("DOUBLE PLAYED");
                    standMode();
                }
            }
            return true;

        }
        return false;
    }

    public static void userDrawsCard() {
        String result[] = drawOneRandomCard();;
        userCards[userCardCounter] = Integer.parseInt(result[0]);
        userCardsSuit[userCardCounter] = Integer.parseInt(result[0]) + result[1].substring(0,1);
        userCardCounter++;
        userCardSum = getCardsSum(userCards);
        System.out.println("  User card sum is <" + userCardSum);


    }

    private static void loadUserCards(){
        for (int i = 0; i < userCardsSuit.length && userCardsSuit[i] != null; i++) {
            String image = userCardsSuit[i] + ".png";
            System.out.print(image);

        }
    }

    private static void dealerDrawsCard() {
        String result[] = drawOneRandomCard();;

        dealerCards[dealerCardCounter] = Integer.parseInt(result[0]);
        dealerCardsSuit[dealerCardCounter] = Integer.parseInt(result[0]) + result[1].substring(0,1);
        dealerCardCounter++;
        dealerCardSum = getCardsSum(dealerCards);
        System.out.println("Dealer card sum is <" + dealerCardSum + ">");
    }

    public static void initCards(int countDecks) {
        for (int i = 0; i < CARD_NUMBERS; i++) {
            for (int j = 0; j < 4; j++) {
                deckOfCards[i][j] = countDecks;
            }
            cardValueArray[i] = i;
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
        randomNumber = (int) Math.floor(Math.random() * (CARD_NUMBERS)) + 1;
        return randomNumber;
    }

    private static int randomSuitNumberGenerator() {
        int randomNumber;
        randomNumber = (int) Math.floor(Math.random() * 4);
        return randomNumber;
    }

    private static String[] drawOneRandomCard() {
        boolean cardIsValid = false;
        int cardNumber = 0;
        int cardSuit = 0;

        while (!cardIsValid) {
            cardNumber = randomCardNumberGenerator();
            cardSuit = randomSuitNumberGenerator();
            if (deckOfCards[cardNumber - 1][cardSuit] > 0) {
                //System.out.println((cardNumber - 1) + " " + getSuit(cardSuit) + " "  + deckOfCards[cardNumber][cardSuit] );
                deckOfCards[cardNumber - 1][cardSuit] = deckOfCards[cardNumber - 1][cardSuit] - 1;
                cardIsValid = !cardIsValid;
            }
        }
        cardValue = cardValueArray[cardNumber];
        System.out.println("The drawn card is <" + (cardNumber) + "> < " + getSuit(cardSuit) + "> ");

        return new String[] {String.valueOf(cardNumber), getSuit(cardSuit)};

    }

    private static void standMode() {

        if (!userBusted){
            checkFiveCardCharlie();

            if (userHasFiveCardCharlie) {
                typeOfWin(FIVECARDCHARLIE);
            } else {
                System.out.println("User Total <" + userCardSum + ">");
                System.out.println("User is now in stand, waiting for dealer moves.");
                System.out.println("");

                boolean dealerPlaying = true;

                while (!dealerBusted && dealerPlaying && continueRound) {
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
    }

    private static void compareTotals() {

        if (userBusted){
            typeOfWin(USERBUSTED);
        }
        if (userHasFiveCardCharlie) {
            typeOfWin(FIVECARDCHARLIE);
        } else {

            if (userCardSum < dealerCardSum) {
                typeOfWin(USERWINS);
            } else {

                if (userCardSum == dealerCardSum) {
                    typeOfWin(PUSH);
                } else {

                    if (userHasBlackJack) {
                        typeOfWin(BLACKJACK);

                    } else {
                        typeOfWin(USERWINS);

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
                checkIfUserIsBusted();
            } else {
                stand = !stand;
            }
        }
    }

    public static void checkIfUserIsBusted() {
        if (userCardSum > 21) {
            System.out.println("User lost!");
            continueRound = false;
           //  BlackJack.userWallet = userWallet; // (money is already lost)
            userBusted = true;
        }
    }

    private static boolean dealerBusted() {
        if (dealerCardSum > 21) {
            System.out.println("Dealer lost!");
            typeOfWin(DEALERBUSTED);
            return true;
         //   throw new IllegalArgumentException("Dealer busted " + dealerCardSum + " (" + userCardSum + ")");
        }
        return false;
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
                // bet payed 2:1 BlackJack.userWallet += (3*BlackJack.userBetTotal)
                break;
            case FIVECARDCHARLIE:
                System.out.println("User wins by FIVECARDCHARLIE \n User:"  + userCardSum + " > Dealer: " + dealerCardSum);
                // bet payed 1:1 (BlackJack.userWallet += 2*BlackJack.userBetTotal)
                break;
            case USERWINS:
                System.out.println("User wins. \nUser:"  + userCardSum + " > Dealer: " + dealerCardSum);
                // bet payed 1:1 (BlackJack.userWallet += 2*BlackJack.userBetTotal)
                break;
            case PUSH:
                System.out.println("\n PUSH - nobody wins. \n User: " + userCardSum + "> Dealer: " + dealerCardSum);
                // bet returned: (BlackJack.userWallet += BlackJack.userBetTotal);
                break;
            case DEALERWINS:
                System.out.println("\n DEALER WINS! \n User: <" + userCardSum + "> Dealer: " + dealerCardSum);
                // bet is already lost, (BlackJack.userWallet = BlackJack.userWallet)
                break;
            case USERBUSTED:
                System.out.println("\n DEALER WINS! \n User: <" + userCardSum + "> Dealer: " + dealerCardSum);
                // bet is already lost, (BlackJack.userWallet = BlackJack.userWallet)
                break;
            case DEALERBUSTED:
                System.out.println("\n USER WINS! \n User: <" + userCardSum + "> Dealer: " + dealerCardSum);
                if (userHasBlackJack) {
                // bet payed 2:1 BlackJack.userWallet += (3*BlackJack.userBetTotal)
                } else {
                // bet payed 1:1 (BlackJack.userWallet += 2*BlackJack.userBetTotal)
                }
                break;
            default:
               throw new IllegalArgumentException("Invalid win ");
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

