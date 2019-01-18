package controller.views;

import controller.views.Controller;

import enums.WindowState;
import enums.GameMode;

import helper.Assert;

import ui.Window;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
/**
* The class <code>HomeController</code> controls the home
* @version 1.0
* @author Dorian Terbah 
**/

public class HomeController extends Controller {

    /**
      * Constant used to describe the PVE button 
    **/
    private static final String PVE_ACTION_COMMAND = "PVE_ACTION_COMMAND";

    /**
      * Constant used to describe the PVP button 
    **/
    private static final String PVP_ACTION_COMMAND = "PVP_ACTION_COMMAND";

    /**
      * Constant used to describe the Credit button 
    **/
    private static final String CREDIT_ACTION_COMMAND = "CREDIT_ACTION_COMMAND";

    /**
      *  Constant used to identify the load button
    **/
    private static final String LOAD_GAME_ACTION_COMMAND = "LOAD_GAME_ACTION_COMMAND";

    public HomeController(Window window, JButton pvpButton, JButton pveButton, JButton creditButton, JButton loadGameButton) {
        super(window);

        //set the action command for all buttons
        pvpButton.setActionCommand(PVP_ACTION_COMMAND);
        pveButton.setActionCommand(PVE_ACTION_COMMAND);
        creditButton.setActionCommand(CREDIT_ACTION_COMMAND);

        //add the controller for all buttons
        pvpButton.addActionListener(this);
        pveButton.addActionListener(this);
        creditButton.addActionListener(this);

        //load button, exception because it can be null if it not exists save of the game
        if(Assert.isSet(loadGameButton)) {
            loadGameButton.setActionCommand(LOAD_GAME_ACTION_COMMAND);
            loadGameButton.addActionListener(this);
        }
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(PVE_ACTION_COMMAND)) {
            this.window.changeView(WindowState.IN_GAME_STATE, GameMode.PVE_MODE);
        } else if(actionCommand.equals(PVP_ACTION_COMMAND)) {
            this.window.changeView(WindowState.IN_GAME_STATE, GameMode.PVP_MODE);
        } else if(actionCommand.equals(CREDIT_ACTION_COMMAND)) {
            this.window.changeView(WindowState.CREDIT_VIEW_STATE);
        } else if(actionCommand.equals(LOAD_GAME_ACTION_COMMAND)) {
            this.window.changeView(WindowState.IN_GAME_STATE, GameMode.LOAD_GAME);
        }
    }
}