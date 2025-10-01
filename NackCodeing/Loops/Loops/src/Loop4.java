import java.util.Scanner;

public class Loop4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int val = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.print(" :Skriv in did tall " + i + ": ");
            int num = scanner.nextInt();
            val += num;
        }

        System.out.println("Value of what you put in: " + val);

        scanner.close();

        
    }
}