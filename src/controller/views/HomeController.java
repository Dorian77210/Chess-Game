package controller.views;

import controller.views.Controller;

import enums.WindowState;
import enums.GameMode;

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

    public HomeController(Window window, JButton pvpButton, JButton pveButton, JButton creditButton) {
        super(window);

        //set the action command for all buttons
        pvpButton.setActionCommand(PVP_ACTION_COMMAND);
        pveButton.setActionCommand(PVE_ACTION_COMMAND);
        creditButton.setActionCommand(CREDIT_ACTION_COMMAND);

        //add the controller for all buttons
        pvpButton.addActionListener(this);
        pveButton.addActionListener(this);
        creditButton.addActionListener(this);
    }

    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(PVE_ACTION_COMMAND)) {
            this.window.changeView(WindowState.IN_GAME_STATE, GameMode.PVE_MODE);
        } else if(actionCommand.equals(PVP_ACTION_COMMAND)) {
            this.window.changeView(WindowState.IN_GAME_STATE, GameMode.PVP_MODE);
        } else if(actionCommand.equals(CREDIT_ACTION_COMMAND)) {
            this.window.changeView(WindowState.CREDIT_VIEW_STATE);
        }
    }
}