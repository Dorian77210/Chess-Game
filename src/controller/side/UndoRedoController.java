package controller.side;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import engine.Engine;
import engine.informations.GameInformations;

import enums.PlayerType;

import helper.Assert;

import ui.board.BoardView;

import log.Log;

import undo.UndoRedo;
import undo.BoardSave;

/**
  * The class <code>UndoRedoController</code> controls the undo redo
  * @version 1.0
  * @author Dorian Terbah 
**/

public class UndoRedoController implements ActionListener {
    
    /**
      * Constant used to identify the undo button 
    **/
    private static final String UNDO_ACTION_COMMAND = "UNDO_ACTION_COMMAND";

    /**
      * Constant used to identify the redo button 
    **/
    private static final String REDO_ACTION_COMMAND = "REDO_ACTION_COMMAND";

    /**
      * The view of the board to refresh it 
    **/
    private BoardView boardView;

    public UndoRedoController(BoardView boardView, JButton undo, JButton redo) {
        this.boardView = boardView;

        //add listeners for the buttons
        undo.setActionCommand(UNDO_ACTION_COMMAND);
        undo.addActionListener(this);

        redo.setActionCommand(REDO_ACTION_COMMAND);
        redo.addActionListener(this);
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        BoardSave save = new BoardSave(Engine.instance().getAllPieces().clone(), new GameInformations(Engine.instance().informations));
 
        BoardSave result;

        if(actionCommand.equals(UNDO_ACTION_COMMAND)) {
            result = UndoRedo.instance().undo(save);
        } else {
            result = UndoRedo.instance().redo(save);
        }

        //update pieces for the players
        if(Assert.isSet(result)) {
            Engine.instance().getPlayer(PlayerType.BLACK_PLAYER).setPieces(result.getPiecesAccordingToColor(false));
            Engine.instance().getPlayer(PlayerType.WHITE_PLAYER).setPieces(result.getPiecesAccordingToColor(true));
            Engine.instance().informations = result.getGameInformations();
            
            //update log
            if(actionCommand.equals(UNDO_ACTION_COMMAND)) {
                Log.instance().undo();
            } else {
                Log.instance().redo();
            }

            //refresh the board
            this.boardView.changeVersion(result.getAllPieces());
            this.boardView.refreshBoard();
            this.boardView.refreshDisplayLog();
            this.boardView.refreshCounts();
        }
    }
}