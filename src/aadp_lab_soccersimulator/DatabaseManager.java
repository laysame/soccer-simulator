package aadp_lab_soccersimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private String dbName = "world_cup";
    private String DB_URL = "jdbc:mysql://localhost/" + dbName;
    private String USER = "root";
    private String PASS = "root";

    Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void createTeams(String[] teams) {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + dbName + ";");
            stmt.execute("USE " + dbName + ";");

            for (String team : teams) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS " + team + " ("
                                + "name VARCHAR(30) NOT NULL,"
                                + "number INT NOT NULL PRIMARY KEY,"
                                + "birth VARCHAR(30),"
                                + "position VARCHAR(30),"
                                + "goalsScored INT,"
                                + "background TEXT(1000));"
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void addPlayer(String teamName, String name, int number, String birth, String position, int goalsScored, String background) {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(
                    String.format("INSERT INTO %s (name, number, birth, position, goalsScored, background) "
                                    + "VALUES (\"%s\", %d, \"%s\", \"%s\", %d,  \"%s\") ;",
                            teamName, name, number, birth, position, goalsScored, background)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    ResultSet getTeamPlayers(String teamName) {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from " + teamName + ";");
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
