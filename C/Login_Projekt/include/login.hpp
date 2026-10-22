#ifndef LOGIN_HPP
#define LOGIN_HPP

#include "account.hpp"
#include <string>
#include <vector>

bool loginCheck(const std::vector<Account>& users, const std::string& email, const std::string& password);

void login(const std::vector<Account>& users);

#endif