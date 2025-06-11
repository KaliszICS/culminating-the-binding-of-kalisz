import java.util.Scanner;

import java.util.ArrayList;

public class Inventory {
    private Potion[] potions;
    private int[] potionCount;
    private int[] potionModNum;

    // private Armor[] armors;
    public Inventory() {
        String[] potionNames = new String[] { "Small Healing Potion", "Medium Healing Potion", "Large Healing Potion",
                "Mega Healing Potion", "Speed Potion" };
        String[] potionEffects = new String[] { "Restores 10 Hp", "Restores 20 Hp", "Restores 30 Hp", "Restores 40 Hp",
                "Increases speed by 20 for the rest of combat" };
        this.potions = new Potion[potionNames.length];
        this.potionModNum = new int[] { 10, 20, 30, 40, 20};
        this.potionCount = new int[potionNames.length];
        for (int i = 0; i < potionNames.length-1; i++) {
            Potion p = new HealthPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            this.potions[i] = p;
        }
            Potion p = new SpeedPotion(potionNames[potionNames.length-1],potionEffects[potionEffects.length-1], this.potionModNum[potions.length-1]);
            this.potions[potions.length-1] = p;

        String[] ArmorNames = new String[] { "Leather Armor", "Copper Armor", "Bronze Armor", "Chainmail Armor",
                "Iron Armor", "Gold Armor", "Diamond Armor", "Platinum Armor", "Mithril Armor", "Obamium" };
        int increment = 10;
        for (int i = 0; i < ArmorNames.length; i++) {
            // Armor a = new Armor(ArmorNames[i], (i+1)*increment, false);
            // this.armors[i] = a;

            //armors[0].setTrue;


        String[] weaponNames = new String[] {};
        String[] weaponDamage = new String[] {};
        }
    }

    public void addPotion(int index, int count) {
        for (int i = 0; i < count; i++) {
            potionCount[index]++;
        }
    }

    public void potionList() {
        for (int i = 0; i < this.potionCount.length; i++) {
            System.out.print((i + 1) + ". ");
            potions[i].potionDesc();
        }
    }



    public int getDefense(){
        for(int i = this.armors.length - 1; i >= 0; i--){
            if(armors[i].hasArmor){
                return armors[i].getDefense;
            }
        }
    }

    public void inventoryNav() {
        boolean running = true;
        int option = -1;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhat would you like to do: ");
            System.out.print("\n1. Use a potion");
            System.out.print("\n2. Swap Weapons");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhat would you like to do: ");
                System.out.print("\n1. Use a potion");
                System.out.print("\n2. Swap Weapons");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option <= 2) {
                running = false;
                if (option == 1) {
                    this.potionNav();
                } else {
                    // weaponMenu();
                }
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }

    public void potionNav() {
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("\nWhich potion do you want to use: ");
            for (int i = 0; i < potions.length - 1; i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.potions[i].potionDesc();
                System.out.print(", Count: " + potionCount[i]);
            }
            System.out.println("\n5. Exit:");
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nWhich potion do you want to use: ");
                for (int i = 0; i < potions.length - 1; i++) {
                    System.out.print("\n" + (i + 1) + ". ");
                    this.potions[i].potionDesc();
                    System.out.print(", Count: " + potionCount[i]);
                }
                System.out.println("\n5. Exit:");
            }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < potions.length) {
                if (potionCount[option - 1] < 1) {
                    System.out.println("You do not have this potion");
                } else {
                    potions[option-1].usePotion();
                    potionCount[option - 1] --;
                    running = false;
                }
            } else if (option == potions.length) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
    }

    public void weaponMenu(){
        System.out.println("What would you like to equip");
    }
}
