/**
	* The Map class allows for the creation of an map and the main option select screen for the game.
    * It maps out all thier rooms and their order, allowing for the printing of the main map.
    * It is also responsible for room selection for the progression of the game and for keeping track of the state of rooms
	* @author Jerry Zhu, Jamin Xie
	* @version 1.0.1
	*/
import java.util.Scanner;
import java.util.ArrayList;

public class Map {
    private int numberOfRooms;
    private Room[] rooms;
    private int[] previousRooms;
    private ArrayList<Room> unlockedRooms;
    private Room currentRoom;

    /**
     * This constructor creates a default map which creates an array of rooms with thier data (roomid, room number, previous room number). It sets the first room as unlocked by default and adds it to the unlocked ArrayList
     */
    public Map() {
        this.numberOfRooms = 32;
        this.unlockedRooms = new ArrayList<Room>();
        this.rooms = new Room[numberOfRooms];
        //every room's "prerequisite" mapped out in room numbers
        this.previousRooms = new int[] { -1, 1, 2, 2, 2, 5, 6, 5, 8, 5, 10, 11, 10, 10, 14, 15, 16, 17, 14, 19, 20, 19,
            22, 19, 24, 25, 26, 24, 28, 29, 28, 28 };
            //creating the room array
            for (int i = 1; i < numberOfRooms + 1; i++) {
                if (i < 10) {
                    Room room = new Room("L0" + (i), i, previousRooms[i - 1]);
                    rooms[i - 1] = room;
            } else {
                Room room = new Room("L" + i, i, previousRooms[i - 1]);
                rooms[i - 1] = room;
            }
        }
        //unlock the first room by default
        this.currentRoom = rooms[0];
        this.unlockedRooms.add(this.currentRoom);
    }

    /**
     * This method clears a room of a specific room number and removes it from the unlocked rooms list(cleared takes priority over unlocked)
     * @param roomNumber represents the room number of the room to be set as cleared
     */
    public void clearRoom(int roomNumber) {
        this.rooms[roomNumber].setClear();
        for(int i = 0; i < unlockedRooms.size(); i++){
            if(roomNumber == unlockedRooms.get(i).getRoomNumber()-1){
                this.unlockedRooms.remove(i);
            }
        }
    }

    /**
     * This Method is responsible for getting the array of Rooms for this map 
     * @return  returns the array of rooms as a Room array
     */
    public Room[] getRooms() {
        return this.rooms;
    }

    /**
     * This method checks if any room is clear and sets the next room as unlocked if it isn't unlocked yet 
     */
    public void checkClear() {
        for (int i = 1; i < this.rooms.length; i++) {
            if (!this.rooms[i].isUnlocked() && this.rooms[previousRooms[i] - 1].isCleared()) {
                rooms[i].setUnlocked();
                this.unlockedRooms.add(rooms[i]);
            }
        }
    }

    /**
     * This method is responsible for printing out the main map to be used and is also responsible for running the method that gives you the main selection screen.
     */
    public void print() {
        //separator text between rows
        String buffer = "                        | ";
        //updates on the state of the rooms
        checkClear();
        System.out.println("LOADING MAP...\n");
        //this array has the individual rooms in their respective spots for when the map prints, with each room updating its colour
        String[] rows = new String[] {
            "                      " + this.rooms[0].crID(),
            "                " + this.rooms[2].crID() + "-" + this.rooms[1].crID() + "-" + this.rooms[3].crID(),
            "          " + this.rooms[8].crID() + "-" + this.rooms[7].crID() + "-" + this.rooms[4].crID() + "-" + this.rooms[5].crID() + "-" + this.rooms[6].crID(),
            "                " + this.rooms[12].crID() + "-" + this.rooms[9].crID() + "-" + this.rooms[10].crID() + "-" + this.rooms[11].crID(),
            "                      " + this.rooms[13].crID() + "-" + this.rooms[14].crID() + "-" + this.rooms[15].crID() + "-" + this.rooms[16].crID() + "-" + this.rooms[17].crID(),
            "          " + this.rooms[20].crID() + "-" + this.rooms[19].crID() + "-" + this.rooms[18].crID() + "-" + this.rooms[21].crID() + "-" + this.rooms[22].crID(),
            "    " + this.rooms[26].crID() + "-" + this.rooms[25].crID() + "-" + this.rooms[24].crID() + "-" + this.rooms[23].crID(),
            "                " + this.rooms[30].crID() + "-" + this.rooms[27].crID() + "-" + this.rooms[28].crID() + "-" + this.rooms[29].crID(),
            "                      " + this.rooms[31].crID() + " (Boss)\n"
         };
        //print the map
         for(int i = 0; i < rows.length; i++) {
            if(i > 0){
                System.out.println(buffer);
            }
            System.out.println(rows[i]);
         }
        //run the main options screen
        printOptions();
    }

    /**
     * This method is responsible for running the main selection screen
     */
    public void printOptions() {
        Scanner scanner = new Scanner(System.in);
        //player prompt
        System.out.println(" 1.SHOP\n 2.INVENTORY\n 3.Continue");
        System.out.println("\nPlayer Gold: " + Combat.playerGold);
        boolean running = true;
        while (running) {
            System.out.print("\nType a choice: ");
            //check for int type
            while(!scanner.hasNextInt()){
                scanner.nextLine();
                //player prompt
                System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                System.out.print("\nType a choice: ");
            }
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    //open the shop
                    System.out.println("Opening Shop...");
                    Main.shop.mainShop();
                    running = false;
                    break;
                case 2:
                    //open the inventory
                    System.out.println("Opening Inventory...");
                    Main.inventory.inventoryNav();
                    running = false;
                    break;
                case 3:
                    //brings the player to the room select screen
                    roomSelection();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                    break;
            }
        }

    }
    /**
     * This method is responsible for choosing the next room to go to after you decide to continue with the dungeon. Also starts the combat sequence when the a proper room is chosen
     */
    public void roomSelection() {
        int option = -1;
        Scanner input = new Scanner(System.in);
        //user prompt
        
        boolean running = true;
        while (running) {
            //user prompt
            System.out.println("Choose an available room: ");
            for(int i = 0; i < this.unlockedRooms.size(); i++){
                System.out.println((i+1) + "." + this.unlockedRooms.get(i).getRoomID());
            }
            System.out.print("\nType a choice: ");
            //check for int type
            while(!input.hasNextInt()){
                input.nextLine();
                //user prompt
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nType a choice: ");
            }
            option = input.nextInt();
            input.nextLine();
            //valid room inputs
            if(option <= unlockedRooms.size() &&  option > 0){
                running = false;
            }
            else{
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
        //set the current room as the selected one
        currentRoom = unlockedRooms.get(option-1);
        Main.clearConsole();
        //run conbat
        Combat.mainCombat(currentRoom);
}
}