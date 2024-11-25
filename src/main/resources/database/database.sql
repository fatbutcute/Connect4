CREATE DATABASE connect4;

USE connect4;

CREATE TABLE leaderboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL,
    wins INT DEFAULT 0
);