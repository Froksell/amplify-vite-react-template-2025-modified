#include "../include/hash.hpp"

#include <openssl/md5.h>
#include <string>
#include <sstream>
#include <iomanip>

//turns plain text to md5 hash
std::string md5Hash(const std::string& input) {
    unsigned char digest[MD5_DIGEST_LENGTH];
    MD5((unsigned char*)input.c_str(), input.size(), digest);

    std::stringstream ss;
    for (int i = 0; i < MD5_DIGEST_LENGTH; i++)
    ss << std::hex << std::setw(2) << std::setfill('0') << (int)digest[i];

    return ss.str();
}