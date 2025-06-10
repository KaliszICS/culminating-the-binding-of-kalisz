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
    int gold;

    public Entity(int gold) {
        this.gold = gold;
    }

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

    // Getters and setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int[] getSpeedBuff() {
        return speedBuff;
    }

    public void setSpeedBuff(int[] speedBuff) {
        this.speedBuff = speedBuff;
    }

    public void gold(int gold) {
        this.gold = gold;
    }

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

    @Override
    public String toString() {
        return name + " | HP: " + hp + "/" + maxHp + " | Speed: " + speed + " | Status: " + status;
    }
}