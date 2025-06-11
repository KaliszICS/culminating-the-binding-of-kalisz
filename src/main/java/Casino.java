import java.util.Scanner;
import java.util.Random;

public class Casino {
    public static void mainCasino() {
        Scanner s = new Scanner(System.in);
        Random random = new Random();

        int gold = Combat.playerGold;

        if (gold <= 0) {
            System.out.println("You are too broke to bet. Come back when you have gold.");
            return;
        }

        System.out.println("\n🎰 Welcome to the Casino! 🎰");

        boolean playing = true;
        while (gold > 0 && playing) {
            int bet = getValidBet(s, gold);
            String playerChoice = getPlayerChoice(s);

            double winChance = Math.max(0.2, 1.0 - ((double) bet / (gold + 1))); 
            boolean playerWins = random.nextDouble() < winChance;
            String correctSide;
            if (random.nextBoolean()) {
                correctSide = "h";
            } else {
                correctSide = "t";
            }

            if (playerChoice.equals(correctSide)) {
                gold += bet;
                System.out.println("🎉 You guessed right! You won " + bet + " gold. New total: " + gold);
            } else {
                gold -= bet;
                System.out.println("💸 You guessed wrong. You lost " + bet + " gold. New total: " + gold);
                if (gold <= 0) {
                    System.out.println("\nYou are out of gold. Come back when you have more.");
                    break;
                }
            }

            playing = askToPlayAgain(s);
        }

        System.out.println("\nReturning to the main menu...");
        Main.showGame();
        s.close();
    }

    private static int getValidBet(Scanner s, int gold) {
        int bet;
        while (true) {
            System.out.print("\nEnter your bet (current gold: " + gold + "): ");
            bet = s.nextInt();
            if (bet > 0 && bet <= gold) {
                s.nextLine(); // Clear buffer
                return bet;
            }
            System.out.println("Invalid bet. Please try again.");
        }
    }

    private static String getPlayerChoice(Scanner s) {
        String choice;
        System.out.println("\nChoose Heads or Tails:");
        while (true) {
            System.out.print("Your choice (H/T): ");
            choice = s.nextLine().toLowerCase();
            if (choice.equals("h") || choice.equals("t")) {
                return choice;
            }
            System.out.println("Invalid input. Please enter H or T.");
        }
    }

    private static boolean askToPlayAgain(Scanner s) {
        while (true) {
            System.out.print("\nPlay again? (Y/N): ");
            String input = s.nextLine().toLowerCase();
            if (input.equals("y")) {
                return true;
            }
            if (input.equals("n")) {
                System.out.println("Thanks for playing!");
                return false;
            }
            System.out.println("Invalid input. Please enter Y or N.");
        }
    }
}
