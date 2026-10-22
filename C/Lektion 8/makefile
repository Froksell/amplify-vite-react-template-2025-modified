# Variables
CC = gcc
CFLAGS = -g -O0 -Wall -Wextra -std=c11
LDFLAGS =
LIBS =
SRC = main.c
OBJ = $(SRC:.c=.o)
PROG = main.exe

# Default target
all: $(PROG)

# Link object files to create the executable
$(PROG): $(OBJ)
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $^ $(LIBS)

# Compile source files into object files
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Clean build artifacts
clean:
	rm -f $(OBJ) $(PROG)

.PHONY: all clean
