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
    private Armor[] armors;
    public Inventory() {
        this.potionNames = new String[] { "Small Healing Potion", "Medium Healing Potion", "Large Healing Potion",
                "Mega Healing Potion", "Speed Potion", "Mana Potion" };
        this.potionEffects = new String[] { "Restores 10 Hp", "Restores 20 Hp", "Restores 30 Hp", "Restores 40 Hp",
                "Increases speed by 20 for the rest of combat", "Restores 50 Mp" };
        this.potions = new Potion[potionNames.length];
        this.potionModNum = new int[] { 10, 20, 30, 40, 20, 50};
        this.potionCount = new int[potionNames.length];
        for (int i = 0; i < potionNames.length; i++) {
            if (i == 4) {
                this.potions[i] = new SpeedPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            } else if (i == 5) {
                this.potions[i] = new ManaPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            } else {
                this.potions[i] = new HealthPotion(potionNames[i], potionEffects[i], potionModNum[i]);
            }
        }

        String[] armorNames = new String[] { "Leather Armor", "Copper Armor", "Bronze Armor", "Chainmail Armor",
                "Iron Armor", "Gold Armor", "Diamond Armor", "Platinum Armor", "Mithril Armor", "Obamium" };
        int increment = 10;
        this.armors = new Armor[armorNames.length];
        for (int i = 0; i < armorNames.length; i++) {
            Armor a = new Armor(armorNames[i], (i+1)*increment, false);
            this.armors[i] = a;
            
            armors[0].setHasArmor(true);
            Weapon stick = new Melee("Stick", 200);            
            Weapon woodenSword = new Melee("Wooden Sword", 20);
            this.unequipped = new ArrayList<Weapon>();
            this.unequipped.add(stick);
            this.equipped = new Weapon[] {woodenSword, null, null, null};
            
        }
    }

    public String[] getPotionNames() {
        return this.potionNames;
    }

    public String[] getPotionEffects() {
        return this.potionEffects;
    }

    public Potion getPotion(int index) {
        return this.potions[index];
    }

    public int[] getPotionModNum() {
        return this.potionModNum;
    }

    public Weapon[] getWeapons(){
        return this.equipped;
    }
    public String[] makeMoveSet(){
        String[] moveSet = new String[this.equipped.length];
        for(int i = 0; i < moveSet.length; i++){
            if(this.equipped[i] != null){
                moveSet[i] = this.equipped[i].getName();
            }
            else{
                moveSet[i] = "";
            }
        }
        return moveSet;
    }

    public int[] makeMoveSetDmg(){
        int[] moveSet = new int[this.equipped.length];
        for(int i = 0; i < moveSet.length; i++){
            if(this.equipped[i] != null){
                moveSet[i] = this.equipped[i].getDamage();
            }
            else{
                moveSet[i] = 0;
            }
        }
        return moveSet;
    }

    public int[] makeMoveSetMp(){
        int[] moveSetCosts = new int[this.equipped.length];
        for(int i = 0; i < moveSetCosts.length; i++){
            if(this.equipped[i] != null){
                moveSetCosts[i] = this.equipped[i].getMpCost();
            }
            else{
                moveSetCosts[i] = 0;
            }
        }
        return moveSetCosts;
    }

    public int getPotionCount(int index) {
        return this.potionCount[index];
    }

    public void decreasePotionCount(int index) {
        if (potionCount[index] > 0) {
            potionCount[index]--;
        }
    }

    public void addPotion(int index, int count) {
        for (int i = 0; i < count; i++) {
            potionCount[index]++;
        }
    }
    
    public void addWeapon(Weapon weapon){
        this.unequipped.add(weapon);
    }

    public void potionList() {
        for (int i = 0; i < this.potionCount.length; i++) {
            System.out.print((i + 1) + ". ");
            potions[i].potionDesc();
        }
    }

    public void usePotion(int index){
        this.potionCount[index]--;
    }


    public int getDefense(){
        int defense = 0;
        for(int i = this.armors.length - 1; i >= 0; i--){
            if(armors[i].hasArmor()){
                return armors[i].getDefense();
            }
        }
        return defense;
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
                    weaponMenu();
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
        
        int indexEquiped = -1;
        int indexUnequiped = -1;
        int option = -1;
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println("\nWhat would you like to equip");
        for (int i = 0; i < unequipped.size(); i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.unequipped.get(i).weaponDesc();
    }
        System.out.print("\n" + (this.unequipped.size()+1) + ". Return");
        System.out.println("\nCurrent Weapons\n");
       for (int i = 0; i < this.equipped.length; i++){
            System.out.print("Slot " + (i + 1) + ". ");
        if(this.equipped[i]==null){
            System.out.println("Empty Slot");
        }
        else{
            this.equipped[i].weaponDesc();
            System.out.println("");
        }
    }
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.println("\nWhat would you like to equip");
        for (int i = 0; i < unequipped.size(); i++) {
                System.out.print("\n" + (i + 1) + ". ");
                this.unequipped.get(i).weaponDesc();
    }
        System.out.print("\n" + (this.unequipped.size()+1) + ". Return");
        System.out.println("\nCurrent Weapons");
       for (int i = 0; i < this.equipped.length; i++){
            System.out.print("\nSlot " + (i + 1) + ". ");
        if(this.equipped[i]==null){
            System.out.print("Empty Slot");
        }
        else{
            this.equipped[i].weaponDesc();
        }
            }
        }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < this.unequipped.size()+1) {
                running = false;
                indexUnequiped = option-1;
            } else if (option == this.unequipped.size()+1) {
                running = false;
                Main.showGame();
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }

        running = true;
        while (running) {
            System.out.println("\nWhat would you like to replace");

        System.out.println("\nCurrent Weapons");
       for (int i = 0; i < this.equipped.length; i++){
            System.out.print("\nSlot " + (i + 1) + ". ");
        if(this.equipped[i]==null){
            System.out.print("Empty Slot");
        }
        else{
            this.equipped[i].weaponDesc();
        }
    }
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.println("\nWhat would you like to replace");
        
        System.out.println("\nCurrent Weapons");
       for (int i = 0; i < this.equipped.length; i++){
            System.out.print("\nSlot " + (i + 1) + ". ");
        if(this.equipped[i]==null){
            System.out.print("Empty Slot");
        }
        else{
            this.equipped[i].weaponDesc();
        }
            }
        }
            option = input.nextInt();
            input.nextLine();
            if (option > 0 && option < this.equipped.length+1) {
            indexEquiped = option-1;
            running = false;
            } else {
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
Weapon temp = unequipped.get(indexUnequiped);
if(equipped[indexEquiped] != null){
    unequipped.add(equipped[indexEquiped]);
}
unequipped.remove(indexUnequiped);
equipped[indexEquiped] = temp;
        Main.showGame();
    }

    }

