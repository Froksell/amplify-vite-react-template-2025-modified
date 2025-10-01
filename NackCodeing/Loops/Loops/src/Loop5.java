import java.util.Scanner;

public class Loop5 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("What number do you want to countdown from");

        int count = scanner.nextInt();

        while (true) {
            System.out.println("Count is:" + count);
            count--;

            if (count < 0)  {
                System.out.println("Breaking the loop...");
                break;
            }

        }

        System.out.println("Loop ended.");

        scanner.close();
    }
    
}
