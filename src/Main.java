import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int boardSize = 3;
        char[][] field = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                field[i][j] = ' ';
            }
        }
        Scanner scanner = new Scanner(System.in);
        char[] players = {'X', 'O'};
        int currentPlayerindex = 0;
        boolean gameOn = true;

        while (gameOn) {
            System.out.println("Position on field:");
            for (int i = 1; i <= 9; i++) {
                System.out.print("[" + i + "]");
                if (i % 3 == 0) {
                    System.out.println();
                }
            }
            System.out.println("Game:");
            char currentPlayer = players[currentPlayerindex];
            showField(field);
            System.out.println("Player " + currentPlayer + " choose position (1-9)");
            int position = 0;

            try {
                position = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                scanner.next();
                continue;
            }

            if (position < 1 || position > 9) {
                System.out.println("WRONG position! Choose number 1 between 9!");
                continue;
            }
            if (playerMove(field, position, currentPlayer)) {
                if (checkWin(field, currentPlayer)) {
                    showField(field);
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOn = false;
                } else if (checkDraw(field)) {
                    showField(field);
                    System.out.println("Draw!!");
                    gameOn = false;
                } else {
                    currentPlayerindex = (currentPlayerindex + 1) % 2;
                }
            } else {
                System.out.println("Choose other position!");
            }
        }
        scanner.close();

    }


    public static void showField(char[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print("[" + pole[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static boolean playerMove(char[][] field, int position, char symbol) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (field[row][col] == ' ') {
            field[row][col] = symbol;
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char[][] field, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) return true;
            if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] == symbol) return true;
        }
        if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) return true;
        if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) return true;
        return false;
    }
}

