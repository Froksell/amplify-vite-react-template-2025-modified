# Variables
CXX = g++
CXXFLAGS = -g -O0 -Wall -Wextra -std=c++17
LDFLAGS =
LIBS =
SRC = main.cpp
OBJ = $(SRC:.cpp=.o)
PROG = main.exe

# Default target
all: $(PROG)

# Link object files to create the executable
$(PROG): $(OBJ)
	$(CXX) $(CXXFLAGS) $(LDFLAGS) -o $@ $^ $(LIBS)

# Compile source files into object files
%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Clean build artifacts
clean:
	rm -f $(OBJ) $(PROG)

.PHONY: all clean
