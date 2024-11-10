package org.example;
import java.util.Random;

public class Player {
    private final String name;
    private final char disc;
    private final Random random = new Random();

    public Player(String name, char disc) {
        this.name = name;
        this.disc = disc;
    }

    public String getName() {
        return name;
    }

    public char getDisc() {
        return disc;
    }

    public int makeMove(Board board) {
        int column;
        do {
            column = random.nextInt(7);
        } while (!board.isValidMove(column));
        return column;
    }
}