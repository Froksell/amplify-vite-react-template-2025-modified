import java.util.Scanner;

public class Loop3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String answer;

        while (true) {

            System.out.print(": Enter the first number ");
            int num1 = scanner.nextInt();

            System.out.print(": Enter the second number ");
            int num2 = scanner.nextInt();

            scanner.nextLine(); // consume the leftover newline

            int result = num1 + num2;
            System.out.println("Answer: " + result);

            System.out.print(num1 + num2 + " :Answer");

            System.out.print("Do you want to continue? (yes/no): ");
            answer = scanner.nextLine().trim().toLowerCase();
            // type yes/y to continue the loop and no/n to close it
            if (answer.equals("no") || answer.equals("n")) {
                System.out.println("Done.");
                break;
            } else if (answer.equals("yes") || answer.equals("y")) {
                System.out.println("Again...");
            } else {

                // if you don't say or no it will give you an error and restart the loop

                System.out.println("Wrong input try again yes or no \n");
            }
        }

        scanner.close();
    }
}


