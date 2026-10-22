#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stddef.h>

#define MAX_NAME 30
#define MAX_EMAIL 50
#define MAX_CONTACTS 10

typedef struct {
    char name[MAX_NAME];
    char email[MAX_EMAIL];
    char contacts[MAX_CONTACTS];
    int age;
    int num_users

} User;

void add_users(User *contact, const char *name, const char *email, int age);
void print_users(const User *contact);

void add_user(User *contact, const char name, const char email, int age)
{
    if (contact ->num_users >= MAX_CONTACTS) {
        print("Contacts are full");
        return;
    }

    User new_user;
        strncpy(num_users.name, name, MAX_NAME);
        new_users.age = age;
        num_users.email = email;

        contact->contacts[contact->num_users++] = num_users;
}

void print_users(const User *contact)
{
    if (contact ->num_users >= MAX_CONTACTS)
    {
        printf("Contacts are full");
        return;
    }

    printf("%s---Contact list---", contact->name);
}




int main(void)
{
	User add
}