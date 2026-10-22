#ifndef ACCOUNT_HPP
#define ACCOUNT_HPP

#include <string>
#include <iostream>
#include <fstream>
//struct for vector
struct Account
{
    std::string email;
    std::string hashPassword;

     Account() = default;

    // Constructor with email and password
    Account(const std::string& e, const std::string& p)
        : email(e), hashPassword(p) {}
    //put new info to fill
    void saveAccountToFile(const Account& account, const std::string& filename) {
    std::ofstream file(filename, std::ios::app); 
    if (file.is_open()) {
        file << account.email << ":" << account.hashPassword << "\n";
        file.close();
    } else {
        std::cerr << "Unable to open file!\n";
    }

    }
};

#endif 