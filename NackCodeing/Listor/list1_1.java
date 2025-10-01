import java.util.Scanner;
import java.util.ArrayList;

public class list1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            System.out.println("Enter number " + (i+ 1) +": ");
            int num = scanner.nextInt();
            numList.add(num);
        }

        System.out.println("\nNumber you put in are:");
        for (int num : numList) {
            System.out.println(num);
        }


        scanner.close();

    }
}
