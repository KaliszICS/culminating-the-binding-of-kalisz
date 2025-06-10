import java.util.Random;

public class Room {
  private String roomID;
  private boolean isCleared;
  private int previousRoom;
  private int roomNumber;
  private boolean isUnlocked;

  public Room(String roomID, int roomNumber, int previousRoom) {
    this.isCleared = false;
    this.roomID = roomID;
    this.previousRoom = previousRoom;
    this.roomNumber = roomNumber;
    if (roomNumber == 1) {
      isUnlocked = true;
    } else {
      this.isUnlocked = false;
    }
  }

  public String getRoomID() {
    return this.roomID;
  }

  public boolean isCleared() {
    return this.isCleared;
  }

  public boolean isUnlocked() {
    return this.isUnlocked;
  }

  public int getPreviousROom() {
    return this.previousRoom;
  }

  public int getRoomNumber() {
    return this.roomNumber;
  }

  public void setClear() {
    this.isCleared = true;
  }

  public void setLocked() {
    this.isUnlocked = false;
  }

  public void setUnlocked() {
    this.isUnlocked = true;
  }

  public String crID() {
    String colourRoomID;
    // boss room always red
    if (roomNumber == 32) {
      colourRoomID = "\u001B[31m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    } else if (this.isCleared) {
      colourRoomID = "\u001B[32m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    } else if (this.isUnlocked) {
      colourRoomID = "\u001B[33m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    } else {
      return "[" + roomID + "]";
    }
  }

  public static Entity getRandomMonster(int roomNumber) {
    Random rand = new Random();
    int pick = rand.nextInt(11);

    Entity baseMonster;

    switch (pick) {
      case 0:
        return new Entity(
            "Goblin", 80, 60, "Mob",
            new String[] { "Claw", "Bite", "Snarl", "Frenzy" },
            new int[] { 6, 8, 0, 10 },
            new int[] { 0, 0, 5, 0 },
            new int[] { 0, 0, 0, 15 });
      case 1:
        return new Entity(
            "Orc", 120, 50, "Mob",
            new String[] { "Smash", "Roar", "Recover", "Charge" },
            new int[] { 12, 10, 0, 15 },
            new int[] { 0, 0, 10, 0 },
            new int[] { 0, 0, 0, 20 });
      case 2:
        return new Entity(
            "Dark Mage", 90, 70, "Mob",
            new String[] { "Fireball", "Curse", "Drain", "Teleport" },
            new int[] { 15, 10, 5, 0 },
            new int[] { 0, 0, 10, 0 },
            new int[] { 0, 0, 0, 25 });
      case 3:
        return new Entity(
            "Skeleton Warrior", 100, 55, "Mob",
            new String[] { "Bone Slash", "Shield Bash", "Rattle", "Soul Drain" },
            new int[] { 10, 12, 0, 8 },
            new int[] { 0, 0, 0, 5 },
            new int[] { 0, 0, 10, 0 });
      case 4:
        return new Entity(
            "Slime", 70, 30, "Mob",
            new String[] { "Bounce", "Split", "Regenerate", "Absorb" },
            new int[] { 4, 6, 0, 5 },
            new int[] { 0, 0, 10, 5 },
            new int[] { 0, 0, 0, 10 });
      case 5:
        return new Entity(
            "Bandit", 85, 75, "Mob",
            new String[] { "Slash", "Steal", "Smoke Bomb", "Backstab" },
            new int[] { 7, 0, 0, 15 },
            new int[] { 0, 0, 0, 0 },
            new int[] { 0, 0, 20, 10 });
      case 6:
        return new Entity(
            "Fire Spirit", 90, 80, "Mob",
            new String[] { "Flame Burst", "Ignite", "Blaze", "Reignite" },
            new int[] { 14, 10, 12, 0 },
            new int[] { 0, 0, 0, 8 },
            new int[] { 0, 0, 10, 10 });
      case 7:
        return new Entity(
            "Ice Golem", 140, 40, "Mob",
            new String[] { "Ice Punch", "Frost Nova", "Shield", "Hibernate" },
            new int[] { 12, 10, 0, 0 },
            new int[] { 0, 0, 0, 15 },
            new int[] { 0, 0, 20, 0 });
      case 8:
        return new Entity(
            "Zombie", 110, 25, "Mob",
            new String[] { "Bite", "Rot", "Groan", "Infect" },
            new int[] { 6, 4, 0, 7 },
            new int[] { 5, 0, 0, 0 },
            new int[] { 0, 0, 0, 10 });
      case 9:
        return new Entity(
            "Witch", 100, 65, "Mob",
            new String[] { "Hex", "Potion", "Cackle", "Curse" },
            new int[] { 10, 0, 0, 12 },
            new int[] { 0, 12, 0, 0 },
            new int[] { 0, 0, 0, 15 });
      case 10:
        return new Entity(
            "Treant", 130, 35, "Mob",
            new String[] { "Branch Whip", "Leaf Storm", "Root Heal", "Grow" },
            new int[] { 10, 8, 0, 0 },
            new int[] { 0, 0, 15, 10 },
            new int[] { 0, 0, 0, 10 });
      default:
        return null;
    }

    double multiplier = 1 + (roomNumber * 0.05);
    baseMonster.scaleStats(multiplier);
    
    return baseMonster;
  }
}