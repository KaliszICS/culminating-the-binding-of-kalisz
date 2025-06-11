public class Player {
    String name;
    int maxHp;
    int hp;
    int speed;
    String[] moveset;
    int[] damage;
    int[] heal;
    int[] mpDeduction;
    String[] potion;
    int[] potionEffect;
    int gold;

    public Player(String name, int maxHp, int hp, int speed, String[] moveset, int[] damage, int[] heal, int[] mpDeduction, String[] potion, int[] potionEffect, int gold) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.speed = speed;
        this.moveset = moveset;
        this.damage = damage;
        this.heal = heal;
        this.mpDeduction = mpDeduction;
        this.potion = potion;
        this.potionEffect = potionEffect;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String[] getMoveset() {
        return moveset;
    }

    public void setMoveset(String[] moveset) {
        this.moveset = moveset;
    }

    public int[] getDamage() {
        return damage;
    }

    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    public int[] getHeal() {
        return heal;
    }

    public void setHeal(int[] heal) {
        this.heal = heal;
    }

    public int[] getMpDeduction() {
        return mpDeduction;
    }

    public void setMpDeduction(int[] mpDeduction) {
        this.mpDeduction = mpDeduction;
    }

    public String[] getPotion() {
        return potion;
    }

    public void setPotion(String[] potion) {
        this.potion = potion;
    }

    public int[] getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(int[] potionEffect) {
        this.potionEffect = potionEffect;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addMaxHp(int hp){
        this.maxHp += hp;
        this.hp += hp;
    }
}