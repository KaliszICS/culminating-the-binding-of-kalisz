import java.util.Scanner;
import java.util.Random;

/**
 * Manages turn-based combat between the player and a randomly selected enemy.
 * 
 * The battle continues until either the player's HP or the enemy's HP drops to zero.
 * Support attack moves, potions, and enemy actions (damage, heal, speed buffs).
 */
public class Combat {
    /**
     * Shared inventory instance from Main.
     */
    static Inventory inventory = Main.inventory;

    // Player attributes
    static String playerName = Main.name;
    static int playerHp = 100;
    static int playerMaxHp = 100;
    static int playerMp = 100;
    static int playerMaxMp = 100;
    static int playerSpeed = 60;
    static int tempPlayerSpeed = playerSpeed; //stores base speed to restore after buffs/debuffs
    static int playerGold = 50;

    //Potions data pulled from inventory
    static String[] potionMoveset = inventory.getPotionNames();
    static int[] potionHealAmounts = inventory.getPotionModNum();
    
    // Enemy (Monster)
    static Entity enemy;
    
    static Scanner scanner = new Scanner(System.in);
    
    /**
     * Begins a combat encounter in the specified room.
     * 
     * A random monster is selected, then the player and enemy take turns until one is defeated.
     * If the player wins in the final room, their time is recorded on the leaderboard.
     * 
     * @param room The room in which combat takes place.
     */
    public static void mainCombat(Room room) {
        //Select a random monster for this room
        enemy = Room.getRandomMonster(room.getRoomNumber());
        
        //Continue battle while both fighter have HP remaining.
        while (playerHp > 0 && enemy.hp > 0) {
            displayBattleScreen();
            playerTurn();
            
            //If enemy died, handle victory.
            if (enemy.hp <= 0) {
                System.out.println("\nEnemy " + enemy.name + " is defeated! You win!");
                if (room.getRoomNumber() == 32) {
                    Main.endTime = System.currentTimeMillis();
                    long totalTime = (Main.endTime - Main.startTime) / 1000;
                    System.out.println("\n\u001B[33mYou completed the game in " + totalTime + " seconds!\u001B[0m");
                    Leaderboard.saveScore(Main.name, totalTime);
                    return; //exit combat entirely
                }
                //Award gold, clear room, then wait for user to exit.
                int goldReward = enemy.maxHp / 2;
                playerGold += goldReward;
                System.out.println("\nYou earned " + goldReward + " gold! Total Gold: " + playerGold);
                Main.map.clearRoom(room.getRoomNumber() - 1);
                boolean running = true;
                //Loop until user types "exit" to leave combat screen.
                while (running) {
                    System.out.print("Type \"Exit\" to leave the room: ");
                    String choiceOption = scanner.nextLine();
                    if (choiceOption.equalsIgnoreCase("exit")) {
                        running = false;
                        playerSpeed = tempPlayerSpeed; //Restores base speed
                        Main.showGame(); //Return to main game UI
                        break;
                    } else {
                        System.out.print("\nINVALID INPUT PLEASE TRY AGAIN");
                    }
                }
            }

            //If player still alive, enemy takes a turn.
            if (enemy.hp > 0) {
                enemyTurn();
                System.out.println("\n--------------------- End of Turn ---------------------\n");
            }
            
            //If player died, handle defeat.
            if (playerHp <= 0) {
                System.out.println("\nYou have been defeated. Game over.");
                playerSpeed = tempPlayerSpeed;
                break;
            }
        }
        //goes to main menu if loss
        Main.mainMenu();
    }

    /**
     * Displays the current hp, mp, speed, and status of the enemy
     */
    private static void displayBattleScreen() {
        System.out.println("======================= Battle ========================\n");
        System.out.println("Player: " + playerName);
        System.out.println("HP: " + playerHp + "/" + playerMaxHp);
        System.out.println("MP: " + playerMp + "/" + playerMaxMp);
        System.out.println("Speed: " + playerSpeed + "\n");
        
        System.out.println("Enemy: " + enemy.name);
        System.out.println("HP: " + enemy.hp + "/" + enemy.maxHp);
        System.out.println("Speed: " + enemy.speed);
        System.out.println("Status: " + enemy.status + "\n");
    }
    
    /**
     * Processes the player's turn: choose Attack or use a Potion.
     * 
     * Invalid choices cost the player their turn.
     */
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
    
    /**
     * Handles the player's attack phase: choose a move, check MP cost, calculate hit/miss, apply damage.
     * 
     * Confusing bit: movesets and costs are pulled fresh from inventory each attack, so changes in inventory immediately show up here.
     */
    private static void handleAttack() {
        // Build the current moveset & associated arrays
        String[] attackMoveset = Main.inventory.makeMoveSet();
        int[] attackDamageMoveset = Main.inventory.makeMoveSetDmg();
        int[] mpDeduction = Main.inventory.makeMoveSetMp();
        System.out.println("\nChoose an attack move:");
        for (int i = 0; i < attackMoveset.length; i++) {
            if (mpDeduction[i] > 0) {
                System.out.print((i + 1) + ". " + attackMoveset[i] + " (MP Cost: " + mpDeduction[i] + ")   ");
            } else {
                System.out.print((i + 1) + ". " + attackMoveset[i] + "   ");
            }
        }
        System.out.println();
        System.out.print("Select your move (1-4): ");
        int attackOption = scanner.nextInt();
        scanner.nextLine();

        // Validate choice; default to first move if out of range
        if (attackOption < 1 || attackOption > attackMoveset.length) {
            System.out.println("Invalid attack option, defaulting to the first move.");
            attackOption = 1;
        }

        int mpCost = mpDeduction[attackOption - 1];

        if (playerMp < mpCost) {
            System.out.println(
                    "\n\u001B[31mNot enough MP to use " + attackMoveset[attackOption - 1] + "! Turn lost.\u001B[0m");
            return;
        }

        playerMp -= mpCost; // Deduct MP cost
        int damage = attackDamageMoveset[attackOption - 1];
        int hitChance = (int) (Math.random() * 100) + 1;

        // Hit if random roll ≤ playerSpeed
        if (hitChance <= playerSpeed) {
            System.out.println("\nYou used " + attackMoveset[attackOption - 1] + " and dealt " + damage + " damage!");
            if (mpCost > 0) {
                System.out.println("MP deducted: " + mpCost + " | Remaining MP: " + playerMp);
            }
            enemy.hp -= damage;
            if (enemy.hp < 0) {
                enemy.hp = 0;
            }
        } else {
            System.out.println("\nYour attack missed!");
        }
    }

    /**
     * Handles the player using a potion. 
     */
    private static void handlePotion() {
        System.out.println("\nChoose a potion:");
        // List all available potions + a Cancel option
        for (int i = 0; i < potionMoveset.length; i++) {
            System.out.println((i + 1) + ". " + potionMoveset[i] + " - " + inventory.getPotionEffects()[i] +
                    " (Count: " + inventory.getPotionCount(i) + ")");
        }
        System.out.println((potionMoveset.length + 1) + ". Cancel");

        System.out.print("Select a potion (1-" + (potionMoveset.length + 1) + "): ");
        int potionChoice = scanner.nextInt();
        scanner.nextLine();

        // Cancel or invalid -> skip turn
        if (potionChoice < 1 || potionChoice > potionMoveset.length + 1) {
            System.out.println("Invalid option. Turn skipped.");
            return;
        }

        if (potionChoice == potionMoveset.length + 1) {
            System.out.println("Cancelled.\n");
            playerTurn();
            return;
        }

        int index = potionChoice - 1;
        int count = inventory.getPotionCount(index);

        if (count < 1) {
            System.out.println("You don't have any of this potion left!");
            return;
        }

        // Remove one from inventory
        Potion chosenPotion = inventory.getPotion(index);
        inventory.decreasePotionCount(index);

        // Apply the correct effect based on potion type
        if (chosenPotion instanceof HealthPotion) {
            int healAmount = potionHealAmounts[index];
            playerHp += healAmount;
            if (playerHp > playerMaxHp)
                playerHp = playerMaxHp;
        } else if (chosenPotion instanceof SpeedPotion) {
            Main.inventory.getPotion(index).usePotion();
            int buff = potionHealAmounts[index];
            playerSpeed += buff;
        } else if (chosenPotion instanceof ManaPotion){
            int mpHealAmount = potionHealAmounts[index];
            playerMp += mpHealAmount;
            if (playerMp > playerMaxMp) {
                playerMp = playerMaxMp;
            }
        } else {
            System.out.println("You used an unknown potion. Nothing happened...");
        }
    }

    /**
     * Processes the enemy's turn: randomly selects a move, the applies damage, heal, or speed buff.
     */
    private static void enemyTurn() {
        System.out.println("\nEnemy's turn!");
        int enemyMove = new Random().nextInt(enemy.moveset.length);
        System.out.println("Enemy uses " + enemy.moveset[enemyMove] + "!");

        if (enemy.damage[enemyMove] > 0) {
            int enemyDamage = enemy.damage[enemyMove];
            System.out.println("It deals " + enemyDamage + " damage to you!");
            playerHp -= enemyDamage;
            if (playerHp < 0) {
                playerHp = 0;
            }
        } else if (enemy.heal[enemyMove] > 0) {
            int heal = enemy.heal[enemyMove];
            System.out.println("Enemy heals for " + heal + " HP!");
            enemy.hp += heal;
            if (enemy.hp > enemy.maxHp) {
                enemy.hp = enemy.maxHp;
            }
        } else if (enemy.speedBuff[enemyMove] > 0) {
            int buff = enemy.speedBuff[enemyMove];
            enemy.speed += buff;
            System.out.println("Enemy increases its speed by " + buff + "!");
        } else {
            System.out.println("Enemy did something... mysterious!");
        }
    }
}