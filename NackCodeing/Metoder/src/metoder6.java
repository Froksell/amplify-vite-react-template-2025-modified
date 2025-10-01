import java.util.ArrayList;
import java.util.Scanner;

public class metoder6 {
    private ArrayList<Integer> numbers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    // Metod för att spara till listan
    public void addNumber(int number) {
        numbers.add(number);
        System.out.println(number + " Number have been added.");
    }
    // uppdatera lista intex med att byta ut number från index platsen
    public void updateNumber(int index, int newNumber) {
        if (index >= 0 && index < numbers.size()) {
            int oldNumber = numbers.set(index, newNumber);
            System.out.println("Number  place" + index + "has changed "+ oldNumber + " has been updated to " + newNumber);
        } else {
            System.out.print("Error not a number");
        }
    } 
    // Tar bort index number på index platsen
    public void removeNumber(int index) {
        if (index >= 0 && index < numbers.size()) {
            int remove = numbers.remove(index);
            System.out.println("Number " + remove + " has been removed from index " + index );
        } else {
            System.out.println("Not a Number");
        }
    }  // <-- avslutar removeNumber här!
    // Visa alla tall i lista med deras index number
    public void showAllNumbers() {
        if (numbers.isEmpty()) {
            System.out.println("list is empty");
        } else {
            System.out.println("Number in list are");
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println(i + ": " + numbers.get(i));
            }
        }
    }  
    // While true loop för att loppa tillbacka tills man vill breka loopen
    public void run() {
        while (true) {
            System.out.println("\nVälje en av valen");
            System.out.println("1. Lägg till nummer");
            System.out.println("2. Uppdatera nummer");
            System.out.println("3. Ta bort nummer");
            System.out.println("4. Visa alla nummer");
            System.out.println("5. Avsluta programmet");
            // String input för att välja villken case man ska ta
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Ange nummer att lägga till: ");
                    try {
                        int numToAdd = Integer.parseInt(scanner.nextLine());
                        addNumber(numToAdd);
                    } catch (NumberFormatException e) {
                        System.out.println("Ogiltigt nummer!");
                    }
                    break;

                case "2":
                    System.out.print("Ange index för numret att uppdatera: ");
                    try {
                        int indexToUpdate = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ange nytt nummer: ");
                        int newNum = Integer.parseInt(scanner.nextLine());
                        updateNumber(indexToUpdate, newNum);
                    } catch (NumberFormatException e) {
                        System.out.println("Ogiltigt nummer eller index!");
                    }
                    break;

                case "3":
                    System.out.print("Ange index för numret att ta bort: ");
                    try {
                        int indexToRemove = Integer.parseInt(scanner.nextLine());
                        removeNumber(indexToRemove);
                    } catch (NumberFormatException e) {
                        System.out.println("Ogiltigt index!");
                    }
                    break;

                case "4":
                    showAllNumbers();
                    break;

                case "5":
                    System.out.println("Avslutar programmet.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    public static void main(String[] args) {
        metoder6 manager = new metoder6(); 
        manager.run();
    }
}
