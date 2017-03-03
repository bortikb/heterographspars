include Makefile.in

INCLUDES = -I. 
CFLAGS = $(COPTIONS) $(OPTFLAGS) $(INCLUDES)
LIBS = -lm 

LD = $(CC) $(LDOPTIONS) 

default: sparsify

sparsify: *.c *.h
	$(CC) $(CFLAGS) -c *.c
	$(LD) $(LIBS) *.o -o $@
	chmod 755 $@

clean:
	rm -f *.o sparsify

