SRC_DIR := src/
BIN_DIR := bin/

#packages


FLAGS := -d $(BIN_DIR) -sourcepath $(SRC_DIR) -classpath $(BIN_DIR)
JC := javac
JEXE := java -classpath "$(BIN_DIR):$(JUNIT_JAR):$(HAMCREST_JAR)"
JDOC := javadoc
RM := rm -rf 
MKDIR := mkdir


all: $(BIN_DIR) $(JAVADOC_DIR) $(BIN_DIR)Application.class doc

#application
$(BIN_DIR)Application.class: $(SRC_DIR)Application.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(SRC_DIR)Application.java



#launch program
run:
	$(JEXE) Application

#clean bin dir
clean:
	$(RM) $(BIN_DIR)*
#clean javadoc
docclean:
	$(RM) $(JAVADOC_DIR)*

#dev utility
dev: clean $(BIN_DIR)Application.class

.PHONY: clean test do