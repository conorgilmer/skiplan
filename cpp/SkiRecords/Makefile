CC=g++
CFLAGS=-I.
DEPS = 
OBJ = SkiRecords.o 

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

SkiRecords: $(OBJ)
	g++ -o $@ $^ $(CFLAGS)

