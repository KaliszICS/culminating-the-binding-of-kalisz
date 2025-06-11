public class Magic extends Weapon{

    public Magic(String name, int damage, int mpCost){
        super(name, damage, mpCost);

    }

    
    @Override
    void weaponDesc() {
        System.out.println(this.name + ": Damage: " + this.damage + " Cost: " + mpCost +"mp.");
    }
}
