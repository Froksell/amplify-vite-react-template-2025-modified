import java.util.ArrayList;

public class Klassar2 {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        //Lägger till variabel till listan för att sparas
        students.add(new Student("Jermaine", "Smith","22","Nackademin" ));
        students.add(new Student("Peter", "Parker","30","Jensen yrkeshögskola" ));
        students.add(new Student("Robin", "Hood","15","Hjulsta skolan" ));
        //Loopar igenom listan och lägger till index number till listan
        for (int i = 0; i < students.size(); i++) {
            System.out.print(i + ": " );
            students.get(i).displayInfo();
        }

        
    }
}
// Klass
class Student {
    // addar variabel till klassen
    String firstName;
    String lastName;
    String age;
    String school;
    
    
    public Student(String firstname, String lastName, String age, String school) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.age = age;
        this.school = school;


    }

    public void displayInfo() {
        System.out.println("Förnamn: " + firstName + " EfterNamn: " + lastName + " Ålder: " + age + " Skola: " + school );
    }
}


