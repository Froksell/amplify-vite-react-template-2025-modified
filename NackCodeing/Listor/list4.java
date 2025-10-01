import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;



public class list4 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = 0, num2 = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Typ first number: ");
                num1 = scanner.nextInt();

                System.out.print("Type the sceonde nummber: ");
                num2 = scanner.nextInt();


                validInput = true;

            } catch (InputMismatchException e) {
                System.out.print("Error: Wrong input");
                scanner.nextLine();

            }
        }

        int start = Math.min(num1, num2);
        int end = Math.max(num1, num2);

        if (start == end) {
            System.out.println("Number are the same there is no inbetween");
            scanner.close();
            return;
        }

        ArrayList<Integer> evenNumbers = new ArrayList<>();

        for (int i = start + 1; i < end; i++) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            }
        }

        System.out.println("Jämna nummer mellan " + start + " och " + end + ":");
        for (int num : evenNumbers) {
            System.out.println(num);
        }

        scanner.close();

    }
    
}
