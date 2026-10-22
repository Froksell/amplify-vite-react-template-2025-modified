#include "../include/clear.hpp"
#include "../include/account.hpp"
#include "../include/storageLoad.hpp"
#include "../include/login.hpp"
#include "../include/hash.hpp"

#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

//checks if login worked
bool loginCheck(const std::vector<Account>& users, const std::string& email, const std::string& password)
{
    return std::any_of(users.begin(), users.end(),
    [&](const Account& a){return a.email == email && a.hashPassword == password;});
}
// login and turn the plain text to hash to match the database
void login(const std::vector<Account>& users)
{
    
    std::string email, password;
    clear_screen();
    std::cout << "Please enter Email and password :\n" << std::endl;
    std::cout << "Email\n" << std::endl;
    std::cin >> email;
    std::cout << "Password\n" << std::endl;
    std::cin >> password;
    std::string hashedInput = md5Hash(password);

        if (loginCheck(users, email, hashedInput))
        {
            clear_screen();
            std::cout << " Login successful\n" << std::endl;
        }

        else {
            std::cout << "wrong password or email try again" << std::endl;
            return;
        }
    }

