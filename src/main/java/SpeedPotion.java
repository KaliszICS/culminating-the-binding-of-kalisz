/**
 * This class inherits from Potion class.
 */
public class SpeedPotion extends Potion {
    
    /**
     * 
     * @param name speed potion's name
     * @param effect speed potion's effect 
     * @param statMod speed potion's speeding stats
     */
    public SpeedPotion(String name, String effect, int statMod){
        super(name, effect, statMod);   
    }

    /**
     * State out the potion name and the stats of the potion,
     * the amount it speeds the user.
     */
    public void usePotion(){
        System.out.print("\nYou have used a " + super.name + ". Your speed inceasaes by " + statMod + ".");
    }
}
