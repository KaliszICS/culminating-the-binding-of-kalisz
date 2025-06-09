public class Potion {
    private String name;
    private String effect;

    Potion(String name, String effect){
this.name = name;
this.effect = effect;
    }



public void potionDesc(){
    System.out.print(this.name + ";" + this.effect);
}

public String getEffect(){
    return this.effect;
}

public String getName(){
    return this.name;
}
}
