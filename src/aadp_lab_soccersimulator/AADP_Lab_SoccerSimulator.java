/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aadp_lab_soccersimulator;

import java.util.Scanner;

/**
 * @author Sam
 * Please put the names and student numbers of the team here:
 * <p>
 * Name 1: Laysa Dias
 * Number 1: 2021295
 * <p>
 * Name 2: Leonardo Oliveira
 * Number 2: 2021361
 * <p>
 * Name 3: Gabriel Eugenio
 * Number 3: 2021240
 * <p>
 * Name 4: Guilherme Felix
 * Number 4 2021309
 */

public class AADP_Lab_SoccerSimulator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] teams = {"Ireland", "Brazil", "Argentina", "Japan", "Mexico", "Senegal", "Tunisia", "Qatar"};

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createTeams(teams);

        SessionManager sessionManager = new SessionManager(databaseManager);

        int option;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Welcome! What would you like to do today? Please enter the number corresponding to your choice.");
            System.out.println("1. Enter a new player to a team.");
            System.out.println("2. See the players on a team.");
            System.out.println("3. Simulate a number of matches.");
            System.out.println("4. Exit the programme.");
            try {
                option = Integer.parseInt(sc.nextLine());
                if (option == 1) {
                    System.out.println("Please follow the instructions to enter player data.");
                    String teamName = sessionManager.getTeamName(teams);
                    String name;
                    int number = 0;
                    String birth;
                    String position;
                    int goalsScored = 0;
                    String background;

                    System.out.println("Please enter the player's name: ");
                    name = sessionManager.getPlayerName();

                    System.out.println("Please enter the player's number: ");
                    number = sessionManager.getPositiveNumber();

                    System.out.println("Please enter the player's date of birth: ");
                    birth = sessionManager.getPlayerBirth();

                    System.out.println("Please enter the player's position: ");
                    position = sessionManager.getPlayerPosition();

                    System.out.println("Please enter the number of goals the player has scored: ");
                    goalsScored = sessionManager.getPositiveNumber();

                    System.out.println("Please enter the player's background: ");
                    background = sessionManager.getPlayerBackground();

                    System.out.println("Thank you for entering a player");
                    databaseManager.addPlayer(teamName, name, number, birth, position, goalsScored, background);

                } else if (option == 2) {
                    String teamName = sessionManager.getTeamName(teams);
                    sessionManager.printPlayerData(teamName);

                } else if (option == 3) {
                    System.out.println("How many matches would you like to simulate?");
                    int numMatches = sessionManager.getPositiveNumber();

                    for (int matchNum = 1; matchNum <= numMatches; matchNum++) {
                        int team1Num = (int) (Math.floor(Math.random() * teams.length));
                        String team1 = teams[team1Num];
                        int team2Num;
                        do {
                            team2Num = (int) (Math.floor(Math.random() * teams.length));
                        } while (team1Num == team2Num);
                        String team2 = teams[team2Num];
                        int team1Score = (int) (Math.floor(Math.random() * 6));
                        int team2Score = (int) (Math.floor(Math.random() * 6));
                        System.out.println("Time for mathch: " + matchNum);
                        if (team1Score > team2Score) {
                            System.out.println(String.format("Congratulation %s! %s scored %d goals and %s scored %d goals.", team1, team1, team1Score, team2, team2Score));
                        } else if (team1Score < team2Score) {
                            System.out.println(String.format("Congratulation %s! %s scored %d goals and %s scored %d goals.", team2, team1, team1Score, team2, team2Score));
                        } else {
                            System.out.println(String.format("It was a draw!! %s scored %d goals and %s scored %d goals.", team1, team1Score, team2, team2Score));
                        }
                    }
                } else if (option == 4) {
                    System.out.println("Goodbye, and thank you for using the SoccerSimulator!");
                    exit = true;
                } else {
                    System.out.println("That is an invalid number. Please try again!");
                }
            } catch (Exception e) {
                System.out.println("That is not a number. Please try again!");
            }
        } while (!exit);
    }
}
