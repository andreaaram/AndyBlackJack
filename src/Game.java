import apple.laf.JRSUIConstants;

public class Game {

    public static void main (String [] args){
        int user = 0;
        int dealer = 2;
        HitFunction(dealer, user);
        checkForDouble(user);
      /*  HitFunction(seeIfUserWantsTo);
        StandMode(seeIfUserWantsTo);
        endRound(seeIfUserWantsTo or userTotal > 21);
        endGame(notEnoughMoney); */
    }

    private static void checkForDouble(int user) {
        System.out.println(user);
    }

    private static void HitFunction(int user, int dealer) {
        System.out.println(dealer + user);
    }

    private static void endRound() {
    }

    private static void StandMode() {
    }

    private static void endGame () {
    }
}
