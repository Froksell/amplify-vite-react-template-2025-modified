import java.util.Random;
import java.util.Scanner;

public class Loop6 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);  

        String answer; 

        while (true) {
            System.out.println("Rolling Dice");
            int dieRoll1 = rand.nextInt(6) + 1;
            int dieRoll2 = rand.nextInt(6) + 1;

            System.out.println("Dice Roll 1: " + dieRoll1);
            System.out.println("Dice Roll 2: " + dieRoll2);

            System.out.print("Do you want to continue? (yes/no): ");
            answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("no") || answer.equals("n")) {
                System.out.println("Done.");
                break;
            } else if (answer.equals("yes") || answer.equals("y")) {
                System.out.println("Again...");
            } else {
                System.out.println("Wrong input, try again yes or no \n");
            
            }
        }

        scanner.close(); 
    }
}
