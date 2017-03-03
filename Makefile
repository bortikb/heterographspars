#CFLAGS=-O0
CFLAGS=-O3
#CFLAGS=-g -O0
#CFLAGS += -std=c++11
CFLAGS += -std=c++0x
#-pedantic-errors -ansi -Wall -Wextra -Werror
LFLAGS=

OS := $(shell uname -s)
ifeq ($(OS),Linux)
LFLAGS += -lrt
endif

all: bin/test bin/hSpar

bin/test:  obj/test.o 
	g++ $^ $(LFLAGS) -o $@ $(CFLAGS)

bin/hSpar: obj/hSpar.o 
	g++ $^ $(LFLAGS) -o $@ $(CFLAGS)


obj/test.o: src/test.cpp
	g++ -c src/test.cpp -o $@ $(CFLAGS)

obj/hSpar.o: src/heteroSparsify.cpp
	g++ -c src/heteroSparsify.cpp -o $@ $(CFLAGS)


clean:
	rm -f bin/* obj/*.o
