#include <iostream>
#include <vector>
#include <sstream>
#include <products.h>
#include <baskets.h>

void newCustomer(const std::vector<Product> &products){
    Baskets basket;
    std::string userInput;
    std::cout << "enter PAY to pay";
    while (true)
{
    std::cout << "products id"
    
}

};



void runProgram(const std::vector<Product> &products) {
    std::string choice;
    while (true)
    {
        std::cout << "1. new custemer\n2. quit\n";
        std::getline(std::cin, choice);

        if (choice == "1")
        {
            newCustomer(products)
        }

        if (choice == "2")
        {
            break;
        }

        else 
        {
            std::cout << "invalid input\n";
        }
    }
    
}

int main(void) {
    std::vector
    Product p1(100, 19.9, "candy", KILO);
    Product p1(100, 19.9, "candy", KILO);
    Product p1(100, 19.9, "candy", KILO);
    Product p1(100, 19.9, "candy", KILO);
    Product p1(100, 19.9, "candy", KILO);
    Product p1(100, 19.9, "candy", KILO);
    products.push_back(p1);
    products.push_back(p1);
    products.push_back(p1);
    products.push_back(p1);
    products.push_back(p1);
    products.push_back(p1);
    products.push_back(p1);
}