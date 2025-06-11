import java.io.*;
import java.util.*;

/**
 * Manages player scores by reading from and writing to a leaderboard file.
 * 
 * This class provides functionality to save, read, sort, and display scores
 * from a file while maintaining leaderboard ranking.
 */
public class Leaderboard {
    private ArrayList<ScoreRecord> records;

    /**
     * Constructs a Leaderboard instance by reading scores from the provider file.
     * 
     * @param filePath The file path containing leaderboard scores.
     */
    public Leaderboard(String filePath) {
        records = readScores(filePath);
    }

    /**
     * Saves a new score to the leaderboard file in the format "Name": "Time".
     * 
     * @param playerName The name of the player.
     * @param totalTime  The recorded time for the player.
     */
    public static void saveScore(String playerName, long totalTime) {
        try (FileWriter fileWriter = new FileWriter("ScoreRecord.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            //Append the player's score in the expected file format
            printWriter.println("\"" + playerName + "\": \"" + totalTime + "\"");

            System.out.println("Score saved successfully!");

        } catch (IOException e) {
            System.err.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }

    /**
     * Reads scores from the leaderboard file and parses them into a list of ScoreRecord object.
     * 
     * @param filePath The file path containing score records.
     * @return A list of ScoreRecord objects sorted by time.
     */
    private ArrayList<ScoreRecord> readScores(String filePath) {
        ArrayList<ScoreRecord> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":"); //Split "Name": "Time"
                if (parts.length == 2) {
                    //Remove surrounding quotation marks and trim spaces.
                    String name = parts[0].trim().replaceAll("^\"|\"$", "");
                    String timeStr = parts[1].trim().replaceAll("^\"|\"$", "");
                    try {
                        int time = Integer.parseInt(timeStr);
                        list.add(new ScoreRecord(name, time));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid time format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    /**
     * Sorts the leaderboard records in ascending order based on the player time. 
     */
    public void sortRecords() {
        Collections.sort(records);
    }

    /**
     * Displays the leaderboard, ranking players by their recorded times.
     * 
     * If no records exist, a message will displayed indicating an empty leaderboard.
     */
    public void displayRecords() {
        if (records == null || records.isEmpty()) {
            System.out.println("No leaderboard records found.");
            return;
        }

        System.out.println("====== Leaderboard ======\n");
        for (int i = 0; i < records.size(); i++) {
            ScoreRecord rec = records.get(i);
            System.out.println((i + 1) + ". " + rec.playerName + " - " + rec.runTime);
        }
    }

    /**
     * Represents a player's score record.
     * 
     * This class holds player name and completion time, while supporting comparison for sorting.
     */
    public static class ScoreRecord implements Comparable<ScoreRecord> {
        String playerName;
        int runTime;

        /**
         * Constructs a new ScoreRecord object.
         * 
         * @param playerName The name of the player.
         * @param runTime The recorded time for the player.
         */
        public ScoreRecord(String playerName, int runTime) {
            this.playerName = playerName;
            this.runTime = runTime;
        }

        /**
         * Compares two score records based on completion time.
         * 
         * Used for sorting leaderboard entries from best (lowest time) to worst.
         * @param other Another ScoreRecord to compare against.
         * @return A negative, zero, or positive integer based on comparison
         */
        @Override
        public int compareTo(ScoreRecord other) {
            return Integer.compare(this.runTime, other.runTime);
        }
    }
}