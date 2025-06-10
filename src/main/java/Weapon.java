import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Weapon {
    String name;
    int damage;

    public Weapon (String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
    public String getName() {
        return name;
    }
    public int getDamage() {
        return damage;
    }

    int damageDealt = playerWeapon.getDamage();
    System.out.println("You attack with " + playerWeapon.getName() + " and deal " + damageDealt + " damage!");
    

}