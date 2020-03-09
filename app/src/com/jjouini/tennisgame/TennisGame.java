package com.jjouini.tennisgame;

import java.util.Scanner;

import static com.jjouini.tennisgame.TennisGame.SCORE_ENUM.translateScore;

public class TennisGame {


    private static String playerOneName;
    private static String playerTwoName;
    private static int playerOneScore = 0;
    private static int playerTwoScore = 0;

    public static void main(String[] args) {

        // Init the game and read player names
        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert first player's name:");
        playerOneName = sc.nextLine();
        System.out.println("Please insert second player's name:");
        playerTwoName = sc.nextLine();

        // Iterate to get the game of each player
        while (!isGameFinished()) {

            // Player scores if input=1 or misses if input=0
            int playerOneHit;
            do {
                System.out.println("Game for " + playerOneName + ":");
                playerOneHit = sc.nextInt();
            } while (playerOneHit != 0 && playerOneHit != 1);

            int playerTwoHit;
            do {
                System.out.println("Game for " + playerTwoName + ":");
                playerTwoHit = sc.nextInt();
            } while (playerTwoHit != 0 && playerTwoHit != 1);

            playerOneScore += playerOneHit;
            playerTwoScore += playerTwoHit;
        }

    }

    private static boolean isGameFinished() {
        if (hasWinner()) {
            System.out.println(playerWithHighestScore() + " wins!");
            return true;
        }

        if (hasAdvantage()) {
            System.out.println("Advantage " + playerWithHighestScore());
            return true;
        }

        if (isDeuce()) {
            System.out.println("Deuce");
            return true;
        }

        if (playerOneScore == playerTwoScore) {
            System.out.println(translateScore(playerOneScore) + " all");
        } else {
            System.out.println(translateScore(playerOneScore) + "," + translateScore(playerTwoScore));
        }
        return false;
    }

    private static String playerWithHighestScore() {
        if (playerOneScore > playerTwoScore)
            return playerOneName;
        return playerTwoName;
    }

    private static boolean hasWinner() {
        return (playerTwoScore >= 4 || playerOneScore >= 4) && (Math.abs(playerOneScore - playerTwoScore)) >= 2;
    }

    private static boolean hasAdvantage() {
        return (playerTwoScore >= 4 || playerOneScore >= 4) && (Math.abs(playerOneScore - playerTwoScore)) == 1;
    }

    private static boolean isDeuce() {
        return playerOneScore >= 3 && playerTwoScore == playerOneScore;
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
