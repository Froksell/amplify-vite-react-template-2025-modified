#include "../include/account.hpp"
#include <iostream>
#include <vector>
#include <fstream>
//loads form database to vector
std::vector<Account> loadAccountsFromFile(const std::string& filename)
{
    std::vector<Account> accounts;
    std::ifstream file(filename);

    if(!file.is_open())
    {
        std::cout << "Failed to open" << std::endl;
        return accounts;
    }

    std::string email, password;
    while (file >> email >> password)
    {
       
        accounts.emplace_back(email, password);
    }

    return accounts;
    
}
//store email and hashed password from vector to database
void saveAccountsToFile(const std::vector<Account>& users, const std::string& filename)
{
    std::ofstream file(filename);
    if (!file.is_open()) {
        std::cout << "Could not open file for writing: " << filename << std::endl;
        return;
    }

    for (const auto& a : users)
    {
        file << a.email << " " << a.hashPassword << "\n";
    }
}
