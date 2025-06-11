/**
 * The class represents the potion with its name, effect and stats,
 * it inherits HealthPotion, SpeedPotion and ManaPotion class.
 */
abstract class Potion {
    
    // Instance variable
    protected String name;
    protected String effect;
    protected int statMod;

    /**
     * 
     * @param name potion's name
     * @param effect potion's effect
     * @param statMod potion's stats
     */
    Potion(String name, String effect, int statMod){
        this.name = name;
        this.effect = effect;
        this.statMod = statMod;
    }

    /**
     * Stat out the name of the potion and then its effect.
     */
    public void potionDesc(){
        System.out.print(this.name + ": " + this.effect);
    }

    /**
     * Allow user to use the potion in Inventory class.
     */
    abstract void usePotion();
        
    /**
     * Get the effect of the potion.
     * 
     * @return potion's effect
     */
    public String getEffect(){
        return this.effect;
    }

    /**
     * Get the name of the effect.
     * 
     * @return potion's name
     */
    public String getName(){
        return this.name;
    }


}
