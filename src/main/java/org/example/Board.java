package org.example;

public class Board {
    private final char[][] grid = new char[6][7];

    public Board() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void display() {
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (char[] row : grid) {
            System.out.print("|");
            for (char cell : row) {
                System.out.print(cell + "|");
            }
            System.out.println();
            System.out.println("---------------");
        }
    }

    public boolean isValidMove(int column) {
        return column >= 0 && column < grid[0].length && grid[0][column] == ' ';
    }

    public void placeDisc(int column, char disc) {
        for (int row = grid.length - 1; row >= 0; row--) {
            if (grid[row][column] == ' ') {
                grid[row][column] = disc;
                break;
            }
        }
    }

    public boolean isFull() {
        for (char cell : grid[0]) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner(char disc) {
        // Horizontal check
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == disc && grid[row][col + 1] == disc && grid[row][col + 2] == disc && grid[row][col + 3] == disc) {
                    return true;
                }
            }
        }

        // Vertical check
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == disc && grid[row + 1][col] == disc && grid[row + 2][col] == disc && grid[row + 3][col] == disc) {
                    return true;
                }
            }
        }

        // Diagonal (down-right) check
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == disc && grid[row + 1][col + 1] == disc && grid[row + 2][col + 2] == disc && grid[row + 3][col + 3] == disc) {
                    return true;
                }
            }
        }

        // Diagonal (up-right) check
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == disc && grid[row - 1][col + 1] == disc && grid[row - 2][col + 2] == disc && grid[row - 3][col + 3] == disc) {
                    return true;
                }
            }
        }

        return false;
    }
}

