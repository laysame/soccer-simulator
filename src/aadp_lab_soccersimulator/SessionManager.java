package aadp_lab_soccersimulator;

import java.util.Scanner;

public class SessionManager {
    private Scanner scanner;

    public SessionManager() {
        scanner = new Scanner(System.in);
    }

    String getTeamName(String[] teams) {
        String teamName;
        boolean validTeam = false;

        do {
            System.out.println("For which team would you like to enter data?");
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
    int getPlayerNumber() {
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
    
}

