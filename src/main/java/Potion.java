<<<<<<< HEAD
abstract class Potion {
    protected String name;
    protected String effect;
    protected int statMod;

    Potion(String name, String effect, int statMod){
this.name = name;
this.effect = effect;
this.statMod = statMod;
=======
public class Potion {

    private String name;
    private String effect;

    public Potion(String name, String effect) {
        this.name = name;
        this.effect = effect;
>>>>>>> 266e02b (added need to fix Casino)
    }

    public String getName() {
        return this.name;
    }

    public String getEffect() {
        return this.effect;
    }

<<<<<<< HEAD
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


=======
    public void setName(String name) {
        this.name = name;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
>>>>>>> 266e02b (added need to fix Casino)
}
