import java.util.ArrayList;

public class Klassar1 {

    public static void main(String[] args) {

        ArrayList<Dinner> dinners = new ArrayList<>();


        dinners.add(new Dinner("Pizza", "120kr","Kött","3,200 kcal" ));
        dinners.add(new Dinner("Salad", "80krkr","Vegetarisk","400 kcal" ));
        dinners.add(new Dinner("Pizza", "100kr","Kött","1,200 kcal" ));

        for (Dinner dinner : dinners) {
            dinner.displayInfo();

        }
        
    }
}

class Dinner {

    String name;
    String price;
    String type;
    String calories;

    public Dinner(String name, String price, String type, String calories) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.calories = calories;

    }

    public void displayInfo() {
        System.out.println("Name: " + name + " Price: " + price + " Type" + type + " Calories: " + calories );
    }
}

