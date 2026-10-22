#include <iostream>
#include <string>
#include <products.h>




Product::Product(int id, float price, std::string name, ProductTypeEnum type)
: id_(id), price_(price), name_(name), type_(type){};

