# Simple C++ Login/Registration System

A basic console-based user authentication system written in C++. It supports user registration with email/password validation, secure password hashing (MD5), login, and persistent storage in a text file.

## Features

- **Registration**:
  - Email validation (must contain `@`, domain with `.`, valid TLD, etc.)
  - Password validation:
    - At least 8 characters
    - At least one lowercase letter
    - At least one uppercase letter
    - At least one digit
    - At least one special character (`@$!%*?&`)
  - Checks for duplicate emails
  - Passwords are hashed with MD5 before storage

- **Login**:
  - Enter email and password
  - Password is hashed and compared to stored hash

- **Storage**:
  - Users stored in `users.txt` (format: `email hashed_password`)

- **Menu**:
  1. Login
  2. Create account
  3. Password crack (currently unimplemented)
  4. Exit

- Console clearing between actions for cleaner UI

## Project Structure

├── include/              # Header files
│   ├── account.hpp
│   ├── clear.hpp
│   ├── errorMessage.hpp
│   ├── hash.hpp
│   ├── login.hpp
│   ├── Registration.hpp
│   └── storageLoad.hpp
├── src/                  # Source files (assumed based on makefile)
│   ├── main.cpp
│   ├── Registration.cpp
│   ├── login.cpp
│   ├── storageLoad.cpp
│   ├── hash.cpp
│   ├── errorMessage.cpp
│   ├── clear.cpp
│   └── account.cpp
├── users.txt             # User database (plain text)
├── makefile              # Build script
└── README.md             # This file


## Requirements

- C++17 compatible compiler (g++)
- OpenSSL development libraries (`libssl-dev` on Debian/Ubuntu)

## Build Instructions

1. Install OpenSSL dev package (if not already installed):
   ```bash
   sudo apt install libssl-dev   
   # or equivalent for your distro



Important: The provided makefile needs a small fix to link OpenSSL:

Edit makefile and change the linking line to:makefile$(TARGET): $(OBJS)
    $(CXX) $(CXXFLAGS) -o $(TARGET) $(OBJS) -lcrypto


Usage Example

=====LogIn Meny=====

1. login
2. creat account
3. exit
====================
choose option
2
Enter Email: test@example.com
Enter Password: StrongPass123!

Account created successfully!