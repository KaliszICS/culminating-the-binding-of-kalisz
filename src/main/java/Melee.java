/**
 * This class inherits from Weapon class.
 */
public class Melee extends Weapon {
    
    /**
     * 
     * @param name melee's name
     * @param damage melee's damage
     */
    public Melee(String name, int damage){
        // Melee is free because it is the initial attack method for user
        super(name, damage, 0);
    } 

    /**
     * State out the name and damage of melee.
     */
    @Override
        public void weaponDesc(){
        System.out.print(this.name + ": Damage: " + this.damage);
    }
}
