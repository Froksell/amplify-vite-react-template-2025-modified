public class Loop1 {
    public static void main(String[] args) {
        // setting up int variable
        int count = 0;

        while (true) {
            // adding value to int variable to come to the set number
            System.out.println("Count is: " + count);
            count++;

            // when the count reaches 10 the loop will break
            if (count >= 11) {
                System.out.println("Breaking the loop...");
                break;
            }
        }

        System.out.println("Loop ended.");
    }
}
