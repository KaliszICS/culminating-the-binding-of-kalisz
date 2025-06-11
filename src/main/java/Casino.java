import java.util.Scanner;
import java.util.Random;

public class Casino {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        
        //player gold for now
        int gold = Combat.playerGold;

        //this is for when player intial gold is under a dollar, after this statement -> return back to main menu
        if (gold <= 0) {
            System.out.println("You are too broke to bet. Come back when you have gold.");
        }
            
        System.out.println("\nWelcome to the Casino!");    
       
        
        boolean game = true;
        while (gold > 0 && game) {
            int bet = 0;
            while (true) {
                System.out.print("\nInsert the amount of gold you want to bet on: ");
                bet = s.nextInt();
                if (bet <= gold && bet > 0){
                    break;
                }
                else {
                    System.out.println("\nInvalid bet. Please try again.");
                }
            }
            s.nextLine();

            String choice = "";
            System.out.println("\nPlease choose Heads or Tails");
            while (game) {           
                System.out.print("Your choice (Type H/T): ");
                choice = s.nextLine().toLowerCase();
                if (choice.equalsIgnoreCase("H") || choice.equalsIgnoreCase("T")) {
                break;
                }
                else {
                    System.out.println("\nInvalid input. Please try again.\n"); 
                }
            }
            
            boolean headsOrTails = random.nextBoolean();
            String result;
            if (headsOrTails == true) {
                result = "T";
            }
            else {
                result = "H";
            }
            if (choice.equalsIgnoreCase(result)) {
                gold += bet;
                System.out.println("Congrats! You won. Now you have " + gold + " gold.");
            }
            else {
                gold -= bet;
                System.out.println("You lost. Now you have " + gold + " gold.");
                if (gold <= 0) {
                    System.out.println("\nYou are out of gold. Come back when you have more.");
                    break;
                }
            }
            while (game) {
                System.out.println("\nDo you want to play again?");
                System.out.print("Your choice (Y/N): ");
                String againChoice = s.nextLine().toLowerCase();
                if (againChoice.equalsIgnoreCase("Y")) {
                    break;
                } 
                else if (againChoice.equalsIgnoreCase("N")) {
                    System.out.println("\nThank you for playing! Hope to see you back!");
                    game = false;
                    break;
                    }
                    else {
                        System.out.println("\nInvalid input. Please select Y or N");
                    }
            }        
        }           
        s.close();
    } 
}  
    // link back to main menu or game
    // System.out.println("TELEPORTING BACK TO MAIN MENU...");










