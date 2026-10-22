import java.util.ArrayList;

public class Tester3000 {
    public static void main(String[] args) {
        ArrayList<WashRoom> Wash = new ArrayList<>();

        Wash.add(new WashRoom(2, 4, 3, 5, 5));
        
        for (WashRoom room : Wash) {
            room.displayInfo();
        }

    }
    
}
