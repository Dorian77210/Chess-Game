SRC_DIR := src/
BIN_DIR := bin/

#packages

#######ENGINE#######
ENGINE_DIR := $(SRC_DIR)engine/
ENGINE_ACTIONS_DIR := $(ENGINE_DIR)actions/
ENGINE_COUNTER_DIR = $(ENGINE_DIR)counter/
ENGINE_GAME_DIR = $(ENGINE_DIR)game/
ENGINE_INFORMATIONS_DIR := $(ENGINE_DIR)informations/
ENGINE_INITIALIZER_DIR = $(ENGINE_DIR)initializer/
ENGINE_RANGES_DIR := $(ENGINE_DIR)ranges/
ENGINE_RANGES_STATES_DIR := $(ENGINE_RANGES_DIR)states/

######ENUMS PART########
ENUMS_DIR := $(SRC_DIR)enums/


######CONTROLLER########
CONTROLLER_DIR := $(SRC_DIR)controller/
CONTROLLER_BOARD_DIR = $(CONTROLLER_DIR)board/
CONTROLLER_VIEWS_DIR := $(CONTROLLER_DIR)views/
CONTROLLER_WINDOW_DIR := $(CONTROLLER_DIR)window/

#######HELPER#######
HELPER_DIR := $(SRC_DIR)helper/
HELPER_CAST_DIR := $(HELPER_DIR)cast/
HELPER_COLLIDE_DIR := $(HELPER_DIR)collide/
HELPER_CONSTANTS_DIR := $(HELPER_DIR)constants/
HELPER_FILTERS_DIR := $(HELPER_DIR)filters/

######MODEL PART########
MODEL_DIR := $(SRC_DIR)models/
MODEL_GAME_DIR := $(MODEL_DIR)game/
MODEL_GAME_PIECES_DIR := $(MODEL_GAME_DIR)pieces/
MODEL_PLAYERS_DIR := $(MODEL_GAME_DIR)players/
MODEL_VIEWS_DIR := $(MODEL_DIR)views/
MODEL_VIEWS_SIDE_DIR := $(MODEL_VIEWS_DIR)side/

#######UI PART#######
UI_DIR := $(SRC_DIR)ui/
UI_BOARD_DIR := $(UI_DIR)board/
UI_SIDE_DIR := $(UI_DIR)side/
UI_VIEWS_DIR := $(UI_DIR)views/


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

#controller.board
$(BIN_DIR)BoardController.class: $(CONTROLLER_BOARD_DIR)BoardController.java $(BIN_DIR)BoardModel.class $(BIN_DIR)Cell.class \
								 $(BIN_DIR)Palette.class $(BIN_DIR)Engine.class $(BIN_DIR)BoardView.class \
								 $(BIN_DIR)Assert.class $(BIN_DIR)Piece.class
	$(JC) $(FLAGS) $(CONTROLLER_BOARD_DIR)BoardController.java

#controller.views
$(BIN_DIR)CreditController.class: $(CONTROLLER_VIEWS_DIR)CreditController.java $(BIN_DIR)Window.class $(BIN_DIR)WindowState.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)CreditController.java

$(BIN_DIR)Controller.class: $(CONTROLLER_VIEWS_DIR)Controller.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)Controller.java

$(BIN_DIR)HomeController: $(CONTROLLER_VIEWS_DIR)HomeController.java $(BIN_DIR)Window.class $(BIN_DIR)GameMode.class \
						  $(BIN_DIR)WindowState.class
	$(JC) $(FLAGS) $(CONTROLLER_VIEWS_DIR)HomeController.java

#controller.window
$(BIN_DIR)WindowSizeController.class: $(CONTROLLER_WINDOW_DIR)WindowSizeController.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(CONTROLLER_WINDOW_DIR)WindowSizeController.java

#engine
$(BIN_DIR)Engine.class: $(ENGINE_DIR)Engine.java $(BIN_DIR)Player.class $(BIN_DIR)Cell.class \
						$(BIN_DIR)BoardModel.class $(BIN_DIR)PieceInitializer.class $(BIN_DIR)BoardInitializer.class \
						$(BIN_DIR)GameMode.class $(BIN_DIR)PlayerType.class $(BIN_DIR)PieceType.class \
						$(BIN_DIR)GamePieces.class $(BIN_DIR)Piece.class $(BIN_DIR)BoardView.class \
						$(BIN_DIR)GameInformations.class $(BIN_DIR)Ranges.class $(BIN_DIR)BishopRange.class \
						$(BIN_DIR)BishopMovementStates.class
	$(JC) $(FLAGS) $(ENGINE_DIR)Engine.java

#engine.actions
$(BIN_DIR)Actions.class: $(ENGINE_ACTIONS_DIR)Actions.java $(BIN_DIR)Position.class $(BIN_DIR)Cell.class \
						 $(BIN_DIR)Cell.class $(BIN_DIR)BoardModel.class $(BIN_DIR)Engine.class \
						 $(BIN_DIR)PlayerType.class $(BIN_DIR)Player.class $(BIN_DIR)Assert.class
	$(JC) $(FLAGS) $(ENGINE_ACTIONS_DIR)Actions.java

#engine.counter
$(BIN_DIR)PieceCounter.class: $(ENGINE_COUNTER_DIR)PieceCounter.java $(BIN_DIR)Engine.class $(BIN_DIR)Player.class \
							  $(BIN_DIR)Piece.class $(BIN_DIR)Bishop.class $(BIN_DIR)Knight.class \
							  $(BIN_DIR)King.class $(BIN_DIR)Queen.class $(BIN_DIR)Pawn.class \
							  $(BIN_DIR)Rook.class $(BIN_DIR)KindOfPiece.class $(BIN_DIR)PlayerType.class
	$(JC) $(FLAGS) $(ENGINE_COUNTER_DIR)PieceCounter.java
#engine.game
$(BIN_DIR)GamePieces.class: $(ENGINE_GAME_DIR)GamePieces.java $(BIN_DIR)Piece.class $(BIN_DIR)PieceType.class 
	$(JC) $(FLAGS) $(ENGINE_GAME_DIR)GamePieces.java

#engine.informations
$(BIN_DIR)GameInformations.class: $(ENGINE_INFORMATIONS_DIR)GameInformations.java
	$(JC) $(FLAGS) $(ENGINE_INFORMATIONS_DIR)GameInformations.java

#engine.initializer
$(BIN_DIR)BoardInitializer.class: $(ENGINE_INITIALIZER_DIR)BoardInitializer.java $(BIN_DIR)Piece.class $(BIN_DIR)Palette.class \
								  $(BIN_DIR)Cell.class $(BIN_DIR)Position.class
	$(JC) $(FLAGS) $(ENGINE_INITIALIZER_DIR)BoardInitializer.java

$(BIN_DIR)PieceInitializer.class: $(ENGINE_INITIALIZER_DIR)PieceInitializer.java $(BIN_DIR)PieceType.class $(BIN_DIR)Piece.class \
								  $(BIN_DIR)Bishop.class $(BIN_DIR)Knight.class $(BIN_DIR)King.class $(BIN_DIR)Rook.class \
								  $(BIN_DIR)Queen.class $(BIN_DIR)Pawn.class $(BIN_DIR)GamePieces.class $(BIN_DIR)BoardView.class \
								  $(BIN_DIR)Console.class $(BIN_DIR)Position.class $(BIN_DIR)PieceConstants.class
	$(JC) $(FLAGS) $(ENGINE_INITIALIZER_DIR)PieceInitializer.java

#engine.ranges
$(BIN_DIR)BishopRange.class: $(ENGINE_RANGES_DIR)BishopRange.java $(BIN_DIR)Bishop.class $(BIN_DIR)Cell.class \
							 $(BIN_DIR)BoardModel.class $(BIN_DIR)Assert.class $(BIN_DIR)Position.class \
							 $(BIN_DIR)BishopMovementStates.class
	$(JC) $(FLAGS) $(ENGINE_RANGES_DIR)BishopRange.java

$(BIN_DIR)KingRange.class: $(ENGINE_RANGES_DIR)KingRange.java $(BIN_DIR)Position.class $(BIN_DIR)Assert.class \
						   $(BIN_DIR)BoardModel.class $(BIN_DIR)Cell.class $(BIN_DIR)Piece.class \
						   $(BIN_DIR)King.class $(BIN_DIR)Player.class $(BIN_DIR)PlayerType.class \
						   $(BIN_DIR)Engine.class $(BIN_DIR)PieceCollision.class $(BIN_DIR)FilterPiece.class
	$(JC) $(FLAGS) $(ENGINE_RANGES_DIR)KingRange.java
	

$(BIN_DIR)Ranges.class: $(ENGINE_RANGES_DIR)Ranges.java $(BIN_DIR)Piece.class $(BIN_DIR)Bishop.class \
	   					$(BIN_DIR)Knight.class $(BIN_DIR)King.class $(BIN_DIR)Queen.class \
						$(BIN_DIR)Rook.class $(BIN_DIR)Pawn.class $(BIN_DIR)BoardView.class \
						$(BIN_DIR)BishopRange.class $(BIN_DIR)BishopMovementStates.class $(BIN_DIR)RookMovementStates.class \
						$(BIN_DIR)RookRange.class $(BIN_DIR)KingRange.class $(BIN_DIR)ClassCast.class
	$(JC) $(FLAGS) $(ENGINE_RANGES_DIR)Ranges.java

$(BIN_DIR)RookRange.class: $(ENGINE_RANGES_DIR)RookRange.java $(BIN_DIR)Rook.class $(BIN_DIR)Cell.class \
						   $(BIN_DIR)BoardModel.class $(BIN_DIR)Assert.class $(BIN_DIR)Position.class \
						   $(BIN_DIR)RookMovementStates.class
	$(JC) $(FLAGS) $(ENGINE_RANGES_DIR)BishopRange.java


#engine.ranges.states
$(BIN_DIR)BishopMovementStates.class: $(ENGINE_RANGES_STATES_DIR)BishopMovementStates.java
	$(JC) $(FLAGS) $(ENGINE_RANGES_STATES_DIR)BishopMovementStates.java

$(BIN_DIR)QueenMovementStates.class: $(ENGINE_RANGES_STATES_DIR)QueenMovementStates.java
	$(JC) $(FLAGS) $(ENGINE_RANGES_STATES_DIR)QueenMovementStates.java

$(BIN_DIR)RookMovementStates.class: $(ENGINE_RANGES_STATES_DIR)RookMovementStates.java
	$(JC) $(FLAGS) $(ENGINE_RANGES_STATES_DIR)RookMovementStates.java

#enums
$(BIN_DIR)GameMode.class: $(ENUMS_DIR)GameMode.java
	$(JC) $(FLAGS) $(ENUMS_DIR)GameMode.java


$(BIN_DIR)KindOfPiece.class: $(ENUMS_DIR)KindOfPiece.java
	$(JC) $(FLAGS) $(ENUMS_DIR)KindOfPiece.java

$(BIN_DIR)PieceType.class: $(ENUMS_DIR)PieceType.java
	$(JC) $(FLAGS) $(ENUMS_DIR)PieceType.java

$(BIN_DIR)PlayerType.class: $(ENUMS_DIR)PlayerType.java
	$(JC) $(FLAGS) $(ENUMS_DIR)PlayerType.java

$(BIN_DIR)WindowState.class: $(ENUMS_DIR)WindowState.java 
	$(JC) $(FLAGS) $(ENUMS_DIR)WindowState.java

#helper
$(BIN_DIR)Assert.class: $(HELPER_DIR)Assert.java 
	$(JC) $(FLAGS) $(HELPER_DIR)Assert.java

$(BIN_DIR)Console.class: $(HELPER_DIR)Console.java
	$(JC) $(FLAGS) $(HELPER_DIR)Console.java

$(BIN_DIR)Distance.class: $(HELPER_DIR)Distance.java $(BIN_DIR)Position.class
	$(JC) $(FLAGS) $(HELPER_DIR)Distance.java

$(BIN_DIR)Position.class: $(HELPER_DIR)Position.java
	$(JC) $(FLAGS) $(HELPER_DIR)Position.java

#helper.cast
$(BIN_DIR)ClassCast.class: $(HELPER_CAST_DIR)ClassCast.java
	$(JC) $(FLAGS) $(HELPER_CAST_DIR)ClassCast.java

#helper.constants
$(BIN_DIR)Palette.class: $(HELPER_CONSTANTS_DIR)Palette.java
	$(JC) $(FLAGS) $(HELPER_CONSTANTS_DIR)Palette.java

$(BIN_DIR)PieceConstants.class: $(HELPER_CONSTANTS_DIR)PieceConstants.java
	$(JC) $(FLAGS) $(HELPER_CONSTANTS_DIR)PieceConstants.java

#helper.collide
$(BIN_DIR)PieceCollision.class: $(HELPER_COLLIDE_DIR)PieceCollision.java $(BIN_DIR)Piece.class $(BIN_DIR)BoardModel.class \
								$(BIN_DIR)Engine.class $(BIN_DIR)Cell.class $(BIN_DIR)Position.class \
								$(BIN_DIR)King.class $(BIN_DIR)KingRange.class
	$(JC) $(FLAGS) $(HELPER_COLLIDE_DIR)PieceCollision.java

#helper.filters
$(BIN_DIR)FilterPiece.class: $(HELPER_FILTERS_DIR)FilterPiece.java $(BIN_DIR)Cell.class $(BIN_DIR)BoardModel.class \
							 $(BIN_DIR)Position.class $(BIN_DIR)Piece.class $(BIN_DIR)Engine.class \
							 $(BIN_DIR)King.class $(BIN_DIR)KingRange.class
	$(JC) $(FLAGS) $(HELPER_FILTERS_DIR)FilterPiece.java

#models

#models.game.pieces
$(BIN_DIR)Bishop.class: $(MODEL_GAME_PIECES_DIR)King.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)King.java

$(BIN_DIR)King.class: $(MODEL_GAME_PIECES_DIR)King.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)King.java

$(BIN_DIR)Knight.class: $(MODEL_GAME_PIECES_DIR)Knight.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Knight.java

$(BIN_DIR)Pawn.class: $(MODEL_GAME_PIECES_DIR)Pawn.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Pawn.java

$(BIN_DIR)Piece.class: $(MODEL_GAME_PIECES_DIR)Piece.java $(BIN_DIR)Position.class $(BIN_DIR)PieceType.class \
					   $(BIN_DIR)Engine.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Piece.java

$(BIN_DIR)Queen.class: $(MODEL_GAME_PIECES_DIR)Queen.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Queen.java

$(BIN_DIR)Rook.class: $(MODEL_GAME_PIECES_DIR)Rook.java $(BIN_DIR)Piece.class $(BIN_DIR)Position.class \
						$(BIN_DIR)PieceType.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(MODEL_GAME_PIECES_DIR)Rook.java

#model.players
$(BIN_DIR)Player.class: $(MODEL_PLAYERS_DIR)Player.java $(BIN_DIR)Piece.class $(BIN_DIR)PlayerType.class \
						$(BIN_DIR)King.class
	$(JC) $(FLAGS) $(MODEL_PLAYERS_DIR)Player.java

#model.views
$(BIN_DIR)BoardModel.class: $(MODEL_VIEWS_DIR)BoardModel.java $(BIN_DIR)Cell.class $(BIN_DIR)Piece.class \
							$(BIN_DIR)Position.class $(BIN_DIR)GameMode.class $(BIN_DIR)BoardView.class \
							$(BIN_DIR)Assert.class $(BIN_DIR)Engine.class
	$(JC) $(FLAGS) $(MODEL_VIEWS_DIR)BoardModel.java

#model.views.side
$(BIN_DIR)PieceRepresentation.class: $(MODEL_VIEWS_SIDE_DIR)PieceRepresentation.java $(BIN_DIR)PlayerType.class $(BIN_DIR)KindOfPiece.class
	$(JC) $(FLAGS) $(MODEL_VIEWS_SIDE_DIR)PieceRepresentation.java

#ui 

#ui.board
$(BIN_DIR)BoardView.class: $(UI_BOARD_DIR)BoardView.java $(BIN_DIR)Cell.class $(BIN_DIR)Engine.class \
						$(BIN_DIR)View.class $(BIN_DIR)BoardController.class $(BIN_DIR)Palette.class
	$(JC) $(FLAGS) $(UI_BOARD_DIR)BoardView.java

$(BIN_DIR)Cell.class: $(UI_BOARD_DIR)Cell.java $(BIN_DIR)Position.class $(BIN_DIR)Assert.class \
					  $(BIN_DIR)Piece.class
	$(JC) $(FLAGS) $(UI_BOARD_DIR)Cell.java

#ui.side
$(BIN_DIR)ItemView.class: $(UI_SIDE_DIR)ItemView.java $(BIN_DIR)PlayerType.class $(BIN_DIR)KindOfPiece.class \
						  $(BIN_DIR)PieceRepresentation.class $(BIN_DIR)Engine.class*
	$(JC) $(FLAGS) $(UI_SIDE_DIR)ItemView.java

$(BIN_DIR)PieceCountView.class: $(UI_SIDE_DIR)PieceCountView.java $(BIN_DIR)ItemView.class $(BIN_DIR)Engine.class \
								$(BIN_DIR)KindOfPiece.class $(BIN_DIR)PlayerType.class $(BIN_DIR)Piece.class 
	$(JC) $(FLAGS) $(UI_SIDE_DIR)PieceCountView.java

#ui.views
$(BIN_DIR)CreditView.class: $(UI_VIEWS_DIR)CreditView.java $(BIN_DIR)View.class $(BIN_DIR)Window.class \
							$(BIN_DIR)CreditController.class
	$(JC) $(FLAGS) $(UI_VIEWS_DIR)CreditView.java

$(BIN_DIR)HomeView.class: $(UI_VIEWS_DIR)HomeView.java $(BIN_DIR)View.class $(BIN_DIR)Window.class \
						  $(BIN_DIR)HomeController.class
	$(JC) $(FLAGS) (UI_VIEWS_DIR)HomeView.java

$(BIN_DIR)View.class: $(UI_VIEWS_DIR)View.java $(BIN_DIR)Window.class $(BIN_DIR)Controller.class
	$(JC) $(FLAGS) $(UI_VIEWS_DIR)View.java

$(BIN_DIR)Window.class: $(UI_DIR)Window.java $(BIN_DIR)View.class $(BIN_DIR)WindowState.class \
						$(BIN_DIR)BoardView.class
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