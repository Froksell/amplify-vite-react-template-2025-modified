public class WashRoom extends Room {
    private int toilet;
    private int sink;

    public WashRoom(int roomNumber, int floor, int totalLamps, int toilet, int sink) {
        super(roomNumber, floor, totalLamps);
        this.toilet = toilet;
        this.sink = sink;

        
    }
    @Override
    public void displayInfo() {

        super.displayInfo();

        System.out.print(" Det har " + toilet +" toaletter" + " och det har " + sink + " tvättställ");
    }
   
    
}
