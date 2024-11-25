package org.example;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;
import java.io.StringReader;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    private static final String TEST_URL = "jdbc:h2:mem:connect4db;DB_CLOSE_DELAY=-1";
    private static final String TEST_USER = "root";
    private static final String TEST_PASSWORD = "asdfghjkl";
    private Leaderboard leaderboard;

    @BeforeAll
    static void setupDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD)) {
            String createTableQuery =
                    "CREATE TABLE leaderboard (" +
                            "player_name VARCHAR(255) PRIMARY KEY, " +
                            "wins INT DEFAULT 1" +
                            ");";
            RunScript.execute(connection, new StringReader(createTableQuery));
        }
    }

    @BeforeEach
    void setupLeaderboard() {
        leaderboard = new Leaderboard() {
            // Override connection properties for testing
            protected String getUrl() { return TEST_URL; }
            protected String getUser() { return TEST_USER; }
            protected String getPassword() { return TEST_PASSWORD; }
        };
    }

    @Test
    @DisplayName("Given a new player, when saveWin is called, then their win is saved")
    void testSaveWinForNewPlayer() throws SQLException {
        // Given
        String playerName = "Alice";

        // When
        leaderboard.saveWin(playerName);

        // Then
        try (Connection connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT wins FROM leaderboard WHERE player_name = ?");
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt("wins"));
        }
    }

    @Test
    @DisplayName("Given an existing player, when saveWin is called, then their wins are incremented")
    void testSaveWinForExistingPlayer() throws SQLException {
        // Given
        String playerName = "Bob";
        try (Connection connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO leaderboard (player_name, wins) VALUES (?, ?)");
            statement.setString(1, playerName);
            statement.setInt(2, 2);
            statement.executeUpdate();
        }

        // When
        leaderboard.saveWin(playerName);

        // Then
        try (Connection connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT wins FROM leaderboard WHERE player_name = ?");
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(3, resultSet.getInt("wins"));
        }
    }

    @Test
    @DisplayName("Given leaderboard data, when displayHighScores is called, then the scores are printed")
    void testDisplayHighScores() {
        // Given
        String setupQuery =
                "INSERT INTO leaderboard (player_name, wins) VALUES " +
                        "('Charlie', 5)," +
                        "('Alice', 3)," +
                        "('Bob', 7);";
        try (Connection connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD)) {
            RunScript.execute(connection, new StringReader(setupQuery));
        } catch (SQLException e) {
            fail("Failed to setup data for testDisplayHighScores");
        }

        // When & Then
        // Using system output testing libraries is a good idea for verifying console output
        assertDoesNotThrow(() -> leaderboard.displayHighScores());
    }
}
