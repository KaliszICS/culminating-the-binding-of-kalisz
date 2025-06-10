public class Shop {
    
    protected Armor[] armor;
    protected Weapon[] weapon;
    protected Potion[] potion;

    public Shop(Armor[] armor, Weapon[] weapon, Potion[] potion) {
        this.armor = armor;
        this.weapon = weapon;
        this.potion = potion;
    }

    public Shop() {
        this.armor = new Armor[0];
        this.weapon = new Weapon[0];
        this.potion = new Potion[0];
    }


}

