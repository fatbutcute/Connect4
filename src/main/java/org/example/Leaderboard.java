package org.example;

import java.sql.*;

public class Leaderboard {
    private final String url = "jdbc:mysql://localhost:3306/connect4";
    private final String user = "gabiszmo"; //
    private final String password = "asdfghjkl";

    public void saveWin(String playerName) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO leaderboard (player_name, wins) VALUES (?, 1) " +
                    "ON DUPLICATE KEY UPDATE wins = wins + 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, playerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayHighScores() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT player_name, wins FROM leaderboard ORDER BY wins DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("High Scores:");
            System.out.println("Player Name | Wins");
            while (resultSet.next()) {
                System.out.printf("%s | %d%n", resultSet.getString("player_name"), resultSet.getInt("wins"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
