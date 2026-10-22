#ifndef REGISTRATION_HPP
#define REGISTRATION_HPP

#include "account.hpp"
#include <string>
#include <vector>

bool UserExist(const std::vector<Account>& users, const std::string& email);
void registration(std::vector<Account>& users, const std::string& filename);


#endif