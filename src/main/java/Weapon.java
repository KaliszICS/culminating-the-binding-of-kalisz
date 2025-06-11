import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


abstract class Weapon {
    
    protected String name;
    protected int damage;

    public Weapon (String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
    public String getName() {
        return name;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    abstract void weaponDesc();

}


    
