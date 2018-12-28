SRC_DIR := src/
BIN_DIR := bin/

#packages

#######UI PART#######
UI_DIR := $(SRC_DIR)ui/
UI_BOARD_DIR := $(UI_DIR)board/
UI_VIEWS_DIR := $(UI_DIR)views/

######MODEL PART########
MODEL_DIR := $(SRC_DIR)models/
MODEL_GAME_DIR := $(MODEL_DIR)game/
MODEL_GAME_PIECES_DIR := $(MODEL_GAME_DIR)/pieces
MODEL_VIEWS_DIR := $(MODEL_DIR)views/

######ENUMS PART########
ENUMS_DIR := $(SRC_DIR)enums/


######CONTROLLER########
CONTROLLER_DIR := $(SRC_DIR)controller/
CONTROLLER_VIEWS_DIR := $(CONTROLLER_DIR)views/

#######HELPER#######
HELPER_DIR := $(SRC_DIR)helper/
HELPER_CONSTANTS_DIR := $(HELPER_DIR)constants/


FLAGS := -d $(BIN_DIR) -sourcepath $(SRC_DIR) -classpath $(BIN_DIR)
JC := javac
JEXE := java -classpath $(BIN_DIR)
JDOC := javadoc
RM := rm -rf 
MKDIR := mkdir


all: $(BIN_DIR) $(JAVADOC_DIR) $(BIN_DIR)Application.class doc

#application
$(BIN_DIR)Application.class: $(SRC_DIR)Application.java $(BIN_DIR)Window.class $(BIN_DIR)Engine.class
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

$(BIN_DIR)PieceType.class: $(ENUMS_DIR)PieceType.java
	$(JC) $(FLAGS) $(ENUMS_DIR)PieceType.java

$(BIN_DIR)PlayerType.class: $(ENUMS_DIR)PlayerType.java
	$(JC) $(FLAGS) $(ENUMS_DIR)PlayerType.java

$(BIN_DIR)WindowState.class: $(ENUMS_DIR)WindowState.java 
	$(JC) $(FLAGS) $(ENUMS_DIR)WindowState.java

#helper
$(BIN_DIR)Assert.class: $(HELPER_DIR)Assert.java 
	$(JC) $(FLAGS) $(HELPER_DIR)Assert.java

$(BIN_DIR)Position.class: $(HELPER_DIR)Position.java
	$(JC) $(FLAGS) $(HELPER_DIR)Position.java

#helper.constants
$(BIN_DIR)Palette.class: $(HELPER_DIR)Palette.java
	$(JC) $(FLAGS) $(HELPER_DIR)Palette.class

$(BIN_DIR)PieceConstants.class: $(HELPER_CONSTANTS_DIR)PieceConstants.java
	$(JC) $(FLAGS) $(HELPER_CONSTANTS_DIR)PieceConstants.java

#models

#models.game.pieces
$(BIN_DIR)Bishop.class: $(MODEL_GAME_PIECES_DIR)Bishop.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Bishop.java

$(BIN_DIR)Bishop.class: $(MODEL_GAME_PIECES_DIR)King.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)King.java

$(BIN_DIR)Knight.class: $(MODEL_GAME_PIECES_DIR)Knight.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Knight.java

$(BIN_DIR)Pawn.class: $(MODEL_GAME_PIECES_DIR)Pawn.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Pawn.java

$(BIN_DIR)Piece.class: $(MODEL_GAME_PIECES_DIR)Piece.java $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Piece.java

$(BIN_DIR)Queen.class: $(MODEL_GAME_PIECES_DIR)Queen.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Queen.java

$(BIN_DIR)Rook.class: $(MODEL_GAME_PIECES_DIR)Rook.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Rook.java

#model.views
$(BIN_DIR)BoardModel.class: $(MODEL_VIEWS_DIR)BoardModel.java $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)BoardModel.java

#ui 

#ui.board
$(BIN_DIR)BoardView.class: $(UI_BOARD_DIR)BoardView.java $(BIN_DIR)Cell.class $(BIN_DIR)Engine.class \
						   $(BIN_DIR)BoardController.class 
	$(JC) $(FLAGS) $(UI_BOARD_DIR)BoardView.java

$(BIN_DIR)Cell.class: $(UI_BOARD_DIR)Cell.class $(BIN_DIR)Position.class $(BIN_DIR)Assert.class \
					  $(BIN_DIR)Piece.class
	$(JC) $(FLAGS) $(UI_BOARD_DIR)Cell.java

#ui.views
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