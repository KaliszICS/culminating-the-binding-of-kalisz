import java.util.Scanner;

public class Shop {
    

    private Magic[] magicWeapons;
    private Melee[] meleeWeapons;
    private Potion[] potions;
    private int[] potionCost;
    private int[] magicCost;
    private int[] meleeCost;


    public Shop() {
        String[] magicNames = new String[]{"Fire Staff", "Wind Staff", "Lightning Staff", "Ice Staff"};
        int[] magicDmg = new int[]{70, 50, 80, 60};
        int[] mpCosts = new int[]{20, 10, 25, 15};
        this.magicCost = new int[]{200, 150, 225, 175};
        this.magicWeapons = new Magic[magicNames.length];
        for(int i = 0; i < magicWeapons.length; i++){
            Magic m = new Magic(magicNames[i], magicDmg[i], mpCosts[i]);
            magicWeapons[i] = m;
        }

        String[] meleeNames = new String[]{"Wooden Sword", "Stone Sword", "Iron Sword", "Diamond Sword", "Obsidian Sword", "Void Sword"};
        int[] meleeDmg = new int[]{20, 25, 30, 35, 40, 45};
        this.meleeCost = new int[]{30, 40, 50, 60, 70, 80};
        this.meleeWeapons = new Melee[meleeNames.length];
        for(int i = 0; i < meleeWeapons.length; i++){
            Melee m = new Melee(meleeNames[i], meleeDmg[i]);
            meleeWeapons[i] = m;
        }


        this.potions = Main.inventory.getPotions();
        this.potionCost = new int[] {15, 30, 60, 120, 40, 40};


    }

    public void mainShop(){
 boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhat would you like to do: ");
            System.out.print("\n1. Buy a potion");
            System.out.print("\n2. Buy a weapon");
            System.out.print("\n3. Go Gambling");
            System.out.print("\n3. Exit");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhat would you like to do: ");
                System.out.print("\n1. Buy a potion");
                System.out.print("\n2. Buy a weapon");
                System.out.print("\n3. Go Gambling");
                System.out.print("\n4. Exit");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option <= 3) {
                running = false;
                if (option == 1) {
                    running = false;
                    this.potionStore();
                }
                else if(option==2){
                    running = false;
                    this.weaponStore();
                }

                else if(option == 3){
                    running = false;
                    Casino.mainCasino();
                }
                else {
                    running = false;
                    Main.showGame();
                }
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    } 

    public void weaponStore(){
        boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhat would you like to buy: ");
            System.out.print("\n1. Magic Weapons");
            System.out.print("\n2. Melee Weapons");
            System.out.print("\n3. Exit");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhat would you like to buy: ");
                System.out.print("\n1. Magic Weapons");
                System.out.print("\n2. Melee Weapons");
                System.out.print("\n3. Exit");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option <= 3) {
                running = false;
                if (option == 1) {
                    running = false;
                    this.magicStore();
                }
                else if(option==2){
                    running = false;
                    this.meleeStore();
                }
                else {
                    running = false;
                    Main.showGame();
                }
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }


    public void magicStore(){
        
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhich magic weapon do you want to buy: ");
            for (int i = 0; i < this.magicWeapons.length - 1; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.magicWeapons[i].weaponDesc();
                System.out.print(", Cost: " + magicCost[i]);
            }
            System.out.println("\n" + (magicWeapons.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich magic weapon do you want to buy: ");
                for (int i = 0; i < magicWeapons.length - 1; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.magicWeapons[i].weaponDesc();
                    System.out.print(", Cost: " + magicCost[i]);
                }
                System.out.println("\n" + (magicWeapons.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < magicWeapons.length) {
                if (magicCost[option - 1] > Main.player.getGold()) {
                    System.out.println("You do not enough money");
                } else {
                    Main.inventory.addWeapon(magicWeapons[option-1]);
                    running = false;
                    Main.showGame();
                }
            } else if (option == magicWeapons.length+1) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

    }

    public void meleeStore(){
        
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhich melee weapon do you want to buy: ");
            for (int i = 0; i < this.meleeWeapons.length- 1; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.meleeWeapons[i].weaponDesc();
                System.out.print(", Cost: " + meleeCost[i]);
            }
            System.out.println("\n" + (meleeWeapons.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich melee weapon do you want to buy: ");
                for (int i = 0; i < meleeWeapons.length - 1; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.meleeWeapons[i].weaponDesc();
                    System.out.print(", Cost: " + meleeCost[i]);
                }
                System.out.println("\n" + (meleeWeapons.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < meleeWeapons.length) {
                if (meleeCost[option - 1] > Main.player.getGold()) {
                    System.out.println("You do not enough money");
                } else {
                    Main.inventory.addWeapon(meleeWeapons[option-1]);
                    running = false;
                    Main.showGame();
                }
            } else if (option == meleeWeapons.length+1) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

    }

    public void potionStore(){

        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhich potion do you want to buy: ");
            for (int i = 0; i < this.potions.length - 1; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.potions[i].potionDesc();
                System.out.print(", Cost: " + potionCost[i]);
            }
            System.out.println("\n" + (this.potions.length+1) + ". Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich potion do you want to buy: ");
                for (int i = 0; i < this.potions.length - 1; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.potions[i].potionDesc();
                    System.out.print(", Cost: " + potionCost[i]);
                }
                System.out.println("\n" + (this.potions.length+1) + ". Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < this.potions.length) {
                if (potionCost[option - 1] > Main.player.getGold()) {
                    System.out.println("You do not enough money");
                } else {
                    Main.inventory.addPotion(option-1, 1);
                    Main.player.subtractGold(potionCost[option-1]);
                    running = false;
                }
            } else if (option == this.potions.length+1) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    
}

    public void buyArmor(){
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
                System.out.print("\nWould you like to upgrade your armor?: ");
                System.out.print("\n1. Yes(+10Maxhp, -25 gold");
                System.out.print("\n2. No");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWould you like to upgrade your armor?: ");
                System.out.print("\n1. Yes(+10Maxhp, -25 gold");
                System.out.print("\n2. No");
                }
                System.out.println("\n" + (potions.length+1) + ". Exit:");
                option = input.nextInt();
                input.nextLine();
                if (option == 1) {
                    if (25 > Main.player.getGold()) {
                        System.out.println("You do not enough money");
                    } else {
                        Main.player.addMaxHp(10);
                        Main.player.subtractGold(potionCost[option-1]);
                        running = false;
                    }
                } else if (option == 2) {
                    running = false;
                    Main.showGame();
                } else {
                    System.out.println("Invalid choice! Please enter a valid number");
                }
            }
        }
    
    }


