public class ManaPotion extends Potion{
    ManaPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
    }

    public void usePotion(){
        Main.player.addMana(this.statMod);
    System.out.print("\nYou have used a " + super.name + ". Your speed inceasaes by " + statMod + ".");
    }
}
