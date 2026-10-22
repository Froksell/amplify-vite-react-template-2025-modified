#include <iostream>

//clears screen and is better than system(cls)

void clear_screen(void) {
    std::cout << "\033[2J\033[H";
    std::cout.flush();
}