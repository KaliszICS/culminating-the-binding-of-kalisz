/**
 * This class inherits from Potion class.
 */
public class ManaPotion extends Potion{
    
    /**
     * 
     * @param name mana potion's name
     * @param effect mana potion's effect 
     * @param statMod mana potion's speeding stats
     */
    ManaPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
    }

    /**
     * State out the name of the potion and the stats of the potion,
     * the amount it speeds the user.
     */
    public void usePotion(){
    System.out.print("\nYou have used a " + super.name + ". Your Mp inceasaes by " + statMod + ".");
    }
}
