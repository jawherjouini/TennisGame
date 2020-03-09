package com.jjouini.tennisgame;

import java.util.Scanner;

import static com.jjouini.tennisgame.TennisGame.SCORE_ENUM.translateScore;

public class TennisGame {

    private static String firstPlayerName;
    private static String secondPlayerName;
    private static int firstPlayerScore = 0;
    private static int secondPlayerScore = 0;

    public static void main(String[] args) {

        // Init the game and read player names
        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert first player's name:");
        firstPlayerName = sc.nextLine();
        System.out.println("Please insert second player's name:");
        secondPlayerName = sc.nextLine();

        // Iterate to get the game of each player
        while (!isGameFinished()) {

            // Player scores if input=1 or misses if input=0
            int firstPlayerGame;
            do {
                System.out.println(String.format("-> Game for %s (press 1 to score a point / 0 to miss):", firstPlayerName));
                firstPlayerGame = sc.nextInt();
            } while (firstPlayerGame != 0 && firstPlayerGame != 1);

            int secondPlayerGame;
            do {
                System.out.println(String.format("-> Game for %s (press 1 to score a point / 0 to miss):", secondPlayerName));
                secondPlayerGame = sc.nextInt();
            } while (secondPlayerGame != 0 && secondPlayerGame != 1);

            // Add Game result to the global score
            firstPlayerScore += firstPlayerGame;
            secondPlayerScore += secondPlayerGame;
        }

    }

    /**
     * Game is finished if One player wins or is in advantage or the game is draw
     *
     * @return boolean
     */
    private static boolean isGameFinished() {

        // Game is finished
        if (isGameWon()) {
            System.out.println((String.format("Final Score -> ***** %s wins the game! *****\r\n", getWinningPlayerName())));
            return true;
        }

        if (isGameAdvantage()) {
            System.out.println((String.format("Final Score -> ***** Advantage for %s! *****\r\n", getWinningPlayerName())));
            return true;
        }

        if (isGameDeuce()) {
            System.out.println("Final Score -> ***** Deuce! *****\r\n");
            return true;
        }

        // Game is still in progress...
        if (firstPlayerScore == secondPlayerScore) {
            System.out.println((String.format("Live Score -> ***** %s All *****\r\n", translateScore(firstPlayerScore))));
        } else {
            System.out.println(String.format("Live Score -> \"%s\": %s ***** \"%s\": %s\r\n", firstPlayerName, translateScore(firstPlayerScore), secondPlayerName, translateScore(secondPlayerScore)));
        }
        return false;
    }

    private static boolean isGameWon() {
        return (hasOnePlayerScoredMaxPoints()) && getScoresAbsDifference() >= 2;
    }

    private static boolean isGameAdvantage() {
        return hasOnePlayerScoredMaxPoints() && (getScoresAbsDifference()) == 1;
    }

    private static boolean isGameDeuce() {
        return firstPlayerScore >= 3 && secondPlayerScore == firstPlayerScore;
    }

    /**
     * Returns true if at least one of the two players scored 4 points
     *
     * @return boolean
     */
    private static boolean hasOnePlayerScoredMaxPoints() {
        return secondPlayerScore >= 4 || firstPlayerScore >= 4;
    }

    /**
     * returns the Absolute value of the difference between the scores of the two players
     *
     * @return int
     */
    private static int getScoresAbsDifference() {
        return Math.abs(firstPlayerScore - secondPlayerScore);
    }

    /**
     * Returns the name of the player that scored the most
     *
     * @return String
     */
    private static String getWinningPlayerName() {
        if (firstPlayerScore > secondPlayerScore)
            return firstPlayerName;
        return secondPlayerName;
    }

    public enum SCORE_ENUM {
        SCORE_LOVE(0, "Love"),
        SCORE_FIFTEEN(1, "Fifteen"),
        SCORE_THIRTY(2, "Thirty"),
        SCORE_FOURTY(3, "Fourty");

        private int code;
        private String label;

        SCORE_ENUM(int code, String label) {
            this.code = code;
            this.label = label;
        }

        public static String translateScore(int id) {
            for (SCORE_ENUM e : values()) {
                if (e.code == id) return e.label;
            }
            return null;
        }
    }
}
