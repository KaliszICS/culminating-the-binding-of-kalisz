import java.util.Scanner;
public class Room {
    private String roomID;
    private boolean isCleared;
    private int previousRoom;
    private int roomNumber;
    private boolean isUnlocked;


    public Room(String roomID, int roomNumber, int previousRoom){
        this.isCleared = false;
        this.roomID = roomID;
        this.previousRoom = previousRoom;
        this.roomNumber = roomNumber;
        if(roomNumber == 1){
          isUnlocked = true;
        }
        else{
          this.isUnlocked = false;
        }
    }

        public String getRoomID(){
            return this.roomID;
        }

        public boolean isCleared(){
            return this.isCleared;
        }

        public boolean isUnlocked(){
            return this.isUnlocked;
        }

        public int getPreviousROom(){
            return this.previousRoom;
        }

        public int getRoomNumber(){
          return this.roomNumber;
        }

        public void setClear(){
          this.isCleared = true;
        }

        public void setLocked(){
          this.isUnlocked = false;
        }

        public void setUnlocked(){
          this.isUnlocked = true;
        }


        public String crID(){
          String colourRoomID;
          //boss room always red
          if(roomNumber == 32){
            colourRoomID = "\u001B[31m" +"[" + roomID + "]" + "\u001B[0m";
          return colourRoomID;
          }
          else if(this.isCleared){
            colourRoomID = "\u001B[32m" + "[" + roomID + "]" + "\u001B[0m";
            return colourRoomID;
          }
          else if(this.isUnlocked){
            colourRoomID = "\u001B[33m" + "[" + roomID + "]" + "\u001B[0m";
            return colourRoomID;
          }
          else{
            return "[" + roomID + "]";
          }
        }
    }