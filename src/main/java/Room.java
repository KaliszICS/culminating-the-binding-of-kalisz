/**
	* The Room class allows for the creation of a room with a roomID, clear/unlocked status, and room number
  * It is also responsible for providing colours that will be used on the map for the room ids
  * It houses the monsters within the game as well, which are the enemies to defeat throughout the game
	* @author Jerry Zhu, Jamin Xie
	* @version 1.0.1
	*/

import java.util.Random;
/**
 * 
 */
public class Room {
  
  // Instance variable
  private String roomID;
  private boolean isCleared;
  private int previousRoom;
  private int roomNumber;
  private boolean isUnlocked;

  /**
   * This constructor creates a room with parameters roomID, roomNumber and previous room.
   * it also sets the default states of the rooms to be created
   * @param roomID a String representing the ID of the room in the format L(Level Number)
   * @param roomNumber represents the number associated with the room
   * @param previousRoom  represents the number associated with the previous room
   */
  public Room(String roomID, int roomNumber, int previousRoom) {
    this.isCleared = false;
    this.roomID = roomID;
    this.previousRoom = previousRoom;
    this.roomNumber = roomNumber;
    //first room is always unlocked at the start
    if (roomNumber == 1) {
      isUnlocked = true;
    } else {
      this.isUnlocked = false;
    }
  }

  /**
   * This method gets the Room's roomID
   * @return returns the roomID as a String
   */
  public String getRoomID() {
    return this.roomID;
  }

    /**
   * This method gets the value of isCleared
   * @return returns the value of isCleared as a boolean
   */
  public boolean isCleared() {
    return this.isCleared;
  }

  /**
   * This method gets the value of isUnlocked
   * @return returns the value of isUnlocked as a boolean
   */
  public boolean isUnlocked() {
    return this.isUnlocked;
  }

  /**
   * This method gets the previous Room's room number
   * @return returns the the previous Room's room number as an int
   */
  public int getPreviousROom() {
    return this.previousRoom;
  }

  /**
   * This method gets the Room's room number
   * @return returns the the Room's room number as an int
   */
  public int getRoomNumber() {
    return this.roomNumber;
  }

  /**
   * This method sets the value of isCleared as true
   */
  public void setClear() {
    this.isCleared = true;
  }

  /**
   * This method sets the value of isUnlocked as false
   */
  public void setLocked() {
    this.isUnlocked = false;
  }

  /**
   * This method sets the value of isUnlocked as true
   */
  public void setUnlocked() {
    this.isUnlocked = true;
  }

  /**
   * This method is responsible for creating the room nodes on the map and giving them the appropriate colours
   * @return returns the coloured form of the room node as a String 
   */
  public String crID() {
    String colourRoomID;
    // boss room always red
    if (roomNumber == 32) {
      colourRoomID = "\u001B[31m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    //clear is second priority(colour as green)
    } else if (this.isCleared) {
      colourRoomID = "\u001B[32m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    //unlocked is third priority(colour as yellow)
    } else if (this.isUnlocked) {
      colourRoomID = "\u001B[33m" + "[" + roomID + "]" + "\u001B[0m";
      return colourRoomID;
    //otherwise uncoloured
    } else {
      return "[" + roomID + "]";
    }
  }

  /**
   * This method is responsible for creating all of the monsters that the player will fight and scale difficulty wise based on room number
   * @param roomNumber represents the number associated with the room that will be used to scale difficulty
   * @return returns an Entity (enemy) that is scaled in difficulty with repsect to the room number
   */
  public static Entity getRandomMonster(int roomNumber) {
    Random rand = new Random();
    Entity baseMonster;

    if (roomNumber == 32) {
      int bossPick = rand.nextInt(5);

      //boss monsters for room 32(boss room)
      switch (bossPick) {
        case 0:
          baseMonster = new Entity(
              "Kalisz Jr.", 500, 100, "Boss",
              new String[] { "Fire Breath", "Tail Swipe", "Roar", "Wing Attack" },
              new int[] { 75, 60, 45, 90 },
              new int[] { 0, 0, 15, 0 },
              new int[] { 0, 0, 0, 120 });
          break;
        case 1:
          baseMonster = new Entity(
              "C.H.A.D", 800, 40, "Boss",
              new String[] { "Hellfire", "Smite", "Curse", "Teleport" },
              new int[] { 120, 132, 30, 0 },
              new int[] { 0, 0, 80, 0 },
              new int[] { 0, 0, 0, 15 });
          break;
        case 2:
          baseMonster = new Entity(
              "Kalisz's Mom", 800, 70, "Boss",
              new String[] { "Scold", "Slap", "Motherly Wrath", "Comfort" },
              new int[] { 75, 90, 120, 30 },
              new int[] { 15, 30, 0, 45 },
              new int[] { 0, 0, 60, 0 });
          break;
        case 3:
          baseMonster = new Entity(
              "Kalisz's Horseman", 700, 90, "Boss",
              new String[] { "Charge", "Lance Thrust", "Gallop", "Reap" },
              new int[] { 90, 105, 60, 120 },
              new int[] { 0, 15, 15, 30 },
              new int[] { 0, 0, 45, 0 });
          break;
        case 4:
          baseMonster = new Entity(
              "Chatgpt", 650, 100, "Boss",
              new String[] { "Syntax Slam", "Logical Loop", "Recursive Riddle", "Binary Burst" },
              new int[] { 66, 84, 54, 105 },
              new int[] { 0, 30, 0, 15 },
              new int[] { 0, 0, 60, 0 });
          break;
        default:
          baseMonster = new Entity(
              "Kalisz Karate Kombat", 750, 85, "Boss",
              new String[] { "Roundhouse Kick", "Dragon Punch", "Iron Palm", "Flying Kick" },
              new int[] { 84, 96, 78, 102 },
              new int[] { 15, 15, 15, 30 },
              new int[] { 0, 0, 0, 30 });
      }
      return baseMonster;
    }

    int pick = rand.nextInt(11);

    //list of regular mobs to pick from
    switch (pick) {
      case 0:
        baseMonster = new Entity(
            "Goblin", 80, 60, "Mob",
            new String[] { "Claw", "Bite", "Snarl", "Frenzy" },
            new int[] { 6, 8, 0, 10 },
            new int[] { 0, 0, 5, 0 },
            new int[] { 0, 0, 0, 15 });
        break;
      case 1:
        baseMonster = new Entity(
            "Orc", 120, 50, "Mob",
            new String[] { "Smash", "Roar", "Recover", "Charge" },
            new int[] { 12, 10, 0, 15 },
            new int[] { 0, 0, 10, 0 },
            new int[] { 0, 0, 0, 20 });
        break;
      case 2:
        baseMonster = new Entity(
            "Dark Mage", 90, 70, "Mob",
            new String[] { "Fireball", "Curse", "Drain", "Teleport" },
            new int[] { 15, 10, 5, 0 },
            new int[] { 0, 0, 10, 0 },
            new int[] { 0, 0, 0, 25 });
        break;
      case 3:
        baseMonster = new Entity(
            "Skeleton Warrior", 100, 55, "Mob",
            new String[] { "Bone Slash", "Shield Bash", "Rattle", "Soul Drain" },
            new int[] { 10, 12, 0, 8 },
            new int[] { 0, 0, 0, 5 },
            new int[] { 0, 0, 10, 0 });
        break;
      case 4:
        baseMonster = new Entity(
            "Slime", 70, 30, "Mob",
            new String[] { "Bounce", "Split", "Regenerate", "Absorb" },
            new int[] { 4, 6, 0, 5 },
            new int[] { 0, 0, 10, 5 },
            new int[] { 0, 0, 0, 10 });
        break;
      case 5:
        baseMonster = new Entity(
            "Bandit", 85, 75, "Mob",
            new String[] { "Slash", "Steal", "Smoke Bomb", "Backstab" },
            new int[] { 7, 0, 0, 15 },
            new int[] { 0, 0, 0, 0 },
            new int[] { 0, 0, 20, 10 });
        break;
      case 6:
        baseMonster = new Entity(
            "Fire Spirit", 90, 80, "Mob",
            new String[] { "Flame Burst", "Ignite", "Blaze", "Reignite" },
            new int[] { 14, 10, 12, 0 },
            new int[] { 0, 0, 0, 8 },
            new int[] { 0, 0, 10, 10 });
        break;
      case 7:
        baseMonster = new Entity(
            "Ice Golem", 140, 40, "Mob",
            new String[] { "Ice Punch", "Frost Nova", "Shield", "Hibernate" },
            new int[] { 12, 10, 0, 0 },
            new int[] { 0, 0, 0, 15 },
            new int[] { 0, 0, 20, 0 });
        break;
      case 8:
        baseMonster = new Entity(
            "Zombie", 110, 25, "Mob",
            new String[] { "Bite", "Rot", "Groan", "Infect" },
            new int[] { 6, 4, 0, 7 },
            new int[] { 5, 0, 0, 0 },
            new int[] { 0, 0, 0, 10 });
        break;
      case 9:
        baseMonster = new Entity(
            "Witch", 100, 65, "Mob",
            new String[] { "Hex", "Potion", "Cackle", "Curse" },
            new int[] { 10, 0, 0, 12 },
            new int[] { 0, 12, 0, 0 },
            new int[] { 0, 0, 0, 15 });
        break;
      case 10:
        baseMonster = new Entity(
            "Treant", 130, 35, "Mob",
            new String[] { "Branch Whip", "Leaf Storm", "Root Heal", "Grow" },
            new int[] { 10, 8, 0, 0 },
            new int[] { 0, 0, 15, 10 },
            new int[] { 0, 0, 0, 10 });
        break;
      default:
        return null;
    }

    //difficulty scaling(+5% difficulty per increment in room numeber)
    double multiplier = 1 + (roomNumber * 0.05);
    baseMonster.scaleStats(multiplier);
    return baseMonster;
  }
}