public class Armor {

    private String name;
    private int defense;
    private boolean hasArmor;

    /**
     * 
     * @param name
     * @param defense
     * @param check to check whether or not the player has a armor
     */
    public Armor(String name, int defense, boolean hasArmor) {
        this.name = name;
        this.defense = defense;
        this.hasArmor = hasArmor;
    }

    public String getName() {
        return this.name;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean hasArmor() {
        return this.hasArmor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }



    //loop thru array make the false one be true
    //if u hv the armor
}