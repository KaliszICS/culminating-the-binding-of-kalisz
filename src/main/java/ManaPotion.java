public class ManaPotion extends Potion{
    ManaPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
    }

    public void usePotion(){
    System.out.print("\nYou have used a " + super.name + ". Your speed inceasaes by " + statMod + ".");
    }
}
