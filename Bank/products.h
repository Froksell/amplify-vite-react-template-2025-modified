#include <iostream>
#include <string>

#ifndef PRODUCTS_H
#define PRODUCTS_H

enum ProductTypeEnum
{
    PCS,
    KILO
};

class Product
{
private:
    int id_;
    float price_;
    std::string name_;
    ProductTypeEnum type_;

public:
    Product::Product(int id, float price, std::string name, ProductTypeEnum type);
    int getId() const { return id_; };
    float getPrice() const { return price_; };
    std::string getName() const { return name_; };
    ProductTypeEnum getType() const { return type_; };
    

    
};

#endif
