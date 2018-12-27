package ui.views;

import ui.views.View;

import ui.Window;

import controller.views.CreditController;

import javax.swing.JButton;
/**
  * The class <code>CreditView</code> represents the view for the credit and the rules
  * @version 1.0
  * @author Dorian Terbah 
**/

public class CreditView extends View {

    /**
      * Button to return to home 
    **/
    private JButton backButton;

    public CreditView(Window window) {
        super(window);

        //create the back button
        this.backButton = new JButton("Back to home");

        //add the button
        this.add(this.backButton);

        this.controller = new CreditController(window, this.backButton);
    }
}