import java.util.ArrayList;
import java.util.Scanner;

class Food {
    String name;
    double price;
    String typ;
    int calories;

    public Food(String name, double price, String typ, int calories) {
        this.name = name;
        this.price = price;
        this.typ = typ;
        this.calories = calories;
    }

    public void displayInfo() {

        System.out.println("Namn: " + name + " Pris: " + price + " Kr " + " Typ: " + typ + " Kalorier: " + calories + " kcal" );
    }
}

public class Lunch {

    public static void main(String[] args) {
        ArrayList<Food> menu = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n====Meny====");
            System.out.println("1. Lägg till Mat");
            System.out.println("2. Vissa Meny");
            System.out.println("3. Ta bort mat från menyn");
            System.out.println("4. Avsluta");
            System.out.print("Välj ett val: ");  

            int val = scanner.nextInt();
            scanner.nextLine();

            switch (val) {
                case 1:
                    System.out.print("Skriv namnet på maten: ");
                    String name = scanner.nextLine();

                    System.out.print("Lägg till pris: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Vad för typ är det: ");
                    String type = scanner.nextLine();

                    System.out.print("Hur mycket kalorier har den: ");
                    int calories = scanner.nextInt();
                    scanner.nextLine();

        
                    menu.add(new Food(name, price, type, calories));  

                    System.out.println("Maten har lagts till i menyn.");
                    break;

                case 2:
                    if (menu.isEmpty()) {
                        System.out.println("Ingen maträtt i menyn.");
                        break;
                    } else {
                        System.out.println("Meny:");
                        for (int i = 0; i < menu.size(); i++) {
                            System.out.print(i + ": ");
                            menu.get(i).displayInfo();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Skriv vilket nummer på maträtten du vill ta bort: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine();

                    if (indexToRemove >= 0 && indexToRemove < menu.size()) {
                        Food removed = menu.remove(indexToRemove);
            
                        System.out.println("Tog bort: " + removed.name);  
                    } else {
                        System.out.println("Det finns inget på den platsen.");
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Stänger menyn.");
                    break;

                default:
                    System.out.println("Inte rätt nummer. Försök igen.");
                    break;
            }
        }

        scanner.close();
    }
}
