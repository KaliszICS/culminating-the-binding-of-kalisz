import java.io.*;
import java.util.*;

public class Leaderboard {
    private ArrayList<ScoreRecord> records;

    public Leaderboard(String filePath) {
        records = readScores(filePath);
    }

    public static void saveScore(String playerName, long totalTime) {
        try (FileWriter fileWriter = new FileWriter("ScoreRecord.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("\"" + playerName + "\": \"" + totalTime + "\"");

            System.out.println("Score saved successfully!");

        } catch (IOException e) {
            System.err.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }


    private ArrayList<ScoreRecord> readScores(String filePath) {
        ArrayList<ScoreRecord> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
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

    public void sortRecords() {
        Collections.sort(records);
    }

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

    public static class ScoreRecord implements Comparable<ScoreRecord> {
        String playerName;
        int runTime;

        public ScoreRecord(String playerName, int runTime) {
            this.playerName = playerName;
            this.runTime = runTime;
        }

        @Override
        public int compareTo(ScoreRecord other) {
            return Integer.compare(this.runTime, other.runTime);
        }
    }
}