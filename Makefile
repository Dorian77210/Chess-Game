SRC_DIR := src/
BIN_DIR := bin/

#packages

#######UI PART#######
UI_DIR := $(SRC_DIR)ui/
UI_VIEWS_DIR := $(UI_DIR)views/

######MODEL PART########

######ENUMS PART########
ENUMS_DIR := $(SRC_DIR)enums/


######CONTROLLER########
CONTROLLER_DIR := $(SRC_DIR)controller/
CONTROLLER_VIEWS_DIR := $(CONTROLLER_DIR)views/


FLAGS := -d $(BIN_DIR) -sourcepath $(SRC_DIR) -classpath $(BIN_DIR)
JC := javac
JEXE := java -classpath $(BIN_DIR)
JDOC := javadoc
RM := rm -rf 
MKDIR := mkdir


all: $(BIN_DIR) $(JAVADOC_DIR) $(BIN_DIR)Application.class doc

#application
$(BIN_DIR)Application.class: $(SRC_DIR)Application.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(SRC_DIR)Application.java


#controllers
$(BIN_DIR)CreditController.class: $(CONTROLLER_VIEWS_DIR)CreditController.java $(BIN_DIR)Window.class $(BIN_DIR)WindowState.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)CreditController.java

$(BIN_DIR)Controller.class: $(CONTROLLER_VIEWS_DIR)Controller.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)Controller.java

$(BIN_DIR)HomeController: $(CONTROLLER_VIEWS_DIR)HomeController.java $(BIN_DIR)Window.class $(BIN_DIR)GameMode.class \
						  $(BIN_DIR)WindowState.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)HomeController.java

#enums
$(BIN_DIR)GameMode.class: $(ENUMS_DIR)GameMode.java
	$(JC) $(FLAGS) $(ENUMS_DIR)GameMode.java

$(BIN_DIR)WindowState.class: $(ENUMS_DIR)WindowState.java 
	$(JC) $(FLAGS) $(ENUMS_DIR)WindowState.java

#ui 
$(BIN_DIR)CreditView.class: $(UI_VIEWS_DIR)CreditView.java $(BIN_DIR)View.class $(BIN_DIR)Window.class \
							$(BIN_DIR)CreditController.class
	$(JC) $(FLAGS) $(UI_VIEWS_DIR)CreditView.java

$(BIN_DIR)HomeView.class: $(UI_VIEWS_DIR)HomeView.java $(BIN_DIR)View.class $(BIN_DIR)Window.class \
						  $(BIN_DIR)HomeController.class
	$(JC) $(FLAGS) (UI_VIEWS_DIR)HomeView.java

$(BIN_DIR)View.class: $(UI_VIEWS_DIR)View.java $(BIN_DIR)Window.class $(BIN_DIR)Controller.class
	$(JC) $(FLAGS) $(UI_VIEWS_DIR)View.java

$(BIN_DIR)Window.class: $(UI_DIR)Window.java $(BIN_DIR)View.class $(BIN_DIR)WindowState.class 
	$(JC) $(FLAGS) $(UI_DIR)Window.java


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