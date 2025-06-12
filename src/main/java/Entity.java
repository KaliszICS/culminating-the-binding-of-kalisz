/**
 * Represents a character or unit in a battle with stats, moves, and effects.
 * @author Jamin Xie
 */
public class Entity {
    String name;
    int maxHp;
    int hp;
    int speed;
    String status;
    String[] moveset;
    int[] damage;
    int[] heal;
    int[] speedBuff;

    /**
     * Creates a new Entity with all required stats and moves.
     *
     * @param name       name of the entity
     * @param maxHp      maximum HP
     * @param speed      base speed
     * @param status     current status 
     * @param moveset    of move names
     * @param damage     damage values for each move
     * @param heal       healing values for each move
     * @param speedBuff  speed buffs for each move
     */
    public Entity(String name, int maxHp, int speed, String status, String[] moveset, int[] damage, int[] heal, int[] speedBuff) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.speed = speed;
        this.status = status;
        this.moveset = moveset;
        this.damage = damage;
        this.heal = heal;
        this.speedBuff = speedBuff;
    }

    // 

    /**
     * @return  name of the entity
     */
    public String getName() {
        return name;
    }

    /**
     * @return  max HP of the entity
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * @param maxHp new max HP to set
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * @return current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp new HP to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return entity's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed new speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return entity's current status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status new status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return names of the moves this entity can use
     */
    public String[] getMoveset() {
        return moveset;
    }

    /**
     * @param moveset new moveset to set
     */
    public void setMoveset(String[] moveset) {
        this.moveset = moveset;
    }

    /**
     * @return damage values for each move
     */
    public int[] getDamage() {
        return damage;
    }

    /**
     * @param damage new damage values to set
     */
    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    /**
     * @return healing values each of the moves
     */
    public int[] getHeal() {
        return heal;
    }

    /**
     * @param heal new heal values to set
     */
    public void setHeal(int[] heal) {
        this.heal = heal;
    }

    /**
     * @return speed buffs for each move
     */
    public int[] getSpeedBuff() {
        return speedBuff;
    }

    /**
     * @param speedBuff new speed buffs to set
     */
    public void setSpeedBuff(int[] speedBuff) {
        this.speedBuff = speedBuff;
    }

    /**
     * Adjusts the HP, Speed, Damage, Heal, and speedBuff of the entity, used by leveling up or powering up the enemy or player
     *
     * @param multiplier the factor to scale stats by so bosses get harder and stronger
     */
    public void scaleStats(double multiplier) {
        this.maxHp = (int)(this.maxHp * multiplier);
        this.hp = this.maxHp;
        this.speed = (int)(this.speed * multiplier);

        for (int i = 0; i < damage.length; i++) {
            damage[i] = (int)(damage[i] * multiplier);
        }

        for (int i = 0; i < heal.length; i++) {
            heal[i] = (int)(heal[i] * multiplier);
        }

        for (int i = 0; i < speedBuff.length; i++) {
            speedBuff[i] = (int)(speedBuff[i] * multiplier);
        }
    }

    /**
     * @return the string showing this entity's name, HP, speed, and status
     */
    @Override
    public String toString() {
        return name + " | HP: " + hp + "/" + maxHp + " | Speed: " + speed + " | Status: " + status;
    }
}
