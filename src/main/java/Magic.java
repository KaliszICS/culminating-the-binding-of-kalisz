/**
 * This class inherits from Weapon class.
 */
public class Magic extends Weapon{ 
    
    /**
     * 
     * @param name magic spell's name
     * @param damage magic spell's damage
     * @param mpCost magic spell's cost
     */
    public Magic(String name, int damage, int mpCost){
        super(name, damage, mpCost);
    }

    /**
     * State out the name, damage and the cost of the magic spell.
     */
    @Override
    void weaponDesc() {
        System.out.print(this.name + ": Damage: " + this.damage + ", Mp Cost: " + mpCost +"mp");
    }
}
