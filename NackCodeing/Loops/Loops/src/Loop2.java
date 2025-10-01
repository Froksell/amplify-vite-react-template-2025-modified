import java.util.Scanner;

public class Loop2 {
    public static void main(String[] args) {
// Setting up Scanner for taking user input.
        Scanner scanner = new Scanner(System.in);
// Input Two number for the loop.
        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();
// closeing the scanner for it is not needed anymore.
        scanner.close();

        while (true) {
//counting upp from the first inputed number to the seconed number.
            System.out.println("count: " + num1);
            num1++;
// Checking if num1 has reached num2 and if has it breaks the loop.
            if (num1 == num2) {
                System.out.print(" :Done");
                break;
            }
        }
    }
}
