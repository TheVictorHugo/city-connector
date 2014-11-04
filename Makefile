#3137 homework 3 Makefile
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
	
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	City.java \
	CityGraph.java \
	GraphDraw.java \
	CityConnector.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *~

#end of file
