CC=gcc
CFLAGS=-I.
DEPS = skicalc.h
OBJ = skicalc.o skicalcfn.o 

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

skicalc: $(OBJ)
	gcc -o $@ $^ $(CFLAGS)

