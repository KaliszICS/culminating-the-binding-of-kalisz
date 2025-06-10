public class Armor {

    private String name;
    private int defense;
    private boolean check;

    /**
     * 
     * @param name
     * @param defense
     * @param check to check whether or not the player has a armor
     */
    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
        this.check = false;
    }

    public String getName() {
        return this.name;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean getCheck() {
        return this.check;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }



    //loop thru array make the false one be true
    //if u hv the armor
}