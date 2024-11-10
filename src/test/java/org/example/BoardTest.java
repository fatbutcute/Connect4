package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    @DisplayName("Given an empty board, when checking if a move is valid, then it should return true for a valid column")
    void testIsValidMoveOnEmptyBoard() {
        // Given
        Board board = new Board();

        // When
        boolean isValid = board.isValidMove(3);

        // Then
        assertTrue(isValid, "The move should be valid on an empty board for a valid column.");
    }

    @Test
    @DisplayName("Given a board with a full column, when checking if a move is valid, then it should return false")
    void testIsValidMoveOnFullColumn() {
        // Given
        Board board = new Board();
        for (int i = 0; i < 6; i++) {
            board.placeDisc(2, 'R');
        }

        // When
        boolean isValid = board.isValidMove(2);

        // Then
        assertFalse(isValid, "The move should be invalid as the column is full.");
    }

    @Test
    @DisplayName("Given a board, when placing a disc in an empty column, then it should place the disc in the lowest available row")
    void testPlaceDiscInEmptyColumn() {
        // Given
        Board board = new Board();

        // When
        board.placeDisc(4, 'R');

        // Then
        assertEquals('R', board.getGrid()[5][4], "The disc should be placed in the lowest row of the column.");
    }

    @Test
    @DisplayName("Given a board with some discs, when checking if the board is full, then it should return false")
    void testIsFullWithEmptySlots() {
        // Given
        Board board = new Board();
        board.placeDisc(0, 'R');
        board.placeDisc(1, 'B');

        // When
        boolean isFull = board.isFull();

        // Then
        assertFalse(isFull, "The board should not be full with empty slots remaining.");
    }

    @Test
    @DisplayName("Given a completely filled board, when checking if the board is full, then it should return true")
    void testIsFullWithFilledBoard() {
        // Given
        Board board = new Board();
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board.placeDisc(col, 'R');
            }
        }

        // When
        boolean isFull = board.isFull();

        // Then
        assertTrue(isFull, "The board should be full when all slots are filled.");
    }

    @Test
    @DisplayName("Given a board with four consecutive horizontal discs, when checking for a winner, then it should return true")
    void testCheckWinnerHorizontal() {
        // Given
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.placeDisc(i, 'R');
        }

        // When
        boolean hasWinner = board.checkWinner('R');

        // Then
        assertTrue(hasWinner, "There should be a winner with four consecutive horizontal discs.");
    }

    @Test
    @DisplayName("Given a board with four consecutive vertical discs, when checking for a winner, then it should return true")
    void testCheckWinnerVertical() {
        // Given
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.placeDisc(0, 'R');
        }

        // When
        boolean hasWinner = board.checkWinner('R');

        // Then
        assertTrue(hasWinner, "There should be a winner with four consecutive vertical discs.");
    }

    @Test
    @DisplayName("Given a board with four consecutive diagonal discs (down-right), when checking for a winner, then it should return true")
    void testCheckWinnerDiagonalDownRight() {
        // Given
        Board board = new Board();
        board.placeDisc(0, 'R');
        board.placeDisc(1, 'B');
        board.placeDisc(1, 'R');
        board.placeDisc(2, 'B');
        board.placeDisc(2, 'B');
        board.placeDisc(2, 'R');
        board.placeDisc(3, 'B');
        board.placeDisc(3, 'B');
        board.placeDisc(3, 'B');
        board.placeDisc(3, 'R');

        // When
        boolean hasWinner = board.checkWinner('R');

        // Then
        assertTrue(hasWinner, "There should be a winner with four consecutive diagonal discs (down-right).");
    }

    @Test
    @DisplayName("Given a board with four consecutive diagonal discs (up-right), when checking for a winner, then it should return true")
    void testCheckWinnerDiagonalUpRight() {
        // Given
        Board board = new Board();
        board.placeDisc(3, 'R');
        board.placeDisc(2, 'B');
        board.placeDisc(2, 'R');
        board.placeDisc(1, 'B');
        board.placeDisc(1, 'B');
        board.placeDisc(1, 'R');
        board.placeDisc(0, 'B');
        board.placeDisc(0, 'B');
        board.placeDisc(0, 'B');
        board.placeDisc(0, 'R');

        // When
        boolean hasWinner = board.checkWinner('R');

        // Then
        assertTrue(hasWinner, "There should be a winner with four consecutive diagonal discs (up-right).");
    }
}
