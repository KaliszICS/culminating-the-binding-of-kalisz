import java.util.Scanner;

public class Combat {
    // Player attributes
    static String playerName = "Test";
    static int playerHp = 100;
    static int playerMaxHp = 100;
    static int playerMp = 100;
    static int playerMaxMp = 100;
    static int playerSpeed = 50;

    // Enemy attributes
    static String enemyName = "Johnson";
    static int enemyHp = 100;
    static int enemyMaxHp = 100;
    static int enemySpeed = 80;
    static String enemyStatus = "Boss";

    // Player movesets
    static String[] attackMoveset = {"Slash", "Stab", "Fireball", "Thunder"};
    static int[] attackDamageMoveset = {5, 8, 9, 30};

    static String[] potionMoveset = {"Small Potion", "Medium Potion", "Large Potion", "Mega Potion"};
    static int[] potionHealAmounts = {10, 20, 30, 40};

    // Enemy movesets
    static String[] enemyMoveset = {"Slash", "Stab", "Heal", "Quick Step"};
    static int[] enemyAttackDamageMoveset = {8, 10, 0, 0};
    static int[] enemyHealAmounts = {0, 0, 15, 0};
    static int[] enemySpeedBuff = {0, 0, 0, 20};

    static Scanner scanner = new Scanner(System.in);

    public static void mainCombat() {
        // Main battle loop: continues until one side's HP drops to 0
        while (playerHp > 0 && enemyHp > 0) {
            displayBattleScreen();
            playerTurn();
            if (enemyHp <= 0) {
                System.out.println("\nEnemy " + enemyName + " is defeated! You win!");
                break;
            }
            enemyTurn();
            if (playerHp <= 0) {
                System.out.println("\nYou have been defeated. Game over.");
                break;
            }
            System.out.println("\n--------------------- End of Turn ---------------------\n");
        }
        scanner.close();
    }

    // Displays the current status of both player and enemy
    private static void displayBattleScreen() {
        System.out.println("======================= Battle ========================\n");
        System.out.println("Player: " + playerName);
        System.out.println("HP: " + playerHp + "/" + playerMaxHp);
        System.out.println("MP: " + playerMp + "/" + playerMaxMp);
        System.out.println("Speed: " + playerSpeed + "\n");

        System.out.println("Enemy: " + enemyName);
        System.out.println("HP: " + enemyHp + "/" + enemyMaxHp);
        System.out.println("Enemy Speed: " + enemySpeed);
        System.out.println("Status: " + enemyStatus + "\n");
    }

    // Processes the player's turn and available options
    private static void playerTurn() {
        System.out.println("1. Attack    2. Potion");
        System.out.print("\nWhat option would you like to do?: ");
        int combatOption = scanner.nextInt();
        scanner.nextLine();

        switch (combatOption) {
            case 1:
                handleAttack();
                break;
            case 2:
                handlePotion();
                break;
            default:
                System.out.println("Invalid option! You lose your turn.");
                break;
        }
    }

    // Handles the player attack move
    private static void handleAttack() {
        System.out.println("\nChoose an attack move:");
        for (int i = 0; i < attackMoveset.length; i++) {
            System.out.print((i + 1) + ". " + attackMoveset[i] + "   ");
        }
        System.out.println();
        System.out.print("Select your move (1-4): ");
        int attackOption = scanner.nextInt();
        scanner.nextLine();

        if (attackOption < 1 || attackOption > attackMoveset.length) {
            System.out.println("Invalid attack option, defaulting to the first move.");
            attackOption = 1;
        }
        int damage = attackDamageMoveset[attackOption - 1];

        // Using player's speed as the hit chance
        int hitChance = (int) (Math.random() * 100) + 1;
        if (hitChance <= playerSpeed) {
            System.out.println("\nYou used " + attackMoveset[attackOption - 1] + " and dealt " + damage + " damage!");
            enemyHp -= damage;
            if (enemyHp < 0) {
                enemyHp = 0;
            }
        } else {
            System.out.println("\nYour attack missed!");
        }
    }

    // Handles the player potion move
    private static void handlePotion() {
        System.out.println("\nChoose a potion:");
        for (int i = 0; i < potionMoveset.length; i++) {
            System.out.print((i + 1) + ". " + potionMoveset[i] + "   ");
        }
        System.out.println();
        System.out.print("Select a potion (1-4): ");
        int potionChoice = scanner.nextInt();
        scanner.nextLine();

        if (potionChoice < 1 || potionChoice > potionMoveset.length) {
            System.out.println("Invalid potion option, defaulting to the first potion.");
            potionChoice = 1;
        }
        int healAmount = potionHealAmounts[potionChoice - 1];
        System.out.println("\nYou used " + potionMoveset[potionChoice - 1] + " and healed " + healAmount + " HP!");
        playerHp += healAmount;
        if (playerHp > playerMaxHp) {
            playerHp = playerMaxHp;
        }
    }

    // Processes the enemy's turn with randomized move selection
    private static void enemyTurn() {
        System.out.println("\nEnemy's turn!");
        int enemyMove = (int) (Math.random() * enemyMoveset.length) + 1;
        System.out.println("Enemy uses " + enemyMoveset[enemyMove - 1] + "!");

        // Process move based on enemy action
        if (enemyMove == 1 || enemyMove == 2) {
            int enemyDamage = enemyAttackDamageMoveset[enemyMove - 1];
            System.out.println("It deals " + enemyDamage + " damage to you!");
            playerHp -= enemyDamage;
            if (playerHp < 0) {
                playerHp = 0;
            }
        } else if (enemyMove == 3) {
            int enemyHeal = enemyHealAmounts[enemyMove - 1];
            System.out.println("Enemy heals for " + enemyHeal + " HP!");
            enemyHp += enemyHeal;
            if (enemyHp > enemyMaxHp) {
                enemyHp = enemyMaxHp;
            }
        } else if (enemyMove == 4) {
            int buff = enemySpeedBuff[enemyMove - 1];
            enemySpeed += buff;
            System.out.println("Enemy increases its speed by " + buff + "!");
        }
    }
}
