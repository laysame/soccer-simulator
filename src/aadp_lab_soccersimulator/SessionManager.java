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
}
