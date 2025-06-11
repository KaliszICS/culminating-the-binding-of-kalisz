public class Magic extends Weapon{
    private int mpCost;
    public Magic(String name, int damage, int mpCost){
        super(name, damage);
        this.mpCost = mpCost;
    }

    @Override
    void weaponDesc() {
        System.out.println(this.name + " Damage: " + this.damage + " Cost: " + mpCost +"mp.");
    }
}
