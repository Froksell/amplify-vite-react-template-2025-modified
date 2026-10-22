#ifndef STORAGELOAD_HPP
#define STORAGELOAD_HPP
#include "account.hpp"
#include <vector>
#include <string>

std::vector<Account>loadAccountsFromFile(const std::string& filename);
void saveAccountsToFile(const std::vector<Account>& users, const std::string& filename);

#endif
