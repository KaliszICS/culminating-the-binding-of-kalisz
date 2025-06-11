public class Melee extends Weapon {
    public Melee(String name, int damage){
        super(name, damage, 0);
    } 

@Override
    public void weaponDesc(){
        System.out.print(this.name + " Damage: " + this.damage);
    }
}
