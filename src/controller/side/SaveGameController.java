package controller.side;

import engine.Engine;

import ui.board.BoardView;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>SaveGameController</code> controls the save of the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class SaveGameController implements ActionListener {

    /**
      * Constant used to identofy the save button 
    **/
    private static final String SAVE_BUTTON_ACTION_COMMAND = "SAVE_BUTTON_ACTION_COMMAND";

    /**
      * The board view 
    **/
    private BoardView boardView;

    public SaveGameController(JButton saveButton, BoardView boardView) {
        saveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND);
        saveButton.addActionListener(this);

        this.boardView = boardView;
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(SAVE_BUTTON_ACTION_COMMAND)) {
            Engine.instance().saveGame();
            this.boardView.displayMessage("The game has been saved, exit...");
        }
    }
}