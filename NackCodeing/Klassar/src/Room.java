public class Room {
    int roomNumber;
    int floor;
    int totalLamps;

    public Room(int roomNumber, int floor, int totalLamps) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.totalLamps = totalLamps;
    
        
        
    }


    public void displayInfo() {
        System.out.println("Rumnumber är " + roomNumber + " och vånigen den är på är " + floor + " och antal lampor den har är " + totalLamps + " för rummet "  );
    }

    
    }

