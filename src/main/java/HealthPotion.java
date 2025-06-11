public class HealthPotion extends Potion{
    public HealthPotion(String name, String effect, int statMod){
        super(name, effect, statMod);
    
    }
public void usePotion(){
    System.out.print("\nYou have used a " + super.name + ". You heal " + statMod + " Hp.");
    // Main.player.addHp(potionModNum[option-1]);
}

}