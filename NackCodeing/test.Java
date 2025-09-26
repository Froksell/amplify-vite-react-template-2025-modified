public class test {
    public static void main(String[] args) {
        int count = 0; 

        while (true) {
            System.out.println("Count is:" + count);
            count++;

            if (count >= 5)  {
                System.out.println("Breaking the loop...");
                break;
            }

            
        }

        System.out.println("Loop ended.");
    }
    
}
