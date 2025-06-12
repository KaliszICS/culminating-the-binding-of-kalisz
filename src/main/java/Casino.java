import java.util.Scanner;
import java.util.Random;

/**
 * This is a simulation of the casino. Players can bet gold and flip a coin 
 * to attempt increasing their gold. 
 * The system is rigged — the higher the bet, the lower the chance of winning.
 * 
 * This class uses Math.random (via Random) to simulate chance while 
 * maintaining unfair odds in favor of the casino.
 * 
 * @author Marcus & Karthik
 */
public class Casino {

    /**
     * Starts the main casino game. Checks if the user has gold,
     * allows betting, flips a coin, and determines win/loss
     * Updates player's gold and asks if they want to return to main menu when done.
     */
    public static void mainCasino() {
        Scanner s = new Scanner(System.in); 
        Random random = new Random();

        int gold = Combat.playerGold; 

        if (gold <= 0) {
            System.out.println("You are too broke to bet. Come back when you have gold.");
            Main.showGame();
            return;
        }

        System.out.println("\n🎰 Welcome to the Casino! 🎰");

        boolean playing = true;

        while (gold > 0 && playing) {
            int bet = getValidBet(s, gold);
            String playerChoice = getPlayerChoice(s);

            // Rigged win chance formula: higher bet = lower win chance
            double winChance = Math.max(0.2, 1.0 - ((double) bet / (gold + 1)));
            boolean playerWins = random.nextDouble() < winChance;

            String correctSide = random.nextBoolean() ? "h" : "t";

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

        Combat.playerGold = gold;
        System.out.println("\nReturning to the main menu...");
        Main.showGame();
        s.close();
    }

    /**
     * The user must enter a bet greater than 0, while less than the amount of gold they have
     * 
     * @param s Scanner object for input
     * @param gold the player's current gold
     * @return the validated bet amount
     */
    private static int getValidBet(Scanner s, int gold) {
        int bet = -1;
        boolean running = true;
        
        while(running){
            
            System.out.print("\nEnter your bet (current gold: " + gold + "): ");
            while(!(s.hasNextInt())){
                s.nextLine();
            System.out.print("\nInvalid bet! Please input an integer");
            System.out.print("\nEnter your bet (current gold: " + gold + "): ");
            }
            
            bet = s.nextInt();
            if (bet > 0 && bet <= gold) {
                s.nextLine(); // Clear input buffer
                return bet;
            }
            System.out.println("Invalid bet. Please try again.");
            
        }
return bet;
    }

    /**
     * Asks the player to choose "Heads" or "Tails" for the coin flip.
     * Accepts "H" or "T" (case-insensitive) else will loop back for an invalid input
     * 
     * @param s Scanner object for input
     * @return "h" for heads or "t" for tails
     */
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

    /**
     * Asks the player if they want to play again after each round.
     * Accepts "Y" or "N" (case-insensitive) else will loop them back for an invalid input
     * 
     * @param s Scanner object for input
     * @return true if the player wants to play again, false otherwise
     */
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
