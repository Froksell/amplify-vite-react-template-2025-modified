#include <iostream>
#include <string>
#include <vector>

struct Player {
    std::string name;
    int age;
    int jerseyNumber;
    int goals;
    int assists;
    double heightCm; // t.ex. 185.5
    int sum = 0;
};

int main() {
    int avrage_age = 0;
    double totalsumama = 0;
    double goal_avrage = 0;
    // En array (vector) med 10 spelare att börja med
    std::vector<Player> team = {
        {"Elias Pettersson", 25, 40, 34, 55, 188.0},
        {"Rasmus Dahlin", 23, 26, 20, 39, 190.0},
        {"Mika Zibanejad", 30, 93, 26, 46, 188.0},
        {"Erik Karlsson", 33, 65, 11, 45, 182.0},
        {"William Nylander", 27, 88, 40, 58, 183.0},
        {"Victor Hedman", 33, 18, 13, 63, 198.0},
        {"Filip Forsberg", 29, 11, 48, 46, 185.0},
        {"Lucas Raymond", 21, 23, 31, 41, 182.0},
        {"Adrian Kempe", 27, 11, 28, 47, 188.0},
        {"Leo Carlsson", 19, 81, 12, 17, 191.0}
    };
    
    // Din kod här...
    for (Player player : team) {
        
        totalsumama = totalsumama + player.goals + player.assists;

        avrage_age = avrage_age + player.age / 10;
        goal_avrage = (double)player.goals / 82;

        std::cout << player.heightCm / 100 << " Meter\n" << std::endl;

        std::cout << player.name << " " <<  goal_avrage <<" avrage goal per match\n" << std::endl;
    }

    std::cout << totalsumama << " total points\n" << std::endl;
    std::cout << avrage_age << " avrage age\n" << std::endl;
    


    return 0;
}