package controller.views;

import ui.Window;

import controller.views.Controller;

import enums.WindowState;;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

/**
  * The class <code>CreditController</code> controls the credit view
  * @version 1.0
  * @author Dorian Terbah 
**/

public class CreditController extends Controller {

    /**
      * Constant used to describe the back button 
    **/
    private static final String BACK_ACTION_COMMAND = "BACK_ACTION_COMMAND";

    public CreditController(Window window, JButton backButton) {
        super(window);
        
        backButton.setActionCommand(BACK_ACTION_COMMAND);
        backButton.addActionListener(this);
    }

    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(BACK_ACTION_COMMAND)) {
            this.window.changeView(WindowState.HOME_VIEW_STATE);
        }
    }
}