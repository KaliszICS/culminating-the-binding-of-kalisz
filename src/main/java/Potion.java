abstract class Potion {
    protected String name;
    protected String effect;
    protected int statMod;

    Potion(String name, String effect, int statMod){
this.name = name;
this.effect = effect;
this.statMod = statMod;
    }



public void potionDesc(){
    System.out.print(this.name + ": " + this.effect);
}

abstract void usePotion();
    


public String getEffect(){
    return this.effect;
}

public String getName(){
    return this.name;
}


}
