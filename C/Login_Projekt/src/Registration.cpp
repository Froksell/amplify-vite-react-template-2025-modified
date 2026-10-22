
#include "../include/account.hpp"
#include "../include/clear.hpp"
#include "../include/Registration.hpp"
#include "../include/errorMessage.hpp"
#include "../include/storageLoad.hpp"
#include "../include/hash.hpp"

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

//looks if user exist
bool UserExist(const std::vector<Account>& users, const std::string& email)
{
    return std::any_of(users.begin(), users.end(),
[&](const Account& a){return a.email == email;});
}
//registrate usere 
void registration(std::vector<Account>& users, const std::string& filename)
{

    std::string email, password;
    clear_screen();
    std::cout << "Enter Email : " << std::endl;
    std::cin >> email;

    auto emailErrors = emailError(email);
    if (!emailErrors.empty()) {
        for (const auto& err : emailErrors)
            std::cout << err << std::endl;
        return; // stop registration
    }


    if (UserExist(users, email))
    {
        std::cout << "account already exist" << std::endl;
        return;
    }

    std::cout << "Enter Password: ";
    std::cin >> password;

     auto passErrors = passwordError(password);
    if (!passErrors.empty()) {
        for (const auto& err : passErrors)
            std::cout << err << std::endl;
        return; // stop registration
    }

     std::string hashedPassword = md5Hash(password);

    // Everything is valid → add account
    users.emplace_back(email, hashedPassword);
    saveAccountsToFile(users, filename);

    std::cout << "Account created successfully!\n";
}
   
    
