#include <baskets.h>

Baskets::Baskets(/* args */)
{
}

Baskets::~Baskets()
{
}

void Baskets::getItem(ItemPurchase item){
    items_.push_back(item);
}

void Baskets::printBaskets()
{
    float total_sum = 0;
    for(auto &item: items_) {
        float total = item.product.getPrice() * item.amount;
        total += total;

        std::cout
        <<

    }
}