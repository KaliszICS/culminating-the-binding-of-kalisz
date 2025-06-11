import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


abstract class Weapon {
    
    protected String name;
    protected int damage;
    protected int mpCost;

    public Weapon (String name, int damage, int mpCost) {
        this.name = name;
        this.damage = damage;
        this.mpCost = mpCost;
    }
    public String getName() {
        return name;
    }

    public int getMpCost(){
    
        return this.mpCost;
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


    
