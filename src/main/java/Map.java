import java.util.Scanner;
import java.util.ArrayList;

public class Map {
    private int numberOfRooms;
    private Room[] rooms;
    private int[] previousRooms;
    private ArrayList<Room> unlockedRooms;
    private Room currentRoom;

    public Map() {
        this.numberOfRooms = 32;
        this.unlockedRooms = new ArrayList<Room>();
        this.rooms = new Room[numberOfRooms];
        this.previousRooms = new int[] { -1, 1, 2, 2, 2, 5, 6, 5, 8, 5, 10, 11, 10, 10, 14, 15, 16, 17, 14, 19, 20, 19,
            22, 19, 24, 25, 26, 24, 28, 29, 28, 28 };
            for (int i = 1; i < numberOfRooms + 1; i++) {
                if (i < 10) {
                    Room room = new Room("L0" + (i), i, previousRooms[i - 1]);
                    rooms[i - 1] = room;
            } else {
                Room room = new Room("L" + i, i, previousRooms[i - 1]);
                rooms[i - 1] = room;
            }
        }
        this.currentRoom = rooms[0];
        this.unlockedRooms.add(this.currentRoom);
    }

    public void clearRooom(int roomNumber) {
        this.rooms[roomNumber].setClear();
        for(int i = 0; i < unlockedRooms.size(); i++){
            if(roomNumber == unlockedRooms.get(i).getRoomNumber()){
                unlockedRooms.remove(i);
            }
        }
    }

    public Room[] getRooms() {
        return this.rooms;
    }

    public void checkClear() {
        for (int i = 1; i < this.rooms.length; i++) {
            if (!this.rooms[i].isUnlocked() && this.rooms[previousRooms[i] - 1].isCleared()) {
                rooms[i].setUnlocked();
                this.unlockedRooms.add(rooms[i]);
            }
        }
    }

    public void print() {

        String buffer = "                        | ";
        checkClear();
        System.out.println("LOADING MAP...\n");

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

         for(int i = 0; i < rows.length; i++) {
            if(i > 0){
                System.out.println(buffer);
            }
            System.out.println(rows[i]);
         }
        printOptions();
    }

    public void printOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" 1.SHOP\n 2.INVENTORY\n 3.Continue");
        boolean running = true;
        while (running) {
            System.out.print("\nType a choice: ");
            while(!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                System.out.print("\nType a choice: ");
            }
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Opening Shop...");
                    //run the shop
                    running = false;
                    break;
                case 2:
                    System.out.println("Opening Inventory...");
                    //run inventory
                    running = false;
                    break;
                case 3:
                    roomSelection();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                    break;
            }
        }

    }

    public void roomSelection(){
        int option = -1;
        Scanner input = new Scanner(System.in);
        System.out.println("Choose an available room: ");
        
        for(int i = 0; i < this.unlockedRooms.size(); i++){
            System.out.println((i+1) + "." + this.unlockedRooms.get(i).getRoomID());
        }

     boolean running = true;
        while (running) {
            System.out.print("\nType a choice: ");
            while(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Invalid choice! Please enter a valid number");
                System.out.print("\nType a choice: ");
            }
            option = input.nextInt();
            input.nextLine();
            if(option <= unlockedRooms.size() &&  option > 0){
                running = false;
            }
            else{
                System.out.println("Invalid choice! Please enter a valid number");
            }
        }
        currentRoom = unlockedRooms.get(option-1);
        Combat.mainCombat(currentRoom);
}
}