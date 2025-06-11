public class SpeedPotion extends Potion {
    public SpeedPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
        
    }

    public void usePotion(){
        Main.player.addSpeed(this.statMod);
    System.out.print("\nYou have used a " + super.name + ". Your speed inceasaes by " + statMod + ".");
    }
}
