package aadp_lab_soccersimulator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SessionManager {
    private DatabaseManager databaseManager;
    private Scanner scanner;

    public SessionManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        scanner = new Scanner(System.in);
    }

    String getTeamName(String[] teams) {
        String teamName;
        boolean validTeam = false;

        do {
            System.out.println("Which team you are interested in?");
            teamName = scanner.nextLine();

            for (String team : teams) {
                if (teamName.toLowerCase().equals(team.toLowerCase())) {
                    validTeam = true;
                    break;
                }
            }
            if (teamName.toLowerCase().equals("exit")) break;
            if (!validTeam) System.out.println("That is not one of the teams. Please try again!");
        } while (!validTeam);

        return teamName;
    }

    int getPositiveNumber() {
        int number = 0;
        boolean validPlayer = false;

        do {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number < 1) {
                    System.out.println("Please enter a positive integer");
                } else validPlayer = true;

            } catch (Exception e) {
                System.out.println("That is not a number. please try again!");
            }
        } while (!validPlayer);

        return number;
    }

    String getPlayerBirth() {
        return scanner.nextLine();
    }

    String getPlayerPosition() {
        return scanner.nextLine();
    }

    void printPlayerData(String teamName) {
        ResultSet rs = databaseManager.getTeamPlayers(teamName);
        try {
            String name;
            int number;
            String birth;
            String position;
            int goalsScored;
            String background;
            while (rs.next()) {
                name = rs.getString("name");
                number = rs.getInt("number");
                birth = rs.getString("birth");
                position = rs.getString("position");
                goalsScored = rs.getInt("goalsScored");
                background = rs.getString("background");
                System.out.println(String.format("Name: %s -- Number: %d -- DoB: %s -- Position: %s -- Number of goals scored: %d", name, number, birth, position, goalsScored));
                System.out.println("Background:");
                System.out.println(background);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

