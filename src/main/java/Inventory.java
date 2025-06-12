
/**
	* The Inventory class allows for the creation of an inventory of the player.
    * By default, it creates an array of potions that will be stored and a parallel array noting the count of the potions.
    * It also allows you to equip up to 4 weapons and swap/add weapons.
    * It also has methods to create a moveset that will be used in combat.
    * Additionally the inventory class allows you to use potions outside of combat(specifically healing ones)
	* @author Jerry Zhu
	* @version 1.0.1
	*/
import java.util.Scanner;
import java.util.ArrayList;

public class Inventory {
    private Potion[] potions;
    private int[] potionCount;
    private int[] potionModNum;
    private String[] potionNames;
    private String[] potionEffects;
    private ArrayList<Weapon> unequipped;
    private Weapon[] equipped;

    /**
     * The Inventory class creates a default inventory that has an array of potions
     * with another array as the counter for each. It also creates an array of
     * weapons, giving you a wooden sword by default, also providing an arraylist
     * for any weapons that are unequipped.
     */
    public Inventory() {
        this.potionNames = new String[] { "Small Healing Potion", "Medium Healing Potion", "Large Healing Potion",
                "Mega Healing Potion", "Speed Potion", "Mana Potion" };
        this.potionEffects = new String[] { "Restores 30 Hp", "Restores 40 Hp", "Restores 40 Hp", "Restores 50 Hp",
                "Increases speed by 20 for the rest of combat", "Restores 50 Mp" };
        this.potions = new Potion[potionNames.length];
        this.potionModNum = new int[] { 30, 40, 50, 60, 20, 50 };
        this.potionCount = new int[potionNames.length];
        // make the potions
        for (int i = 0; i < potionNames.length; i++) {
            if (i == 4) {
                this.potions[i] = new SpeedPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            } else if (i == 5) {
                this.potions[i] = new ManaPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            } else {
                this.potions[i] = new HealthPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            }
        }

        Weapon woodenSword = new Melee("Wooden Sword", 20);
        this.unequipped = new ArrayList<Weapon>();
        this.equipped = new Weapon[] { woodenSword, null, null, null };

    }

    /**
     * This method gets the names of the Potions as a String array
     * 
     * @return returns a String array that represents the names of the potions in
     *         the inventory
     */
    public String[] getPotionNames() {
        return this.potionNames;
    }

    /**
     * This method gets the effects of the Potions as a String array
     * 
     * @return returns a String array that represents the effects of the potions in
     *         the inventory
     */
    public String[] getPotionEffects() {
        return this.potionEffects;
    }

    /**
     * 
     * @param index the index of the potion array that will be retrieved and
     *              returned
     * @return returns the Potion at the given index of the potions array;
     */
    public Potion getPotion(int index) {
        return this.potions[index];
    }

    /**
     * This method gets the gets the Potion array potions
     * 
     * @return returns a String array that represents the potions in the inventory
     */
    public Potion[] getPotions() {
        return this.potions;
    }

    /**
     * This method gets the stat modifier(amount) numbers of the Potions as a int
     * array
     * 
     * @return returns an int array that represents the stat modifier amounts of the
     *         potions in the inventory
     */
    public int[] getPotionModNum() {
        return this.potionModNum;
    }

    /**
     * This method gets the Weapons that are equipped in the inventory
     * 
     * @return returns a Weapon array that represents the weapons that are eqipped
     */
    public Weapon[] getWeapons() {
        return this.equipped;
    }

    /**
     * This method gets the names of the Weapons equipped as a String array
     * 
     * @return returns a String array that represents the names of the Weapons
     *         equipped in the inventory
     */
    public String[] makeMoveSet() {
        String[] moveSet = new String[this.equipped.length];

        for (int i = 0; i < moveSet.length; i++) {
            // get the name if it's not null
            if (this.equipped[i] != null) {
                moveSet[i] = this.equipped[i].getName();
            } else {
                // null weapons have no name
                moveSet[i] = "";
            }
        }
        return moveSet;
    }

    /**
     * This method gets the damage values of the Weapons equipped as an int array
     * 
     * @return returns an int array that represents the damage values of the Weapons
     *         equipped in the inventory
     */
    public int[] makeMoveSetDmg() {
        int[] moveSet = new int[this.equipped.length];
        for (int i = 0; i < moveSet.length; i++) {
            if (this.equipped[i] != null) {
                moveSet[i] = this.equipped[i].getDamage();
            } else {
                // null Weapons do not deal damage
                moveSet[i] = 0;
            }
        }
        return moveSet;
    }

    /**
     * This method gets the mp costs of the Weapons equipped as an int array
     * 
     * @return returns an int array that represents the mp costs of the Weapons
     *         equipped in the inventory
     */
    public int[] makeMoveSetMp() {
        int[] moveSetCosts = new int[this.equipped.length];
        for (int i = 0; i < moveSetCosts.length; i++) {
            if (this.equipped[i] != null) {
                moveSetCosts[i] = this.equipped[i].getMpCost();
            } else {
                // mp cost of null items is 0
                moveSetCosts[i] = 0;
            }
        }
        return moveSetCosts;
    }

    /**
     * This method gets the amount of each individual Potions as a int array
     * 
     * @return returns an int array that represents the amount of each individual
     *         Potion in the inventory
     */
    public int getPotionCount(int index) {
        return this.potionCount[index];
    }

    /**
     * This method removes one potion from the inventory of a certain type(based on
     * index) if there is at least 1 of that potion
     * 
     * @param index The value that dictates the type of potion to remove
     */
    public void decreasePotionCount(int index) {
        if (potionCount[index] > 0) {
            potionCount[index]--;
        }
    }

    /**
     * This method adds a given number of potions of 1 type, dictated by the index
     * provided.
     * 
     * @param index The value that dictates the type of potion to add
     * @param count The amount of potions to be added of a specific type
     */
    public void addPotion(int index, int count) {
        for (int i = 0; i < count; i++) {
            potionCount[index]++;
        }
    }

    /**
     * This method adds a provided Weapon to the inventory, placing it in the
     * unequipped ArrayList
     * 
     * @param weapon A Weapon that is to be added to the inventory
     */
    public void addWeapon(Weapon weapon) {
        this.unequipped.add(weapon);
    }

    /**
     * This method prints out the potions and their effects
     */
    public void potionList() {
        for (int i = 0; i < this.potionCount.length; i++) {
            System.out.print((i + 1) + ". ");
            potions[i].potionDesc();
        }
    }

    /**
     * This method removes one potion from the inventory of a certain type(based on
     * index) if there is at least 1 of that potion
     * 
     * @param index The value that dictates the type of potion to remove
     */
    public void usePotion(int index) {
        this.potionCount[index]--;
    }

    /**
     * this method is reponsible for the display and use of the first page of the
     * inventory
     */
    public void inventoryNav() {

        boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            // user prompt
            System.out.print("\n1. Use a potion");
            System.out.print("\n2. Swap Weapons");
            System.out.print("\n3. Exit");
            System.out.print("\nWhat would you like to do: ");
            // if user imput is not a integer
            while (!input.hasNextInt()) {
                input.nextLine();
                // user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\n1. Use a potion");
                System.out.print("\n2. Swap Weapons");
                System.out.print("\n3. Exit");
                System.out.print("\nWhat would you like to do: ");
            }
            option = input.nextInt();
            input.nextLine();
            // valid integer inputs
            if (option > 0 && option <= 3) {
                running = false;
                if (option == 1) {
                    // open the potion screen
                    this.potionNav();
                } else if (option == 2) {
                    // open the weapon swaps screen
                    weaponMenu();
                }
                // return to main choice page
                else {
                    Main.showGame();
                }
            } else {
                // integer inputs outside the valid range
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }

    /**
     * This method is responsible for running the display and use of potions
     */
    public void potionNav() {

        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            // user prompt
            // only prompt player to be able to use the healing potions(last two are not so
            // thats why the -2)
            for (int i = 0; i < potions.length - 2; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.potions[i].potionDesc();
                System.out.print(", Count: " + potionCount[i]);
            }
            System.out.println("\n5. Exit:");
            System.out.print("\nWhich potion do you want to use: ");
            // input is not an integer check
            while (!input.hasNextInt()) {
                input.nextLine();
                // user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                for (int i = 0; i < potions.length - 2; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.potions[i].potionDesc();
                    System.out.print(", Count: " + potionCount[i]);
                }
                System.out.println("\n5. Exit:");
                System.out.print("\nWhich potion do you want to use: ");
            }
            option = input.nextInt();
            input.nextLine();
            // valid options
            if (option > 0 && option < potions.length - 1) {
                // check if you have the potion
                // no potion of type
                if (potionCount[option - 1] < 1) {
                    System.out.println("You do not have this potion");
                }
                // you have a potion of type
                else {
                    potions[option - 1].usePotion();
                    potionCount[option - 1]--;
                    Main.showGame();
                    running = false;
                }
                // exit option
            } else if (option == potions.length - 1) {
                running = false;
                Main.showGame();
                // outside valid integer range
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }

    /**
     * This method provides a menu to switch out weapons that you currently
     * have(equip or swap weapons).
     */
    public void weaponMenu() {

        int indexEquiped = -1;
        int indexUnequiped = -1;
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            // user prompt
            System.out.println("\nWhat would you like to equip");
            for (int i = 0; i < unequipped.size(); i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.unequipped.get(i).weaponDesc();
            }
            // print weapons that you can equip(not equipped yet)
            System.out.print("\n" + (this.unequipped.size() + 1) + ". Return");
            System.out.println("\nCurrent Weapons\n");

            // print out the current weapons that are equipped
            for (int i = 0; i < this.equipped.length; i++) {
                System.out.print("Slot " + (i + 1) + ". ");
                // check if weapon is null and print emtpy slot
                if (this.equipped[i] == null) {
                    System.out.println("Empty Slot");
                }
                // otherwise print weapon details
                else {
                    this.equipped[i].weaponDesc();
                    System.out.println("");
                }
            }
            // check if user input is an int
            while (!input.hasNextInt()) {
                input.nextLine();
                // user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                // list of weapons uneqipped
                System.out.println("\nWhat would you like to equip");
                for (int i = 0; i < unequipped.size(); i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.unequipped.get(i).weaponDesc();
                    System.out.print("\n" + (this.unequipped.size() + 1) + ". Return");
                    System.out.println("\nCurrent Weapons");
                }
                // equipped weapons
                for (int i = 0; i < this.equipped.length; i++) {
                    System.out.print("\nSlot " + (i + 1) + ". ");
                    if (this.equipped[i] == null) {
                        System.out.print("Empty Slot");
                    } else {
                        this.equipped[i].weaponDesc();
                    }
                }
            }
            option = input.nextInt();
            input.nextLine();
            // valid int inputs for weapon that will replace a specific weapon(to be
            // equipped)
            if (option > 0 && option < this.unequipped.size() + 1) {
                running = false;
                // index of the weapon to be equipped
                indexUnequiped = option - 1;
                // exit option
            } else if (option == this.unequipped.size() + 1) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

        running = true;
        while (running) {
            // user promt(weapon slot to replace)
            System.out.println("\nWhat would you like to replace");
            System.out.println("\nCurrent Weapons");
            for (int i = 0; i < this.equipped.length; i++) {
                System.out.print("\nSlot " + (i + 1) + ". ");
                if (this.equipped[i] == null) {
                    System.out.print("Empty Slot");
                } else {
                    this.equipped[i].weaponDesc();
                }
            }
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.println("\nWhat would you like to replace");
                // print current weapons to be replaced
                System.out.println("\nCurrent Weapons");
                for (int i = 0; i < this.equipped.length; i++) {
                    System.out.print("\nSlot " + (i + 1) + ". ");
                    if (this.equipped[i] == null) {
                        System.out.print("Empty Slot");
                    } else {
                        this.equipped[i].weaponDesc();
                    }
                }
            }
            option = input.nextInt();
            input.nextLine();
            // valid inputs for Weapon to be replaced
            if (option > 0 && option < this.equipped.length + 1) {
                indexEquiped = option - 1;
                running = false;
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
        // swap weapons/move weapon to equipped(if null)
        Weapon temp = unequipped.get(indexUnequiped);
        if (equipped[indexEquiped] != null) {
            unequipped.add(equipped[indexEquiped]);
        }
        unequipped.remove(indexUnequiped);
        equipped[indexEquiped] = temp;
        running = false;
        Main.showGame();
    }

}
