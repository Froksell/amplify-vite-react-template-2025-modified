import java.util.ArrayList;
import java.util.Scanner;

public class list2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numList = new ArrayList<>();

        for (int i = 0; i < 4; i++ ) {
            System.out.println("Enter the Number " + (i + 1) + ": ");
            int num = scanner.nextInt();
            numList.add(num);
        }

        int biggest = numList.get(0);

        for (int num : numList) {
            if (num > biggest) {
                biggest = num;
            }
        }

        
        System.out.println("The biggest number is: " + biggest);

        scanner.close(); // ✅ 
    }
}
