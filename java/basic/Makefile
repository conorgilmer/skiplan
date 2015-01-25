
JFLAGS = -g
JC = javac
JCR = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	skicalc.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

exec-tests: classes
	set -e; \
	do $(JCR) skicalc $$file; done;


.PHONY: default clean classes exec-tests
