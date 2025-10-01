import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class list_test_1_fail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] numbers = new int[4];

        for(int i = 0; i < 4; i++) {
            System.out.println("Enter number " + (i+ 1) +": ");
            numbers[i] = scanner.nextInt();
        }

        try {
            FileWriter writer = new FileWriter("Numbers.txt");
            for(int i = 0; i < 4; i++) {
                writer.write("Number " + (i+1) + ": " + numbers[i] + "\n");
            }
            writer.close();
            System.out.println("\nNumbers saved to numbers text document ");
        }catch (IOException e) {
            System.out.println("An error has occurred while saving the file.");
            e.printStackTrace();
        }

        scanner.close();

    }
}
