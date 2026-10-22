#include "../include/errorMessage.hpp"
#include <string>
#include <regex>
#include <vector>
//will give a specific error message for what you do worng
std::vector<std::string>emailError(const std::string& email)
{
    std::vector<std::string> errors;

    if (email.find('@') == std::string::npos)
        errors.emplace_back("Email must contain '@'.");
    if (email.find('@') != std::string::npos && email.find('@') == 0)
        errors.emplace_back("Email must have characters before '@'.");
    if (!std::regex_search(email, std::regex("@.+\\.")))
        errors.emplace_back("Domain must contain a dot '.' after '@'.");
    if (!std::regex_search(email, std::regex("\\.[A-Za-z]{2,}$")))
        errors.emplace_back("Top-level domain must be at least 2 characters.");
        return errors;

}
//error message if not meet the security standard
std::vector<std::string>passwordError(const std::string& password)
{
    std::vector<std::string> errors;

    if (!std::regex_search(password, std::regex("[a-z]")))
        errors.emplace_back("Password must have a lowercase");
    if (!std::regex_search(password, std::regex("[A-Z]")))
        errors.emplace_back("Password must have an uppercase");
    if (!std::regex_search(password, std::regex("\\d")))
        errors.emplace_back("Password must have a digit");
    if (!std::regex_search(password, std::regex("[@$!%*?&]")))
        errors.emplace_back("Password must have a special character");
    if (password.length() < 8)
        errors.emplace_back("Password must have at least 8 characters");

    return errors;
}
