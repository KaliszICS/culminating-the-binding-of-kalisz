import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main for class for "The Binding of Kalisz". 
 * 
 * Contains entry point, main menu and navitgation to leaderboard and shop.
 */
public class Main {

	/**
	 * Start time of the game.
	 */
	static long startTime;

	/**
	 * End time of the game.
	 */
	static long endTime;
	
	/**
	 * Entry point of the game.
	 */
	public static void main(String args[]) {
		mainMenu();
	}

	/**
	 * Allow to clear the console.
	 */
	// INTERNET HELP
	// (https://github.com/karlasophiacruz/p3-softwareproject-refactoried/blob/9e01fb2e424f91121c1b64e6328e8450227d02d5/payroll/src/app/AuxFunctions.java)
	public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Inventory instance
	 */
	static Inventory inventory = new Inventory();

	/**
	 * Player's name
	 */
	static String name;

	/**
	 * Display of the main menu,
	 * allow user to choose options they want,
	 * navigate to the game
	 */
	public static void mainMenu() {
		clearConsole();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			System.out.println("==== The Binding of Kalisz ====\n");
			System.out.println("1. Start Game");
			System.out.println("2. Leaderboard");
			System.out.println("3. Exit\n");
			System.out.print("Enter your choice (1-3): ");
			while(!(scanner.hasNextInt())){
			scanner.nextLine();
			System.out.println("\nInvalid input! Please choose a valid nuber.");
			System.out.println("1. Start Game");
			System.out.println("2. Leaderboard");
			System.out.println("3. Exit\n");
			System.out.print("Enter your choice (1-3): ");
			}
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
				System.out.print("Choose a name: ");
				name = scanner.nextLine();
				startTime = System.currentTimeMillis();
				showGame();
				running = false;
				break;
				case 2:
				showLeaderBoard();
				running = false;
				break;
					case 3:
					System.out.println("Exiting the game...");
					running = false;
					break;
				default:
				System.out.println("Invalid choice! Please enter a number between 1 and 3.");
					break;
			}
		}

		scanner.close();
	}

	/**
	 * Display leaderboard, user must type "exit" to leave leaderboard and back to main menu.
	 */
	public static void showLeaderBoard() {
		clearConsole();
		Leaderboard leaderboard = new Leaderboard("ScoreRecord.txt");
		leaderboard.sortRecords();
		leaderboard.displayRecords();

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.print("\nType \"Exit\" to main menu: ");
			String choiceOption = scanner.nextLine();
			if (choiceOption.equalsIgnoreCase("exit")) {
				running = false;
				mainMenu();
			} else {
				System.out.print("\nINVALID INPUT PLEASE TRY AGAIN");
			}
		}
		scanner.close();
	}

	/**
	 * Shop instance
	 */
	static Shop shop = new Shop();

	/**
	 * PLayer instance
	 */
	static Player player = new Player(name, 100, 100, 70, null, null, null, null, null, null, 0);

	/**
	 * Map instance
	 */
	static Map map = new Map();

	/**
	 * Start the game after dispaying the map.
	 */
	public static void showGame() {
		clearConsole();
		map.print();
	}

	/**
	 * End the game and clear the 
	 */
	public static void endGame() {
		clearConsole();
	}
}