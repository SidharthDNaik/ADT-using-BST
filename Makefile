#--------------------------------------------------------------------------------------
# Name: Sidharth Naik
# ID: 1647945
# Class: 12B/M
# Date: March 11,2018
# Description: Compiles all the code
# File Name: Makefile
#---------------------------------------------------------------------------------------

JAVAC      = javac
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)
SUBMIT = submit cmps012b-pt.w19 lab7


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit: $(SOURCES)
		$(SUBMIT) $(SOURCES)

check:
		ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab7/sidnaik
