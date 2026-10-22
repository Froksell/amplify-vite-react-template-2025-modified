#include "../include/account.hpp"
#include "../include/errorMessage.hpp"
#include "../include/storageLoad.hpp"
#include "../include/Registration.hpp"
#include "../include/clear.hpp"
#include "../include/login.hpp"

#include <string>
#include <iostream>
#include <fstream>
#include <vector>




int main()
{   
    std::string filename = "users.txt";
    std::vector<Account> users = loadAccountsFromFile(filename);
    int c;
    while (true)
    {
    std::cout << "=====LogIn Meny=====\n " << std::endl;
    std::cout << "1. login\n" << std::endl;
    std::cout << "2. creat account\n" << std::endl;
    std::cout << "3. password crack\n" << std::endl;
    std::cout << "4. exit\n" << std::endl;
    std::cout << "====================" << std::endl;
    std::cin >> c;  
    std::cout << std::endl;
    std::cout <<"choose option\n"<< std::endl;

    switch (c)
    {
    case 1:
        login(users);
        break;
    case 2:
        registration(users, filename);

       
        break;
    
    case 3:
        
        break;
    case 4:
        std::cout << "Exit app....\n" << std::endl;
        return 0;

        break;
    default:
        clear_screen();
        std::cout << "please select on of the options" << std::endl;
        break;
        
    }

    }
}
