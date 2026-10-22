#ifndef HASH_HPP
#define HASH_HPP

#include <openssl/md5.h>
#include <string>

std::string md5Hash(const std::string& input);

#endif