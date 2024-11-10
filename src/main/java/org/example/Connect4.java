package org.example;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Connect4
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Adja meg a játékos nevét: ");
        String playerName = scanner.nextLine();

        Board board = new Board();
        Player humanPlayer = new Player(playerName, 'Y');
        Player computerPlayer = new Player("Gép", 'R');

        Player currentPlayer = humanPlayer;
        boolean hasWinner = false;

        while (!hasWinner && !board.isFull()) {
            board.display();
            int column;

            if (currentPlayer == humanPlayer) {
                System.out.print(currentPlayer.getName() + ", válasszon egy oszlopot: ");
                column = scanner.nextInt();
            } else {
                column = computerPlayer.makeMove(board);
                System.out.println("A gép választott oszlop: " + column);
            }

            if (board.isValidMove(column)) {
                board.placeDisc(column, currentPlayer.getDisc());
                hasWinner = board.checkWinner(currentPlayer.getDisc());

                currentPlayer = (currentPlayer == humanPlayer) ? computerPlayer : humanPlayer;
            } else {
                System.out.println("Érvénytelen mozdulat. Próbálja újra.");
            }
        }

        board.display();
        if (hasWinner) {
            System.out.println("A nyertes: " + ((currentPlayer == humanPlayer) ? computerPlayer.getName() : humanPlayer.getName()) + "!");
        } else {
            System.out.println("Döntetlen mérkőzés!");
        }
    }
}
