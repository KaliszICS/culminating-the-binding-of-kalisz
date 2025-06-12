/**
 * Represents the player character in the game
 * 
 * Holds stats like health (HP), speed, available moves, potion effects, and gold.
 * All arrays (moveset, damage, heal, mpDeduction, potion, potionEffect) should be kept in sync by index: e.g., moveset[i] uses damage[i], heal[i], and mpDeduction[i].
 */
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

    /**
     * Constructs a new Player with the given stats.
     *
     * @param name the player’s name
     * @param maxHp the maximum HP
     * @param hp the starting/current HP
     * @param speed the player’s speed stat
     * @param moveset the array of move names
     * @param damage the array of damage values for each move
     * @param heal the array of healing values for each move
     * @param mpDeduction the array of MP costs for each move
     * @param potion the array of potion names
     * @param potionEffect the array of potion effect amounts
     * @param gold the starting amount of gold
     */
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

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the maximum hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Set a new maximum hp
     * 
     * @param maxHp maxHp the new maximum hp
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * @return the current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the current HP.
     * 
     * Does not clamp [0, maxHp]; caller must ensure valid range.
     * 
     * @param hp the new current hp.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return the player's speed stats
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the new speed stats
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the moveset array
     */
    public String[] getMoveset() {
        return moveset;
    }

    /**
     * Replaces the moveset.
     * 
     * Ensure damage[], heal[], and mpDeduction[] are updated to match indexing.
     * 
     * @param moveset the new array of move names
     */
    public void setMoveset(String[] moveset) {
        this.moveset = moveset;
    }

    /**
     * @return the damage array per move
     */
    public int[] getDamage() {
        return damage;
    }

    /**
     * Replaces the damage array.
     * 
     * Must align with moveset[] length.
     * 
     * @param damage the new damage values
     */
    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    /**
     * @return the heal array per move
     */
    public int[] getHeal() {
        return heal;
    }

    /** 
     * @param heal the new heal values per move 
     */
    public void setHeal(int[] heal) {
        this.heal = heal;
    }

    /** 
     * @return the MP cost array per move 
     */
    public int[] getMpDeduction() {
        return mpDeduction;
    }

    /** 
     * @param mpDeduction the new MP costs per move 
     */
    public void setMpDeduction(int[] mpDeduction) {
        this.mpDeduction = mpDeduction;
    }

    /** 
     * @return the potion names array 
     */
    public String[] getPotion() {
        return potion;
    }

    /** 
     * @param potion the new potion names 
     */
    public void setPotion(String[] potion) {
        this.potion = potion;
    }

    /** 
     * @return the potion effect values 
     */
    public int[] getPotionEffect() {
        return potionEffect;
    }

    /** 
     * @param potionEffect the new potion effect amounts 
     */
    public void setPotionEffect(int[] potionEffect) {
        this.potionEffect = potionEffect;
    }

    /** 
     * @return the current gold 
     */
    public int getGold() {
        return gold;
    }

    /** 
     * @param gold the new gold total 
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Increases both maximum HP and current HP by the given amount.
     * 
     * Use this when leveling up or using a buff that permanently rasies max HP.
     * 
     * @param hp the amount to add to both maxHP and hp
     */
    public void addMaxHp(int hp){
        this.maxHp += hp;
        this.hp += hp;
    }
}