/**
 * The class represents the weapon with its name, damage and cost,
 * it inherits Magic and Melee class.
 */
abstract class Weapon {
    
    //instance variables
    protected String name;
    protected int damage;
    protected int mpCost;
    
    /**
     * 
     * @param name weapon's name
     * @param damage weapon's damage 
     * @param mpCost weapon's cost
     */
    public Weapon (String name, int damage, int mpCost) {
        this.name = name;
        this.damage = damage;
        this.mpCost = mpCost;
    }
    
    /**
     * Get the name of the weapon.
     * 
     * @return weapon's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the cost of the weapon.
     * 
     * @return weapon's cost
     */
    public int getMpCost(){
    
        return this.mpCost;
    }

    /**
     * Get the damage of the weapon.
     * 
     * @return weapon's damage
     */
    public int getDamage() {
        return this.damage;
    }
    
    /**
     * 
     * @param name name is the String that is replacing the stored one
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @param damage damage is the int that is replacing the stored one
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * For inventory and shop class to display the description of the weapon.
     */
    abstract void weaponDesc();

}


    
