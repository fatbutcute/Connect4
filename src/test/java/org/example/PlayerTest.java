package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Given a player with a specific name and disc, when getting the name, then it should return the correct name")
    void testGetName() {
        // Given
        Player player = new Player("Alice", 'R');

        // When
        String name = player.getName();

        // Then
        assertEquals("Alice", name, "The player's name should be 'Alice'.");
    }

    @Test
    @DisplayName("Given a player with a specific name and disc, when getting the disc, then it should return the correct disc")
    void testGetDisc() {
        // Given
        Player player = new Player("Alice", 'R');

        // When
        char disc = player.getDisc();

        // Then
        assertEquals('R', disc, "The player's disc should be 'R'.");
    }

    @Test
    @DisplayName("Given a player and an empty board, when making a move, then it should return a valid column index")
    void testMakeMoveOnEmptyBoard() {
        // Given
        Board board = new Board();
        Player player = new Player("Alice", 'R');

        // When
        int column = player.makeMove(board);

        // Then
        assertTrue(board.isValidMove(column), "The move should be valid on an empty board.");
    }

    @Test
    @DisplayName("Given a player and a board with only one valid column, when making a move, then it should return the valid column")
    void testMakeMoveOnBoardWithOneValidColumn() {
        // Given
        Board board = new Board();
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 6; row++) {
                if (col != 3) board.placeDisc(col, 'B');
            }
        }
        Player player = new Player("Alice", 'R');

        // When
        int column = player.makeMove(board);

        // Then
        assertEquals(3, column, "The player should select the only valid column (column 3).");
    }

    @Test
    @DisplayName("Given a player and a full board, when making a move, then it should not allow a valid move")
    void testMakeMoveOnFullBoard() {
        // Given
        Board board = new Board();
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board.placeDisc(col, 'B');
            }
        }
        Player player = new Player("Alice", 'R');

        // When/Then
        assertThrows(IllegalStateException.class, () -> {
            player.makeMove(board);
        }, "An exception should be thrown when the board is full and no valid moves are possible.");
    }
}
