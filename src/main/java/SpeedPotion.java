public class SpeedPotion extends Potion {
    public SpeedPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
        
    }

    public void usePotion(){
        System.out.print("\nYou have used a " + super.name + ". Your speed inceasaes by " + statMod + ".");
    }
}
