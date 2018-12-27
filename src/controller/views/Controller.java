package controller.views;

import ui.Window;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>Controller</code> represents base of the controller for the main views
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Controller implements ActionListener {

    /**
      * The view that is controled by this 
    **/
    protected Window window;

    public Controller(Window window) {
        this.window = window;
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {

    }
}