/**
	* The Shop class allows for the creation of a Shop for the player to purchase items from. By default, it creates an array of potions that will be stored and a parallel array noting the cost of the potions. The same is done for Magic Weapons and Melee weapons. You can pay gold to buy an armor upgrade, increasing your max hp. Lastly, you can also access the casino from this class.
	* @author Jerry Zhu, Marcus Wong
	* @version 1.0.1
	*/

import java.util.Scanner;


public class Shop {
    

    private Magic[] magicWeapons;
    private Melee[] meleeWeapons;
    private Potion[] potions;
    private int[] potionCost;
    private int[] magicCost;
    private int[] meleeCost;

    /**
     * This contructor makes sets of items for sale(a set of magic weapons, melee weapons, and potions) with parallel arrays associated with the costs of each individual item)
     */
    public Shop() {

        //magic weapons
        String[] magicNames = new String[]{"Fire Staff", "Wind Staff", "Lightning Staff", "Ice Staff"};
        int[] magicDmg = new int[]{70, 50, 80, 60};
        int[] mpCosts = new int[]{20, 10, 25, 15};
        this.magicCost = new int[]{200, 150, 225, 175};
        this.magicWeapons = new Magic[magicNames.length];
        for(int i = 0; i < magicWeapons.length; i++){
            Magic m = new Magic(magicNames[i], magicDmg[i], mpCosts[i]);
            magicWeapons[i] = m;
        }

        //melee weapons
        String[] meleeNames = new String[]{"Wooden Sword", "Stone Sword", "Iron Sword", "Diamond Sword", "Obsidian Sword", "Void Sword"};
        int[] meleeDmg = new int[]{20, 25, 30, 35, 40, 45};
        this.meleeCost = new int[]{30, 40, 50, 60, 70, 80};
        this.meleeWeapons = new Melee[meleeNames.length];
        for(int i = 0; i < meleeWeapons.length; i++){
            Melee m = new Melee(meleeNames[i], meleeDmg[i]);
            meleeWeapons[i] = m;
        }

        //potions(taken from inventory)
        this.potions = Main.inventory.getPotions();
        this.potionCost = new int[] {15, 30, 45, 60, 20, 40};


    }
    /**
     * this method is Responsible for the running of the first and main page of the Shop where you can access all the other parts. It allows you access the casino, go buy potions, or go buy armor
     */
    public void mainShop(){
 boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            //user prompts
            System.out.print("\nWhat would you like to do: ");
            System.out.print("\n1. Buy a potion");
            System.out.print("\n2. Buy a weapon");
            System.out.print("\n3. Buy armor");
            System.out.print("\n4. Go Gambling");
            System.out.print("\n5. Exit");
            //check if input is integer
            while (!input.hasNextInt()) {
                input.nextLine();
                //user prompts
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhat would you like to do: ");
                System.out.print("\n1. Buy a potion");
                System.out.print("\n2. Buy a weapon");
                System.out.print("\n3. Buy armor");
                System.out.print("\n4. Go Gambling");
                System.out.print("\n5. Exit");
            }
            option = input.nextInt();
            input.nextLine();
            //valid inputs
            if (option > 0 && option <= 5) {
                running = false;
                if (option == 1) {
                    //go to potions screen
                    running = false;
                    this.potionStore();
                }
                else if(option == 2){
                    //go to the weapon screen
                    running = false;
                    this.weaponStore();
                }
                else if(option == 3){
                    //go to armor screen
                    running = false;
                    this.buyArmor();
                }

                else if(option == 4){
                    //go to casino
                    running = false;
                    Casino.mainCasino();
                }
                else {
                    //back to main map display
                    running = false;
                    Main.showGame();
                }
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    } 


    /**
     * This method is responsible for the secondary screen for the shop and is responsible for accessing both the melee weapons shop and the magic weapons shop.
     */
    public void weaponStore(){
        boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            //use prompt
            System.out.print("\nWhat would you like to buy: ");
            System.out.print("\n1. Magic Weapons");
            System.out.print("\n2. Melee Weapons");
            System.out.print("\n3. Exit");
            //check for integer input
            while (!input.hasNextInt()) {
                input.nextLine();
                //user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhat would you like to buy: ");
                System.out.print("\n1. Magic Weapons");
                System.out.print("\n2. Melee Weapons");
                System.out.print("\n3. Exit");
            }
            option = input.nextInt();
            input.nextLine();
            //valid inputs
            if (option > 0 && option <= 3) {
                running = false;
                if (option == 1) {
                    //magic weapon store
                    running = false;
                    this.magicStore();
                } 
                else if(option==2){
                    //melee weapon store
                    running = false;
                    this.meleeStore();
                }
                else {
                    //go to main map and selection screen
                    running = false;
                    Main.showGame();
                    }
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }

    /**
     * this method is reponsible for running the magic weapon section of the store and allowing players to browse/purchase magical weapons
     */
    public void magicStore(){
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            //user prompt(provides the selection of magic weapons)
            System.out.print("\nWhich magic weapon do you want to buy: ");
            for (int i = 0; i < this.magicWeapons.length; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.magicWeapons[i].weaponDesc();
                System.out.print(", Cost: " + magicCost[i]);
            }
            //exit option
            System.out.println("\n" + (magicWeapons.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                //user prompt(provides the selection of magic weapons)
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich magic weapon do you want to buy: ");
                for (int i = 0; i < magicWeapons.length; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.magicWeapons[i].weaponDesc();
                    System.out.print(", Cost: " + magicCost[i]);
                }
                //exit option
                System.out.println("\n" + (magicWeapons.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            //valid input range
            if (option > 0 && option < magicWeapons.length) {
                //checks if the player has the money to buy
                if (magicCost[option - 1] > Combat.playerGold) {
                    System.out.println("You do not enough money");
                } else {
                    //buy and add the weapon
                    Main.inventory.addWeapon(magicWeapons[option-1]);
                    Combat.playerGold -= magicCost[option-1];
                    running = false;
                    //exit to map/main selection screen
                    Main.showGame();
                }
            } else if (option == magicWeapons.length+1) {
                running = false;
                //exit to map/main selection screen
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

    }
    /**
     * this method is reponsible for running the melee weapon section of the store and allowing players to browse/purchase melee weapons
     */
    public void meleeStore(){
        
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
             //user prompt(provides the selection of melee weapons)
            System.out.print("\nWhich melee weapon do you want to buy: ");
            for (int i = 0; i < this.meleeWeapons.length; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.meleeWeapons[i].weaponDesc();
                System.out.print(", Cost: " + meleeCost[i]);
            }
            //exit option
            System.out.println("\n" + (meleeWeapons.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                 //user prompt(provides the selection of melee weapons)
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich melee weapon do you want to buy: ");
                for (int i = 0; i < meleeWeapons.length; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.meleeWeapons[i].weaponDesc();
                    System.out.print(", Cost: " + meleeCost[i]);
                }
                //exit option
                System.out.println("\n" + (meleeWeapons.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            //valid inputs
            if (option > 0 && option < meleeWeapons.length) {
                //checks if the player has the money to buy
                if (meleeCost[option - 1] > Combat.playerGold) {
                    System.out.println("You do not enough money");
                } else {
                    //buy and add the weapon
                    Main.inventory.addWeapon(meleeWeapons[option-1]);
                    Combat.playerGold -= meleeCost[option-1];
                    running = false;
                    //exit to map/main selection screen
                    Main.showGame();
                }
            } else if (option == meleeWeapons.length+1) {
                running = false;
                //exit to map/main selection screen
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

    }

    /**
     * this method is reponsible for running the potion section of the store and allowing players to browse/purchase potions
     */
    public void potionStore(){

        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            //user prompt(provides the selection of potions)
            System.out.print("\nWhich potion do you want to buy: ");
            for (int i = 0; i < this.potions.length; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.potions[i].potionDesc();
                System.out.print(", Cost: " + potionCost[i]);
            }
            //exit option
            System.out.println("\n" + (this.potions.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                //user prompt(provides the selection of potions)
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich potion do you want to buy: ");
                for (int i = 0; i < this.potions.length; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.potions[i].potionDesc();
                    System.out.print(", Cost: " + potionCost[i]);
                }
                //exit option
                System.out.println("\n" + (this.potions.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            //valid input range
            if (option > 0 && option < this.potions.length) {
                //checks for adequate funds
                if (potionCost[option - 1] > Combat.playerGold) {
                    System.out.println("You do not enough money");
                } else {
                    //buy and add potion to inventory
                    Main.inventory.addPotion(option-1, 1);
                    Combat.playerGold -= (potionCost[option-1]);
                    running = false;
                    //exit to map/main selection screen
                    Main.showGame();
                }
            } else if (option == this.potions.length+1) {
                running = false;
                //exit to map/main selection screen
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    
}
    /**
     * this method is reponsible for running the armor section of the store and allowing players to purchase armor which increases their maxHP
     */
    public void buyArmor(){
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
                //user prompts
                System.out.print("\nWould you like to upgrade your armor?: ");
                System.out.print("\n1. Yes(+10 Maxhp, -25 gold)");
                //exit option
                System.out.print("\n2. No");
            while (!input.hasNextInt()) {
                input.nextLine();
                //user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWould you like to upgrade your armor?: ");
                System.out.print("\n1. Yes(+10 Maxhp, -25 gold)");
                //exit option
                System.out.print("\n2. No");
                }
                option = input.nextInt();
                input.nextLine();
                //try to buy armor
                if (option == 1) {
                    //check for adequate funds
                    if (25 > Combat.playerGold) {
                        System.out.println("You do not enough money");
                    } else {
                        //buy armor
                        Main.player.addMaxHp(10);
                        Combat.playerMaxHp += 10;
                        Combat.playerHp += 10;
                        Combat.playerGold -= 25;
                        running = false;
                        //exit to map/main selection screen
                        Main.showGame();
                    }
                } else if (option == 2) {
                    running = false;
                    //exit to map/main selection screen
                    Main.showGame();
                } else {
                    System.out.println("Invalid choice! Please enter a valid number");
                }
            }
        }
    
    }


