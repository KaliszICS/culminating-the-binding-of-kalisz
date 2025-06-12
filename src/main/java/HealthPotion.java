/**
 * This class inherits from Potion class.
 */
public class HealthPotion extends Potion{
    
    /**
     * runs the super class' constuctor 
     * @param name health potion's name
     * @param effect health potion's effect
     * @param statMod health potion's healing stats
     */
    public HealthPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
    
    }
    /**
     * State out the potion name and the stats of the potion,
     * the amount it heals.
     */
    public void usePotion(){
        System.out.print("\nYou have used a " + super.name + ". You heal " + statMod + " Hp.");
}

}